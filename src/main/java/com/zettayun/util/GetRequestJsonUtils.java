package com.zettayun.util;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.requestParamEntity.QueryData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * request 对象的相关操作
 * @author zhangtengda
 * @version 1.0
 * @created 2015年5月2日 下午8:25:43
 */
public class GetRequestJsonUtils {

    private final static Logger logger = LoggerFactory.getLogger(GetRequestJsonUtils.class);

    public static QueryData getParameterMap(HttpServletRequest request) {
        Map map = request.getParameterMap();
        QueryData queryData = new QueryData();
        if (map != null) {
            Set set = map.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (entry.getValue() instanceof String[]) {
                    logger.info("==A==entry的key： " + entry.getKey());
                    String key = (String) entry.getKey();
                    if (key != null && !"id".equals(key) && key.startsWith("[") && key.endsWith("]")) {
                        break;
                    }
                    String[] values = (String[]) entry.getValue();
                    for (int i = 0; i < values.length; i++) {
                        logger.info("==B==entry的value: " + values[i]);
                        //key += "="+values[i];
                        if ("token".equals(key)){
                            queryData.setToken(values[i]);
                            break;
                        }
                        if ("setType".equals(key)) {
                            queryData.setSetType(Integer.parseInt(values[i]));
                            break;
                        }
                        if ("startRow".equals(key)) {
                            queryData.setStartRow(Integer.parseInt(values[i]));
                            break;
                        }
                        if ("pageSize".equals(key)) {
                            queryData.setPageSize(Integer.parseInt(values[i]));
                            break;
                        }
                        if ("sortSet".equals(key)) {
                            queryData.setSortSet(JSONObject.parseObject(values[i]));
                            break;
                        }
                    }
                    if (key.startsWith("[") && key.endsWith("]")) {
                        break;
                    }
                } else if (entry.getValue() instanceof String) {
                    logger.info("==========entry的key： " + entry.getKey());
                    logger.info("==========entry的value: " + entry.getValue());
                }
            }
        }
        return queryData;
    }
}
