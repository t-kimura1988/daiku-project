package daiku.app.service.input.goal;

import daiku.domain.infra.entity.TGoalArchive;
import daiku.domain.infra.enums.PublishLevel;
import daiku.domain.infra.enums.UpdatingFlg;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveServiceInput {
    Long goalId;
    LocalDate createDate;
    Long accountId;
    String thoughts;
    PublishLevel publish;

    public TGoalArchive toEntity() {
        TGoalArchive entity = new TGoalArchive();
        entity.setGoalId(goalId);
        entity.setArchivesCreateDate(LocalDate.now());
        entity.setUpdatingFlg(UpdatingFlg.ARCHIVING);
        entity.setThoughts(thoughts);
        entity.setPublish(publish);
        return entity;
    }

    public TGoalArchive toNextDataEntity(Long archiveId) {
        TGoalArchive entity = new TGoalArchive();
        entity.setId(archiveId);
        entity.setGoalId(goalId);
        entity.setArchivesCreateDate(LocalDate.now());
        entity.setUpdatingFlg(UpdatingFlg.ARCHIVING);
        entity.setThoughts(thoughts);
        entity.setPublish(publish);
        return entity;

    }
}
