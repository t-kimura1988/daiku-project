package daiku.app.service.input.goal;

import daiku.domain.infra.entity.TGoalArchive;
import daiku.domain.infra.enums.PublishLevel;
import daiku.domain.infra.enums.UpdatingFlg;
import daiku.domain.infra.model.param.GoalArchiveDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveUpdateServiceInput {
    Long archiveId;
    LocalDate archiveCreateDate;
    String thoughts;
    PublishLevel publish;

    public TGoalArchive toUpdateEntity(TGoalArchive oldData) {
        TGoalArchive entity = new TGoalArchive();
        entity.setId(archiveId);
        entity.setThoughts(thoughts);
        entity.setPublish(publish);
        entity.setUpdatingFlg(UpdatingFlg.ARCHIVING);
        return entity;
    }

    public GoalArchiveDaoParam toArchiveRepositoryParam() {
        return GoalArchiveDaoParam.builder()
                .archiveId(archiveId)
                .archiveCreateDate(archiveCreateDate).build();
    }
}
