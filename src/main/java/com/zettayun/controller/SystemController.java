package com.zettayun.controller;

import com.zettayun.api.DataSetApi;
import com.zettayun.api.requestParamEntity.*;
import com.zettayun.common.RestResponse;
import com.zettayun.common.StatusCode;
import com.zettayun.entity.RShuLie;
import com.zettayun.entity.ShuLie;
import com.zettayun.service.MongoDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Api(value = "LdDataSetController.API", tags = { "LdDataSetController 接口" }, description = "LdDataSetController相关Api")
@RestController
@RequestMapping("/system")
public class SystemController {
    @Resource
    private DataSetApi dataSetApi;

    @Resource
    private MongoDbService mongoDbService;

    private final Logger log = LoggerFactory.getLogger(SystemController.class);

    @ApiOperation(value = "根据token查询数列中所有的点", notes = "根据token查询数列中所有的点", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiModelProperty(name = "requestData", value = "查询参数", dataType = "RequestData")
    @RequestMapping(value = "/query/queryValueSet", method = RequestMethod.POST)
    public RestResponse<List<RShuLie>> queryDataSet(@RequestBody RequestData requestData) {
        RestResponse<List<RShuLie>> response = new RestResponse<>();
        try {
            Assert.notNull(requestData.getToken(), "token不能为空");
            Assert.notNull(requestData.getSetType(), "setType不能为空");
            List<RShuLie> shuLies = dataSetApi.queryDataSet(requestData.getToken(),
                    requestData.getSetType(),
                    requestData.getStartRow(),
                    requestData.getPageSize(),
                    requestData.getSortSet());
            ShuLie shuLie = new ShuLie();
            shuLie.setToken(requestData.getToken());
            log.info(shuLie.toString());
            long count = mongoDbService.count(shuLie, "shulie");
            response.setCode(StatusCode.OK.code());
            response.setMessage(StatusCode.OK.message());
            response.setResult(shuLies);
            response.setTotal(count);
        } catch (Exception e) {
            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "根据token和数据点时间区间查询数据", notes = "根据token和数据点时间区间查询数据", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/query/queryValueSetByTime", method = RequestMethod.POST)
    public RestResponse<List<RShuLie>> queryValueSetByTime(@RequestBody RequestValueSetByTime request){
        RestResponse<List<RShuLie>> response = new RestResponse<>();
        try {
            Assert.notNull(request, "请提交参数");
            Assert.notNull(request.getToken(), "token不能为空");
            Assert.notNull(request.getSetType(), "setType不能为空");
            List<RShuLie> shuLies = dataSetApi.queryValueSetByTime(request);
            response.setCode(StatusCode.OK.code());
            response.setMessage(StatusCode.OK.message());
            response.setTotal((long) shuLies.size());
            response.setResult(shuLies);
        } catch (Exception e) {
            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
            if(e.getMessage() != null)
                response.setMessage(e.getMessage());
            else {
                response.setMessage(e.toString());
            }
        }
        return response;
    }

//    @ApiOperation(value = "根据token查询数列中所有的点", notes = "根据token查询数列中所有的点", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiModelProperty(name = "queryData2", value = "查询参数", dataType = "RequestData")
//    @RequestMapping(value = "/query/queryValueSet2", method = RequestMethod.POST)
//    public RestResponse<List<RShuLie>> queryDataSet2(HttpServletRequest request){
//        RestResponse<List<RShuLie>> response = new RestResponse<>();
//        RequestData requestData = GetRequestJsonUtils.getParameterMap(request);
//        try {
//            Assert.notNull(requestData.getToken(), "token不能为空");
//            Assert.notNull(requestData.getSetType(), "setType不能为空");
//            System.out.println(requestData);
//            List<RShuLie> shuLies = dataSetApi.queryDataSet(requestData.getToken(),
//                    requestData.getSetType(),
//                    requestData.getStartRow(),
//                    requestData.getPageSize(),
//                    requestData.getSortSet());
//            ShuLie shuLie = new ShuLie();
//            shuLie.setToken(requestData.getToken());
//            log.info(shuLie.toString());
//            long count = mongoDbService.count(shuLie);
//            response.setCode(StatusCode.OK.code());
//            response.setMessage(StatusCode.OK.message());
//            response.setResult(shuLies);
//            response.setTotal(count);
//        } catch (IllegalArgumentException e) {
//            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
//            response.setMessage(e.getMessage());
//        }
//        return response;
//    }

    @ApiOperation(value = "将单个数据点插入Mongo集合中", notes = "将单个数据点插入Mongo集合中", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @RequestMapping(value = "/modify/importValueSet", method = RequestMethod.POST)
    public RestResponse<Map<String ,Long>> importDataSet(@RequestParam String token,
                                                    @RequestParam Object value,
                                                    @RequestParam Long date,
                                                    @RequestParam Integer isReplace,
                                                    @RequestParam Integer setType){
        RestResponse<Map<String, Long>> response = new RestResponse<>();
        Map<String, Long> map = dataSetApi.importDataSet(token, value, new Date(date), isReplace, setType);
        ShuLie criteria = new ShuLie();
        criteria.setToken(token);
        criteria.setDate(new Date(date));
        if (isReplace == 1)
            response.setTotal(mongoDbService.count(criteria, "shulie"));
        else
            response.setTotal(1L);
        response.setCode(StatusCode.OK.code());
        response.setMessage(StatusCode.OK.message());
        response.setResult(map);
        return response;
    }

    @ApiOperation(value = "将单个数据点插入Mongo集合中", notes = "将单个数据点插入Mongo集合中", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @RequestMapping(value = "/modify/importValueSet2", method = RequestMethod.POST)
    public RestResponse<Map<String ,Long>> importDataSet2(@RequestBody RequestValueSet request){
        RestResponse<Map<String, Long>> response = new RestResponse<>();
        RequestValue requestValue = request.getRequestValue();
        Map<String, Long> map = dataSetApi.importDataSet(requestValue.getToken(),
                requestValue.getValue(), requestValue.getDate(),
                request.getIsReplace(), request.getSetType());
        ShuLie criteria = new ShuLie();
        criteria.setToken(requestValue.getToken());
        criteria.setDate(requestValue.getDate());
        if (request.getIsReplace() == 1)
            response.setTotal(mongoDbService.count(criteria, "shulie"));
        else
            response.setTotal(1L);
        response.setCode(StatusCode.OK.code());
        response.setMessage(StatusCode.OK.message());
        response.setResult(map);
        return response;
    }

    @ApiOperation(value = "批量插入数据点", notes = "批量插入数据点", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/modify/importValueSetBatches", method = RequestMethod.POST)
    public RestResponse<Map<String, Long>> importValueSetBatches(@RequestBody RequestValueSetBatches request){
        RestResponse<Map<String, Long>> response = new RestResponse<>();
        try {
            Assert.notNull(request.getValueRows(), "valueRows不能为空");
            Assert.notNull(request.getIsReplace(), "isReplace不能为空");
            Assert.notNull(request.getSetType(), "setType不能为空");
            Map<String, Long> batches = dataSetApi.importValueSetBatches(request.getValueRows(), request.getIsReplace(), request.getSetType());
            response.setCode(StatusCode.OK.code());
            response.setMessage(StatusCode.OK.message());
            response.setResult(batches);
            Long updated = batches.get("updated");
            Long inserted = batches.get("inserted");
            response.setTotal(updated + inserted);
        } catch (Exception e) {
            response.setCode(StatusCode.INVALID_PARAMS_CONVERSION.code());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "创建Collection", notes = "创建Collection", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/modify/createCollection", method = RequestMethod.POST)
    public RestResponse<String> createCollection(@RequestBody RequestCreateCollection createCollection){
        RestResponse<String> response = new RestResponse<>();
        try {
            Assert.notNull(createCollection.getCollectionName(), "CollectionName不能为空");
            Assert.notNull(createCollection.getIndexSet(), "indexSet不能为空");
            Map<String, String> map = dataSetApi.createCollection(createCollection);
            String result = map.get("result");
            if (result.equals("success")){
                response.setCode(StatusCode.OK.code());
                response.setMessage(StatusCode.OK.message());
                response.setResult(result);
            }else {
                response.setCode(StatusCode.SERVER_UNKNOWN_ERROR.code());
                response.setMessage(result);
            }
        } catch (Exception e) {
            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "构建单个数列", notes = "构建单个数列", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/modify/buildDataSet", method = RequestMethod.POST)
    public RestResponse<Map<String, String>> buildDataSet(@RequestBody RequestDataSet request){
        RestResponse<Map<String, String>> response = new RestResponse<>();
        HashMap<String, String> map = new HashMap<>();
        try {
            Assert.notNull(request.getDataSetName(), "dataSetName不能为空");
            Assert.notNull(request.getDataSource(), "dataSource不能为空");
            Assert.notNull(request.getDataSetName(), "period不能为空");
            Assert.notNull(request.getDataSetName(), "valueUnit不能为空");
            Assert.notNull(request.getDataSetName(), "setType不能为空");
            String token = dataSetApi.buildDataSet(request);
            if (token != null){
                map.put("token", token);
                response.setCode(StatusCode.OK.code());
                response.setMessage(StatusCode.OK.message());
                response.setResult(map);
                response.setTotal(1L);
            }else {
                response.setCode(StatusCode.SERVER_UNKNOWN_ERROR.code());
                response.setMessage("构建失败");
            }
        } catch (Exception e) {
            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "批量构建数列", notes = "批量构建数列", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/modify/buildDataSetBatches", method = RequestMethod.POST)
    public RestResponse<Map<String, String>> buildDataSetBatches(@RequestBody List<RequestDataSet> request){
        RestResponse<Map<String, String>> response = new RestResponse<>();
        try {
            for (RequestDataSet dataSet : request){
                Assert.notNull(dataSet.getDataSetName(), "dataSetName不能为空");
                Assert.notNull(dataSet.getDataSource(), "dataSource不能为空");
                Assert.notNull(dataSet.getDataSetName(), "period不能为空");
                Assert.notNull(dataSet.getDataSetName(), "valueUnit不能为空");
                Assert.notNull(dataSet.getDataSetName(), "setType不能为空");
            }
            Map<String, String> map = dataSetApi.buildDataSetBatches(request);
            if (map.size() > 0){
                response.setCode(StatusCode.OK.code());
                response.setMessage(StatusCode.OK.message());
                response.setTotal((long) request.size());
                response.setResult(map);
            }else {
                response.setCode(StatusCode.SERVER_UNKNOWN_ERROR.code());
                response.setMessage("构建失败");
            }
        } catch (Exception e) {
            response.setCode(StatusCode.INVALID_MODEL_FIELDS.code());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
