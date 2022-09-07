package daiku.app.service.input.account;

import com.google.firebase.auth.UserRecord;
import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.enums.DelFlg;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDeleteServiceInput {
    TAccounts account;

    public TAccounts toRepo() {
        account.setDelFlg(DelFlg.DELETED);
        return account;
    }
}
