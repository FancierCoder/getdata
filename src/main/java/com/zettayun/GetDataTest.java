package com.zettayun;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zettayun.api.requestParamEntity.RequestData;
import com.zettayun.api.requestParamEntity.RequestValueSetByTime;
import com.zettayun.controller.SystemController;
import com.zettayun.entity.JobAndTrigger;
import com.zettayun.entity.RShuLie;
import com.zettayun.entity.ShuLie;
import com.zettayun.job.JobControl;
import com.zettayun.method.GetExcelInfo;
import com.zettayun.method.GetExcelInfoFromOld;
import com.zettayun.service.MongoDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
//@MapperScan("com.zetta.fancier.getdata.dao")
public class GetDataTest {

    @Resource
    private GetExcelInfo getExcelInfo;

    @Resource
    private GetExcelInfoFromOld getExcelInfoFromOld;

    @Resource
    private MongoDbService mongoDbService;

    @Resource
    private JobControl jobControl;

    @Resource
    private SystemController systemController;

    @Test
    public void test1() {
        getExcelInfoFromOld.getDataFromExcelFilePath("D:\\liduo_profile\\证券市场");
    }

    @Test
    public void test2() {
        getExcelInfo.getDataFromExcelFile("D:\\liduo_profile\\电力电量完成情况（月）.xlsx");
    }

    @Test
    public void test3() {
        long count = mongoDbService.count(null, "shulie");
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
        List<RShuLie> shuLieList = mongoDbService.findByConditionAndOrderBy(shuLie, 0, 5, jsonObject, "shulie");
        System.out.println(Arrays.toString(shuLieList.toArray()));
    }

    @Test
    public void test6(){
        RequestData queryData = new RequestData();
        queryData.setToken("479ea6da-8a78-471d-8051-73e449de68b3");
        queryData.setSetType(1);
        queryData.setStartRow(0);
        queryData.setPageSize(100);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //RestResponse<List<RShuLie>> response = ldDataSetController.queryDataSet(queryData.getToken(), queryData.getSetType(), queryData.getStartRow(), queryData.getPageSize(), queryData.getSortSet());
                    //List<RShuLie> result = response.getResult();
                    //System.out.println(result.size());
                }
            }).start();
        }
    }

    @Test
    public void test7(){
        boolean flag = mongoDbService.createCollection("test");
        ArrayList<String> list = new ArrayList<>();
        list.add("token");
        boolean test = mongoDbService.createIndex("test", list);

    }

    @Test
    public void test8(){
        ShuLie shuLie = new ShuLie();
        shuLie.setDate(new Date(1417392000000L));
        List<ShuLie> lieList = mongoDbService.findByCondition(shuLie, "shulie");
        System.out.println(lieList);
    }

    @Test
    public void test9() throws ParseException {
        RequestValueSetByTime setByTime = new RequestValueSetByTime();
        setByTime.setToken("658475a253a04c18ad5559a00944daff");
        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时区为格林尼治时间，处理mongodb时间减8个小时的错误
        format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
        setByTime.setStartTime(format.parse("2018-01-27 00:00:00").getTime());
        setByTime.setEndTime(format.parse("2018-02-06 00:00:00").getTime());
        setByTime.setSetType(1);
        List<ShuLie> shulie = mongoDbService.findByRequest(setByTime, "shulie");
        System.out.println(Arrays.toString(shulie.toArray()));
    }
}
