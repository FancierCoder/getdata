package com.zetta.fancier.getdata.service;

import com.zetta.fancier.getdata.entity.DataSet;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李铎
 * @since 2018-03-14
 */
@Transactional
public interface DataSetService extends IService<DataSet> {

}
