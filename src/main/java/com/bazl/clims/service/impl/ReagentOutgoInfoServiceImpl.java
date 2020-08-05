package com.bazl.clims.service.impl;

import com.bazl.clims.dao.ReagentOutgoInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.ReagentInfo;
import com.bazl.clims.model.po.ReagentOutgoInfo;
import com.bazl.clims.service.ReagentOutgoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Sun on 2018/12/20.
 */
@Service
public class ReagentOutgoInfoServiceImpl extends BaseService implements ReagentOutgoInfoService {

    @Autowired
    private ReagentOutgoInfoMapper reagentOutgoInfoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return reagentOutgoInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ReagentOutgoInfo record) {
        return reagentOutgoInfoMapper.insert(record);
    }

    @Override
    public ReagentOutgoInfo selectByPrimaryKey(String id) {
        return reagentOutgoInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ReagentOutgoInfo> selectAll() {
        return reagentOutgoInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ReagentOutgoInfo record) {
        return reagentOutgoInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ReagentOutgoInfo> selectByRecordType(String recordType,String orgId) {
        return reagentOutgoInfoMapper.selectByRecordType(recordType,orgId);
    }

    @Override
    public List<ReagentOutgoInfo> selectInList(ReagentOutgoInfo record, PageInfo pageInfo) {
        List<ReagentOutgoInfo> reagentOutgoInfos = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            record.setOffset((pageNo - 1) * pageSize);
            record.setRows(record.getOffset() + pageSize);
            reagentOutgoInfos= reagentOutgoInfoMapper.selectInList(record);
        } catch(Exception ex) {
            logger.info("查询试剂入库信息报错："+ex);
            return null;
        }

        return reagentOutgoInfos;
    }

    @Override
    public List<ReagentOutgoInfo> selectOutList(ReagentOutgoInfo record, PageInfo pageInfo) {
        List<ReagentOutgoInfo> reagentOutgoInfos = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            record.setOffset((pageNo - 1) * pageSize);
            record.setRows(record.getOffset() + pageSize);
            reagentOutgoInfos= reagentOutgoInfoMapper.selectOutList(record);
        } catch(Exception ex) {
            logger.info("查询试剂入库信息报错："+ex);
            return null;
        }

        return reagentOutgoInfos;
    }

    @Override
    public List<ReagentOutgoInfo> selectOutInList(ReagentOutgoInfo record, PageInfo pageInfo) {
        List<ReagentOutgoInfo> reagentOutgoInfos = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            record.setOffset((pageNo - 1) * pageSize);
            record.setRows(record.getOffset() + pageSize);
            reagentOutgoInfos= reagentOutgoInfoMapper.selectOutInList(record);
        } catch(Exception ex) {
            logger.info("查询试剂入库信息报错："+ex);
            return null;
        }

        return reagentOutgoInfos;
    }

    @Override
    public List<ReagentOutgoInfo> selectOrgId(ReagentOutgoInfo record) {
        return reagentOutgoInfoMapper.selectOrgId(record);
    }

    @Override
    public int selectCount(ReagentOutgoInfo record) {
        return reagentOutgoInfoMapper.selectCount(record);
    }

    @Override
    public List<ReagentOutgoInfo> selectByStorageNum(ReagentOutgoInfo record) {
        return reagentOutgoInfoMapper.selectByStorageNum(record);
    }


    /**
     * 根据试剂名称，所属单位查询入库信息
     */
    @Override
    public List<ReagentOutgoInfo> selectreagentOutgoList(ReagentOutgoInfo reagentOutgoInfo) {
        return reagentOutgoInfoMapper.selectreagentOutgoList(reagentOutgoInfo);
    }

    /**
     * 修改试剂名称
     * @param reagentOutgoInfo
     */
    @Override
    public int updateByReagentOutgoInfo(ReagentOutgoInfo reagentOutgoInfo) {
        return reagentOutgoInfoMapper.updateByReagentOutgoInfo(reagentOutgoInfo);
    }

}
