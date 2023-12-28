/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class MyJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("JOB is about to start");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("JOB is finished " + jobExecution.getStatus().toString());
	}

}
