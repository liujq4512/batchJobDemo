package com.starter;


import com.confinguration.ProcessorBaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by jinqiang.liu on 17-4-27.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.*")
@EnableScheduling
public class ProcessorStarter {
    public static void main(String[] args){
        SpringApplication.run(ProcessorStarter.class);

    }
}
