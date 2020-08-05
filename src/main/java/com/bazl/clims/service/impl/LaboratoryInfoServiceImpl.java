package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LaboratoryInfoMapper;
import com.bazl.clims.model.LaboratoryInfo;
import com.bazl.clims.service.LaboratoryInfoService;
import com.bazl.clims.utils.IPsegmentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
@Service
public class LaboratoryInfoServiceImpl implements LaboratoryInfoService {
    /**
     * 日志对象
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LaboratoryInfoMapper laboratoryInfoMapper;
    @Override
    public List<LaboratoryInfo> queryList() {
        return laboratoryInfoMapper.queryList();
    }

    @Override
    public LaboratoryInfo queryById(LaboratoryInfo laboratoryInfo) {
        return laboratoryInfoMapper.queryByLab(laboratoryInfo);
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void addOne(LaboratoryInfo laboratoryInfo) {

    }

    @Override
    public void updateOne(LaboratoryInfo laboratoryInfo) {
        laboratoryInfoMapper.updateLabInfo(laboratoryInfo);
    }

    @Override
    public LaboratoryInfo queryByClientIP() {
        LaboratoryInfo laboratoryInfo=null;
        try {
            InetAddress addr=InetAddress.getLocalHost();
            String ip=addr.getHostAddress();//拿到访问客户端ip
            //获取所有单位的实验室信息
            List<LaboratoryInfo> laboratoryInfoList=laboratoryInfoMapper.queryList();
            for(int i=0;i<laboratoryInfoList.size();i++){
                if(IPsegmentUtil.inIpsegment(ip,laboratoryInfoList.get(i).getIpSegment())){
                    laboratoryInfo=laboratoryInfoList.get(i);
                    break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("访问服务器的客户端IP错误："+e.getMessage());
        }
        return laboratoryInfo;
    }
}
