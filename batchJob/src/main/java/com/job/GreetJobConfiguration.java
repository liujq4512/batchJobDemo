package com.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;

/**
 * Created by jinqiang.liu on 17-4-27.
 */
@Configuration
public class GreetJobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory ;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private JobRepository jobRepository ;


    @Bean
    public Step greetStep(){
        return stepBuilderFactory.get("greetStep")
                .allowStartIfComplete(true)
                .tasklet((contribution, chunkContext) -> {
            System.out.println("==================hello world ===================");
            contribution.setExitStatus(ExitStatus.COMPLETED);
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Job greetJob(){
        return jobBuilderFactory.get("greetJob").start(greetStep()).build();
    }
}
