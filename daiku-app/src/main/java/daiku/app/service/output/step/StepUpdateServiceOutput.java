package daiku.app.service.output.step;

import daiku.domain.model.res.StepSearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StepUpdateServiceOutput {
    StepSearchModel stepSearchModel;

    public StepSearchModel toRes() {
        return stepSearchModel;
    }
}
