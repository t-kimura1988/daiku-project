package daiku.domain.infra.dao;

import daiku.domain.infra.entity.TGoalArchive;
import daiku.domain.infra.entity.TGoals;
import daiku.domain.infra.model.param.GoalArchiveDaoParam;
import daiku.domain.infra.model.param.GoalDaoParam;
import daiku.domain.infra.model.res.GoalArchiveSearchModel;
import daiku.domain.infra.model.res.GoalSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.time.LocalDate;
import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface GoalArchiveDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R selectArchive(GoalArchiveDaoParam param, SelectOptions options, Collector<GoalArchiveSearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectByGoalId(Long goalId, Collector<TGoalArchive, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectById(GoalArchiveDaoParam param, Collector<TGoalArchive, ?, R> collector);

    @Insert
    int insert(TGoalArchive archive);

    @Update(excludeNull = true)
    int update(TGoalArchive archive);
}
