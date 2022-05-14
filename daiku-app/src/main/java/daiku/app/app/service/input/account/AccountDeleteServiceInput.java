package daiku.app.app.service.input.account;

import com.google.firebase.auth.UserRecord;
import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.enums.DelFlg;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDeleteServiceInput {
    Long accountId;
    String uid;

    public TAccounts toRepo() {
        TAccounts accounts = new TAccounts();
        accounts.setId(accountId);
        accounts.setDelFlg(DelFlg.DELETED);
        return accounts;
    }

    public UserRecord.UpdateRequest toFirebaseUser() {
        return new UserRecord.UpdateRequest(uid)
                .setDisabled(true);
    }
}
