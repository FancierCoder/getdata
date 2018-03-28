package com.zettayun.mongo;

import java.util.List;

/**
 * Mongo数据库的统一接口
 * @author LD
 *
 * @param <T>
 */
public interface MongoDbDao<T> {
    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param document
     */
    void insert(T document, String collectionName);

    /**
     * 新增或更新，有重复的Id则会更新
     * <br>------------------------------<br>
     *
     * @param document
     */
    void save(T document, String collectionName);

    /**
     * 插入所有
     * <br>------------------------------<br>
     *
     * @param documents
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
     * @param criteriadocument
     */
    void delete(T criteria, String collectionName);

    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll(String collectionName);

    /**
     * 根据ID修改
     * <br>------------------------------<br>
     *
     * @param document
     */
    void updateById(String id, T document, String collectionName);

    /**
     * 根据条件查询出来所有后 再去修改
     * <br>------------------------------<br>
     *
     * @param criteria 查询条件
     * @param update   修改的值对象
     * @return
     */
    void update(T criteria, T update, String collectionName);

    /**
     * 根据主键查询
     * <br>------------------------------<br>
     *
     * @param id
     * @return
     */
    T findById(String id, String collectionName);

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
     * @param criteria
     * @param skip
     * @param limit
     * @return
     */
    List<T> find(T criteria, int skip, int limit, String collectionName);

    /**
     * 根据条件查询
     * <br>------------------------------<br>
     *
     * @param criteria 查询条件
     * @return
     */
    List<T> findByCondition(T criteria, String collectionName);

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteria 查询条件
     * @param updatedocument   修改的值对象
     * @return
     */
    T findAndModify(T criteria, T update, String collectionName);

    
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     *
     * @param criteria
     * @return
     */
    T findAndRemove(T criteria, String collectionName);

    /**
     * count根据条件查询出数量
     * <br>------------------------------<br>
     *
     * @param criteria
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
