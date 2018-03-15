package com.zetta.fancier.getdata.service;

import com.github.pagehelper.PageInfo;
import com.zetta.fancier.getdata.entity.JobAndTrigger;

public interface JobService {

    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);

}
