package com.bazl.clims.service.impl;

import com.bazl.clims.dao.FugitivesInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.FugitivesInfo;
import com.bazl.clims.model.vo.FugitivesInfoVo;
import com.bazl.clims.service.FugitivesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huawei
 * @date 2020/6/15.
 */
@Service
public class FugitivesInfoServiceImpl extends BaseService implements FugitivesInfoService {

    @Autowired
    FugitivesInfoMapper fugitivesInfoMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return fugitivesInfoMapper.deleteByPrimaryKey(id);
        }catch(Exception e){
            e.getStackTrace();
            logger.error(id + "删除失败！");
            return 0;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insert(FugitivesInfo record) {
        try {
            return fugitivesInfoMapper.insert(record);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("插入失败！");
            return 0;
        }
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public FugitivesInfo selectByPrimaryKey(String id) {
        try {
            return fugitivesInfoMapper.selectByPrimaryKey(id);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error(id + "查询失败！");
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public List<FugitivesInfo> selectAll() {
        try {
            return fugitivesInfoMapper.selectAll();
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("查询失败！");
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateByPrimaryKey(FugitivesInfo record) {
        try {
            return fugitivesInfoMapper.updateByPrimaryKey(record);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("更新失败！");
            return 0;
        }
    }

    @Override
    public List<FugitivesInfoVo> selectVOList(FugitivesInfoVo query, PageInfo pageInfo) {

        try {

            int pageNo = pageInfo.getPage();
            int pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            return fugitivesInfoMapper.selectVOList(query);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("分页查询报错"+ e);
            return null;
        }
    }

    @Override
    public int selectVOCnt(FugitivesInfoVo query) {
        try {
            return fugitivesInfoMapper.selectVOCnt(query);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("查询总数报错！"+ e);
            return 0;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteFugitivesInfo(FugitivesInfo fugitivesInfo) {
        try {
            fugitivesInfoMapper.deleteFugitivesInfo(fugitivesInfo);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("删除信息报错！", e);
        }
    }

    @Override
    public List<FugitivesInfoVo> selectFugitivesList(String searchFugitives) {
        try {
            return fugitivesInfoMapper.selectFugitivesList(searchFugitives);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("查询信息失败！", e);
            return null;
        }
    }

}
