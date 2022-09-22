package daiku.domain.infra.model.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddGoalListParam {
    private Long goalId;
    private Long accountId;
    private Long makiId;
}
