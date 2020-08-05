package com.bazl.clims.service.impl;

import com.bazl.clims.common.Constants;
import com.bazl.clims.dao.SeqNoGenerateMapper;
import com.bazl.clims.dao.SerialNumberMapper;
import com.bazl.clims.model.po.DnaNoSeq;
import com.bazl.clims.model.po.SerialNumber;
import com.bazl.clims.service.SeqNoGenerateService;
import com.bazl.clims.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by Administrator on 2017/1/7.
 */
@Service
public class SeqNoGenerateServiceImpl extends BaseService implements SeqNoGenerateService {

    @Autowired
    SerialNumberMapper serialNumberMapper;
    @Autowired
    SeqNoGenerateMapper seqNoGenerateMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String getNextCaseNoVal(String symbol, String text,String orgId) {
        String year = DateUtils.getCurrentYear();
        SerialNumber serialNumber = serialNumberMapper.selectNextVal(year, Constants.TYPE_CODE_CASE_NO,orgId);
        String nextVal = "";
        try {
            if (serialNumber != null && StringUtils.isNotBlank(serialNumber.getTypeValue())) {
                int val = Integer.parseInt(serialNumber.getTypeValue());
                val = val + 1;
                if(val<10000){
                    nextVal = getFullCharString(String.valueOf(val), 4);
                }else{
                    nextVal = val+"";
                }
                serialNumber.setTypeValue(nextVal);
                serialNumberMapper.update(serialNumber);
            } else {
                int val = 1;
                if(val<10000){
                    nextVal = getFullCharString(String.valueOf(val), 4);
                }else{
                    nextVal = val+"";
                }
                serialNumber = new SerialNumber();
                serialNumber.setId(UUID.randomUUID().toString());
                serialNumber.setTypeCode(Constants.TYPE_CODE_CASE_NO);
                serialNumber.setYear(year);
                serialNumber.setTypeValue(nextVal);
                serialNumber.setOrgId(orgId);
                serialNumberMapper.insert(serialNumber);
            }
        } catch (Exception ex) {
            logger.info("获取案件流水号报错："+ex);
            throw ex;
        }
        String yearCut = year.substring(2,4);
        String orgNameSp = Constants.selectOrgNameSp(orgId);
        if(Constants.CASE_NO_PREFIX.equals(orgNameSp)){
            String nextVal2 = nextVal;
            if(nextVal2.length()==4){
                nextVal2 = "0" + nextVal2;
            }
            return Constants.CASE_NO_PREFIX + yearCut + nextVal2 + symbol + year + text + nextVal;
//            return year + text + nextVal;
        }else{
            return orgNameSp + year + text + nextVal;
        }
    }

    @Override
    public String getNextTastNoVal(String symbol, String orgId) {
        String year = DateUtils.getCurrentYear();
        SerialNumber serialNumber = serialNumberMapper.selectNextVal(year, Constants.TYPE_CODE_TASK_NO,orgId);
        int val = 0;
        try {
            if (serialNumber != null && StringUtils.isNotBlank(serialNumber.getTypeValue())) {
                val = Integer.parseInt(serialNumber.getTypeValue());
                val = val + 1;
                serialNumber.setTypeValue(val+"");
                serialNumberMapper.update(serialNumber);
            } else {
                val = val + 1;
                serialNumber = new SerialNumber();
                serialNumber.setId(UUID.randomUUID().toString());
                serialNumber.setTypeCode(Constants.TYPE_CODE_TASK_NO);
                serialNumber.setYear(year);
                serialNumber.setTypeValue(val+"");
                serialNumber.setOrgId(orgId);
                serialNumberMapper.insert(serialNumber);
            }
        } catch (Exception ex) {
            logger.info("获取任务流水号报错："+ex);
            throw ex;
        }
        //返回任务编号
        return year + Constants.TASK_NO_PREFIX + symbol + val;
    }

    @Override
    public String getNextExtractNoVal(String symbol, String orgId) {
        String year = DateUtils.getCurrentYear();
        SerialNumber serialNumber = serialNumberMapper.selectNextVal(year, Constants.TYPE_CODE_EXTRACT_NO,orgId);
        int val = 0;
        try {
            if (serialNumber != null && StringUtils.isNotBlank(serialNumber.getTypeValue())) {
                val = Integer.parseInt(serialNumber.getTypeValue());
                val = val + 1;
                serialNumber.setTypeValue(val+"");
                serialNumberMapper.update(serialNumber);
            } else {
                val = val + 1;
                serialNumber = new SerialNumber();
                serialNumber.setId(UUID.randomUUID().toString());
                serialNumber.setTypeCode(Constants.TYPE_CODE_EXTRACT_NO);
                serialNumber.setYear(year);
                serialNumber.setTypeValue(val+"");
                serialNumber.setOrgId(orgId);
                serialNumberMapper.insert(serialNumber);
            }
        } catch (Exception ex) {
            logger.info("获取提取任务号报错："+ex);
            throw ex;
        }
        //返回任务编号
        return year + Constants.EXTRACT_NO_PREFIX + symbol + val;
    }

