package daiku.app.app.controller.input.goal;

import daiku.app.app.controller.input.groups.CreateGroups;
import daiku.app.app.controller.input.groups.UpdateGroups;
import daiku.app.app.service.input.goal.GoalArchiveServiceInput;
import daiku.app.app.service.input.goal.GoalArchiveUpdateServiceInput;
import daiku.app.app.service.input.goal.GoalCreateServiceInput;
import daiku.app.app.service.input.goal.GoalUpdateServiceInput;
import daiku.domain.infra.enums.PublishLevel;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveCreateParam {
    @NotNull(groups = {UpdateGroups.class})
    Long archiveId;
    @NotNull(groups = {UpdateGroups.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate archiveCreateDate;
    @NotNull(groups = {CreateGroups.class})
    Long goalId;
    @NotNull(groups = {CreateGroups.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate createDate;
    @NotNull
    String thoughts;
    @NotNull
    PublishLevel publish;

    public GoalArchiveServiceInput toArchiveService(Long accountId) {
        return GoalArchiveServiceInput.builder()
                .goalId(goalId)
                .createDate(createDate)
                .thoughts(thoughts)
                .publish(publish).build();
    }

    public GoalArchiveUpdateServiceInput toArchiveUpdateService() {
        return GoalArchiveUpdateServiceInput.builder()
                .archiveId(archiveId)
                .archiveCreateDate(archiveCreateDate)
                .thoughts(thoughts)
                .publish(publish).build();
    }
}
