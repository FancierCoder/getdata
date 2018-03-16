package com.zetta.fancier.getdata.controller;

import com.zetta.fancier.getdata.api.DataSetApi;
import com.zetta.fancier.getdata.common.RestResponse;
import com.zetta.fancier.getdata.common.StatusCode;
import com.zetta.fancier.getdata.entity.RShuLie;
import com.zetta.fancier.getdata.entity.ShuLie;
import com.zetta.fancier.getdata.requestParamEntity.QueryData;
import com.zetta.fancier.getdata.service.MongoDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "LdDataSetController.API", tags = { "LdDataSetController 接口" }, description = "LdDataSetController相关Api")
@RestController
@RequestMapping("/system")
public class LdDataSetController {
    @Resource
    private DataSetApi dataSetApi;

    @Resource
    private MongoDbService mongoDbService;

    private final Logger log = LoggerFactory.getLogger(LdDataSetController.class);

    @ApiOperation(value = "查询数列", notes = "查询数列信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiModelProperty(name = "queryData", value = "查询参数", dataType = "com.zetta.fancier.getdata.requestParamEntity.QueryData")
    @RequestMapping(value = "/query/queryDataSet", method = RequestMethod.POST)
    public RestResponse<List<RShuLie>> queryDataSet(@RequestBody QueryData queryData){
        RestResponse<List<RShuLie>> response = new RestResponse<>();
        List<RShuLie> shuLies = dataSetApi.queryDataSet(queryData.getToken(),
                queryData.getSetType(),
                queryData.getStartRow(),
                queryData.getPageSize(),
                queryData.getSortSet());
        ShuLie shuLie = new ShuLie();
        log.info(shuLie.toString());
        shuLie.setToken(queryData.getToken());
        long count = mongoDbService.count(shuLie);
        response.setCode(StatusCode.OK.code());
        response.setMessage(StatusCode.OK.message());
        response.setResult(shuLies);
        response.setTotal(count);
        return response;
    }
}
