package daiku.domain.infra.dao;

import daiku.domain.infra.entity.TMakiGoalRelation;
import daiku.domain.infra.model.param.MakiGoalDaoParam;
import daiku.domain.infra.model.res.MakiGoalSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface MakiGoalDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(MakiGoalDaoParam param, SelectOptions options, Collector<MakiGoalSearchModel, ?, R> collector);

    @Select
    Long selectSortNum(Long makiId);

    @Insert
    int insert(TMakiGoalRelation entity);

    @Update(excludeNull = true)
    int update(TMakiGoalRelation entity);
}
