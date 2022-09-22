package daiku.domain.infra.repository;

import daiku.domain.infra.dao.MakiGoalDao;
import daiku.domain.infra.entity.TMakiGoalRelation;
import daiku.domain.infra.model.param.MakiGoalDaoParam;
import daiku.domain.infra.model.res.MakiGoalSearchModel;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static daiku.domain.infra.utils.CollectorUtil.toOptional;
import static java.util.stream.Collectors.toList;

@Repository
public class MakiGoalRepository {
    @Autowired
    MakiGoalDao makiGoalDao;

    public List<MakiGoalSearchModel> makiGoalRelationList(MakiGoalDaoParam param) {
        return makiGoalDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<MakiGoalSearchModel> makiGoalOptional(MakiGoalDaoParam param) {
        return makiGoalDao.select(param, SelectOptions.get(), toOptional());
    }

    public Long makiGoalRelationSortNumMax(Long makiId) {
        return makiGoalDao.selectSortNum(makiId);
    }

    public void save(TMakiGoalRelation entity) {
        Optional.ofNullable(entity.getId())
                .ifPresentOrElse(
                        id -> makiGoalDao.update(entity),
                        () -> makiGoalDao.insert(entity)
                );

    }
}
