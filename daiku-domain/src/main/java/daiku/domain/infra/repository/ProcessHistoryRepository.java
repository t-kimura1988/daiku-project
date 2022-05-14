package daiku.domain.infra.repository;

import daiku.domain.infra.dao.ProcessHistoryDao;
import daiku.domain.infra.entity.TProcessesHistory;
import daiku.domain.infra.model.param.ProcessHistoryDaoParam;
import daiku.domain.infra.model.res.ProcessHistorySearchModel;
import daiku.domain.infra.utils.CollectorUtil;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class ProcessHistoryRepository {
    @Autowired
    ProcessHistoryDao processHistoryDaoDao;

    public void save(TProcessesHistory process) {
        Optional.ofNullable(process.getId())
                .ifPresentOrElse(
                        id -> processHistoryDaoDao.update(process),
                        () -> processHistoryDaoDao.insert(process)
                );
    }

    public List<ProcessHistorySearchModel> list(ProcessHistoryDaoParam param) {
        return processHistoryDaoDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<ProcessHistorySearchModel> detail(ProcessHistoryDaoParam param) {
        return processHistoryDaoDao.select(param, SelectOptions.get(), CollectorUtil.toOptional());
    }

    public Optional<ProcessHistorySearchModel> selectForLatest(ProcessHistoryDaoParam param) {
        return processHistoryDaoDao.select(param, SelectOptions.get(), CollectorUtil.toOptional());
    }


}
