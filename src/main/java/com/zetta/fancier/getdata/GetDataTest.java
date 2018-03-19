package com.zetta.fancier.getdata;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zetta.fancier.getdata.common.RestResponse;
import com.zetta.fancier.getdata.controller.LdDataSetController;
import com.zetta.fancier.getdata.entity.JobAndTrigger;
import com.zetta.fancier.getdata.entity.RShuLie;
import com.zetta.fancier.getdata.entity.ShuLie;
import com.zetta.fancier.getdata.job.JobControl;
import com.zetta.fancier.getdata.method.GetExcelInfo;
import com.zetta.fancier.getdata.requestParamEntity.QueryData;
import com.zetta.fancier.getdata.service.MongoDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
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

    @Resource
    private LdDataSetController ldDataSetController;

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
        ShuLie shuLie = new ShuLie();
        shuLie.setToken("a0f5652e-2f20-4e05-9abc-6e38d27696c0");
        HashMap<String, Object> map = new HashMap<>();
        map.put("value", 1);
        JSONObject jsonObject = new JSONObject(map);
        List<RShuLie> shuLieList = mongoDbService.findByConditionAndOrderBy(shuLie, 0, 5, jsonObject);
        System.out.println(Arrays.toString(shuLieList.toArray()));
    }

    @Test
    public void test6(){
        QueryData queryData = new QueryData();
        queryData.setToken("479ea6da-8a78-471d-8051-73e449de68b3");
        queryData.setSetType(1);
        queryData.setStartRow(0);
        queryData.setPageSize(100);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RestResponse<List<RShuLie>> response = ldDataSetController.queryDataSet(queryData.getToken(), queryData.getSetType(), queryData.getStartRow(), queryData.getPageSize(), queryData.getSortSet());
                    List<RShuLie> result = response.getResult();
                    System.out.println(result.size());
                }
            }).start();
        }
    }
}
