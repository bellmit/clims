package com.bazl.clims.service;

import com.bazl.clims.model.po.MatchRelativeLib;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRelativeLibService {
    int deleteByPrimaryKey(String id);

    int insert(MatchRelativeLib record);

    MatchRelativeLib selectByPrimaryKey(String id);

    List<MatchRelativeLib> selectAll();

    int updateByPrimaryKey(MatchRelativeLib record);

    MatchRelativeLib selectBySampleaId(String sampleaid);

    MatchRelativeLib selectBySampleId(String sampleid);

    int updateBySampleaId(MatchRelativeLib record);

    /**
     * 分页查询亲缘比对列表
     * @return
     */
    public List<MatchRelativeLib> selectLibByPage(int page, int size);
}