package com.bazl.clims.service.impl;

import com.bazl.clims.dao.ComporeSameQueueMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.vo.ComporeSameQueueVo;
import com.bazl.clims.service.ComporeSameQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huawei on 2019/8/6.
 */
@Service
public class ComporeSameQueueServiceImpl extends BaseService implements ComporeSameQueueService {

    @Autowired
    ComporeSameQueueMapper comporeSameQueueMapper;

    public List<ComporeSameQueueVo> selectVOPaginationList(ComporeSameQueueVo query, PageInfo pageInfo) {
        List<ComporeSameQueueVo> comporeSameQueueVoList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            comporeSameQueueVoList = comporeSameQueueMapper.selectVOPaginationList(query);
        } catch(Exception ex) {
            logger.info("查询同一比对队列报错："+ex);
            return null;
        }

        return comporeSameQueueVoList;
    }

    public int selectVOCount(ComporeSameQueueVo query) {
        return comporeSameQueueMapper.selectVOCount(query);
    }

}
