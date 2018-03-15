package com.zetta.fancier.getdata;

import com.github.pagehelper.PageInfo;
import com.zetta.fancier.getdata.entity.JobAndTrigger;
import com.zetta.fancier.getdata.job.JobControl;
import com.zetta.fancier.getdata.method.GetExcelInfo;
import com.zetta.fancier.getdata.service.MongoDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
//@MapperScan("com.zetta.fancier.getdata.dao")
public class GetDataTest {

    @Resource
    private GetExcelInfo getExcelInfo;

    @Resource
    private MongoDbService mongoDbService;

    @Resource
    private JobControl jobControl;

    @Test
    public void test1() {
        getExcelInfo.getDataFromExcelFilePath("D:\\liduo_profile\\证券市场");
    }

    @Test
    public void test2() {
        getExcelInfo.getDataFromExcelFile("D:\\liduo_profile\\证券市场\\证券公司统计\\Y证券营业部数量：爱建证券.xlsx");
    }

    @Test
    public void test3() {
        long count = mongoDbService.count(null);
        System.out.println(count);
    }

    @Test
    public void test4() {
        Map<String, Object> job = jobControl.queryjob(1, 5);
        PageInfo<JobAndTrigger> jobAndTrigger = (PageInfo<JobAndTrigger>) job.get("JobAndTrigger");
        List<JobAndTrigger> list = jobAndTrigger.getList();
        System.out.println(list.get(0));
        System.out.println(job.get("number"));
    }

    @Test
    public void test5() {
        long i = 5l;
        String.valueOf(i);
    }
}
