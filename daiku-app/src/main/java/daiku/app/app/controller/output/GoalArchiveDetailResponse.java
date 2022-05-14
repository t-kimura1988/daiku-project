package daiku.app.app.controller.output;

import daiku.domain.infra.model.res.GoalArchiveSearchModel;
import daiku.domain.infra.model.res.GoalSearchModel;
import daiku.domain.infra.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalArchiveDetailResponse {
    GoalArchiveSearchModel goalArchiveInfo;
    GoalSearchModel goalInfo;
    List<ProcessSearchModel> processInfo;
}
