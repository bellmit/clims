package com.bazl.clims.dao;

import com.bazl.clims.model.po.CompetencePost;
import java.util.List;

public interface CompetencePostMapper {
    int insert(CompetencePost record);

    List<CompetencePost> selectAll();
}