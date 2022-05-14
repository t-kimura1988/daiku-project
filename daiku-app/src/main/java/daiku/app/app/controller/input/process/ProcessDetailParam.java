package daiku.app.app.controller.input.process;

import daiku.app.app.service.input.process.ProcessDetailServiceInput;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProcessDetailParam {
    Long processId;
    LocalDate goalCreateDate;

    public ProcessDetailServiceInput toService(Long accountId) {
        return ProcessDetailServiceInput.builder()
                .processId(processId)
                .accountId(accountId).build();
    }
}
