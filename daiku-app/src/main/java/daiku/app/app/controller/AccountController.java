package daiku.app.app.controller;

import com.google.firebase.auth.FirebaseAuthException;
import daiku.app.app.controller.input.account.AccountCreateParam;
import daiku.app.app.service.AccountService;
import daiku.app.app.service.input.account.AccountDeleteServiceInput;
import daiku.app.app.service.input.account.AccountReUpdateServiceInput;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.entity.GoenUserDetails;
import daiku.domain.infra.entity.TAccounts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public TAccounts show(@AuthenticationPrincipal GoenUserDetails userDetails) throws GoenNotFoundException, GoenIntegrityException {
        return accountService.showService(userDetails.account().getUid());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TAccounts create(
            @RequestBody AccountCreateParam param,
            @AuthenticationPrincipal GoenUserDetails userDetails) {
        return accountService.baseCreate(param.toService(userDetails.account()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public TAccounts update(
            @RequestBody AccountCreateParam param,
            @AuthenticationPrincipal GoenUserDetails userDetails) {
        return accountService.update(param.toUpdateService(userDetails.account().getId())).toResponse();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public TAccounts delete(
            @AuthenticationPrincipal GoenUserDetails userDetails) throws FirebaseAuthException {
        return accountService.delete(AccountDeleteServiceInput.builder()
                .accountId(userDetails.account().getId())
                .uid(userDetails.account().getUid()).build());
    }

    @RequestMapping(value = "/re-update", method = RequestMethod.POST)
    public TAccounts reUpdate(
            @AuthenticationPrincipal GoenUserDetails userDetails) throws FirebaseAuthException {
        return accountService.reUpdate(AccountReUpdateServiceInput.builder()
                .accountId(userDetails.account().getId())
                .uid(userDetails.account().getUid()).build());
    }

}
