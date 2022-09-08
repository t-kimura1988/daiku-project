package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryCreateServiceInput;
import daiku.domain.infra.enums.ProcessPriority;
import daiku.domain.infra.enums.ProcessStatus;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class ProcessHistoryCreateParam {
    @NotNull
    Long processId;
    String comment;
    ProcessStatus processStatus;
    ProcessPriority priority;

    public ProcessHistoryCreateServiceInput toService(Long accountId) {
        return ProcessHistoryCreateServiceInput.builder()
                .accountId(accountId)
                .processId(processId)
                .processStatus(processStatus)
                .priority(priority)
                .comment(comment).build();
    }
}
