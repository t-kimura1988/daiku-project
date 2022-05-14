package daiku.domain.infra.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProcessDaoParam {
    private Long id;
    private Long goalId;
    private Long accountId;
    private LocalDate createDate;
}
