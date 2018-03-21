package com.zettayun.controller;

import com.zettayun.api.DataSetApi;
import com.zettayun.api.requestParamEntity.RequestGetElectProCon;
import com.zettayun.api.responseParamEntity.ResponseRow;
import com.zettayun.common.RestResponse;
import com.zettayun.service.MongoDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(value = "ModuleController.API", tags = { "ModuleController 接口" }, description = "ModuleController相关Api")
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Resource
    private DataSetApi dataSetApi;

    @Resource
    private MongoDbService mongoDbService;

    @ApiOperation(value = "构建单个数列", notes = "构建单个数列", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/report/getElectProCon", method = RequestMethod.POST)
    public RestResponse<List<ResponseRow>> getElectProCon(@RequestParam Long dateTime,
                                                          @RequestParam String areaNames){
        RestResponse<List<ResponseRow>> response = new RestResponse<>();
        String[] areaName = areaNames.split("&");
        ArrayList<RequestGetElectProCon> electProCons = new ArrayList<>();
        for (int i = 0; i < areaName.length; i ++){
            RequestGetElectProCon electProCon = new RequestGetElectProCon();
            electProCon.setAreaNames(areaName[i]);
            electProCon.setDataTime(dateTime);
            electProCons.add(electProCon);
        }
        List<ResponseRow> rows = dataSetApi.getElectProCon(electProCons);
        return response;
    }
}
