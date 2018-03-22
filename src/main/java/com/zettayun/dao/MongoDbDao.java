package com.zettayun.dao;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.api.requestParamEntity.RequestValueSetByTime;
import com.zettayun.entity.ShuLie;

import java.util.List;

public interface MongoDbDao {
    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLie
     */
    void insert(ShuLie ShuLie, String collectionName);

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLie
     */
    void save(ShuLie ShuLie, String collectionName);

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLies
     */
    void insertAll(List<ShuLie> ShuLies, String collectionName);

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
    void delete(ShuLie criteriaShuLie, String collectionName);

    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll(String collectionName);

//    /**
//     * 修改
//     * <br>------------------------------<br>
//     *
//     * @param ShuLie
//     */
//    void updateById(ShuLie ShuLie, String collectionName);

    /**
     * 更新多条
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @param ShuLie
     */
    void update(ShuLie criteriaShuLie, ShuLie ShuLie, String collectionName);

    /**
     * 根据时间查询
     * @param request
     * @param collectionName
     * @return
     */
    List<ShuLie> findByRequest(RequestValueSetByTime request, String collectionName);

    /**
     * 根据主键查询
     * <br>------------------------------<br>
     *
     * @param id
     * @return
     */
    ShuLie findById(String id, String collectionName);

    /**
     * 查询全部
     * <br>------------------------------<br>
     *
     * @return
     */
    List<ShuLie> findAll(String collectionName);

    /**
     * 按条件查询
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @param skip
     * @param limit
     * @return
     */
    List<ShuLie> find(ShuLie criteriaShuLie, int skip, int limit, String collectionName);

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie 查询条件
     * @return
     */

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie 查询条件
     * @return
     */
    List<ShuLie> findByCondition(ShuLie criteriaShuLie, String collectionName);

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie 查询条件
     * @param updateShuLie   修改的值对象
     * @return
     */


    ShuLie findAndModify(ShuLie criteriaShuLie, ShuLie updateShuLie, String collectionName);

    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @return
     */
    ShuLie findAndRemove(ShuLie criteriaShuLie, String collectionName);

    /**
     * count
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @return
     */
    long count(ShuLie criteriaShuLie, String collectionName);

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @return
     */
    List<ShuLie> findByConditionAndOrderBy(ShuLie criteriaShuLie, Integer skip, Integer limit, JSONObject sortSet, String collectionName);


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
    boolean createIndex(String collectionName ,List<String> filedNames);
}
