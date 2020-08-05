package com.bazl.clims.compare.relative.impl;


import com.bazl.clims.compare.basedata.PopulationAlleleFrequencyProvider;
import com.bazl.clims.compare.relative.ParentageCalculator;
import com.bazl.clims.compare.relative.po.Marker;
import com.bazl.clims.compare.relative.po.ParentageMatchOptions;
import com.bazl.clims.compare.relative.po.ParentageMatchResult;
import com.bazl.clims.compare.relative.po.ParentageMatchResultRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Sun on 2019/4/2.
 */
@Service
public class EssenMollerParentageCalculator implements ParentageCalculator {
    private static Log log = LogFactory.getLog(EssenMollerParentageCalculator.class);
    @Autowired
    private PopulationAlleleFrequencyProvider freqProvider;
    private static double DEFAULT_MUTATION_RATE = 0.002D;
    private static double PI_MISMATCH = 0.0D / 0.0;
    private static double DEFAULT_FREQUENCY = 0.001D;

    public EssenMollerParentageCalculator() {
    }

    public void setPopulationAlleleFrequencyProvider(PopulationAlleleFrequencyProvider freqProvider) {
        this.freqProvider = freqProvider;
    }

    @Override
    public ParentageMatchResult calculate(Map<String, Marker> af, Map<String, Marker> m, Map<String, Marker> c, ParentageMatchOptions options) {
        log.debug("进入calculate方法。");
        ParentageMatchResult result = new ParentageMatchResult();
        ParentageMatchResultRecord afcmRecord = new ParentageMatchResultRecord();
        ParentageMatchResultRecord afRecord = new ParentageMatchResultRecord();
        ParentageMatchResultRecord mRecord = new ParentageMatchResultRecord();
        afcmRecord.setPiOfMarker(new Hashtable());
        afRecord.setPiOfMarker(new Hashtable());
        mRecord.setPiOfMarker(new Hashtable());
        log.debug("遍历C的marker，计算三联体、二联体的PI值。");
        Iterator var10 = c.keySet().iterator();

        String markerName;
        while(var10.hasNext()) {
            markerName = (String)var10.next();
            boolean piOfMutation = RelativeCalculationHelper.findMatchAlleleName((Marker)c.get(markerName), (Marker)m.get(markerName)) != null;
            Double maxMutationStep;
            if(piOfMutation && af.get(markerName) != null) {
                maxMutationStep = this.calculateAfcmPi(options.getPopulationId(), markerName, (Marker)af.get(markerName), (Marker)m.get(markerName), (Marker)c.get(markerName));
                afcmRecord.getPiOfMarker().put(markerName, maxMutationStep);
            }

            maxMutationStep = this.calculateAfPi(options.getPopulationId(), markerName, (Marker)af.get(markerName), (Marker)c.get(markerName));
            afRecord.getPiOfMarker().put(markerName, maxMutationStep);
            Double mPi = this.calculateMPi(options.getPopulationId(), markerName, (Marker)m.get(markerName), (Marker)c.get(markerName));
            mRecord.getPiOfMarker().put(markerName, mPi);
        }

        log.debug("根据计算出来的PI值，计算三联体、二联体的matchCount。");
        afcmRecord.setMatchCount(this.calculateMatchCount(afcmRecord));
        afRecord.setMatchCount(this.calculateMatchCount(afRecord));
        mRecord.setMatchCount(this.calculateMatchCount(mRecord));
        log.debug("根据计算出来的PI值，计算三联体、二联体的diffCount。");
        afcmRecord.setDiffCount(this.calculateDiffCount(afcmRecord));
        afRecord.setDiffCount(this.calculateDiffCount(afRecord));
        mRecord.setDiffCount(this.calculateDiffCount(mRecord));
        log.debug("检查三联体、二联体是否满足options中给出的条件。");
        afcmRecord.setSuccessful(this.isFitOptions(afcmRecord, options));
        afRecord.setSuccessful(this.isFitOptions(afRecord, options));
        mRecord.setSuccessful(this.isFitOptions(mRecord, options));
        log.debug("计算突变基因座的PI");
        if(afcmRecord.isSuccessful()) {
            var10 = afcmRecord.getPiOfMarker().keySet().iterator();

            label34:
            while(true) {
                Double piOfMutation1;
                do {
                    if(!var10.hasNext()) {
                        break label34;
                    }

                    markerName = (String)var10.next();
                    piOfMutation1 = (Double)afcmRecord.getPiOfMarker().get(markerName);
                } while(!Double.isNaN(piOfMutation1.doubleValue()) && piOfMutation1.doubleValue() >= 0.0D);

                log.debug("突变基因座：" + markerName);
                boolean maxMutationStep1 = true;
                piOfMutation1 = this.calculateAfcmPiOfMutation(options.getPopulationId(), markerName, (Marker)af.get(markerName), (Marker)m.get(markerName), (Marker)c.get(markerName), 3);
                afcmRecord.getPiOfMarker().put(markerName, piOfMutation1);
            }
        }

        log.debug("计算三联体、二联体的累计PI。");
        afcmRecord.setPi(this.calculateTotalPi(afcmRecord.getPiOfMarker().values()));
        afRecord.setPi(this.calculateTotalPi(afRecord.getPiOfMarker().values()));
        mRecord.setPi(this.calculateTotalPi(mRecord.getPiOfMarker().values()));
        result.setResult(afcmRecord);
        result.setResultOfAf(afRecord);
        result.setResultOfM(mRecord);
        log.debug("计算完毕，返回结果。");
        return result;
    }

