package daiku.domain.infra.dao;

import daiku.domain.infra.entity.TGoals;
import daiku.domain.infra.model.param.GoalArchiveDaoParam;
import daiku.domain.infra.model.param.GoalDaoParam;
import daiku.domain.infra.model.res.GoalSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.time.LocalDate;
import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface GoalDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(GoalDaoParam param, SelectOptions options,  Collector<GoalSearchModel, ?, R> collector);

    @Select
    Long selectCount(LocalDate createDate);

    @Insert
    int insert(TGoals accounts);

    @Update(excludeNull = true)
    int update(TGoals accounts);
}
