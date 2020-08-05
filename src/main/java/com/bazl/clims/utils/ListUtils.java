package com.bazl.clims.utils;

import com.bazl.clims.model.po.MatchAuditedGene;

import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 */
public class ListUtils {

    public static boolean isNotNullAndEmptyList(List<?> list) {
        return list != null && list.size() > 0;
    }

    public static boolean isNullOrEmptyList(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isNull(String str) {
        boolean result = false;
        if (str != null && !"".equals(str) && !"null".equalsIgnoreCase(str) && !"NaN".equalsIgnoreCase(str)  && !"undefined".equalsIgnoreCase(str)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 去除list中重复数据
     * @param list
     * @return
     */
    public static List<MatchAuditedGene> removeDuplicate(List<MatchAuditedGene> list) {
        for(int i = 0 ; i < list.size() - 1; i++ ) {
            for( int j = list.size() - 1 ; j > i; j -- ) {
                if (list.get(j) != null && list.get(i) != null) {
                    if (list.get(j).getSampleId().equals(list.get(i).getSampleId())) {
                        list.remove(j);
                    }
                }
            }
        }
        return list;
    }
}
