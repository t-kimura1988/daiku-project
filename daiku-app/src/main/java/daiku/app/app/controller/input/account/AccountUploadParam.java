package daiku.app.app.controller.input.account;

import daiku.app.app.service.input.account.AccountUploadServiceInput;
import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.enums.AccountImageType;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class AccountUploadParam {
    @NotNull
    String imagePath;
    @NotNull
    AccountImageType imageType;

    public AccountUploadServiceInput toService(TAccounts account) {
        return AccountUploadServiceInput.builder()
                .account(account)
                .imagePath(imagePath)
                .imageType(imageType).build();
    }
}
