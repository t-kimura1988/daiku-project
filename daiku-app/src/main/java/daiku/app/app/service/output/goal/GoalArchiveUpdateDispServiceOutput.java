package daiku.app.app.service.output.goal;

import daiku.app.app.controller.output.GoalArchiveDetailResponse;
import daiku.domain.infra.entity.TGoalArchive;
import daiku.domain.infra.model.res.GoalArchiveSearchModel;
import daiku.domain.infra.model.res.GoalSearchModel;
import daiku.domain.infra.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalArchiveUpdateDispServiceOutput {
    TGoalArchive goalArchiveInfo;

    public TGoalArchive toResponse() {
        return goalArchiveInfo;
    }
}
