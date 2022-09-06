package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryUpdateStatusServiceInput;
import daiku.domain.infra.enums.ProcessPriority;
import daiku.domain.infra.enums.ProcessStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistoryUpdateStatusParam {
    Long processId;
    ProcessStatus processStatus;
    ProcessPriority priority;

    public ProcessHistoryUpdateStatusServiceInput toService(Long accountId) {
        return ProcessHistoryUpdateStatusServiceInput.builder()
                .processId(processId)
                .accountId(accountId)
                .processStatus(processStatus)
                .priority(priority).build();
    }
}
