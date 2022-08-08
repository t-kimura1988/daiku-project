package daiku.app.app.service.output.processHistory;

import daiku.domain.infra.model.res.ProcessHistorySearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProcessHistorySearchServiceOutput {
    List<ProcessHistorySearchModel> processHistorySearchModelList;

    public List<ProcessHistorySearchModel> toResponse() {
        return processHistorySearchModelList;
    }
}
