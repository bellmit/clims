package com.bazl.clims.dao;

import com.bazl.clims.model.po.LabDetails;

import java.util.List;

public interface LabDetailsMapper {

    public List<LabDetails> querylabDetailsList(String labId);
}