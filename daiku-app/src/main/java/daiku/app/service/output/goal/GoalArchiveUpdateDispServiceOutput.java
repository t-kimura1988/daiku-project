package daiku.app.service.output.goal;

import daiku.domain.infra.entity.TGoalArchive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoalArchiveUpdateDispServiceOutput {
    TGoalArchive goalArchiveInfo;

    public TGoalArchive toResponse() {
        return goalArchiveInfo;
    }
}
