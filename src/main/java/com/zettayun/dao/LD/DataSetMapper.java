package com.zettayun.dao.LD;

import com.zettayun.entity.LD.DataSet;

import java.util.List;

public interface DataSetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataSet record);

    int insertSelective(DataSet record);

    DataSet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSet record);

    int updateByPrimaryKey(DataSet record);
    
    DataSet selectByToken(String token);

	List<DataSet> selectListBySetName(String areaName);
}