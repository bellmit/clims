package com.bazl.clims.service.impl;

import com.bazl.clims.dao.MatchRelativeLibMapper;
import com.bazl.clims.model.po.MatchRelativeLib;
import com.bazl.clims.service.MatchRelativeLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2019/4/9.
 */
@Service
public class MatchRelativeLibServiceImpl implements MatchRelativeLibService {

    @Autowired
    MatchRelativeLibMapper matchRelativeLibMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return matchRelativeLibMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MatchRelativeLib record) {
        return matchRelativeLibMapper.insert(record);
    }

    @Override
    public MatchRelativeLib selectByPrimaryKey(String id) {
        return matchRelativeLibMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MatchRelativeLib> selectAll() {
        return matchRelativeLibMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MatchRelativeLib record) {
        return matchRelativeLibMapper.updateByPrimaryKey(record);
    }

    @Override
    public MatchRelativeLib selectBySampleaId(String sampleaid){
        return matchRelativeLibMapper.selectBySampleaId(sampleaid);
    }

    @Override
    public MatchRelativeLib selectBySampleId(String sampleid){
        return matchRelativeLibMapper.selectBySampleId(sampleid);
    }

    @Override
    public int updateBySampleaId(MatchRelativeLib record) {
        return matchRelativeLibMapper.updateBySampleaId(record);
    }

    @Override
    public List<MatchRelativeLib> selectLibByPage(int page, int size) {
        int startRum = page*size;
        int endRum = (page+1)*size;
        return matchRelativeLibMapper.selectLibByPage(startRum, endRum);
    }
}