    private Double calculateProbability(Marker parent, String childAllele, double childAlleleFreq, int maxStep, double mutationRate) {
        if(parent == null) {
            return Double.valueOf(childAlleleFreq);
        } else {
            double matchCount = 0.0D;
            double mutationFactor = 0.0D;
            Iterator var13 = parent.iterator();

            while(var13.hasNext()) {
                String parentAllele = (String)var13.next();
                if(parentAllele == null || parentAllele.equals("")) {
                    return Double.valueOf(childAlleleFreq);
                }

                int mutationStep = (int)Math.abs(Double.parseDouble(parentAllele) - Double.parseDouble(childAllele));
                if(mutationStep == 0) {
                    ++matchCount;
                } else if(mutationStep <= maxStep) {
                    mutationFactor += Math.pow(0.1D, (double)(mutationStep - 1));
                }
            }

            return Double.valueOf(matchCount != 0.0D?matchCount / 2.0D:mutationRate * 0.25D * mutationFactor);
        }
    }

    private Double calculateAfcmPiOfMutation(String populationId, String markerName, Marker af, Marker m, Marker c, int maxMutationStep) {
        this.checkMarker(af);
        this.checkMarker(m);
        this.checkMarker(c);
        String childAllele1 = (String)c.get(0);
        double p1 = this.freqProvider.get(populationId, markerName, childAllele1, DEFAULT_FREQUENCY);
        String childAllele2 = (String)c.get(1);
        double p2 = this.freqProvider.get(populationId, markerName, childAllele2, DEFAULT_FREQUENCY);
        Double m1 = this.calculateProbability(m, childAllele1, p1, maxMutationStep, DEFAULT_MUTATION_RATE / 3.5D);
        Double m2 = this.calculateProbability(m, childAllele2, p2, maxMutationStep, DEFAULT_MUTATION_RATE / 3.5D);
        Double af1 = Double.valueOf(0.0D);
        if(!m.contains(childAllele1) || m.contains(childAllele2)) {
            af1 = this.calculateProbability(af, childAllele1, p1, maxMutationStep, DEFAULT_MUTATION_RATE);
        }

        Double af2 = Double.valueOf(0.0D);
        if(!m.contains(childAllele2) || m.contains(childAllele1)) {
            af2 = this.calculateProbability(af, childAllele2, p2, maxMutationStep, DEFAULT_MUTATION_RATE);
        }

        Double rm1 = this.calculateProbability((Marker)null, childAllele1, p1, maxMutationStep, DEFAULT_MUTATION_RATE);
        Double rm2 = this.calculateProbability((Marker)null, childAllele2, p2, maxMutationStep, DEFAULT_MUTATION_RATE);
        Double X = Double.valueOf(m1.doubleValue() * af2.doubleValue() + m2.doubleValue() * af1.doubleValue());
        Double Y = Double.valueOf(m1.doubleValue() * rm2.doubleValue() + m2.doubleValue() * rm1.doubleValue());
        return Double.valueOf(X.doubleValue() / Y.doubleValue());
    }

    private double calculateTotalPi(Collection<Double> piOfMarker) {
        double result = 1.0D;
        Iterator var5 = piOfMarker.iterator();

        while(var5.hasNext()) {
            Double pi = (Double)var5.next();
            if(!Double.isNaN(pi.doubleValue()) && pi.doubleValue() > 0.0D) {
                result *= pi.doubleValue();
            }
        }

        return result;
    }

