package com.bazl.clims.service;

import com.bazl.clims.model.po.LabDetails;

import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */
public interface LabDetailsService {
    List<LabDetails> querylabDetailsList(String labId);
}
