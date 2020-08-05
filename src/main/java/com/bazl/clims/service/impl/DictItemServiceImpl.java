package com.bazl.clims.service.impl;

import com.bazl.clims.dao.DictItemMapper;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hj on 2018/12/20.
 */
@Service
public class DictItemServiceImpl implements DictItemService {

    @Autowired
    DictItemMapper dictItemMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<DictItem> selectListByDictCode(String dictCode) {

        return dictItemMapper.selectListByDictCode(dictCode);
    }

    @Override
    public List<DictItem> selectAllCode() {
        List<DictItem> listDictItem = dictItemMapper.selectAllCode();
        return listDictItem;
    }

    /**
     * 根据dictTypeCode查询出对应字典项内容
     * @param dictTypeCode
     * @return
     */
    @Override
    public List<DictItem> selectListByDictTypeCode(String dictTypeCode) {
        return dictItemMapper.selectListByDictTypeCode(dictTypeCode);
//        List<DictItem> dicts = (List<DictItem>) redisTemplate.boundHashOps("dictItem").get(dictTypeCode);
//        if(dicts==null){
//            System.out.println("缓从中没有此字典类型的详情，将此类型详情加入缓存");
//            dicts=dictItemMapper.selectListByDictTypeCode(dictTypeCode);
//            redisTemplate.boundHashOps("dictItem").put(dictTypeCode,dicts);
//        }else{
//            System.out.println("从缓从中加载此字典类型的详情数据");
//        }
//        return dicts;
    }

    @Override
    public int deleteByPrimaryKey(String dictItemId) {
//        DictItem dictItemo = dictItemMapper.selectByPrimaryKey(dictItemId);
//        String dictTypeCode = dictItemo.getDictTypeCode();
//        List<DictItem> dicts = (List<DictItem>)redisTemplate.boundHashOps("dictItem").get(dictTypeCode);
//        for (int i = 0; i <dicts.size(); i++) {
//            DictItem dictItem = dicts.get(i);
//            String dictItemIdone = dictItem.getDictItemId();
//            if(dictItemIdone.equals(dictItemId)){
//                dicts.remove(i);
//                break;
//            }
//        }
//        redisTemplate.boundHashOps("dictItem").put(dictTypeCode,dicts);
        return dictItemMapper.deleteByPrimaryKey(dictItemId);
    }

    @Override
    public int insert(DictItem record) {
//        List<DictItem> dicts = (List<DictItem>)redisTemplate.boundHashOps("dictItem").get(record.getDictTypeCode());
//        dicts.add(record);
//        redisTemplate.boundHashOps("dictItem").put(record.getDictTypeCode(),dicts);
        return dictItemMapper.insert(record);
    }

    @Override
    public DictItem selectByPrimaryKey(String dictItemId) {
        return dictItemMapper.selectByPrimaryKey(dictItemId);
    }

    @Override
    public int updateByPrimaryKey(DictItem record) {
//        List<DictItem> aa = (List<DictItem>)redisTemplate.boundHashOps("dictItem").get(record.getDictTypeCode());
//        for (int i =0;i<aa.size();i++){
//            DictItem dictItem = aa.get(i);
//            String dictItemId = dictItem.getDictItemId();
//            if(record.getDictItemId().equals(dictItemId)){
//                aa.remove(i);
//                break;
//            }
//        }
//        aa.add(record);
//        redisTemplate.boundHashOps("dictItem").put(record.getDictTypeCode(),aa);
        return dictItemMapper.updateByPrimaryKey(record);
    }
}
