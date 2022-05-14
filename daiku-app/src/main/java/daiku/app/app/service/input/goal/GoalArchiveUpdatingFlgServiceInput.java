package daiku.app.app.service.input.goal;

import daiku.domain.infra.entity.TGoalArchive;
import daiku.domain.infra.enums.PublishLevel;
import daiku.domain.infra.enums.UpdatingFlg;
import daiku.domain.infra.model.param.GoalArchiveDaoParam;
import daiku.domain.infra.model.param.GoalDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveUpdatingFlgServiceInput {
    Long goalId;
    LocalDate goalCreateDate;
    Long accountId;

    public TGoalArchive toEntity(TGoalArchive oldData) {
        TGoalArchive entity = new TGoalArchive();
        entity.setId(oldData.getId());
        entity.setUpdatingFlg(UpdatingFlg.ARCHIVE_UPDATING);
        return entity;
    }
}
