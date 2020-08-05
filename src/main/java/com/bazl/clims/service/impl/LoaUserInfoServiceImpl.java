package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LoaUserInfoMapper;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.AmPersonalInfo;
import com.bazl.clims.service.LoaUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2018/12/20.
 */
@Service
public class LoaUserInfoServiceImpl implements LoaUserInfoService {

    @Autowired
    LoaUserInfoMapper loaUserInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LoaUserInfo selectByUserName(String loginName,String areaCode) {
         return loaUserInfoMapper.selectByUserName(loginName,areaCode);
//        String userkey = loginName  + ":" + areaCode;
//        LoaUserInfo user = (LoaUserInfo) redisTemplate.boundHashOps("loausers").get(userkey);
//        if(user==null){
//            System.out.println("没有此用户的数据，将此用户的数据放入缓存");
//            user = loaUserInfoMapper.selectByUserName(loginName,areaCode);
//            redisTemplate.boundHashOps("loausers").put(userkey,user);
//        }else {
//            System.out.println("从缓从中读取用户的数据");
//        }
//
//        return user;
    }


    @Override
    public LoaUserInfo selectByUserName2(String loginName) {
        return loaUserInfoMapper.selectByUserName2(loginName);
//        String userkey = loginName  + ":" + areaCode;
//        LoaUserInfo user = (LoaUserInfo) redisTemplate.boundHashOps("loausers").get(userkey);
//        if(user==null){
//            System.out.println("没有此用户的数据，将此用户的数据放入缓存");
//            user = loaUserInfoMapper.selectByUserName(loginName,areaCode);
//            redisTemplate.boundHashOps("loausers").put(userkey,user);
//        }else {
//            System.out.println("从缓从中读取用户的数据");
//        }
//
//        return user;
    }

    @Override
    public void addLoaUserInfo(LoaUserInfo loaUserInfo) {
        loaUserInfoMapper.addLoaUserInfo(loaUserInfo);
    }

    @Override
    public List<LoaUserInfo> queryloaUserInfoByPersonalId(String personalId) {
        return loaUserInfoMapper.queryloaUserInfoByPersonalId(personalId);
    }

    @Override
    public LoaUserInfo queryloaUserInfoByuserId(String userid) {
        return loaUserInfoMapper.queryloaUserInfoByuserId(userid);
    }

    @Override
    public void deleteloaUserInfoByUserId(String userId) {
        loaUserInfoMapper.deleteloaUserInfoByUserId(userId);
    }

    @Override
    public void updateloaUserInfoByUserId(AmPersonalInfo amPersonalInfo) {
        loaUserInfoMapper.updateloaUserInfoByUserId(amPersonalInfo);
    }

    @Override
    public void forbiddenLoaUserInfo(String personalId, String user_active_flase) {
        loaUserInfoMapper.forbiddenLoaUserInfo(personalId,user_active_flase);
    }

    @Override
    public void startusingLoaUserInfo(String personalId, String user_active_true) {
        loaUserInfoMapper.startusingLoaUserInfo(personalId,user_active_true);
    }


}
