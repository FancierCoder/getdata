package com.zetta.fancier.getdata.service;

import com.alibaba.fastjson.JSONObject;
import com.zetta.fancier.getdata.entity.RShuLie;
import com.zetta.fancier.getdata.entity.ShuLie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MongoDbService {
    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLie
     */
    void insert(ShuLie ShuLie);

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param ShuLies
     */
    void insertAll(List<ShuLie> ShuLies);

    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 按条件删除
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     */
    void delete(ShuLie criteriaShuLie);

    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll();

//    /**
////     * 修改
////     * <br>------------------------------<br>
////     *
////     * @param ShuLie
////     */
////    void updateById(ShuLie ShuLie);

    /**
     * 更新多条
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @param ShuLie
     */
    void update(ShuLie criteriaShuLie, ShuLie ShuLie);

    /**
     * 根据主键查询
     * <br>------------------------------<br>
     *
     * @param id
     * @return
     */
    ShuLie findById(String id);

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie 查询条件
     * @return
     */
    List<ShuLie> findByCondition(ShuLie criteriaShuLie);

    /**
     * 查询全部
     * <br>------------------------------<br>
     *
     * @return
     */
    List<ShuLie> findAll();

    /**
     * 按条件查询
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @param skip
     * @param limit
     * @return
     */
    List<RShuLie> findByConditionAndOrderBy(ShuLie criteriaShuLie, Integer skip, Integer limit, JSONObject sortSet);

    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie 查询条件
     * @param updateShuLie   修改的值对象
     * @return
     */
    ShuLie findAndModify(ShuLie criteriaShuLie, ShuLie updateShuLie);

    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @return
     */
    ShuLie findAndRemove(ShuLie criteriaShuLie);

    /**
     * count
     * <br>------------------------------<br>
     *
     * @param criteriaShuLie
     * @return
     */
    long count(ShuLie criteriaShuLie);
}
