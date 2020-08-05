package com.bazl.clims.dao;

import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.AmPersonalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaUserInfoMapper {
    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    public LoaUserInfo selectByUserName(@Param("loginName") String loginName,@Param("areaCode") String areaCode);
    public LoaUserInfo selectByUserName2(@Param("loginName") String loginName);

    void addLoaUserInfo(LoaUserInfo loaUserInfo);

    List<LoaUserInfo> queryloaUserInfoByPersonalId(String personalId);

    LoaUserInfo queryloaUserInfoByuserId(String userid);

    void deleteloaUserInfoByUserId(String userId);

    void updateloaUserInfoByUserId(AmPersonalInfo amPersonalInfo);

    void forbiddenLoaUserInfo(@Param("personalId") String personalId, @Param("useractiveflase") String user_active_flase);

    void startusingLoaUserInfo(@Param("personalId")String personalId,  @Param("useractiveflase") String user_active_true);
}