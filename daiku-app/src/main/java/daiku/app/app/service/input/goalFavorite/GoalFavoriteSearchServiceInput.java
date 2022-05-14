package daiku.app.app.service.input.goalFavorite;

import daiku.domain.infra.model.param.GoalFavoriteDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalFavoriteSearchServiceInput {
    Long accountId;
    LocalDate fromDate;
    LocalDate toDate;

    public GoalFavoriteDaoParam toParam() {
        return GoalFavoriteDaoParam.builder()
                .favoriteCreateDateFrom(fromDate)
                .favoriteCreateDateTo(toDate)
                .accountId(accountId).build();
    }
}
