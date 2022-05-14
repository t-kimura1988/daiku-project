package daiku.app.app.service.output.processHistory;

import daiku.domain.infra.model.res.ProcessHistorySearchModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistoryDetailServiceOutput {
    ProcessHistorySearchModel processHistoryDetail;

    public ProcessHistorySearchModel toResponse() {
        return processHistoryDetail;
    }
}
