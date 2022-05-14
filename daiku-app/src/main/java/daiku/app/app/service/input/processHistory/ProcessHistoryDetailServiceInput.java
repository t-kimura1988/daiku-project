package daiku.app.app.service.input.processHistory;

import daiku.domain.infra.model.param.ProcessHistoryDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProcessHistoryDetailServiceInput {
    Long processHistoryId;
    LocalDate goalCreateDate;
    Long accountId;

    public ProcessHistoryDaoParam toProcessDetailParam() {
        return ProcessHistoryDaoParam.builder()
                .id(processHistoryId)
                .goalCreateDate(goalCreateDate)
                .accountId(accountId).build();
    }
}
