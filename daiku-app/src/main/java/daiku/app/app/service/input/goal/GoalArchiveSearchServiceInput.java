package daiku.app.app.service.input.goal;

import daiku.domain.infra.model.param.GoalArchiveDaoParam;
import daiku.domain.infra.model.param.GoalDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveSearchServiceInput {
    Long accountId;
    LocalDate fromDate;
    LocalDate toDate;

    public GoalArchiveDaoParam toRepository() {
        return GoalArchiveDaoParam.builder()
                .accountId(accountId)
                .build();
    }
}
