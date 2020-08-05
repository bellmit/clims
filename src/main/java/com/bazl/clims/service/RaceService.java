package com.bazl.clims.service;

import com.bazl.clims.model.po.Race;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sun on 2019/4/2.
 */
@Repository
public interface RaceService {

    public List<Race> selectAll();

}
