package com.bazl.clims.service.impl;

import com.bazl.clims.dao.QueueDetailMapper;
import com.bazl.clims.model.po.QueueDetail;
import com.bazl.clims.service.QueueDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2019/4/9.
 */
@Service
public class QueueDetailServiceImpl implements QueueDetailService {

    @Autowired
    QueueDetailMapper queueDetailMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return queueDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(QueueDetail record) {
        return queueDetailMapper.insert(record);
    }

    @Override
    public QueueDetail selectByPrimaryKey(String id) {
        return queueDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<QueueDetail> selectAll() {
        return queueDetailMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(QueueDetail record) {
        return queueDetailMapper.updateByPrimaryKey(record);
    }
}
