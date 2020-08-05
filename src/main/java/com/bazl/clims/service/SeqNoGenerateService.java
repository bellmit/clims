package com.bazl.clims.service;

/**
 * Created by Administrator on 2017/1/7.
 */
public interface SeqNoGenerateService {

    public String getNextCaseNoVal(String symbol, String text,String orgId);

    public String getNextTastNoVal(String symbol, String orgId);

    /**
     * 查询提取任务编号
     * @param symbol
     * @param orgId
     * @return
     */
    public String getNextExtractNoVal(String symbol, String orgId);

    /**
     * 查询扩增任务编号
     * @param symbol
     * @param orgId
     * @return
     */
    public String getNextPcrNoVal(String symbol, String orgId);

    /**
     * 查询电泳任务编号
     * @param symbol
     * @param orgId
     * @return
     */
    public String getNextSyNoVal(String symbol, String orgId);

    public String getNextPerosnNoVal(String personType, String text,String orgId);

    public String getNextSampleNoVal(String serialNumber, String symbol, String orgId, String nextVal);

    public String getNextNoVal(String year, String code, String text,String orgId);

    /**
     * 获取流水号
     * @param year
     * @param code
     * @param orgId
     * @return
     */
    public String getNextVals(String year,String typeCode, String code,String orgId);

    /**
     * 修改流水号
     * @param year
     * @param typeCode
     * @param code
     * @param orgId
     * @return
     */
    public int updateTypeVals(String year,String typeCode, String code,String orgId);
    /**
     * 根据条件查询下一个
     * @param code
     * @return
     */
    public String getNextSampleBoardNoVal(String code);
    /**
     * 根据code还原currentVal的值
     * @param code
     * @return
     */
    public boolean returnCurrentValByCode(String code);
    /**
     * 根据条件查询下一个
     * @param code
     * @return
     */
    public String getNextBoardNoVal(String code);
}
