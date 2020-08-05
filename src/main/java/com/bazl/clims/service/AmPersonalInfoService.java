package com.bazl.clims.service;

import com.bazl.clims.model.po.AmPersonalInfo;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/12/21.
 */
@Repository
public interface AmPersonalInfoService {
    List<AmPersonalInfo> queryAmPersonalInfoLIst(String orgId);

    List<AmPersonalInfoVo> queryAmPersonalInfoVoList(String orgId);

    void addAmPersonalInfo(AmPersonalInfo amPersonalInfo);

    void deleteAmPersonalInfo(AmPersonalInfo amPersonalInfo);

    void updateAmPersonalInfo(AmPersonalInfo amPersonalInfo);

    AmPersonalInfo queryAmPersonalInfo(String personalId);

    void updateAmPersonalInfoById(AmPersonalInfo amPersonalInfo);

    AmPersonalInfo selectByPersonalId(String personalId);

    List<AmPersonalInfo> queryAmPersonalInfoListOrgId(String orgId);
}
