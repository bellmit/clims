package com.bazl.clims.dao;

import com.bazl.clims.model.po.AlleleFrequency;
import com.bazl.clims.model.po.BlendRelationResult;

import java.util.List;

public interface BlendRelationMapper {

    int insert(BlendRelationResult record);

    BlendRelationResult selectById(String id);

    int update(BlendRelationResult blendRelationResult);
}