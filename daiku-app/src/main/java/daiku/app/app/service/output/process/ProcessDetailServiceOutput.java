package daiku.app.app.service.output.process;

import daiku.domain.infra.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessDetailServiceOutput {
    ProcessSearchModel process;

    public ProcessSearchModel toResponse() {
        return process;
    }
}
