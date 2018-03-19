package com.zettayun.service;

import com.github.pagehelper.PageInfo;
import com.zettayun.entity.JobAndTrigger;

public interface JobService {

    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);

}
