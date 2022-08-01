package daiku.batch.config;

import daiku.batch.service.BusinessDataAsyncToFirebaseService;
import daiku.batch.tasklet.BusinessDataAsyncToFirebaseTasklet;
import daiku.domain.infra.model.param.firestore.BusinessDataAsync;
import daiku.domain.infra.repository.FirebaseRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
public class FirebaseAsyncBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BusinessDataAsyncToFirebaseTasklet tasklet1;

    @Autowired
    private JobLauncher jobLauncher;
    @Scheduled(cron = "*/5 * * * * *")
    public void testTask() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("businessDataAsyncFirebaseJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution exe = jobLauncher.run(job1(), params);

    }

    public Job job1() {
        return jobBuilderFactory.get("businessDataAsyncFirebaseJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(tasklet1)
                .build();
    }
}
