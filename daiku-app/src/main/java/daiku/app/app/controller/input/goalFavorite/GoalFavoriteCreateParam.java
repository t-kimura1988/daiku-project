package daiku.app.app.controller.input.goalFavorite;

import daiku.app.app.service.input.goalFavorite.FavoriteCreateServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class GoalFavoriteCreateParam {
    Long goalId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate goalCreateDate;

    public FavoriteCreateServiceInput toService(Long accountId) {
        return FavoriteCreateServiceInput.builder()
                .goalId(goalId)
                .accountId(accountId)
                .createDate(goalCreateDate).build();
    }

}
