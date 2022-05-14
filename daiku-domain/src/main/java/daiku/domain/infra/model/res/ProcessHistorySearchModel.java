package daiku.domain.infra.model.res;

import daiku.domain.infra.enums.ProcessPriority;
import daiku.domain.infra.enums.ProcessStatus;
import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class ProcessHistorySearchModel {
    private Long id;
    private Long processId;
    private Long accountId;
    private LocalDate goalCreateDate;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String title;
    private String beforeTitle;
    private ProcessPriority beforePriority;
    private ProcessPriority priority;
    private ProcessStatus beforeProcessStatus;
    private ProcessStatus processStatus;
    private String comment;
    private LocalDate beforeProcessStartDate;
    private LocalDate processStartDate;
    private LocalDate beforeProcessEndDate;
    private LocalDate processEndDate;
}
