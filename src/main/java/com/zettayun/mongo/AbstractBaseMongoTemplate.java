package com.zettayun.mongo;

import org.springframework.beans.BeansException;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.ApplicationContextAware;  
import org.springframework.data.mongodb.core.MongoTemplate;  

/**
 * 创建MongoTemplate,操作mongo数据库
 * @author LD
 *
 */
public abstract class AbstractBaseMongoTemplate implements  
        ApplicationContextAware {  
  
    protected MongoTemplate mongoTemplate;  
  
    /** 
     * @Description 根据配置文件设置mongoTemplate 
     * @param mongoTemplate 
     */  
    public void setMongoTemplate(MongoTemplate mongoTemplate) {  
        this.mongoTemplate = mongoTemplate;  
    }  
  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
        MongoTemplate mongoTemplate = applicationContext.getBean(  
                "mongoTemplate", MongoTemplate.class);  
        setMongoTemplate(mongoTemplate);  
    }  
} 
