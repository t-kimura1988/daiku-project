package daiku.app.controller.input.idea;

import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.service.input.idea.IdeaCreateServiceInput;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class IdeaEditParameter {
    @NotNull(groups = UpdateGroups.class)
    Long ideaId;

    String body;

    public IdeaCreateServiceInput toService(Long accountId) {
        return IdeaCreateServiceInput.builder()
                .body(body)
                .accountId(accountId).build();
    }
}
