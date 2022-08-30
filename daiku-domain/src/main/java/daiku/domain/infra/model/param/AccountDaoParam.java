package daiku.domain.infra.model.param;

import daiku.domain.infra.enums.DelFlg;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountDaoParam {
    private Long id;
    private DelFlg delFlg;
    private String uid;
    private LocalDateTime updatedAt;
}
