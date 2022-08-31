package daiku.domain.infra.dao;

import daiku.domain.infra.entity.TAccounts;
import daiku.domain.infra.model.param.AccountDaoParam;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.Optional;
import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface AccountDao {
    @Select
    Optional<TAccounts> selectByUid(String uid);

    @Select(strategy = SelectType.COLLECT)
    <R> R select(AccountDaoParam param, SelectOptions options, Collector<TAccounts, ?, R> collector);

    @Select
    Long selectCount();

    @Insert
    int insert(TAccounts accounts);

    @Update(excludeNull = true)
    int update(TAccounts accounts);
}
