package com.zettayun.api;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.api.requestParamEntity.*;
import com.zettayun.entity.LD.RShuLie;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ValueSetAPI接口
 * @author LD
 *	
 */
public interface DataSetApi {

	/**
	 * 根据token查询数列中所有的点
	 * @param token
	 * @param setType
	 * @param startRow
	 * @param pageSize
	 * @param sortSets
	 * @return
	 */
    List<RShuLie> queryValueSet(String token, Integer setType, Integer startRow, Integer pageSize, JSONObject sortSets);

    /**
     * 根据token和数据点时间区间查询数据
     * @param request
     * @return
     */
    List<RShuLie> queryValueSetByTime(RequestValueSetByTime request);
    
    /**
     * 将单个数据点插入Mongo集合中
     * @param token
     * @param value
     * @param date
     * @param isReplace
     * @param setType
     * @return
     */
    Map<String, Long> importValueSet(String token, Object value, Date date, Integer isReplace, Integer setType);

    /**
     * 批量插入数据点
     * @param shuLies
     * @param isReplace
     * @param setType
     * @return
     */
    Map<String, Long> importValueSetBatches(List<Set> shuLies, Integer isReplace, Integer setType);

    /**
     * 创建MonGoDb集合
     * @param collection
     * @return
     */
    Map<String, String> createCollection(RequestCreateCollection collection);

    /**
     * 构建单个数列
     * @param dataSet
     * @return
     */
    String buildDataSet(RequestDataSet dataSet);

    /**
     * 批量构建数列
     * @param dataSets
     * @return
     */
    Map<String, String> buildDataSetBatches(List<RequestDataSet> dataSets);

//    List<ResponseRow> getElectProCon(List<String> request, Date dataTime);

//    List<Map<String, Object>> getElectProCon2(List<RequestGetElectProCon> request, Long dataTime);

//    /**
//     * 批量插入报表
//     * @param request
//     * @return
//     */
//    Map<String, Long> importReportSetBatches(RequestReportSetBatches request);
}
