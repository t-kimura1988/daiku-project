package daiku.domain.infra.model.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MakiDaoParam {
    private Long makiId;
    private Long accountId;
    private int page;
}
