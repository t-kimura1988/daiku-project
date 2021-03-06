package daiku.app.app.service;

import daiku.app.app.service.input.process.*;
import daiku.app.app.service.output.process.ProcessDetailServiceOutput;
import daiku.app.app.service.output.process.ProcessSearchServiceOutput;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.entity.TProcesses;
import daiku.domain.infra.model.param.GoalDaoParam;
import daiku.domain.infra.model.param.ProcessDaoParam;
import daiku.domain.infra.model.param.ProcessHistoryDaoParam;
import daiku.domain.infra.repository.GoalRepository;
import daiku.domain.infra.repository.ProcessHistoryRepository;
import daiku.domain.infra.repository.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProcessService {

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    ProcessHistoryRepository processHistoryRepository;

    public void create(ProcessCreateServiceInput input) throws GoenNotFoundException {
        var goal = goalRepository.detail(
                GoalDaoParam.builder()
                        .goalId(input.getGoalId())
                        .accountId(input.getAccountId()).build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Goal.id", input.getGoalId().toString());
                    return new GoenNotFoundException("goal info not found exception", param);
                });

        TProcesses processes = input.toProcessEntity(goal.getCreateDate());

        processRepository.save(processes);
        processHistoryRepository.save(input.toProcessHistoryEntity(processes));
    }

    public void update(ProcessUpdateServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(
                GoalDaoParam.builder()
                        .goalId(input.getGoalId())
                        .createDate(input.getGoalCreateDate())
                        .accountId(input.getAccountId()).build()
        ).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Goal.id", input.getGoalId().toString());
            return new GoenNotFoundException("goal info not found exception", param);
        });

        processHistoryRepository.save(
                input.toProcessUpdateHistory(
                        processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                                .processId(input.getProcessId())
                                .goalCreateDate(input.getGoalCreateDate())
                                .accountId(input.getAccountId())
                                .latestFlg(true)
                                .build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process-history latest info not found", param);
                                }
                        ),
                        processRepository.detail(ProcessDaoParam.builder()
                                .id(input.getProcessId())
                                .createDate(input.getGoalCreateDate()).build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process detail info not found", param);
                                })
                )
        );

        processRepository.save(input.toProcessEntity());
    }

    public void updateProcessDate(ProcessDateUpdateServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(
                GoalDaoParam.builder()
                        .goalId(input.getGoalId())
                        .createDate(input.getGoalCreateDate())
                        .accountId(input.getAccountId()).build()
        ).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Goal.id", input.getGoalId().toString());
            return new GoenNotFoundException("goal info not found exception", param);
        });

        processHistoryRepository.save(
                input.toProcessUpdateHistory(
                        processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                                .processId(input.getProcessId())
                                .goalCreateDate(input.getGoalCreateDate())
                                .accountId(input.getAccountId())
                                .latestFlg(true)
                                .build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process-history latest info not found", param);
                                }
                        ),
                        processRepository.detail(ProcessDaoParam.builder()
                                .id(input.getProcessId())
                                .createDate(input.getGoalCreateDate()).build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process-history latest info not found", param);
                                })
                )
        );
    }

    public ProcessSearchServiceOutput search(ProcessSearchServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(input.toGoalDaoParam())
                .orElseThrow(() -> {
                        Map<String, String> param = new LinkedHashMap<>();
                        param.put("Goal.id", input.getGoalId().toString());
                        return new GoenNotFoundException("goal info not found exception", param);
                });

        return ProcessSearchServiceOutput.builder()
                .processSearchModelList(processRepository.search(input.toProcessDaoParam()))
                .build();
    }

    public ProcessDetailServiceOutput detail(ProcessDetailServiceInput input) throws GoenNotFoundException{
        var detail = processRepository.detail(input.toParameter())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("process.id", input.getProcessId().toString());
                    return new GoenNotFoundException("goal info not found exception", param);
                });
        return ProcessDetailServiceOutput.builder()
                .process(detail).build();
    }
}
