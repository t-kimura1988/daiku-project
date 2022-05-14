package daiku.app.app.service.input.process;

import daiku.domain.infra.model.param.GoalDaoParam;
import daiku.domain.infra.model.param.ProcessDaoParam;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class ProcessSearchServiceInput {
    Long goalId;
    Long accountId;
    LocalDate createDate;

    public GoalDaoParam toGoalDaoParam() {
        return GoalDaoParam.builder()
                .accountId(accountId)
                .goalId(goalId)
                .createDate(createDate).build();
    }

    public ProcessDaoParam toProcessDaoParam() {
        return ProcessDaoParam.builder()
                .accountId(accountId)
                .goalId(goalId)
                .createDate(createDate).build();
    }
}
