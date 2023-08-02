package com.study.MyBatch.study1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StudyOneJobConfiguration {
	@Bean
	public Job studyOneJob(JobRepository jobRepository, Step studyOneStep) {
		return new JobBuilder("studyOneJob", jobRepository)
			.start(studyOneStep)
			.build();
	}
	@Bean
	public Step studyOneStep(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager){
		return new StepBuilder("studyOneStep", jobRepository)
			.tasklet(testTasklet, platformTransactionManager).build();
	}
	@Bean
	public Tasklet testTasklet2(){
		return ((contribution, chunkContext) -> {
			log.info("배치 Study 첫번째 성공");
			return RepeatStatus.FINISHED;
		});
	}

}
