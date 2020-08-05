package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmExamineRegister;
import java.util.List;

public interface AmExamineRegisterMapper {
    int insert(AmExamineRegister record);

    List<AmExamineRegister> selectAll();
}