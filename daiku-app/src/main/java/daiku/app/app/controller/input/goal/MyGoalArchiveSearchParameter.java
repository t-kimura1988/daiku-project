package daiku.app.app.controller.input.goal;

import daiku.app.app.service.input.goal.MyGoalArchiveSearchServiceInput;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class MyGoalArchiveSearchParameter {
    int year;

    public MyGoalArchiveSearchServiceInput toService(Long accountId) {
        return MyGoalArchiveSearchServiceInput.builder()
                .accountId(accountId)
                .fromDate(LocalDate.of(year, 1, 1))
                .toDate(LocalDate.of(year, 12, 31))
                .build();
    }

}
