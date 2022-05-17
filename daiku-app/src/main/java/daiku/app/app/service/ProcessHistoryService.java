package daiku.app.app.service;

import daiku.app.app.service.input.processHistory.*;
import daiku.app.app.service.output.processHistory.ProcessHistoryDetailServiceOutput;
import daiku.app.app.service.output.processHistory.ProcessHistorySearchServiceOutput;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.model.param.ProcessHistoryDaoParam;
import daiku.domain.infra.model.res.ProcessSearchModel;
import daiku.domain.infra.repository.ProcessHistoryRepository;
import daiku.domain.infra.repository.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class ProcessHistoryService {

    @Autowired
    ProcessHistoryRepository processHistoryRepository;

    @Autowired
    ProcessRepository processRepository;

    public ProcessHistorySearchServiceOutput search(ProcessHistorySearchServiceInput input) throws GoenNotFoundException {
        ProcessSearchModel detail = processRepository.detail(input.toProcessDetailParam())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Process.id: ", input.getProcessId().toString());
                    return new GoenNotFoundException("process detail info no found", param);
                });

        return ProcessHistorySearchServiceOutput.builder()
                .processHistorySearchModelList(processHistoryRepository.list(ProcessHistoryDaoParam.builder()
                        .processId(detail.getId())
                        .accountId(detail.getAccountId())
                        .goalCreateDate(detail.getGoalCreateDate()).build())).build();
    }

    public ProcessHistoryDetailServiceOutput detail(ProcessHistoryDetailServiceInput input) throws GoenNotFoundException {
        return ProcessHistoryDetailServiceOutput.builder()
                .processHistoryDetail(
                        processHistoryRepository.detail(input.toProcessDetailParam()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("ProcessHistory.id: ", input.getProcessHistoryId().toString());
                                    return new GoenNotFoundException("process history detail info not found", param);
                                }
                        )
                )
                .build();
    }

    public void create(ProcessHistoryCreateServiceInput input) throws GoenNotFoundException {
        var process = processRepository.detail(input.toDetailParam()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Process.id: ", input.getProcessId().toString());
            return new GoenNotFoundException("process detail info not found", param);
        });

        processHistoryRepository.save(
                input.toProcessDetailParam(
                        processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                                .processId(input.getProcessId())
                                .goalCreateDate(process.getGoalCreateDate())
                                .accountId(input.getAccountId())
                                .latestFlg(true)
                                .build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process-history latest info not found", param);
                                }
                        )
                )
        );
    }

    public void updateComment(ProcessHistoryUpdateCommentServiceInput input) {
        processHistoryRepository.save(input.toProcessHistoryEntity());
    }

    public void updateStatus(ProcessHistoryUpdateStatusServiceInput input) throws GoenNotFoundException {
        var process = processRepository.detail(input.toDetailParam()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Process.id: ", input.getProcessId().toString());
            return new GoenNotFoundException("process detail info not found", param);
        });

        processHistoryRepository.save(
                input.toProcessDetailParam(
                        processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                                .processId(input.getProcessId())
                                .goalCreateDate(process.getGoalCreateDate())
                                .accountId(input.getAccountId())
                                .latestFlg(true)
                                .build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process-history latest info not found", param);
                                }
                        )
                )
        );
    }
}
