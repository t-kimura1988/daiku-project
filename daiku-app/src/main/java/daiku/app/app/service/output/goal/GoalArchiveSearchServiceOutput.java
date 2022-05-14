package daiku.app.app.service.output.goal;

import daiku.domain.infra.model.res.GoalArchiveSearchModel;
import daiku.domain.infra.model.res.GoalSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalArchiveSearchServiceOutput {
    List<GoalArchiveSearchModel> goalList;

    public List<GoalArchiveSearchModel> toResponse() {
        return goalList;
    }
}
