package daiku.app.app.service.output.goal;

import daiku.domain.infra.model.res.GoalSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Builder
@Value
public class GoalDetailServiceOutput {
    Optional<GoalSearchModel> goal;

    public GoalSearchModel toResponse() {
        return goal.orElseThrow();
    }
}
