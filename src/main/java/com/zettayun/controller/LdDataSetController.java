package com.zettayun.controller;

import com.zettayun.api.DataSetApi;
import com.zettayun.common.RestResponse;
import com.zettayun.common.StatusCode;
import com.zettayun.entity.RShuLie;
import com.zettayun.entity.ShuLie;
import com.zettayun.requestParamEntity.QueryData;
import com.zettayun.service.MongoDbService;
import com.zettayun.util.GetRequestJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "LdDataSetController.API", tags = { "LdDataSetController 接口" }, description = "LdDataSetController相关Api")
@RestController
@RequestMapping("/system")
public class LdDataSetController {
    @Resource
    private DataSetApi dataSetApi;

    @Resource
    private MongoDbService mongoDbService;

    private final Logger log = LoggerFactory.getLogger(LdDataSetController.class);

    @ApiOperation(value = "查询数列", notes = "查询数列信息", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiModelProperty(name = "queryData", value = "查询参数", dataType = "QueryData")
    @RequestMapping(value = "/query/queryValueSet", method = RequestMethod.POST)
    public RestResponse<List<RShuLie>> queryDataSet(@Valid @RequestBody QueryData queryData) throws IOException {
        RestResponse<List<RShuLie>> response = new RestResponse<>();
        List<RShuLie> shuLies = dataSetApi.queryDataSet(queryData.getToken(),
                queryData.getSetType(),
                queryData.getStartRow(),
                queryData.getPageSize(),
                queryData.getSortSet());
        ShuLie shuLie = new ShuLie();
        shuLie.setToken(queryData.getToken());
        log.info(shuLie.toString());
        long count = mongoDbService.count(shuLie);
        response.setCode(StatusCode.OK.code());
        response.setMessage(StatusCode.OK.message());
        response.setResult(shuLies);
        response.setTotal(count);
        return response;
    }

    @ApiOperation(value = "查询数列", notes = "查询数列信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiModelProperty(name = "queryData", value = "查询参数", dataType = "QueryData")
    @RequestMapping(value = "/query/queryValueSet2", method = RequestMethod.POST)
    public RestResponse<List<RShuLie>> queryDataSet2(HttpServletRequest request){
        RestResponse<List<RShuLie>> response = new RestResponse<>();
        QueryData queryData = GetRequestJsonUtils.getParameterMap(request);
        try {
            Assert.notNull(queryData.getToken(), "token不能为空");
            Assert.notNull(queryData.getSetType(), "setType不能为空");
            System.out.println(queryData);
            List<RShuLie> shuLies = dataSetApi.queryDataSet(queryData.getToken(),
                    queryData.getSetType(),
                    queryData.getStartRow(),
                    queryData.getPageSize(),
                    queryData.getSortSet());
            ShuLie shuLie = new ShuLie();
            shuLie.setToken(queryData.getToken());
            log.info(shuLie.toString());
            long count = mongoDbService.count(shuLie);
            response.setCode(StatusCode.OK.code());
            response.setMessage(StatusCode.OK.message());
            response.setResult(shuLies);
            response.setTotal(count);
        } catch (IllegalArgumentException e) {
            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/modify/importValueSet", method = RequestMethod.POST)
    public RestResponse<Map<String ,Long>> importDataSet(@RequestParam String token,
                                                    @RequestParam Object value,
                                                    @RequestParam Long date,
                                                    @RequestParam Integer isReplace,
                                                    @RequestParam Integer setType){
        RestResponse<Map<String, Long>> response = new RestResponse<>();
        HashMap<String, Long> map = new HashMap<>();
        if (setType == 1){
            ShuLie shuLie = new ShuLie();
            shuLie.setToken(token);
            shuLie.setValue(Double.valueOf(value.toString()));
            shuLie.setDate(new Date(date));
            long count = 1;
            if (isReplace == 1) {
                ShuLie criteria = new ShuLie();
                criteria.setToken(token);
                criteria.setDate(shuLie.getDate());
                count = mongoDbService.count(criteria);
                mongoDbService.update(criteria, shuLie);
                map.put("updated", count);
                if (count == 0) {
                    mongoDbService.insert(shuLie);
                    map.put("inserted", 1L);
                }
            }
            else {
                mongoDbService.insert(shuLie);
                map.put("inserted", 1L);
            }
            response.setTotal(count);
        }
        response.setCode(StatusCode.OK.code());
        response.setMessage(StatusCode.OK.message());

        response.setResult(map);
        return response;
    }

}
