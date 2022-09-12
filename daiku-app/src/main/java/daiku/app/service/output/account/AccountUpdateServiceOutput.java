package daiku.app.service.output.account;

import daiku.domain.infra.entity.TAccounts;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountUpdateServiceOutput {
    TAccounts accounts;

    public TAccounts toResponse() {
        return accounts;
    }
}
