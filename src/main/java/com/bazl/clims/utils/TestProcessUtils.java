package com.bazl.clims.utils;

import com.bazl.clims.common.Constants;
import com.bazl.clims.model.po.LabPcrSample;
import com.bazl.clims.model.po.LabSySample;
import com.bazl.clims.model.vo.SampleInfoVo;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author wanghaiyang
 * @date 2019/3/31.
 */
public class TestProcessUtils {
    /**
     * 选取板
     * @param sampleInfoVoList
     * @param holeNum
     * @return
     */
    public static List<Map<String, Object>> boardSort(List<SampleInfoVo> sampleInfoVoList, int holeNum, SampleInfoVo infoVo, String stage) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        List<SampleInfoVo> labSampleInfoVoList = new ArrayList<>();
        /*for(int i = 0; i < holeNum; i++) {
            String position = Constants.POSTION_ARR[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();

            sampleInfoVo.setHoleNum(String.valueOf(holeNum));
            sampleInfoVo.setElutionName(infoVo.getElutionName());
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleLocationSort(Constants.POSTION_HORIZONTAL_NUMBER[i]);

            labSampleInfoVoList.add(sampleInfoVo);
        }*/
        for(int i = 0; i < holeNum; i++) {
            String position = Constants.SYTABLE_POSTION_HORIZONTAL_ARR[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();

            sampleInfoVo.setHoleNum(String.valueOf(holeNum));
            sampleInfoVo.setElutionName(infoVo.getElutionName());
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleLocationSort(Constants.SYTABLE_POSTION_HORIZONTAL_NUMBER[i]);

            labSampleInfoVoList.add(sampleInfoVo);
        }

        tempList = getTempList(sampleInfoVoList, labSampleInfoVoList, holeNum, stage);

        return tempList;
    }
    public static List<Map<String, Object>> getTempList(List<SampleInfoVo> sampleInfoVoList, List<SampleInfoVo> labSampleInfoVoList, int holeNum, String stage) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        for (SampleInfoVo siVo : sampleInfoVoList) {
            for (SampleInfoVo sampleInfoVo : labSampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    if (siVo.getEntity().getExtractLocationSort() != null &&
                            siVo.getEntity().getExtractLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_KZ.equals(stage)){
                    if (siVo.getEntity().getPcrLocationSort() != null &&
                            siVo.getEntity().getPcrLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_SY.equals(stage)) {
                    if (siVo.getEntity().getSyLocationSort() != null &&
                            siVo.getEntity().getSyLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else {
                    if (siVo.getEntity().getSampleLocationSort() != null &&
                            siVo.getEntity().getSampleLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }
            }
        }

        int count = holeNum/24;

        for(int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<>();
            List<SampleInfoVo> list = new ArrayList<>();
            for (int j = i*24; j < (i+1)*24; j++) {
                list.add(labSampleInfoVoList.get(j));
            }
            map.put("list", list);
            tempList.add(map);
        }

        return tempList;
    }

    /**
     * 扩增板检材显示
     * @param labPcrSampleList
     * @return
     */
    public static List<Map<String, Object>> pcrBoardSort(List<LabPcrSample> labPcrSampleList) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        List<LabPcrSample> labPcrSampleBoardList = new ArrayList<>();
        for(int i = 0; i < Constants.SYTABLE_POSTION_ARR.length; i++) {
            String temp = Constants.SYTABLE_POSTION_ARR[i];
            LabPcrSample labPcrSample = new LabPcrSample();
            labPcrSample.setSamplePostion(temp);
            labPcrSampleBoardList.add(labPcrSample);
        }

        for (LabPcrSample lps : labPcrSampleList) {
            for (LabPcrSample pcrSample : labPcrSampleBoardList) {
                if (StringUtils.isNotBlank(lps.getSamplePostion()) &&
                        lps.getSamplePostion().equals(pcrSample.getSamplePostion())) {
                    LocalBeanUtils.copyPropertiesIgnoreNull(lps, pcrSample);
                }
            }
        }

        for(int i = 0; i < Constants.HORIZONTAL_SYTABLE_POSTION_ARR.length; i++) {
            String temp = Constants.HORIZONTAL_SYTABLE_POSTION_ARR[i];
            Map<String, Object> map = new HashMap<>();
            map.put("temp", temp);
            List<LabPcrSample> list = new ArrayList<>();
            for(LabPcrSample lps : labPcrSampleBoardList){
                if(lps.getSamplePostion().contains(temp)){
                    list.add(lps);
                }
            }
            map.put("list", list);
            tempList.add(map);
        }

        return tempList;
    }

    public static List<Map<String, Object>> syBoardSort(List<LabSySample> labSySampleList) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        List<LabSySample> labSySampleBoardList = new ArrayList<>();
        for(int i = 0; i < Constants.SYTABLE_POSTION_ARR.length; i++) {
            String temp = Constants.SYTABLE_POSTION_ARR[i];
            LabSySample labSySample = new LabSySample();
            labSySample.setSamplePostion(temp);
            labSySampleBoardList.add(labSySample);
        }

        for (LabSySample lps : labSySampleList) {
            for (LabSySample sySample : labSySampleBoardList) {
                if (StringUtils.isNotBlank(lps.getSamplePostion()) &&
                        lps.getSamplePostion().equals(sySample.getSamplePostion())) {
                    LocalBeanUtils.copyPropertiesIgnoreNull(lps, sySample);
                }
            }
        }

        for(int i = 0; i < Constants.HORIZONTAL_SYTABLE_POSTION_ARR.length; i++) {
            String temp = Constants.HORIZONTAL_SYTABLE_POSTION_ARR[i];
            Map<String, Object> map = new HashMap<>();
            map.put("temp", temp);
            List<LabSySample> list = new ArrayList<>();
            for(LabSySample lps : labSySampleBoardList){
                if(lps.getSamplePostion().contains(temp)){
                    list.add(lps);
                }
            }

            map.put("list", list);
            tempList.add(map);
        }

        return tempList;
    }
    /**
     * 孔位排序
     * @param sampleInfoVoList
     * @param stage
     * @return
     */
    public static List<SampleInfoVo> holeSort(List<SampleInfoVo> sampleInfoVoList, String stage) {
        Collections.sort(sampleInfoVoList, new Comparator<SampleInfoVo>() {
            @Override
            public int compare(SampleInfoVo o1, SampleInfoVo o2) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    return o1.getEntity().getExtractLocationSort().compareTo(
                            o2.getEntity().getExtractLocationSort());
                }else if (Constants.STAGE_KZ.equals(stage)){
                    return o1.getEntity().getPcrLocationSort().compareTo(
                            o2.getEntity().getPcrLocationSort());
                }else if (Constants.STAGE_SY.equals(stage)) {
                    return o1.getEntity().getSyLocationSort().compareTo(
                            o2.getEntity().getSyLocationSort());
                }else {
                    return o1.getEntity().getSampleLocationSort().compareTo(
                            o2.getEntity().getSampleLocationSort());
                }
            }
        });

