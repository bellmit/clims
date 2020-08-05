package com.bazl.clims.service.impl;

import com.bazl.clims.model.po.Race;
import com.bazl.clims.dao.RaceMapper;
import com.bazl.clims.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2019/4/2.
 */
@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    RaceMapper raceMapper;

    @Override
    public List<Race> selectAll() {
        return raceMapper.selectAll();
    }
}
