package com.zettayun.service.impl;

import com.zettayun.entity.DataSet;
import com.zettayun.dao.DataSetDao;
import com.zettayun.service.DataSetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
public class DataSetServiceImpl extends ServiceImpl<DataSetDao, DataSet> implements DataSetService {

    @Resource
    private DataSetDao dataSetDao;

    @Override
    public DataSet selectByToken(String token) {
        return dataSetDao.selectByToken(token);
    }
}
