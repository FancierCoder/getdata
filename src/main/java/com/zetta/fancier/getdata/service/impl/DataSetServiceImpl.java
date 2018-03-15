package com.zetta.fancier.getdata.service.impl;

import com.zetta.fancier.getdata.entity.DataSet;
import com.zetta.fancier.getdata.dao.DataSetDao;
import com.zetta.fancier.getdata.service.DataSetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