    private boolean isFitOptions(ParentageMatchResultRecord record, ParentageMatchOptions options) {
        return record.getMatchCount() >= options.getMatchThreshold() && record.getDiffCount() <= options.getHalfDiffCount();
    }

    private int calculateMatchCount(ParentageMatchResultRecord record) {
        int result = 0;
        Iterator var4 = record.getPiOfMarker().values().iterator();

        while(var4.hasNext()) {
            Double pi = (Double)var4.next();
            if(!Double.isNaN(pi.doubleValue()) && pi.doubleValue() > 0.0D) {
                ++result;
            }
        }

        return result;
    }

    private int calculateDiffCount(ParentageMatchResultRecord record) {
        int result = 0;
        Iterator var4 = record.getPiOfMarker().values().iterator();

        while(var4.hasNext()) {
            Double pi = (Double)var4.next();
            if(pi.doubleValue() < 0.0D) {
                ++result;
            }
        }

        return result;
    }

    private Double calculateMPi(String populationId, String markerName, Marker m, Marker c) {
        return this.calculateAfPi(populationId, markerName, m, c);
    }

    private Double calculateAfPi(String populationId, String markerName, Marker af, Marker c) {
        this.checkMarker(af);
        this.checkMarker(c);
        if(af != null && c != null) {
            boolean afIsHomozygote = RelativeCalculationHelper.isHomozygote(af);
            boolean cIsHomozygote = RelativeCalculationHelper.isHomozygote(c);
            String qAlleleName;
            double q;
            if(!afIsHomozygote && !cIsHomozygote && RelativeCalculationHelper.isOnlyOneMatch(c, af)) {
                qAlleleName = RelativeCalculationHelper.findMatchAlleleName(af, c);
                q = this.freqProvider.get(populationId, markerName, qAlleleName, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (4.0D * q));
            } else if(!afIsHomozygote && !cIsHomozygote && RelativeCalculationHelper.allAlleleNamesAreSame(c, af)) {
                qAlleleName = (String)c.get(0);
                String q2 = (String)c.get(1);
                double p = this.freqProvider.get(populationId, markerName, qAlleleName, DEFAULT_FREQUENCY);
                double q1 = this.freqProvider.get(populationId, markerName, q2, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (4.0D * p) + 1.0D / (4.0D * q1));
            } else if((!afIsHomozygote && cIsHomozygote || afIsHomozygote && !cIsHomozygote) && RelativeCalculationHelper.findMatchAlleleName(af, c) != null) {
                qAlleleName = RelativeCalculationHelper.findMatchAlleleName(af, c);
                q = this.freqProvider.get(populationId, markerName, qAlleleName, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(afIsHomozygote && cIsHomozygote && RelativeCalculationHelper.allAlleleNamesAreSame(af, c)) {
                qAlleleName = (String)c.get(0);
                q = this.freqProvider.get(populationId, markerName, qAlleleName, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / q);
            } else {
                return Double.valueOf(PI_MISMATCH);
            }
        } else {
            return Double.valueOf(0.0D / 0.0);
        }
    }

    private Double calculateAfcmPi(String populationId, String markerName, Marker af, Marker m, Marker c) {
        this.checkMarker(af);
        this.checkMarker(m);
        this.checkMarker(c);
        if(af != null && c != null && m != null) {
            boolean afIsHomozygote = RelativeCalculationHelper.isHomozygote(af);
            boolean cIsHomozygote = RelativeCalculationHelper.isHomozygote(c);
            boolean mIsHomozygote = RelativeCalculationHelper.isHomozygote(m);
            double q;
            String p1;
            if(!afIsHomozygote && !cIsHomozygote && !mIsHomozygote && RelativeCalculationHelper.isOnlyOneMatch(c, m) && RelativeCalculationHelper.isOnlyOneMatch(c, af) && !RelativeCalculationHelper.findMatchAlleleName(c, m).equalsIgnoreCase(RelativeCalculationHelper.findMatchAlleleName(c, af))) {
                p1 = RelativeCalculationHelper.findMatchAlleleName(af, c);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(!afIsHomozygote && !cIsHomozygote && mIsHomozygote && RelativeCalculationHelper.isOnlyOneMatch(c, m) && RelativeCalculationHelper.isOnlyOneMatch(c, af) && !RelativeCalculationHelper.findMatchAlleleName(c, m).equalsIgnoreCase(RelativeCalculationHelper.findMatchAlleleName(c, af))) {
                p1 = RelativeCalculationHelper.findMatchAlleleName(af, c);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(!afIsHomozygote && !cIsHomozygote && !mIsHomozygote && RelativeCalculationHelper.isOnlyOneMatch(c, m) && RelativeCalculationHelper.allAlleleNamesAreSame(c, af)) {
                p1 = RelativeCalculationHelper.findNotMatchAlleleName(c, m);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(!afIsHomozygote && !cIsHomozygote && mIsHomozygote && RelativeCalculationHelper.isOnlyOneMatch(c, m) && RelativeCalculationHelper.allAlleleNamesAreSame(c, af)) {
                p1 = RelativeCalculationHelper.findNotMatchAlleleName(c, m);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(!afIsHomozygote && cIsHomozygote && !mIsHomozygote && RelativeCalculationHelper.isOnlyOneMatch(af, c) && RelativeCalculationHelper.isOnlyOneMatch(m, c) && !RelativeCalculationHelper.allAlleleNamesAreSame(af, m)) {
                p1 = (String)c.get(0);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(!afIsHomozygote && cIsHomozygote && !mIsHomozygote && RelativeCalculationHelper.isOnlyOneMatch(af, c) && RelativeCalculationHelper.isOnlyOneMatch(m, c) && RelativeCalculationHelper.allAlleleNamesAreSame(af, m)) {
                p1 = (String)c.get(0);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(!afIsHomozygote && cIsHomozygote && mIsHomozygote && RelativeCalculationHelper.allAlleleNamesAreSame(c, m) && RelativeCalculationHelper.isOnlyOneMatch(af, c)) {
                p1 = (String)c.get(0);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / (2.0D * q));
            } else if(afIsHomozygote && c.contains(af.get(0)) && RelativeCalculationHelper.findMatchAlleleName(c, m) != null && (!RelativeCalculationHelper.allAlleleNamesAreSame(c, m) || cIsHomozygote && mIsHomozygote)) {
                p1 = (String)af.get(0);
                q = this.freqProvider.get(populationId, markerName, p1, DEFAULT_FREQUENCY);
                return Double.valueOf(1.0D / q);
            } else {
                double p;
                double q1;
                if(!afIsHomozygote && !cIsHomozygote && !mIsHomozygote && RelativeCalculationHelper.allAlleleNamesAreSame(c, m) && !RelativeCalculationHelper.allAlleleNamesAreSame(af, c) && RelativeCalculationHelper.findMatchAlleleName(af, c) != null) {
                    p = this.freqProvider.get(populationId, markerName, (String)c.get(0), DEFAULT_FREQUENCY);
                    q1 = this.freqProvider.get(populationId, markerName, (String)c.get(1), DEFAULT_FREQUENCY);
                    return Double.valueOf(1.0D / (2.0D * p + 2.0D * q1));
                } else if(!afIsHomozygote && !cIsHomozygote && !mIsHomozygote && RelativeCalculationHelper.allAlleleNamesAreSame(c, m) && RelativeCalculationHelper.allAlleleNamesAreSame(af, c)) {
                    p = this.freqProvider.get(populationId, markerName, (String)c.get(0), DEFAULT_FREQUENCY);
                    q1 = this.freqProvider.get(populationId, markerName, (String)c.get(1), DEFAULT_FREQUENCY);
                    return Double.valueOf(1.0D / (p + q1));
                } else if(afIsHomozygote && !cIsHomozygote && !mIsHomozygote && RelativeCalculationHelper.allAlleleNamesAreSame(c, m) && c.contains(af.get(0))) {
                    p = this.freqProvider.get(populationId, markerName, (String)c.get(0), DEFAULT_FREQUENCY);
                    q1 = this.freqProvider.get(populationId, markerName, (String)c.get(1), DEFAULT_FREQUENCY);
                    return Double.valueOf(1.0D / (p + q1));
                } else {
                    return Double.valueOf(PI_MISMATCH);
                }
            }
        } else {
            return Double.valueOf(0.0D / 0.0);
        }
    }

    private void checkMarker(Marker m) {
        if(m != null && m.size() != 2) {
            log.error("计算亲缘的Marker不为null，包含的allele个数不是2，异常结束！");
            throw new RuntimeException();
        }
    }
}