        return sampleInfoVoList;
    }
    /**
     * 整板孔位
     * @param sampleInfoVoList
     * @param holeNum
     * @return
     */
    public static List<Map<String, Object>> boardSortAll(List<SampleInfoVo> sampleInfoVoList, int holeNum, SampleInfoVo infoVo, String stage) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        List<SampleInfoVo> labSampleInfoVoList = new ArrayList<>();
        for(int i = 0; i < holeNum; i++) {
            String position = Constants.POSTION_ARR_ALL[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();

            sampleInfoVo.setHoleNum(String.valueOf(holeNum));
            sampleInfoVo.setElutionName(infoVo.getElutionName());
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleLocationSort(Constants.POSTION_HORIZONTAL_NUMBER_ALL[i]);

            labSampleInfoVoList.add(sampleInfoVo);
        }

        tempList = getTempList(sampleInfoVoList, labSampleInfoVoList, holeNum, stage);

        return tempList;
    }
    /**
     * 赋值
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> getLocation(List<SampleInfoVo> sampleInfoVoList, String stage) {
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            for (SampleInfoVo siVo : sampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    siVo.getEntity().setExtractPlateLocation(siVo.getEntity().getSamplePlateLocation());
                    siVo.getEntity().setExtractLocationSort(siVo.getEntity().getSampleLocationSort());
                    siVo.getEntity().setExtractPlateSort(siVo.getEntity().getSamplePlateSort());
                }else if (Constants.STAGE_KZ.equals(stage)){
                    siVo.getEntity().setPcrPlateLocation(siVo.getEntity().getExtractPlateLocation());
                    siVo.getEntity().setPcrLocationSort(siVo.getEntity().getExtractLocationSort());
                    siVo.getEntity().setPcrPlateSort(siVo.getEntity().getExtractPlateSort());
                }else if (Constants.STAGE_SY.equals(stage)) {
                    siVo.getEntity().setSyPlateLocation(siVo.getEntity().getPcrPlateLocation());
                    siVo.getEntity().setSyLocationSort(siVo.getEntity().getPcrLocationSort());
                    siVo.getEntity().setSyPlateSort(siVo.getEntity().getPcrPlateSort());
                }
            }
        }

        return sampleInfoVoList;
    }
    /**
     * 板孔排序
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> boardSortAgain(List<SampleInfoVo> sampleInfoVoList, String stage) {
        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        List<SampleInfoVo> labSampleInfoVoList = new ArrayList<>();
        for(int i = 0; i < Constants.SYTABLE_POSTION_ARR.length; i++) {
            String position = Constants.SYTABLE_POSTION_ARR[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleNo("");

            labSampleInfoVoList.add(sampleInfoVo);
        }

        for (SampleInfoVo siVo : sampleInfoVoList) {
            for (SampleInfoVo sampleInfoVo : labSampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getExtractPlateLocation()) &&
                            siVo.getEntity().getExtractPlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_KZ.equals(stage)){
                    if (StringUtils.isNotBlank(siVo.getEntity().getPcrPlateLocation()) &&
                            siVo.getEntity().getPcrPlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_SY.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSyPlateLocation()) &&
                            siVo.getEntity().getSyPlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSamplePlateLocation()) &&
                            siVo.getEntity().getSamplePlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }
            }
        }

        return labSampleInfoVoList;
    }
    /**
     * 转换样本信息
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> tranferInfo(List<SampleInfoVo> sampleInfoVoList, int plateSort, String stage, SampleInfoVo siVo) {
        List<SampleInfoVo> newSampleInfoVoList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            for (SampleInfoVo sampleInfoVo : sampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSamplePlateSort()) &&
                            siVo.getEntity().getSamplePlateSort().equals(sampleInfoVo.getEntity().getSamplePlateSort())) {
                        sampleInfoVo.getEntity().setExtractPlateSort(String.valueOf(plateSort));
                        int locationSort = 0;
                        if (sampleInfoVo.getEntity().getSampleLocationSort() != null) {
                            locationSort = sampleInfoVo.getEntity().getSampleLocationSort().intValue();
                        }
                        int sort = 24*(plateSort - 1) + locationSort%24;
                        sampleInfoVo.getEntity().setExtractPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sort-1]);
                        sampleInfoVo.getEntity().setExtractLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sort-1]);
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }else if (Constants.STAGE_KZ.equals(stage)){
                    if (StringUtils.isNotBlank(siVo.getEntity().getExtractPlateSort()) &&
                            siVo.getEntity().getExtractPlateSort().equals(sampleInfoVo.getEntity().getExtractPlateSort())) {
                        sampleInfoVo.getEntity().setPcrPlateSort(String.valueOf(plateSort));
                        int locationSort = 0;
                        if (sampleInfoVo.getEntity().getExtractLocationSort() != null) {
                            locationSort = sampleInfoVo.getEntity().getExtractLocationSort().intValue();
                        }
                        int sort = 24*(plateSort - 1) + locationSort%24;
                        sampleInfoVo.getEntity().setPcrPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sort-1]);
                        sampleInfoVo.getEntity().setPcrLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sort-1]);
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }else if (Constants.STAGE_SY.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getPcrPlateSort()) &&
                            siVo.getEntity().getPcrPlateSort().equals(sampleInfoVo.getEntity().getPcrPlateSort())) {
                        sampleInfoVo.getEntity().setSyPlateSort(String.valueOf(plateSort));
                        int locationSort = 0;
                        if (sampleInfoVo.getEntity().getPcrLocationSort() != null) {
                            locationSort = sampleInfoVo.getEntity().getPcrLocationSort().intValue();
                        }
                        int sort = 24*(plateSort - 1) + locationSort%24;
                        sampleInfoVo.getEntity().setSyPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sort-1]);
                        sampleInfoVo.getEntity().setSyLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sort-1]);
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }
            }
        }


        return newSampleInfoVoList;
    }
}
