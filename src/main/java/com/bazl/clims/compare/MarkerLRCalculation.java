package com.bazl.clims.compare;

import com.bazl.clims.model.po.AlleleFrequency;
import com.bazl.clims.model.po.Race;
import com.bazl.clims.utils.ListUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun on 2019/4/1.
 */
@Service
public class MarkerLRCalculation {

    /**
     * 北京法医的算法
     */
    public Double calculateSingleMarkerLR(Race race, String markerName, String alleleName1, String alleleName2) {
        Double lr = Double.NaN;
        Double p = getAlleleFreqs(race, markerName, alleleName1);
        if (!p.isNaN()) {
            if (alleleName1.compareTo(alleleName2) == 0) {
                lr = 1 / (p * p);
            } else {
                Double q = getAlleleFreqs(race, markerName, alleleName2);
                if(!q.isNaN()) {
                    lr = 1 / (2 * p * q);
                }
            }
        }
        return lr;
    }

    /**
     * 获取基因频率。
     *
     * 若找不到对应基因频率，则认为是稀有基因，根据顶棚原理，取最小值。
     * 即取该基因座下所有等位基因频率中的最小值，假设为p。这种方法将稀有基因的证据作用缩到最大，
     * 因为计算似然比的时候会乘以p的倒数，p最小则其倒数最大，对于总LR值的贡献最大。
     */
    public Double getAlleleFreqs(Race race, String markerName, String alleleName) {
        Double af = GetAlleleFreqs(markerName, alleleName);
        if (af == 1.0d) {
            af = GetMinAlleleFreq(markerName);
        }
        return af;
    }

    /**
     * Get allele frequency by marker name and allele name.
     *
     * @param markername
     * @param allelename
     * @return allele frequency if found the allele; 1.0d if not
     */
    public double GetAlleleFreqs(String markername, String allelename) {
        for (AlleleFrequency allele : InitDict.alleles) {
            if (allele.getMarkerName().compareToIgnoreCase(markername) == 0 && allele.getAlleleName().compareTo(allelename) == 0) {
                return allele.getAlleleFrequency();
            }
        }
        return 1.0d;
    }

    /**
     * get minimum allele frequency of the specified marker
     *
     * @param markerName
     * @return allele frequency if succeeded; 1.0d if no marker is found
     */
    public Double GetMinAlleleFreq(String markerName) {
        List<AlleleFrequency> alleles = new ArrayList<>();
        for (AlleleFrequency allele : InitDict.alleles) {
            if (allele.getMarkerName().compareToIgnoreCase(markerName) == 0) {
                alleles.add(allele);
            }
        }
        if(ListUtils.isNotNullAndEmptyList(alleles)){
            return getMinAlleleFreq(alleles);
        }else{
            return Double.NaN;
        }
    }

    final int AF_MIN = 0;
    final int AF_MAX = 1;

    public Double getMinAlleleFreq(List<AlleleFrequency> alleles) {
        return getTopAlleleFreq(alleles, AF_MIN);
    }
    public Double getMaxAlleleFreq(List<AlleleFrequency> alleles) {
        return getTopAlleleFreq(alleles, AF_MAX);
    }

    private Double getTopAlleleFreq(List<AlleleFrequency> alleles, int type) {
        Double af = 0.0d;
        if(alleles.size() > 0) {
            af = alleles.get(0).getAlleleFrequency();
        }
        for (AlleleFrequency allele: alleles) {
            if (allele.getAlleleFrequency() > af) {
                if (type == AF_MAX) {
                    af = allele.getAlleleFrequency();
                }
            } else {
                if (type == AF_MIN) {
                    af = allele.getAlleleFrequency();
                }
            }
        }
        return (af <= 0.0) ? Double.NaN : af;
    }
}
