package com.bazl.clims.dao;

import com.bazl.clims.model.po.MatchRelativeLib;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchRelativeLibMapper {
    int deleteByPrimaryKey(String id);

    int insert(MatchRelativeLib record);

    MatchRelativeLib selectByPrimaryKey(String id);

    List<MatchRelativeLib> selectAll();

    int updateByPrimaryKey(MatchRelativeLib record);

    MatchRelativeLib selectBySampleaId(String sampleAid);

    MatchRelativeLib selectBySampleId(String sampleid);

    int updateBySampleaId(MatchRelativeLib record);

    public List<MatchRelativeLib> selectLibByPage(@Param("startRum")int startRum, @Param("endRum")int endRum);
}