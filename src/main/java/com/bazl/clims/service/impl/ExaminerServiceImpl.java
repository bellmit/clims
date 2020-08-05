package com.bazl.clims.service.impl;

import com.bazl.clims.dao.ExaminerMapper;
import com.bazl.clims.model.po.Examiner;
import com.bazl.clims.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class ExaminerServiceImpl extends BaseService implements ExaminerService {

    @Autowired
    ExaminerMapper examinerMapper;


    @Override
    public int insert(Examiner examiner) {
        return examinerMapper.insert(examiner);
    }

    @Override
    public List<Examiner> queryExaminer(String caseId) {
        return examinerMapper.queryExaminer(caseId);
    }

    @Override
    public int update(Examiner examiner) {
        return examinerMapper.update(examiner);
    }

    @Override
    public List<Examiner> selectListByCaseId(String caseId) {
        return examinerMapper.selectListByCaseId(caseId);
    }
}
