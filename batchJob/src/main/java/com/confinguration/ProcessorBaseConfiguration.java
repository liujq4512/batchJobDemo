package com.confinguration;

import com.entity.Person;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by jinqiang.liu on 17-4-27.
 */

@Configuration
@EnableScheduling
@EnableAsync
@EnableBatchProcessing
@ComponentScan(basePackageClasses = ProcessorBaseConfiguration.class)
public class ProcessorBaseConfiguration {


    @Bean
    public Person person(){
        return new Person();
    }

    @Bean
    public DriverManagerDataSource dataSourceForJobRepository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//10.18.19.33:1521/dbdev2");
        dataSource.setUsername("sl_main");
        dataSource.setPassword("sl_main");
        return dataSource;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
