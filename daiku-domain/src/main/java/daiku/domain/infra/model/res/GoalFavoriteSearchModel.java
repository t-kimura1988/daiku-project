package daiku.domain.infra.model.res;

import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class GoalFavoriteSearchModel {
    private Long id;
    private Long goalId;
    private Long accountId;
    private LocalDate favoriteAddDate;
    private LocalDate goalCreateDate;
    private String goalCreatedAccountFamilyName;
    private String goalCreatedAccountGivenName;
    private String goalCreatedAccountNickName;
    private String goalCreatedAccountImg;
    private String title;
    private String purpose;
    private Date dueDate;
    private String favoriteAddAccountFamilyName;
    private String favoriteAddAccountGivenName;
    private String favoriteAddAccountNickName;
    private String favoriteAddAccountImg;
}
