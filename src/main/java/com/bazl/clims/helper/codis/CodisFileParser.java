package com.bazl.clims.helper.codis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.common.Constants;
import com.bazl.clims.utils.ListUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *@author wanghaiyang
 * @date 2019/5/14.
 */
public class CodisFileParser {
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(CodisFileParser.class);

    public static List<CodisContentModel> getDataCodisFile(InputStream inputStream, String fileName, String reagentName) throws Exception {
        if (fileName.toUpperCase().endsWith("DAT")) {
            return getDataDat(inputStream, reagentName);
        }

        if (fileName.toUpperCase().endsWith("TXT")) {
            return getDataFromTxt(inputStream, reagentName);
        }

        throw new Exception("不支持文件扩展名为：" + fileName.substring(fileName.lastIndexOf('.') + 1) + " 的CODIS文件！");
    }

    /**
     * 解析dat文件
     * @param inputStream
     * @param reagentName
     * @return
     * @throws Exception
     */
    public static List<CodisContentModel> getDataDat(InputStream inputStream, String reagentName) throws Exception {
        List<CodisContentModel> fileStrInfoList = new ArrayList<CodisContentModel>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        //基因座个数
        int locusCount = 0;

        while ((str = br.readLine()) != null) {

            if (StringUtils.isBlank(str)) {
                continue;
            }

            try {
                if ("DNA Analysis Result".equalsIgnoreCase(str)) {
                    int readCount = 0;
                    //峰值个数
                    int valCount = 0;

                    //2个无用信息
                    for (int i = 0; i < 2; i++) {
                        br.readLine();
                    }

                    CodisContentModel codisContentModel = new CodisContentModel();

                    //Dna-----------------此处新建map集合--------------------------
                    Map<String, Object> map = new LinkedHashMap<>();

                    //样本实验室编号
                    codisContentModel.setSampleNo(br.readLine());

                    //5个无用信息
                    for (int i = 0; i < 5; i++) {
                        br.readLine();
                    }

                    locusCount = Integer.parseInt(br.readLine());
                    while (readCount < locusCount) {


                        String locusName = br.readLine();
                        if (StringUtils.isBlank(locusName)) {
                            break;
                        }
                        //4个无用信息
                        for (int i = 0; i < 4; i++) {
                            br.readLine();
                        }

                        String alleleValue1 = "";
                        String alleleValue2 = "";
                        String alleleValue3 = "";
                        String alleleValue4 = "";

                        valCount = Integer.parseInt(br.readLine());
                        if (valCount == 1) {
                            alleleValue1 = br.readLine();
                            alleleValue2 = alleleValue1;
                        } else if (valCount == 2) {
                            alleleValue1 = br.readLine();
                            alleleValue2 = br.readLine();
                        } else if (valCount == 3) {
                            alleleValue1 = br.readLine();
                            alleleValue2 = br.readLine();
                            alleleValue3 = br.readLine();
                        } else if (valCount == 4) {
                            alleleValue1 = br.readLine();
                            alleleValue2 = br.readLine();
                            alleleValue3 = br.readLine();
                            alleleValue4 = br.readLine();
                        }

                        //Dna-----------------此处装订map--------------------------
                        ArrayList<String> list = new ArrayList<String>();
                        if (!"".equals(alleleValue1)){
                            list.add(alleleValue1);
                        }
                        if (!"".equals(alleleValue2)){
                            list.add(alleleValue2);
                        }
                        if (!"".equals(alleleValue3)){
                            list.add(alleleValue3);
                        }
                        if (!"".equals(alleleValue4)){
                            list.add(alleleValue4);
                        }
                        map.put(locusName,list);
                        //Dna-----------------装订map结束--------------------------

                        readCount++;
                    }

                    //Dna-----------------Map数据插入--------------------------
                    if(map != null){
                        JSONObject json = new JSONObject(map);
                        codisContentModel.setGeneInfo(json.toJSONString());
                    }
                    //Dna-----------------Map数据插入结束--------------------------

                    fileStrInfoList.add(codisContentModel);
                }
            } catch (Exception e) {
                br.close();
                throw new Exception("CODIS文件格式或基因数据有误！");
            }
        }

        br.close();

        return fileStrInfoList;
    }