    @Override
    public String getNextPcrNoVal(String symbol, String orgId){
        String year = DateUtils.getCurrentYear();
        SerialNumber serialNumber = serialNumberMapper.selectNextVal(year, Constants.TYPE_CODE_PCR_NO,orgId);
        int val = 0;
        try {
            if (serialNumber != null && StringUtils.isNotBlank(serialNumber.getTypeValue())) {
                val = Integer.parseInt(serialNumber.getTypeValue());
                val = val + 1;
                serialNumber.setTypeValue(val+"");
                serialNumberMapper.update(serialNumber);
            } else {
                val = val + 1;
                serialNumber = new SerialNumber();
                serialNumber.setId(UUID.randomUUID().toString());
                serialNumber.setTypeCode(Constants.TYPE_CODE_PCR_NO);
                serialNumber.setYear(year);
                serialNumber.setTypeValue(val+"");
                serialNumber.setOrgId(orgId);
                serialNumberMapper.insert(serialNumber);
            }
        } catch (Exception ex) {
            logger.info("获取扩增任务号报错："+ex);
            throw ex;
        }
        //返回任务编号
        return year + Constants.PCR_NO_PREFIX + symbol + val;
    }

    @Override
    public String getNextSyNoVal(String symbol, String orgId) {
        String year = DateUtils.getCurrentYear();
        SerialNumber serialNumber = serialNumberMapper.selectNextVal(year, Constants.TYPE_CODE_SY_NO,orgId);
        int val = 0;
        try {
            if (serialNumber != null && StringUtils.isNotBlank(serialNumber.getTypeValue())) {
                val = Integer.parseInt(serialNumber.getTypeValue());
                val = val + 1;
                serialNumber.setTypeValue(val+"");
                serialNumberMapper.update(serialNumber);
            } else {
                val = val + 1;
                serialNumber = new SerialNumber();
                serialNumber.setId(UUID.randomUUID().toString());
                serialNumber.setTypeCode(Constants.TYPE_CODE_SY_NO);
                serialNumber.setYear(year);
                serialNumber.setTypeValue(val+"");
                serialNumber.setOrgId(orgId);
                serialNumberMapper.insert(serialNumber);
            }
        } catch (Exception ex) {
            logger.info("获取电泳任务号报错："+ex);
            throw ex;
        }
        //返回任务编号
        return year + Constants.SY_NO_PREFIX + symbol + val;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized String getNextPerosnNoVal(String personType, String text,String orgId) {
        String year = DateUtils.getCurrentYear();
        SerialNumber serialNumber = serialNumberMapper.selectNextVal(year, Constants.TYPE_CODE_PERSON_NO, null);
        String nextVal = "";
        try {
            if (serialNumber != null && StringUtils.isNotBlank(serialNumber.getTypeValue())) {
                int val = Integer.parseInt(serialNumber.getTypeValue());
                val = val + 1;
//                nextVal = getFullCharString(String.valueOf(val), 5);
                nextVal = val + "";
                serialNumber.setTypeValue(nextVal);
                serialNumberMapper.update(serialNumber);
            } else {
                int val = 1;
//                nextVal = getFullCharString(String.valueOf(val), 5);
                nextVal = val + "";
                serialNumber = new SerialNumber();
                serialNumber.setId(UUID.randomUUID().toString());
                serialNumber.setTypeCode(Constants.TYPE_CODE_PERSON_NO);
                serialNumber.setYear(year);
                serialNumber.setTypeValue(nextVal);
                serialNumberMapper.insert(serialNumber);
            }
        } catch (Exception ex) {
            logger.info("获取人员流水号报错："+ex);
            throw ex;
        }
        String yearCut = year.substring(2,4);
//        return Constants.PERSON_NO_PREFIX + yearCut + personType + text + DateUtils.getCurrentMonthStr() + nextVal;
        return yearCut + personType + text + DateUtils.getCurrentMonthStr()+ "-" + nextVal;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String getNextSampleNoVal(String serialNumber, String symbol, String orgId, String nextVal) {
        String year = DateUtils.getCurrentYear();

        String yearCut = year.substring(2,4);
        String orgNameSp = Constants.selectOrgNameSp(orgId);
        if(Constants.CASE_NO_PREFIX.equals(orgNameSp)){
            return yearCut + serialNumber + symbol + nextVal;
        }else{
            return orgNameSp + yearCut + serialNumber + symbol + nextVal;
        }
    }

    private String getFullCharString(String src, int len)
    {
        int srcLen = src.length();
        if(srcLen >= len)
        {
            return src;
        }

        int needlen = len - srcLen;
        for (int i = 0; i < needlen; i++ )
        {
            src = "0" + src;
        }

        return src;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String getNextNoVal(String year, String code, String text,String orgId) {
        SerialNumber serialNumber = serialNumberMapper.selectNextVal(year, code, orgId);
        String nextVal = "";
        try {
            if (serialNumber != null && StringUtils.isNotBlank(serialNumber.getTypeValue())) {
                int val = Integer.parseInt(serialNumber.getTypeValue());
                val = val + 1;
                nextVal = getFullCharString(String.valueOf(val), 5);
                serialNumber.setTypeValue(nextVal);
                serialNumberMapper.update(serialNumber);
            } else {
                int val = 1;
                nextVal = getFullCharString(String.valueOf(val), 5);
                serialNumber = new SerialNumber();
                serialNumber.setId(UUID.randomUUID().toString());
                serialNumber.setTypeCode(code);
                serialNumber.setYear(year);
                serialNumber.setTypeValue(nextVal);
                serialNumberMapper.insert(serialNumber);
            }
        } catch (Exception ex) {
            logger.info("获取流水号报错："+ex);
            throw ex;
        }
        return year+text+nextVal;
    }
    /**
     * 获取流水号
     * @param year
     * @param code
     * @param orgId
     * @return
     */
    @Override
    public String getNextVals(String year, String typeCode, String code, String orgId) {
        SerialNumber serialNumber = new SerialNumber();
        String nextVal = "";
        try {
            serialNumber = serialNumberMapper.selectNextVals(typeCode, orgId);

            if(StringUtils.isNotBlank(serialNumber.getYear()) && serialNumber.getYear() != year){
                serialNumberMapper.updateYear(year,typeCode,orgId);
            }

            int val = Integer.parseInt(serialNumber.getTypeValue());
            val = val + 1;
            nextVal = getFullCharString(String.valueOf(val), serialNumber.getDigit() == 0 ? 4 : serialNumber.getDigit());
        } catch (Exception ex) {
            logger.info("获取流水号出错：" + ex);
            return "";
        }
        String yearVal = serialNumber.getYear() == null ? "" : serialNumber.getYear();
        String prefixVal = serialNumber.getPrefix() == null ? "" : serialNumber.getPrefix();
        return yearVal + prefixVal + nextVal;

    }
    /**
     * 修改流水号
     * @param year
     * @param typeCode
     * @param code
     * @param orgId
     * @return
     */
    @Override
    public int updateTypeVals(String year, String typeCode, String code, String orgId) {
        SerialNumber serialNumber = new SerialNumber();
        String nextVal = "";
        try {
            serialNumber = serialNumberMapper.selectNextVals(typeCode, orgId);
            int val = Integer.parseInt(serialNumber.getTypeValue());
            val = val + 1;

            nextVal = getFullCharString(String.valueOf(val), serialNumber.getDigit() == 0 ? 4 : serialNumber.getDigit());
            serialNumber.setTypeValue(nextVal);
            serialNumberMapper.update(serialNumber);

        } catch (Exception ex) {
            logger.info("修改流水号出错：" + ex);
            return 0;
        }

        return 1;
    }
    @Override
    public String getNextSampleBoardNoVal(String code) {
        return code + "-" + FullCharString(getNextVal(code), 2);
    }
    private String FullCharString(String src, int len)
    {
        int srcLen = src.length();
        if(srcLen >= len)
        {
            return src;
        }

        int needlen = len - srcLen;
        for (int i = 0; i < needlen; i++ )
        {
            src = "0" + src;
        }

        return src;
    }
    private String getNextVal(String code) {
        boolean flag = true;
        DnaNoSeq dnaNoSeq = seqNoGenerateMapper.selectByCode(code);
        if (dnaNoSeq ==null) {
            try {
                DnaNoSeq dns = new DnaNoSeq();
                dns.setCode(code);
                seqNoGenerateMapper.insert(dns);
                seqNoGenerateMapper.updateCurrentValByCode(code);
                dnaNoSeq = seqNoGenerateMapper.selectByCode(code);
            }catch (Exception e) {
                flag = false;
                e.getStackTrace();
            }
        }else{
            try {
                seqNoGenerateMapper.updateCurrentValByCode(code);
                dnaNoSeq = seqNoGenerateMapper.selectByCode(code);
            }catch (Exception e){
                flag = false;
                e.getStackTrace();
            }
        }

        return dnaNoSeq.getCurrentVal() + "";
    }
    @Override
    public boolean returnCurrentValByCode(String code) {
        boolean flag = true;
        try {
            seqNoGenerateMapper.returnCurrentValByCode(code);
        }catch (Exception e) {
            flag = false;
            e.getStackTrace();
        }

        return flag;
    }

    @Override
    public String getNextBoardNoVal(String code) {
        return code + getNextVal(code);
    }
}
