package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LabSySample;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: lzn
 * @Date: 2019/9/8 23:43
 * @Version: 1.0
 */
public class LabSySampleVo implements Comparable<LabSySampleVo> {
    private List<LabSySample> greplist;
    //检材数
    private int sampleCount;

    public List<LabSySample> getGreplist() {
        return greplist;
    }

    public void setGreplist(List<LabSySample> greplist) {
        this.greplist = greplist;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }
    /** 板孔位置 */
    private String samplePostion;

    public String getSamplePostion() {
        return samplePostion;
    }

    public void setSamplePostion(String samplePostion) {
        this.samplePostion = samplePostion;
    }

    @Override
    public int compareTo(LabSySampleVo o) {
        String samplePostion = o.samplePostion;
        String substring = samplePostion.substring(1);
        String substring1 = samplePostion.substring(0, 1);
        String sub = this.samplePostion.substring(1);
        String sub1 = this.samplePostion.substring(0, 1);
        if (substring1.equals(sub1)){//判断字母是否相等
            return this.samplePostion.substring(1).compareTo(samplePostion.substring(1));
        }else if (sub.equals(substring)){//判断数字是否相等
            return this.samplePostion.compareTo(o.samplePostion);
        }
        return this.samplePostion.substring(1).compareTo(samplePostion.substring(1));
    }
}
