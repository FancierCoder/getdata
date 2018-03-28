package com.zettayun.mongo;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.api.requestParamEntity.RequestValueSetByTime;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MongoDbService<T> {
    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLie
     */
    void insert(T document, String collectionName);

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLie
     */
    void save(T document, String collectionName);

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLies
     */
    void insertAll(List<T> documents, String collectionName);

    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     *
     * @param id
     */
    void deleteById(String id, String collectionName);

    /**
     * 按条件删除
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     */
    void delete(T criteria, String collectionName);

    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll(String collectionName);

//    /**
////     * 修改
////     * <br>------------------------------<br>
////     *
////     * @param ShuLie
////     */
////    void updateById(ShuLie document, String collectionName);

    /**
     * 更新多条
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @param ShuLie
     */
    void update(T criteria, T document, String collectionName);

    /**
     * 根据主键查询
     * <br>------------------------------<br>
     *
     * @param id
     * @return
     */
    T findById(String id, String collectionName);

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie 查询条件
     * @return
     */
    List<T> findByCondition(T criteria, String collectionName);

    /**
     * 查询全部
     * <br>------------------------------<br>
     *
     * @return
     */
    List<T> findAll(String collectionName);

    /**
     * 按条件查询
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @param skip
     * @param limit
     * @return
     */
    List<T> findByConditionAndOrderBy(T criteria, Integer skip, Integer limit, JSONObject sortSet, String collectionName);

    /**
     * 根据时间查询
     * @param request
     * @param collectionName
     * @return
     */
    List<T> findByRequest(RequestValueSetByTime request, String collectionName);

    
    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie 查询条件
     * @param updateShuLie   修改的值对象
     * @return
     */
    T findAndModify(T criteria, T update, String collectionName);

    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @return
     */
    T findAndRemove(T criteria, String collectionName);

    /**
     * count
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @return
     */
    long count(T criteria, String collectionName);

    /**
     * 是否存在collection
     * @param collectionName
     * @return
     */
    boolean isExistCollection(String collectionName);

    /**
     * 创建collection
     * @param collectionName
     * @return
     */
    boolean createCollection(String collectionName);

    /**
     * 创建Index
     * @param collectionName
     * @param filedNames
     * @return
     */
    boolean createIndex(String collectionName, List<String> filedNames);
}
