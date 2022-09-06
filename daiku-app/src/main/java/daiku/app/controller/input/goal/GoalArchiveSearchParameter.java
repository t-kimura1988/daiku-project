package daiku.app.controller.input.goal;

import daiku.app.service.input.goal.GoalArchiveSearchServiceInput;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveSearchParameter {
    int year;
    int page;

    public GoalArchiveSearchServiceInput toService(Long accountId) {
        return GoalArchiveSearchServiceInput.builder()
                .accountId(accountId)
                .fromDate(LocalDate.of(year, 1, 1))
                .toDate(LocalDate.of(year, 12, 31))
                .page(page)
                .build();
    }

}
