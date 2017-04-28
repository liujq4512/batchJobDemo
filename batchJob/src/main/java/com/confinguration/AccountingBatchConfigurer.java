package com.confinguration;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Sherwood Wang on 1/24/17.
 */
@Component
public class AccountingBatchConfigurer implements BatchConfigurer {

    private final DataSource dataSource;
    private boolean initialized;

    private PlatformTransactionManager transactionManager;
    private JobRepository jobRepository;
    private JobLauncher jobLauncher;
    private JobExplorer jobExplorer;

    @Autowired
    public AccountingBatchConfigurer(
            @Qualifier("dataSourceForJobRepository") DataSource dataSource
    ) {
        this.dataSource = dataSource;
    }

    @Override
    public JobRepository getJobRepository() throws Exception {
        initialize();
        return jobRepository;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
        initialize();
        return transactionManager;
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {
        initialize();
        return jobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
        initialize();
        return jobExplorer;
    }

    private void initialize() throws Exception {
        synchronized (this) {
            if (!initialized) {
                transactionManager = new DataSourceTransactionManager(dataSource);

                JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
                jobRepositoryFactoryBean.setDataSource(dataSource);
                jobRepositoryFactoryBean.setTransactionManager(transactionManager);
                jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
                jobRepositoryFactoryBean.setTablePrefix("GLBATCH_");
                jobRepositoryFactoryBean.afterPropertiesSet();
                this.jobRepository = jobRepositoryFactoryBean.getObject();

                JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
                jobExplorerFactoryBean.setDataSource(dataSource);
                jobExplorerFactoryBean.setTablePrefix("GLBATCH_");
                jobExplorerFactoryBean.afterPropertiesSet();
                this.jobExplorer = jobExplorerFactoryBean.getObject();

                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                jobLauncher.setJobRepository(jobRepository);
                jobLauncher.afterPropertiesSet();
                this.jobLauncher = jobLauncher;

                initialized = true;
            }
        }
    }
}
