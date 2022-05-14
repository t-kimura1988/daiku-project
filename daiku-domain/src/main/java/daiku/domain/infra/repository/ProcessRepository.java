package daiku.domain.infra.repository;

import daiku.domain.infra.dao.ProcessDao;
import daiku.domain.infra.entity.TProcesses;
import daiku.domain.infra.model.param.ProcessDaoParam;
import daiku.domain.infra.model.res.ProcessSearchModel;
import daiku.domain.infra.utils.CollectorUtil;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class ProcessRepository {
    @Autowired
    ProcessDao processDao;

    public List<ProcessSearchModel> search(ProcessDaoParam param) {
        return processDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<ProcessSearchModel> detail(ProcessDaoParam param) {
        return processDao.select(param, SelectOptions.get(), CollectorUtil.toOptional());
    }

    public void save(TProcesses process) {
        Optional.ofNullable(process.getId())
                .ifPresentOrElse(
                        id -> processDao.update(process),
                        () -> processDao.insert(process)
                );
    }


}
