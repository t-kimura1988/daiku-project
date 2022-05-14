package daiku.domain.infra.dao;

import daiku.domain.infra.entity.TProcessesHistory;
import daiku.domain.infra.model.param.ProcessHistoryDaoParam;
import daiku.domain.infra.model.res.ProcessHistorySearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface ProcessHistoryDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(ProcessHistoryDaoParam param, SelectOptions options, Collector<ProcessHistorySearchModel, ?, R> collector);

    @Insert
    int insert(TProcessesHistory process);

    @Update(excludeNull = true)
    int update(TProcessesHistory process);
}
