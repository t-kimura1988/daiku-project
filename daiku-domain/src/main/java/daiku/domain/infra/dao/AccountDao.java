package daiku.domain.infra.dao;

import daiku.domain.infra.entity.TAccounts;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface AccountDao {
    @Select
    Optional<TAccounts> selectByUid(String uid);

    @Select
    Long selectCount();

    @Insert
    int insert(TAccounts accounts);

    @Update(excludeNull = true)
    int update(TAccounts accounts);
}
