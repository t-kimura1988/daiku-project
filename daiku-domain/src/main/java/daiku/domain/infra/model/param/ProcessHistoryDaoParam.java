package daiku.domain.infra.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class ProcessHistoryDaoParam {
    private Long id;
    private Long processId;
    private Long accountId;
    private LocalDate goalCreateDate;
    private Boolean latestFlg;
}
