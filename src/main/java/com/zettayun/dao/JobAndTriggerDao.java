package com.zettayun.dao;

import com.zettayun.entity.JobAndTrigger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAndTriggerDao {

    List<JobAndTrigger> getJobAndTriggerDetails();
}
