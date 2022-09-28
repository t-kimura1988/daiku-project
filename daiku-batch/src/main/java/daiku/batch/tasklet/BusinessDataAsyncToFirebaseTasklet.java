package daiku.batch.tasklet;

import daiku.batch.service.BusinessDataAsyncToFirebaseService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessDataAsyncToFirebaseTasklet implements Tasklet {

    @Autowired
    BusinessDataAsyncToFirebaseService businessDataAsyncToFirebaseService;
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        businessDataAsyncToFirebaseService.asyncBusinessDataFirebase();
        return RepeatStatus.FINISHED;
    }
}
