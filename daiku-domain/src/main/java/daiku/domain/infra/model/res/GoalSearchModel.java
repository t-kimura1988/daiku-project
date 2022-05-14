package daiku.domain.infra.model.res;

import daiku.domain.infra.enums.UpdatingFlg;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.seasar.doma.Entity;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class GoalSearchModel {
    private Long id;
    private LocalDate createDate;
    private Long accountId;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String title;
    private String purpose;
    private String aim;
    private LocalDate dueDate;
    private Long favoriteId;
    private Long archiveId;
    private LocalDate archivesCreateDate;
    private UpdatingFlg updatingFlg;
}
