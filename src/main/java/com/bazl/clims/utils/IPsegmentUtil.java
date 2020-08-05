package com.bazl.clims.utils;

/**
 * 判断IP是否在本区域的ip段中
 */
public class IPsegmentUtil {
    public static boolean inIpsegment(String ipAddress,String ipSegmentStr){
        //1.首先解析IP段
        String ipSegStr[]=ipSegmentStr.split("-");
        String startIP[]=ipSegStr[0].split("[.]");
        String endIP[]=ipSegStr[1].split("[.]");
        String testIP[]=ipAddress.split("[.]");
        for (int i = 0; i < 4; i++) {
            int start = Integer.parseInt(startIP[i]);
            int end = Integer.parseInt(endIP[i]);
            int test = Integer.parseInt(testIP[i]);
            if (test >= start && test <= end);
             else
                return false;
        }
        return true;
    }
}
