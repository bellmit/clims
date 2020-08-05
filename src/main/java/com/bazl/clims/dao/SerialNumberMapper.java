package com.bazl.clims.dao;

import com.bazl.clims.model.po.SerialNumber;
import org.apache.ibatis.annotations.Param;

public interface SerialNumberMapper {
    int insert(SerialNumber record);

    SerialNumber selectNextVal(@Param("year") String year, @Param("typeCode")String typeCode, @Param("orgId")String orgId);

    int update(SerialNumber record);

    /**
     * 获取流水号
     * @param orgId
     * @return
     */
    SerialNumber selectNextVals(@Param("typeCode")String typeCode, @Param("orgId")String orgId);
    /**
     * 修改流水号
     * @param year
     * @param typeCode
     * @param orgId
     * @return
     */
    int updateYear(@Param("year") String year, @Param("typeCode")String typeCode, @Param("orgId")String orgId);

}