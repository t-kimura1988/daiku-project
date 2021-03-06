package daiku.app.app.controller.input.account;

import daiku.app.app.service.input.account.AccountCreateServiceInput;
import daiku.app.app.service.input.account.AccountUpdateServiceInput;
import daiku.domain.infra.entity.TAccounts;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountCreateParam {
    String familyName;
    String givenName;
    String nickName;

    public AccountCreateServiceInput toService(TAccounts account) {
        return AccountCreateServiceInput.builder()
                .uid(account.getUid())
                .familyName(familyName)
                .givenName(givenName)
                .nickName(nickName)
                .email(account.getEmail())
                .build();
    }

    public AccountUpdateServiceInput toUpdateService(Long accountId) {
        return AccountUpdateServiceInput.builder()
                .accountId(accountId)
                .familyName(familyName)
                .givenName(givenName)
                .nickName(nickName)
                .build();
    }
}
