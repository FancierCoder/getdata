package com.zettayun.job;

import com.zettayun.method.GetExcelInfo;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class GetDataJob implements BaseJob {
    @Resource
    private GetExcelInfo getExcelInfo;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        getExcelInfo.getDataFromExcelFilePath("");
    }
}
