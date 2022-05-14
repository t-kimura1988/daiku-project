package daiku.app.app.controller.input.goal;

import daiku.app.app.service.input.goal.GoalSearchServiceInput;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.YearMonth;

@Value
@Builder
public class GoalSearchParameter {
    int year;

    public GoalSearchServiceInput toService(Long accountId) {
        return GoalSearchServiceInput.builder()
                .accountId(accountId)
                .fromDate(LocalDate.of(year, 1, 1))
                .toDate(LocalDate.of(year, 12, 31))
                .build();
    }

}
