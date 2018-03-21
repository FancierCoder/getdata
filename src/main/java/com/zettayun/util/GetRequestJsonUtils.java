package com.zettayun.util;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.api.requestParamEntity.RequestData;
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

    public static RequestData getParameterMap(HttpServletRequest request) {
        Map map = request.getParameterMap();
        RequestData requestData = new RequestData();
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
                            requestData.setToken(values[i]);
                            break;
                        }
                        if ("setType".equals(key)) {
                            requestData.setSetType(Integer.parseInt(values[i]));
                            break;
                        }
                        if ("startRow".equals(key)) {
                            requestData.setStartRow(Integer.parseInt(values[i]));
                            break;
                        }
                        if ("pageSize".equals(key)) {
                            requestData.setPageSize(Integer.parseInt(values[i]));
                            break;
                        }
                        if ("sortSet".equals(key)) {
                            requestData.setSortSet(JSONObject.parseObject(values[i]));
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
        return requestData;
    }
}
