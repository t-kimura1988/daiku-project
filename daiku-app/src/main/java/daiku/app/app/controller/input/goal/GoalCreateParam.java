package daiku.app.app.controller.input.goal;

import daiku.app.app.controller.input.groups.UpdateGroups;
import daiku.app.app.service.input.goal.GoalCreateServiceInput;
import daiku.app.app.service.input.goal.GoalUpdateServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class GoalCreateParam {
    @NotNull(groups = UpdateGroups.class)
    Long goalId;
    @NotNull(groups = UpdateGroups.class)
    LocalDate createDate;
    @NotNull
    String title;
    @NotNull
    String purpose;
    String aim;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dueDate;

    public GoalCreateServiceInput toCreateService(Long accountId) {
        return GoalCreateServiceInput.builder()
                .title(title)
                .purpose(purpose)
                .aim(aim)
                .dueDate(dueDate)
                .accountId(accountId).build();
    }

    public GoalUpdateServiceInput toUpdateService(Long accountId) {
        return GoalUpdateServiceInput.builder()
                .goalId(goalId)
                .createDate(createDate)
                .title(title)
                .purpose(purpose)
                .aim(aim)
                .dueDate(dueDate)
                .accountId(accountId).build();
    }
}
