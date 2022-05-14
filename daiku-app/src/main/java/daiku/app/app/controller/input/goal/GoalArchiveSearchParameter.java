package daiku.app.app.controller.input.goal;

import daiku.app.app.service.input.goal.GoalArchiveSearchServiceInput;
import daiku.app.app.service.input.goal.GoalSearchServiceInput;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveSearchParameter {
    int year;

    public GoalArchiveSearchServiceInput toService(Long accountId) {
        return GoalArchiveSearchServiceInput.builder()
                .accountId(accountId)
                .fromDate(LocalDate.of(year, 1, 1))
                .toDate(LocalDate.of(year, 12, 31))
                .build();
    }

}