    /**
     * 解析txt文件
     * @param inputStream
     * @param reagentName
     * @return
     * @throws Exception
     */
    public static List<CodisContentModel> getDataFromTxt(InputStream inputStream, String reagentName) throws Exception {
        List<CodisContentModel> fileStrInfoList = new ArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String lineTxt = null;

        String sampleNo = "";
        CodisContentModel sampleInfo = new CodisContentModel();

        while ((lineTxt = br.readLine()) != null) {


            Map<String, Object> map = new LinkedHashMap<>();

            if (StringUtils.isBlank(lineTxt)) {
                continue;
            }

            try {
                String[] arry = lineTxt.split("\t");

                if (arry[0].equalsIgnoreCase("Sample Info")) {
                    continue;
                }

                if (!arry[0].toLowerCase().equals(sampleNo) && !sampleNo.equals("")) {
                    sampleInfo.setSampleNo(sampleNo);

                    //Dna----------------Map数据装订开始----------------------
                    ArrayList<String> list = new ArrayList<String>();
                    if (!"".equals(arry[2])){
                        list.add(arry[2]);
                    }
                    if (arry.length > 4) {
                        list.add(arry[3]);
                    }
                    if (arry.length > 5) {
                        list.add(arry[4]);
                    }
                    if (arry.length > 6) {
                        list.add(arry[5]);
                    }
                    map.put(arry[1],list);
                    //Dna----------------Map数据装订结束----------------------

                    if(map != null){
                        JSONObject json = new JSONObject(map);
                        sampleInfo.setGeneInfo(json.toJSONString());
                    }
                    fileStrInfoList.add(sampleInfo);
                    sampleInfo = new CodisContentModel();
                }

                sampleNo = arry[0].toLowerCase();

                //Dna----------------Map数据装订开始----------------------
                ArrayList<String> list = new ArrayList<String>();
                if (!"".equals(arry[2])){
                    list.add(arry[2]);
                }
                if (arry.length > 4) {
                    list.add(arry[3]);
                }
                if (arry.length > 5) {
                    list.add(arry[4]);
                }
                if (arry.length > 6) {
                    list.add(arry[5]);
                }
                map.put(arry[1],list);
                //Dna----------------Map数据装订结束----------------------
            } catch (Exception e) {
                br.close();
                throw new Exception("CODIS文件格式或基因数据有误！");
            }
        }
        br.close();

        return fileStrInfoList;
    }

    //判断基因型是否属于混合或者常染色体或者Y基因型
    public static String isGeneType(String geneInfo,Integer types) {
        String geneType = null;

        Map<String, List<String>> srcResult = null;
        try {
            srcResult =(Map) JSON.parse(geneInfo);
        } catch (Exception ex) {
            logger.error("解析基因分型信息错误!", ex);
        }

        if (srcResult != null) {
            for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                List<String> srcAlleleList = srcEntry.getValue();
                if (1 == types){
                    geneType = Constants.GENE_YSTR;
                    break;
                }else{
                    if (srcAlleleList.size() > 2) {
                        geneType = Constants.GENE_MIXED;
                        break;
                    }else if(srcAlleleList.size() <= 2) {
                        geneType = Constants.GENE_NORMAL;
                    }
                }
            }
        }

        return geneType;
    }

    /**
     * 判断基因型是否重复
     * @param srcGeneInfo	     待比对基因型
     * @param tarGeneInfo	     被比样本基因型
     * @return
     */
    public static boolean compareGeneInfo(String srcGeneInfo, String tarGeneInfo) {
        boolean flag = true;

        Map<String, List<String>> srcResult = null;
        try {
            srcResult =(Map) JSON.parse(srcGeneInfo);
        } catch (Exception ex) {
            logger.error("解析基因分型信息错误!", ex);
            ex.getStackTrace();
            throw ex;
        }
        Map<String, List<String>> tarResult = null;
        try {
            tarResult =(Map) JSON.parse(tarGeneInfo);
        } catch (Exception ex) {
            logger.error("解析基因分型信息错误！ ", ex);
            ex.getStackTrace();
            throw ex;
        }

        //判断基因型是否为空值
        if(srcResult .size()==0 || tarResult.size() == 0){
            return false;
        }
//        if(srcResult == null || tarResult == null){
//            return false;
//        }

        for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
            String markerName = srcEntry.getKey();
            List<String> srcAlleleList = srcEntry.getValue();
            List<String> tarAlleleList = tarResult.get(markerName);
            if (ListUtils.isNotNullAndEmptyList(srcAlleleList) && ListUtils.isNotNullAndEmptyList(tarAlleleList)) {
                if (srcAlleleList.size() != tarAlleleList.size() || !srcAlleleList.containsAll(tarAlleleList)) {
                    flag = false;
                    break;
                }
            }else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 校验基因座是否有效
     * @param geneInfo
     * @return
     */
    public static boolean checkValidGene(String geneInfo) {
        boolean flag = false;

        Map<String, List<String>> srcResult = null;
        try {
            srcResult =(Map) JSON.parse(geneInfo);
        } catch (Exception ex) {
            logger.error("解析基因分型信息错误!", ex);
        }
        if (srcResult != null) {
            for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                List<String> srcAlleleList = srcEntry.getValue();
                if (srcAlleleList.size() > 0) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

}
