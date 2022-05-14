package daiku.app.app.service.output.goal;

import daiku.domain.infra.model.res.GoalSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalSearchServiceOutput {
    List<GoalSearchModel> goalList;

    public List<GoalSearchModel> toResponse() {
        return goalList;
    }
}
