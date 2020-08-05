package com.bazl.clims.service.impl;

import com.bazl.clims.dao.ReagentInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.ReagentInfo;
import com.bazl.clims.service.ReagentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Sun on 2019/04/02.
 */
@Service
public class ReagentInfoServiceImpl extends BaseService implements ReagentInfoService {


    @Autowired
    private ReagentInfoMapper reagentInfoMapper;


    @Override
    public void addReagenInfo(ReagentInfo reagentInfo) {
        reagentInfoMapper.addReagenInfo(reagentInfo);

    }

    @Override
    public List<ReagentInfo> selectPaginationList(ReagentInfo reagentInfo) {
        return reagentInfoMapper.selectPaginationList(reagentInfo);
    }

    @Override
    public ReagentInfo selectByReagentName(String reagentName) {
        return reagentInfoMapper.selectByReagentName(reagentName);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return reagentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ReagentInfo record) {
        return reagentInfoMapper.insert(record);
    }

    @Override
    public ReagentInfo selectByPrimaryKey(String id) {
        return reagentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ReagentInfo> selectAll() {
        return reagentInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ReagentInfo record) {
        return reagentInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ReagentInfo> selectList(ReagentInfo reagentInfo) {
        return reagentInfoMapper.selectList(reagentInfo);
    }

    @Override
    public List<ReagentInfo> selectOrgId(ReagentInfo reagentInfo, PageInfo pageInfo) {
        List<ReagentInfo> reagentInfoList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            reagentInfo.setOffset((pageNo - 1) * pageSize);
            reagentInfo.setRows(reagentInfo.getOffset() + pageSize);

            reagentInfoList = reagentInfoMapper.selectOrgId(reagentInfo);

        } catch(Exception ex) {
            logger.info("查询试剂名称信息报错："+ex);
            return null;
        }

        return reagentInfoList;
    }

    @Override
    public int selectCount(ReagentInfo reagentInfo) {
        return reagentInfoMapper.selectCount(reagentInfo);
    }

    /**
     * 修改最新的试剂盒信息
     * @param reagentInfo
     */
    @Override
    public int  updateByReagentInfo(ReagentInfo reagentInfo) {
       return reagentInfoMapper.updateByReagentInfo(reagentInfo);
    }
}
