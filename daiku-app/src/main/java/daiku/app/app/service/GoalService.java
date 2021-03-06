package daiku.app.app.service;

import daiku.app.app.service.input.goal.*;
import daiku.app.app.service.output.goal.*;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.enums.PublishLevel;
import daiku.domain.infra.enums.UpdatingFlg;
import daiku.domain.infra.model.param.GoalArchiveDaoParam;
import daiku.domain.infra.model.param.GoalDaoParam;
import daiku.domain.infra.model.param.ProcessDaoParam;
import daiku.domain.infra.model.res.GoalSearchModel;
import daiku.domain.infra.model.res.ProcessSearchModel;
import daiku.domain.infra.repository.GoalArchiveRepository;
import daiku.domain.infra.repository.GoalRepository;
import daiku.domain.infra.repository.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class GoalService {
    @Autowired
    GoalRepository goalRepository;

    @Autowired
    GoalArchiveRepository goalArchiveRepository;

    @Autowired
    ProcessRepository processRepository;

    public GoalSearchServiceOutput search(GoalSearchServiceInput input) {
        var goalList = goalRepository.search(input.toRepository());
        return GoalSearchServiceOutput.builder()
                .goalList(goalList).build();
    }

    public GoalArchiveSearchServiceOutput archiveSearch(GoalArchiveSearchServiceInput input) {
        var goalList = goalArchiveRepository.archiveSearch(input.toRepository());
        return GoalArchiveSearchServiceOutput.builder()
                .goalList(goalList).build();
    }

    public GoalArchiveDetailServiceOutput archiveDetail(GoalArchiveDetailServiceInput input) throws GoenNotFoundException {
        var goalArchive = goalArchiveRepository.archiveOptional(input.toArchiveRepository())
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Archive.id: ", input.getArchiveId().toString());
                            return new GoenNotFoundException("goal detail info no found", param);
                        });

        var goalInfo = new GoalSearchModel();
        List<ProcessSearchModel> processInfo = null;

        goalInfo = goalRepository.detail(GoalDaoParam.builder()
                .goalId(goalArchive.getGoalId())
                .createDate(goalArchive.getGoalCreateDate()).build())
                .orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("goal.id: ", input.getArchiveId().toString());
                    return new GoenNotFoundException("goal detail info no found", param);
                });

        if(input.getAccountId().equals(goalInfo.getAccountId()) ||
            !input.getAccountId().equals(goalInfo.getAccountId()) && (goalArchive.getPublish().equals(PublishLevel.ALL))) {
            processInfo = processRepository.search(ProcessDaoParam.builder()
                    .createDate(goalInfo.getCreateDate())
                    .goalId(goalInfo.getId()).build());
        }
        return GoalArchiveDetailServiceOutput.builder()
                .goalArchiveInfo(goalArchive)
                .goalInfo(goalInfo)
                .processInfo(processInfo).build();
    }

    public GoalArchiveUpdateDispServiceOutput archiveUpdateDisp(GoalArchiveUpdateDispServiceInput input) throws GoenNotFoundException {
        return GoalArchiveUpdateDispServiceOutput.builder()
                .goalArchiveInfo(
                        goalArchiveRepository.selectById(input.toArchiveRepository())
                                .orElseThrow(
                                        () -> {
                                            Map<String, String> param = new LinkedHashMap<>();
                                            param.put("archive.id: ", input.getArchiveId().toString());
                                            return new GoenNotFoundException("archive info no found", param);

                                        }
                                )
                )
                .build();
    }

    public void create(GoalCreateServiceInput input) {
        goalRepository.save(input.toEntity());
    }

    public void update(GoalUpdateServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(GoalDaoParam.builder()
                .goalId(input.getGoalId())
                .createDate(input.getCreateDate())
                .build()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Goal.id: ", input.getGoalId().toString());
                            return new GoenNotFoundException("goal detail info no found", param);
                        }
                );
        goalRepository.save(input.toEntity());
    }

    public GoalDetailServiceOutput detail(GoalDetailServiceInput input) {

        return GoalDetailServiceOutput.builder()
                .goal(goalRepository.detail(input.toParam()))
                .build();
    }

    public void archive(GoalArchiveServiceInput input) throws GoenNotFoundException, GoenIntegrityException {
        var goal = goalRepository.detail(
                        GoalDaoParam.builder()
                                .goalId(input.getGoalId())
                                .createDate(input.getCreateDate())
                                .accountId(input.getAccountId()).build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Goal.id", input.getGoalId().toString());
                    return new GoenNotFoundException("goal info not found exception", param, "");
                });

        goalArchiveRepository.selectByGoalId(goal.getId()).ifPresentOrElse(
                goalArchive -> {
                    goalArchive.setUpdatingFlg(UpdatingFlg.ARCHIVING);
                    goalArchiveRepository.save(goalArchive);
                },
                () -> goalArchiveRepository.save(input.toEntity()));

    }

    public void archiveUpdate(GoalArchiveUpdateServiceInput input) throws GoenNotFoundException {
        var archive = goalArchiveRepository.selectById(input.toArchiveRepositoryParam())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("archive.id", input.getArchiveId().toString());
                    return new GoenNotFoundException("archive info not found exception", param, "");
                });

        goalArchiveRepository.save(input.toUpdateEntity(archive));

    }

    public GoalSearchModel archiveUpdatingFlg(GoalArchiveUpdatingFlgServiceInput input) throws GoenNotFoundException {
        var archive = goalArchiveRepository.selectByGoalId(input.getGoalId())
                .filter(data -> data.getUpdatingFlg().equals(UpdatingFlg.ARCHIVING))
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("goal.id", input.getGoalId().toString());
                    return new GoenNotFoundException("archive info not found exception", param, "");
                });
        goalArchiveRepository.save(input.toEntity(archive));

        return goalRepository.detail(GoalDaoParam.builder()
                .goalId(archive.getGoalId())
                .accountId(input.getAccountId())
                .createDate(input.getGoalCreateDate()).build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("archive.id", archive.getId().toString());
                    return new GoenNotFoundException("goal not found exception", param, "");
                });

    }
}
