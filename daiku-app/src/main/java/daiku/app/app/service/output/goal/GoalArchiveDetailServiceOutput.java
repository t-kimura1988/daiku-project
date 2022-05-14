package daiku.app.app.service.output.goal;

import daiku.app.app.controller.output.GoalArchiveDetailResponse;
import daiku.domain.infra.model.res.GoalArchiveSearchModel;
import daiku.domain.infra.model.res.GoalSearchModel;
import daiku.domain.infra.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalArchiveDetailServiceOutput {
    GoalArchiveSearchModel goalArchiveInfo;
    GoalSearchModel goalInfo;
    List<ProcessSearchModel> processInfo;

    public GoalArchiveDetailResponse toResponse() {
        return GoalArchiveDetailResponse.builder()
                .goalArchiveInfo(goalArchiveInfo)
                .goalInfo(goalInfo)
                .processInfo(processInfo).build();
    }
}
