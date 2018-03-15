package com.zetta.fancier.getdata.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job {
    void execute(JobExecutionContext context) throws JobExecutionException;
}
