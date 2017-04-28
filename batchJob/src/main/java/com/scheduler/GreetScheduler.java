package com.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by jinqiang.liu on 17-4-27.
 */

@Component
public class GreetScheduler {
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private JobLauncher jobLauncher;
    @Resource(name = "greetJob")
    private Job greetJob;


    @Scheduled(cron="*/10 * * * * *")
    public void doGreet(){
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>>>>>>>>>start to do greet job <<<<<<<<<<<<<<<<<<<<");
                try {
                    jobLauncher.run(greetJob,new JobParameters());
                } catch (JobExecutionAlreadyRunningException e) {
                    e.printStackTrace();
                } catch (JobRestartException e) {
                    e.printStackTrace();
                } catch (JobInstanceAlreadyCompleteException e) {
                    e.printStackTrace();
                } catch (JobParametersInvalidException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
