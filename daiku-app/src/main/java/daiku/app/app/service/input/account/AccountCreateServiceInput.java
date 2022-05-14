package daiku.app.app.service.input.account;

import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.enums.DelFlg;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountCreateServiceInput {
    String uid;
    String familyName;
    String givenName;
    String nickName;
    String email;

    public TAccounts toRepo() {
        TAccounts accounts = new TAccounts();
        accounts.setUid(uid);
        accounts.setEmail(email);
        accounts.setFamilyName(familyName);
        accounts.setGivenName(givenName);
        accounts.setNickName(nickName);
        accounts.setDelFlg(DelFlg.NOT_DELETED);
        return accounts;
    }
}
