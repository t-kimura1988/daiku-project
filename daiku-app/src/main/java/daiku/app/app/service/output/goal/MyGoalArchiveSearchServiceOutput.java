package daiku.app.app.service.output.goal;

import daiku.domain.infra.model.res.GoalArchiveSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MyGoalArchiveSearchServiceOutput {
    List<GoalArchiveSearchModel> goalList;

    public List<GoalArchiveSearchModel> toResponse() {
        return goalList;
    }
}
