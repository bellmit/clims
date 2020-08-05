package com.bazl.clims.service.impl;

import com.bazl.clims.dao.DictInfoMapper;
import com.bazl.clims.model.DictInfo;
import com.bazl.clims.service.DictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hj on 2018/12/20.
 */
@Service
public class DictInfoServiceImpl implements DictInfoService {

    @Autowired
    DictInfoMapper dictInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int deleteByPrimaryKey(String dictInfoId) {
//        redisTemplate.boundHashOps("dict").delete(dictInfoId);
        return dictInfoMapper.deleteByPrimaryKey(dictInfoId);
    }

    @Override
    public int insert(DictInfo record) {
//        redisTemplate.boundHashOps("dict").put(record.getDictInfoId(),record);
        return dictInfoMapper.insert(record);
    }

    @Override
    public DictInfo selectByPrimaryKey(String dictInfoId) {
        return dictInfoMapper.selectByPrimaryKey(dictInfoId);
    }

    @Override
    public List<DictInfo> selectAll() {
        return dictInfoMapper.selectAll();
//        List<DictInfo> dicts = (List<DictInfo>)redisTemplate.boundHashOps("dict").values();
//        if(dicts.size()==0){
//            System.out.println("缓从没有字典管理的数据，字典管理数据加入缓存");
//            dicts = dictInfoMapper.selectAll();
//            for (int i=0;i<dicts.size();i++) {
//                DictInfo dictInfo = dicts.get(i);
//                String dictInfoId = dictInfo.getDictInfoId();
//                redisTemplate.boundHashOps("dict").put(dictInfoId,dictInfo);
//            }
//        }else{
//            System.out.println("从缓存中读取字典管理的数据");
//        }
//        return dicts;
    }

    @Override
    public int updateByPrimaryKey(DictInfo record) {
//        redisTemplate.boundHashOps("dict").put(record.getDictInfoId(),record);
        return dictInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public DictInfo selectByDictTypeCode(String dicttypecode) {
        return dictInfoMapper.selectByDictTypeCode(dicttypecode);
    }
}
