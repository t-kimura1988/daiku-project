package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryUpdateCommentServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistoryUpdateCommentParam {
    Long processHistoryId;
    String comment;

    public ProcessHistoryUpdateCommentServiceInput toService(Long accountId) {
        return ProcessHistoryUpdateCommentServiceInput.builder()
                .accountId(accountId)
                .processHistory(processHistoryId)
                .comment(comment).build();
    }
}
