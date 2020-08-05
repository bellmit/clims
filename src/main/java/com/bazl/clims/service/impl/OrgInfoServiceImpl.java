package com.bazl.clims.service.impl;

import com.bazl.clims.common.Constants;
import com.bazl.clims.dao.OrgInfoMapper;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.service.OrgInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sun on 2018/12/20.
 */
@Service
public class OrgInfoServiceImpl implements OrgInfoService {

    @Autowired
    OrgInfoMapper orgInfoMapper;

    @Value("${defaultDnaLabOrgId}")
    private String defaultDnaLabOrgId;

    @Value("${defaultDnaLabName}")
    private String defaultDnaLabName;

    @Override
    public int insert(OrgInfo record) {
        return orgInfoMapper.insert(record);
    }

    @Override
    public List<OrgInfo> selectAll() {
        return orgInfoMapper.selectAll();
    }

    @Override
    public OrgInfo selectByPrimaryKey(String orgId) {
        return orgInfoMapper.selectByPrimaryKey(orgId);
    }

    @Override
    public List<OrgInfo> selectDelegateByParentsId(String parentsId) {
        return orgInfoMapper.selectDelegateByParentsId(parentsId);
    }

    /**
     * 根据单位id获取对应实验室名称
     * @param orgId
     * @return
     */
    public String selectLabNameById(String orgId) {
        OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(orgId);
        if(orgInfo.getOrgLevel().equals(Constants.ORG_LEVEL_PAICHUSUO)){
            orgInfo = orgInfoMapper.selectByPrimaryKey(orgInfo.getParentId());
        }

        if(StringUtils.isNotBlank(orgInfo.getOrgQualification())){
            return orgInfo.getOrgQualification();
        }

        orgInfo = orgInfoMapper.selectByPrimaryKey(defaultDnaLabOrgId);
        if(orgInfo != null){
            return orgInfo.getOrgQualification();
        }else{
            return defaultDnaLabName;
        }
    }

    /**
     * 查询受理单位
     * @return
     */
    @Override
    public List<OrgInfo> selectAcceptOrgList() {

        List<OrgInfo> orgInfos = orgInfoMapper.selectAcceptOrgList();
        LinkedList newOrgList = new LinkedList();
        for(int i=0 ; i< orgInfos.size(); i++){
            OrgInfo orgInfo = new OrgInfo();
            String orgQualification = orgInfos.get(i).getOrgQualification();
            if("".equals(orgQualification) || orgQualification != null){
                orgInfo.setOrgCode(orgInfos.get(i).getOrgCode());
                orgInfo.setOrgName(orgInfos.get(i).getOrgName());
                orgInfo.setOrgId(orgInfos.get(i).getOrgId());
                newOrgList.add(orgInfo);
            }
        }
        return newOrgList;
    }

    public String getUseridByOrgid(String userId){
       return orgInfoMapper.getUseridByOrgid(userId);
    }

}
