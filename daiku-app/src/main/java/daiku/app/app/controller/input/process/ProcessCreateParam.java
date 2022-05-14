package daiku.app.app.controller.input.process;

import daiku.app.app.controller.input.groups.UpdateGroups;
import daiku.app.app.service.input.process.ProcessCreateServiceInput;
import daiku.app.app.service.input.process.ProcessUpdateServiceInput;
import daiku.domain.infra.enums.ProcessPriority;
import daiku.domain.infra.enums.ProcessStatus;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class ProcessCreateParam {
    @NotNull(groups = UpdateGroups.class)
    Long processId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate goalCreateDate;
    @NotNull
    Long goalId;
    String title;
    String body;
    ProcessStatus processStatus;
    ProcessPriority priority;

    public ProcessCreateServiceInput toService(Long accountId) {
        return ProcessCreateServiceInput.builder()
                .goalId(goalId)
                .accountId(accountId)
                .title(title)
                .body(body)
                .processStatus(processStatus)
                .processPriority(priority).build();
    }

    public ProcessUpdateServiceInput toUpdateService(Long accountId) {
        return ProcessUpdateServiceInput.builder()
                .processId(processId)
                .goalId(goalId)
                .goalCreateDate(goalCreateDate)
                .title(title)
                .body(body)
                .processStatus(processStatus)
                .processPriority(priority)
                .accountId(accountId).build();
    }
}
