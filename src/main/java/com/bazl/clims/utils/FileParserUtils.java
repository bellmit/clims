package com.bazl.clims.utils;

import com.bazl.clims.model.po.LimsSampleInfoDna;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *@author wanghaiyang
 * @date 2019/3/5.
 */
public class FileParserUtils {

    /**
     * 判断文件格式
     * @param inputStream
     * @param fileName
     * @return
     * @throws Exception
     */
    public static List<LimsSampleInfoDna> getDataFromFile(InputStream inputStream, String fileName) throws Exception {
        if (fileName.toUpperCase().endsWith("CSV")) {
            return getDataFromDat(inputStream);
        }

        throw new Exception("不支持文件扩展名为：" + fileName.substring(fileName.lastIndexOf('.') + 1) + " 的csv文件！");
    }

    /**
     * 解析csv文件
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static List<LimsSampleInfoDna> getDataFromDat(InputStream inputStream) throws Exception {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        int lineCount = 0;

        try {
            while ((line = br.readLine()) != null) {
                //去除无用信息
                lineCount++;

                if (lineCount == 8 || lineCount == 10 || lineCount == 12 || lineCount == 14|| lineCount == 16
                        || lineCount == 18 || lineCount == 20 || lineCount == 22) {

                    List<LimsSampleInfoDna> sampleInfoDnaList = new ArrayList<>();
                    try {
                        line = line.trim();
                        String[] str = line.split(",");
                        if (lineCount == 8) {
                            sampleInfoDnaList = getSampleInfoModel(str, "A");
                        }else if (lineCount == 10) {
                            sampleInfoDnaList = getSampleInfoModel(str, "B");
                        }else if (lineCount == 12) {
                            sampleInfoDnaList = getSampleInfoModel(str, "C");
                        }else if (lineCount == 14) {
                            sampleInfoDnaList = getSampleInfoModel(str, "D");
                        }else if (lineCount == 16) {
                            sampleInfoDnaList = getSampleInfoModel(str, "E");
                        }else if (lineCount == 18) {
                            sampleInfoDnaList = getSampleInfoModel(str, "F");
                        }else if (lineCount == 20) {
                            sampleInfoDnaList = getSampleInfoModel(str, "G");
                        }else if (lineCount == 22) {
                            sampleInfoDnaList = getSampleInfoModel(str, "H");
                        }

                        limsSampleInfoDnaList.addAll(sampleInfoDnaList);
                    } catch (Exception e) {
                        br.close();
                        throw new Exception("获取编号和位置有误！");
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        br.close();

        return limsSampleInfoDnaList;
    }

    /**
     * 获取编号和位置
     * @param str
     * @param position
     * @return
     * @throws Exception
     */
    public static List<LimsSampleInfoDna> getSampleInfoModel(String[] str, String position) throws Exception {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = new ArrayList<>();
        for (int i = 1; i < str.length; i++) {
            LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
            if (StringUtils.isNotBlank(str[i])) {
                sampleInfoDna.setSampleNo(str[i]);
                //0代表前面补充0; 2代表长度为2; d代表参数为正数型
                sampleInfoDna.setPosition(position + String.format("%02d", i));

                limsSampleInfoDnaList.add(sampleInfoDna);
            }
        }
        return limsSampleInfoDnaList;
    }

}
