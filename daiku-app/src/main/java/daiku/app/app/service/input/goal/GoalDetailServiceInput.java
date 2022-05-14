package daiku.app.app.service.input.goal;

import daiku.domain.infra.model.param.GoalDaoParam;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class GoalDetailServiceInput {
    Long goalId;
    Long accountId;
    LocalDate createDate;

    public GoalDaoParam toParam() {
        return GoalDaoParam.builder()
                .goalId(goalId)
                .accountId(accountId)
                .createDate(createDate).build();
    }
}
