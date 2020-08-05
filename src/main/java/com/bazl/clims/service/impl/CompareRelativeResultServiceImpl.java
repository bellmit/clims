package com.bazl.clims.service.impl;

import com.bazl.clims.dao.CompareRelativeResultMapper;
import com.bazl.clims.model.po.CompareRelativeResult;
import com.bazl.clims.service.CompareRelativeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/4/19.
 */
@Service
public class CompareRelativeResultServiceImpl extends BaseService implements CompareRelativeResultService {

    @Autowired
    CompareRelativeResultMapper compareRelativeResultMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) {
        return compareRelativeResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(CompareRelativeResult record) {
        return compareRelativeResultMapper.insert(record);
    }

    @Override
    public CompareRelativeResult selectByPrimaryKey(String id) {
        return compareRelativeResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CompareRelativeResult> selectAll() {
        return compareRelativeResultMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateByPrimaryKey(CompareRelativeResult record) {
        return compareRelativeResultMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByCaseId(String caseId) {
        return compareRelativeResultMapper.deleteByCaseId(caseId);
    }

    @Override
    public List<CompareRelativeResult> selectListByCaseId(String caseId) {
        return compareRelativeResultMapper.selectListByCaseId(caseId);
    }

}
