package com.zettayun.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zettayun.entity.DataSet;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李铎
 * @since 2018-03-14
 */

public interface DataSetDao extends BaseMapper<DataSet> {

    DataSet selectByToken(String token);
}
