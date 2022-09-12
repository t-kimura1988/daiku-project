package daiku.app.service.input.goal;

import daiku.domain.infra.model.param.GoalArchiveDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveDetailServiceInput {
    Long accountId;
    Long archiveId;
    LocalDate archiveCreateDate;

    public GoalArchiveDaoParam toArchiveRepository() {
        return GoalArchiveDaoParam.builder()
                .accountId(accountId)
                .archiveId(archiveId)
                .archiveCreateDate(archiveCreateDate)
                .build();
    }
}
