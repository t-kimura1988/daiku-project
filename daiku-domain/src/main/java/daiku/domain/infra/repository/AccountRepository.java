package daiku.domain.infra.repository;

import daiku.domain.infra.dao.AccountDao;
import daiku.domain.infra.entity.TAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepository {

    @Autowired
    private AccountDao accountDao;

    public Optional<TAccounts> selectByUid(String uid) {
        return accountDao.selectByUid(uid);
    }

    public Long accountCount() {
        return accountDao.selectCount();
    }
    public int save(TAccounts accounts) {
        if(accounts.getId() == null) {
            return accountDao.insert(accounts);
        }else {
            return accountDao.update(accounts);
        }
    }
}
