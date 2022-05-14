package daiku.app.app.service.input.goalFavorite;

import daiku.domain.infra.entity.TGoalFavorites;
import daiku.domain.infra.model.param.GoalDaoParam;
import daiku.domain.infra.model.param.GoalFavoriteDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class FavoriteCreateServiceInput {
    Long accountId;
    Long goalId;
    LocalDate createDate;

    public GoalDaoParam toGoalParam() {
        return GoalDaoParam.builder()
                .goalId(goalId)
                .createDate(createDate)
                .accountId(accountId).build();
    }

    public GoalFavoriteDaoParam toGoalFavoriteParam() {
        return GoalFavoriteDaoParam.builder()
                .goalId(goalId)
                .accountId(accountId)
                .build();
    }

    public TGoalFavorites toEntity() {
        TGoalFavorites entity = new TGoalFavorites();
        entity.setGoalId(goalId);
        entity.setAccountId(accountId);
        entity.setFavoriteAddDate(LocalDate.now());
        return entity;
    }
}
