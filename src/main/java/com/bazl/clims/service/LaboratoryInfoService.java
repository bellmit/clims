package com.bazl.clims.service;

import com.bazl.clims.model.LaboratoryInfo;

/**
 * 实验室信息接口
 */
public interface LaboratoryInfoService extends BasisInterface<LaboratoryInfo,String>{
    LaboratoryInfo queryByClientIP();
}
