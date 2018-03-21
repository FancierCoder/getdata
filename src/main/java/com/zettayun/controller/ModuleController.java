package com.zettayun.controller;

import com.zettayun.api.DataSetApi;
import com.zettayun.api.requestParamEntity.RequestGetElectProCon;
import com.zettayun.api.responseParamEntity.ResponseRow;
import com.zettayun.common.RestResponse;
import com.zettayun.common.StatusCode;
import com.zettayun.service.MongoDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(value = "ModuleController.API", tags = { "ModuleController 接口" }, description = "ModuleController相关Api")
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Resource
    private DataSetApi dataSetApi;

    @Resource
    private MongoDbService mongoDbService;

//    @ApiOperation(value = "构建单个数列", notes = "构建单个数列", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @RequestMapping(value = "/report/getElectProCon2", method = RequestMethod.POST)
//    public RestResponse<List<Map<String, Object>>> getElectProCon2(@RequestParam Long dateTime,
//                                                          @RequestParam String areaNames){
//        RestResponse<List<Map<String, Object>>> response = new RestResponse<>();
//        String[] areaName = areaNames.split("&");
//        ArrayList<RequestGetElectProCon> electProCons = new ArrayList<>();
//        for (int i = 0; i < areaName.length; i ++){
//            RequestGetElectProCon electProCon = new RequestGetElectProCon();
//            electProCon.setAreaNames(areaName[i]);
//            electProCon.setDataTime(dateTime);
//            electProCons.add(electProCon);
//        }
//        //List<ResponseRow> rows = dataSetApi.getElectProCon(electProCons, dateTime);
//        List<Map<String, Object>> rows = dataSetApi.getElectProCon2(electProCons, dateTime);
//        response.setCode(StatusCode.OK.code());
//        response.setMessage(StatusCode.OK.message());
//        response.setResult(rows);
//        response.setTotal((long) rows.size());
//        return response;
//    }

    @ApiOperation(value = "构建单个数列", notes = "构建单个数列", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/report/getElectProCon", method = RequestMethod.POST)
    public RestResponse<List<ResponseRow>> getElectProCon(@RequestBody RequestGetElectProCon request){
        RestResponse<List<ResponseRow>> response = new RestResponse<>();
        try {
            Assert.notNull(request.getAreaNames(), "areaNames不能为空");
            Assert.notNull(request.getDataTime(), "dataTime不能为空");
            String[] areaName = request.getAreaNames().split("&");
            ArrayList<String> areaNames = new ArrayList<>(Arrays.asList(areaName));
            List<ResponseRow> rows = dataSetApi.getElectProCon(areaNames, request.getDataTime());
            response.setCode(StatusCode.OK.code());
            response.setMessage(StatusCode.OK.message());
            response.setResult(rows);
            response.setTotal((long) rows.size());
        } catch (Exception e) {
            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
