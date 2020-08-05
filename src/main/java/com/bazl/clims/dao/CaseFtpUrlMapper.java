package com.bazl.clims.dao;

import com.bazl.clims.model.po.caseFtpUrl;
import java.util.List;

public interface CaseFtpUrlMapper {
    int deleteByPrimaryKey(String id);

    int insert(caseFtpUrl record);

    caseFtpUrl selectByPrimaryKey(String id);

    List<caseFtpUrl> selectAll();

    int updateByPrimaryKey(caseFtpUrl record);

    List<caseFtpUrl> findCaseIdByTyye(caseFtpUrl caseFtpUrlEneity);
}