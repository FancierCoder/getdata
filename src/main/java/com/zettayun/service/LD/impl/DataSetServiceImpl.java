package com.zettayun.service.LD.impl;

import com.zettayun.dao.LD.DataSetMapper;
import com.zettayun.entity.LD.DataSet;
import com.zettayun.service.LD.DataSetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李铎
 * @since 2018-03-14
 */
@Service
@Transactional
public class DataSetServiceImpl implements DataSetService {

    @Resource
    private DataSetMapper dataSetDao;

    @Override
    public DataSet selectByToken(String token) {
        return dataSetDao.selectByToken(token);
    }

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return dataSetDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DataSet record) {
		// TODO Auto-generated method stub
		return dataSetDao.insert(record);
	}

	@Override
	public int insertSelective(DataSet record) {
		// TODO Auto-generated method stub
		return dataSetDao.insertSelective(record);
	}

	@Override
	public DataSet selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return dataSetDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DataSet record) {
		// TODO Auto-generated method stub
		return dataSetDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DataSet record) {
		// TODO Auto-generated method stub
		return dataSetDao.updateByPrimaryKey(record);
	}

	@Override
	public boolean updateBatchById(List<DataSet> dataSets) {
		// TODO Auto-generated method stub
		for (DataSet dataSet : dataSets) {
			int update = updateByPrimaryKey(dataSet);
			if (update < 0)
				return false;
		}
		return true;
	}

	@Override
	public boolean insertBatch(List<DataSet> dataSets) {
		// TODO Auto-generated method stub
		for (DataSet dataSet : dataSets) {
			int insert = insertSelective(dataSet);
			if (insert < 0)
				return false;
		}
		return true;
	}

	@Override
	public List<DataSet> selectListBySetName(String areaName) {
		// TODO Auto-generated method stub
		return dataSetDao.selectListBySetName("%"+areaName+"%");
	}
}
