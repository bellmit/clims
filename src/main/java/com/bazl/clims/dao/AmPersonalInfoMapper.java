package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmPersonalInfo;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/12/21.
 */
@Repository
public interface AmPersonalInfoMapper {
    List<AmPersonalInfo> queryAmPersonalInfoLIst(@Param("orgId")String orgId);

    List<AmPersonalInfoVo> queryAmPersonalInfoVoList(@Param("orgId")String orgId);

    void addAmPersonalInfo(AmPersonalInfo amPersonalInfo);

    void deleteAmPersonalInfo(AmPersonalInfo amPersonalInfo);

   void updateAmPersonalInfo(AmPersonalInfo amPersonalInfo);

    AmPersonalInfo queryAmPersonalInfo(String personalId);

    void updateAmPersonalInfoById(AmPersonalInfo amPersonalInfo);

    AmPersonalInfo selectByPersonalId(String personalId);

    List<AmPersonalInfo> queryAmPersonalInfoListOrgId(@Param("orgId")String orgId);
}
