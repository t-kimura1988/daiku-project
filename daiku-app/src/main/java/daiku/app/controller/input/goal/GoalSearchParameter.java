package daiku.app.controller.input.goal;

import daiku.app.service.input.goal.GoalSearchServiceInput;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalSearchParameter {
    int year;
    int page;

    public GoalSearchServiceInput toService(Long accountId) {
        return GoalSearchServiceInput.builder()
                .accountId(accountId)
                .fromDate(LocalDate.of(year, 1, 1))
                .toDate(LocalDate.of(year, 12, 31))
                .page(page)
                .build();
    }

}
