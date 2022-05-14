package daiku.app.app.service.input.processHistory;

import daiku.domain.infra.entity.TProcessesHistory;
import daiku.domain.infra.enums.ProcessPriority;
import daiku.domain.infra.enums.ProcessStatus;
import daiku.domain.infra.model.param.ProcessDaoParam;
import daiku.domain.infra.model.res.ProcessHistorySearchModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistoryCreateServiceInput {
    Long processId;
    Long accountId;
    String comment;
    ProcessStatus processStatus;
    ProcessPriority priority;

    public TProcessesHistory toProcessDetailParam(ProcessHistorySearchModel oldData) {
        System.out.println(oldData);
        TProcessesHistory entity = new TProcessesHistory();
        entity.setBeforeProcessStartDate(oldData.getProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getProcessEndDate());
        entity.setBeforePriority(oldData.getPriority());
        entity.setBeforeProcessStatus(oldData.getProcessStatus());
        entity.setGoalCreateDate(oldData.getGoalCreateDate());
        entity.setProcessStatus(processStatus);
        entity.setPriority(priority);
        entity.setComment(comment);
        entity.setProcessId(processId);
        entity.setAccountId(accountId);
        System.out.println(entity);
        return entity;
    }

    public ProcessDaoParam toDetailParam() {
        return ProcessDaoParam.builder()
                .id(processId)
                .accountId(accountId).build();
    }
}
