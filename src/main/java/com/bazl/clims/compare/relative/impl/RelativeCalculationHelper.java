package com.bazl.clims.compare.relative.impl;

import com.bazl.clims.compare.relative.po.Marker;

import java.util.Iterator;

/**
 * Created by Sun on 2019/4/2.
 */
public class RelativeCalculationHelper {
    private RelativeCalculationHelper() {
    }

    public static boolean isOnlyOneMatch(Marker m1, Marker m2) {
        return m2.contains(m1.get(0)) && !m2.contains(m1.get(1)) || m2.contains(m1.get(1)) && !m2.contains(m1.get(0));
    }

    public static boolean isHomozygote(Marker m) {
        if(m != null && m.size() != 0) {
            String firstAlleleName = (String)m.get(0);
            Iterator var3 = m.iterator();

            while(var3.hasNext()) {
                String alleleName = (String)var3.next();
                if(!alleleName.equalsIgnoreCase(firstAlleleName)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static String findMatchAlleleName(Marker m1, Marker m2) {
        if(m1 != null && m2 != null) {
            Iterator var3 = m1.iterator();

            while(var3.hasNext()) {
                String alleleName = (String)var3.next();
                if(m2.contains(alleleName)) {
                    return alleleName;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static String findNotMatchAlleleName(Marker m1, Marker m2) {
        Iterator var3 = m1.iterator();

        while(var3.hasNext()) {
            String alleleName = (String)var3.next();
            if(!m2.contains(alleleName)) {
                return alleleName;
            }
        }

        return null;
    }

    public static boolean allAlleleNamesAreSame(Marker m1, Marker m2) {
        return m1.containsAll(m2) && m2.containsAll(m1);
    }
}
