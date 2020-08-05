package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabAnalysisInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LabAnalysisInfo;
import com.bazl.clims.model.po.ReviewQueueSample;
import com.bazl.clims.model.vo.LabAnalysisInfoVo;
import com.bazl.clims.service.LabAnalysisInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
@Service
public class LabAnalysisInfoServiceImpl extends BaseService implements LabAnalysisInfoService {

    @Autowired
    LabAnalysisInfoMapper labAnalysisInfoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return labAnalysisInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LabAnalysisInfo record) {
        return labAnalysisInfoMapper.insert(record);
    }

    @Override
    public LabAnalysisInfo selectByPrimaryKey(String id) {
        return labAnalysisInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LabAnalysisInfo> selectAll() {
        return labAnalysisInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabAnalysisInfo record) {
        return labAnalysisInfoMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询上样板
     * @param query
     * @param pageInfo
     * @return
     */
    @Override
    public List<LabAnalysisInfoVo> selectLabAnalysisInfoList(LabAnalysisInfoVo query, PageInfo pageInfo) {
        List<LabAnalysisInfoVo> labAnalysisInfoVoList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            labAnalysisInfoVoList = labAnalysisInfoMapper.selectVOPaginationList(query);
        } catch(Exception ex) {
            logger.info("查询与补送报错："+ex);
            return null;
        }

        return labAnalysisInfoVoList;
    }

    /**
     * 查询上样板count
     * @param labAnalysisInfoVo
     * @return
     */
    @Override
    public int selectLabAnalysisInfoCount(LabAnalysisInfoVo labAnalysisInfoVo) {
        return labAnalysisInfoMapper.selectLabAnalysisInfoCount(labAnalysisInfoVo);
    }

    /**
     * 确认审核并上报
     * @param labAnalysisInfo
     */
    @Override
    public void updateuSatus(LabAnalysisInfo labAnalysisInfo) {
        labAnalysisInfoMapper.updateuSatus(labAnalysisInfo);
    }

    @Override
    public List<LabAnalysisInfoVo> selectVOByBoardNo(LabAnalysisInfoVo labAnalysisInfoVo) {
        return labAnalysisInfoMapper.selectVOByBoardNo(labAnalysisInfoVo);
    }

}
