package com.bazl.clims.service;

import com.bazl.clims.model.po.Examiner;
import com.bazl.clims.model.po.Panel;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface ExaminerService {

    /**
     * 插入信息
     * @param examiner
     * @return
     */
    public int insert(Examiner examiner);
    /**
     * 查询案件检验人
     * @param caseId
     * @return
     */
    List<Examiner> queryExaminer(String caseId);

    /**
     * 更新信息
     * @param examiner
     * @return
     */
    public int update(Examiner examiner);

    /**
     * 根据案件id查询信息
     * @param caseId
     * @return
     */
    public List<Examiner> selectListByCaseId(String caseId);
}
