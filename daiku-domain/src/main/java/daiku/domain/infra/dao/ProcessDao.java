package daiku.domain.infra.dao;

import daiku.domain.infra.entity.TProcesses;
import daiku.domain.infra.model.param.ProcessDaoParam;
import daiku.domain.infra.model.res.ProcessSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface ProcessDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(ProcessDaoParam param, SelectOptions options, Collector<ProcessSearchModel, ?, R> collector);

    @Insert
    int insert(TProcesses process);

    @Update(excludeNull = true)
    int update(TProcesses process);
}
