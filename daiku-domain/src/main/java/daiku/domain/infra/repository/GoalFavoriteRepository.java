package daiku.domain.infra.repository;

import daiku.domain.infra.dao.GoalFavoriteDao;
import daiku.domain.infra.entity.TGoalFavorites;
import daiku.domain.infra.model.param.GoalFavoriteDaoParam;
import daiku.domain.infra.model.res.GoalFavoriteSearchModel;
import daiku.domain.infra.utils.CollectorUtil;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class GoalFavoriteRepository {
    @Autowired
    GoalFavoriteDao favoriteDao;

    public Optional<TGoalFavorites> selectByAccountIdAndGoalId(GoalFavoriteDaoParam param) {
        return favoriteDao.selectByAccountIdAndGoalId(param, SelectOptions.get(), CollectorUtil.toOptional());
    }

    public List<GoalFavoriteSearchModel> selectList(GoalFavoriteDaoParam param) {
        return favoriteDao.select(param, SelectOptions.get(), toList());
    }

    public void save(TGoalFavorites favorites) {
        Optional.ofNullable(favorites.getId())
                .ifPresentOrElse(
                        id -> favoriteDao.update(favorites),
                        () -> favoriteDao.insert(favorites)
                );
    }

    public void delete(TGoalFavorites entity) {
        favoriteDao.delete(entity);
    }


}
