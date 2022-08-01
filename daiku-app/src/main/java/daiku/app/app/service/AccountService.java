package daiku.app.app.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import daiku.app.app.service.input.account.AccountCreateServiceInput;
import daiku.app.app.service.input.account.AccountDeleteServiceInput;
import daiku.app.app.service.input.account.AccountReUpdateServiceInput;
import daiku.app.app.service.input.account.AccountUpdateServiceInput;
import daiku.app.app.service.output.account.AccountUpdateServiceOutput;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.enums.DelFlg;
import daiku.domain.infra.repository.AccountRepository;
import daiku.domain.infra.repository.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FirebaseRepository firebaseRepository;

    public TAccounts showService(String uid) throws GoenNotFoundException, GoenIntegrityException {
        var account = accountRepository.selectByUid(uid)
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("uid: ", uid);
                            return new GoenNotFoundException("goal detail info no found", param);
                        }
                );

        if(DelFlg.DELETED.equals(account.getDelFlg())) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("account uid: ", uid);
            throw  new GoenIntegrityException("アカウントが削除済み", param, "E0001");
        }

        return account;
    }

    public TAccounts baseCreate(AccountCreateServiceInput input) throws FirebaseAuthException {
        TAccounts accounts = new TAccounts();
        accounts.setUid(input.getUid());
        accounts.setEmail(input.getEmail());
        accounts.setFamilyName(input.getFamilyName());
        accounts.setGivenName(input.getGivenName());
        accounts.setNickName(input.getNickName());
        accounts.setDelFlg(DelFlg.NOT_DELETED);
        accountRepository.save(accounts);
        firebaseRepository.accountClaims(input.getUid());

        return accounts;
    }

    public AccountUpdateServiceOutput update(AccountUpdateServiceInput input) {
        accountRepository.save(input.toRepo());
        return AccountUpdateServiceOutput.builder()
                .accounts(input.toRepo()).build();
    }

    public TAccounts delete(AccountDeleteServiceInput input) throws FirebaseAuthException {
        accountRepository.save(input.toRepo());
        FirebaseAuth.getInstance().updateUser(input.toFirebaseUser());
        FirebaseAuth.getInstance().revokeRefreshTokens(input.getUid());
        return input.toRepo();
    }

    public TAccounts reUpdate(AccountReUpdateServiceInput input) throws FirebaseAuthException {
        accountRepository.save(input.toRepo());
        FirebaseAuth.getInstance().updateUser(input.toFirebaseUser());
        return input.toRepo();
    }


}
