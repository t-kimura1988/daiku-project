package daiku.domain.infra.repository;

import daiku.domain.infra.dao.AccountDao;
import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.enums.DelFlg;
import daiku.domain.infra.model.param.AccountDaoParam;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class AccountRepository {

    @Autowired
    private AccountDao accountDao;

    public Optional<TAccounts> selectByUid(String uid) {
        return accountDao.selectByUid(uid);
    }

    public List<TAccounts> selectOfDel() {
        return accountDao.select(AccountDaoParam.builder()
                .delFlg(DelFlg.DELETED)
                .updatedAt(LocalDateTime.now().minusMonths(1)).build(), SelectOptions.get(), toList());
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
