package daiku.app.app.service.input.process;

import daiku.domain.infra.entity.TProcesses;
import daiku.domain.infra.entity.TProcessesHistory;
import daiku.domain.infra.enums.ProcessPriority;
import daiku.domain.infra.enums.ProcessStatus;
import daiku.domain.infra.model.res.ProcessHistorySearchModel;
import daiku.domain.infra.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProcessUpdateServiceInput {
    Long processId;
    LocalDate goalCreateDate;
    Long goalId;
    Long accountId;
    String title;
    String body;
    ProcessStatus processStatus;
    ProcessPriority processPriority;

    public TProcesses toProcessEntity() {
        TProcesses entity = new TProcesses();
        entity.setId(processId);
        entity.setGoalCreateDate(goalCreateDate);
        entity.setGoalId(goalId);
        entity.setAccountId(accountId);
        entity.setTitle(title);
        entity.setBody(body);
        return entity;
    }

    public TProcessesHistory toProcessUpdateHistory(ProcessHistorySearchModel oldData, ProcessSearchModel oldProcess) {
        TProcessesHistory entity = new TProcessesHistory();
        entity.setBeforeProcessStartDate(oldData.getProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getProcessEndDate());
        entity.setBeforePriority(oldData.getPriority());
        entity.setBeforeProcessStatus(oldData.getProcessStatus());
        entity.setGoalCreateDate(oldData.getGoalCreateDate());
        entity.setBeforeProcessStartDate(oldData.getBeforeProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getBeforeProcessEndDate());
        entity.setProcessStartDate(oldData.getProcessStartDate());
        entity.setProcessEndDate(oldData.getProcessEndDate());
        entity.setProcessStatus(processStatus);
        entity.setPriority(processPriority);
        entity.setProcessId(processId);
        entity.setAccountId(accountId);
        entity.setBeforeTitle(title.equals(oldProcess.getTitle()) ? null : title);
        entity.setBeforeBody(body.equals(oldProcess.getBody()) ? null : body);
        System.out.println(entity);
        return entity;
    }
}
