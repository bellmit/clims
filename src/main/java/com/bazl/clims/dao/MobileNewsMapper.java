package com.bazl.clims.dao;

import com.bazl.clims.model.po.MobileNews;

import java.util.HashMap;
import java.util.List;

public interface MobileNewsMapper {
    int deleteByPrimaryKey(String id);

    int insert(MobileNews record);

    MobileNews selectByPrimaryKey(String id);

    List<MobileNews> selectAll();

    int updateByPrimaryKey(MobileNews record);

    HashMap getUserMobileNewsNumber(String id);

    List<MobileNews> selectPageAll(MobileNews mobileNews);

    int selectPageCount (MobileNews mobileNews);

    int selectCount (MobileNews mobileNews);

    List<MobileNews> selectPcList(MobileNews mobileNews);

    List<MobileNews> getUserNewsList(MobileNews mobileNews);
}