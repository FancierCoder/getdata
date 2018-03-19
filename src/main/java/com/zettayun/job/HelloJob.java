package com.zettayun.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("HelloJob执行时间：" + new Date());
    }
}
