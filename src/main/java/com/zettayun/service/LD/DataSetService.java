package com.zettayun.service.LD;

import com.zettayun.entity.LD.DataSet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李铎
 * @since 2018-03-14
 */
@Transactional
public interface DataSetService {

	int deleteByPrimaryKey(Long id);

    int insert(DataSet record);

    int insertSelective(DataSet record);

    DataSet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSet record);

    int updateByPrimaryKey(DataSet record);
    
    boolean updateBatchById(List<DataSet> dataSets);
    
    boolean insertBatch(List<DataSet> dataSets);
	
    DataSet selectByToken(String token);

	List<DataSet> selectListBySetName(String areaName);
}
