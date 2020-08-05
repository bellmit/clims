package com.bazl.clims.webservice.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 基因座
 */
public class MarkerAlleleUtil {

	/**
	 * 不在试剂盒中的基因座计数器
	 */
	public static int intCnt = 1;
	
	/**
	 * 试剂盒列表
	 */
	public static List<String> markersPanelList;
	
	/**
	 * 基因座排序
	 * 将基因座列表按照试剂盒列表的顺序来排序
	 * 不在试剂盒列表中的基因座放在最后
	 * 
	 * @param alleleList			基因座列表
	 * @param markersList	试剂盒列表
	 * @return	基因座排序后列表
	 */
	public static List<Map<String, Object>> arraySortAlleleList(
			List<Map<String, Object>> alleleList, List<String> markersList) {
		markersPanelList = markersList;
		Comparator<Map<String, Object>> markerList = new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				int a = 0;
				int b = 0;
				int countA = 0;
				int countB = 0;
				for(int i=0; i<markersPanelList.size(); i++) {
					if (o1.get("markerName").toString().equalsIgnoreCase(markersPanelList.get(i))) {
						a = i;
						countA ++;
					}
					if (o2.get("markerName").toString().equalsIgnoreCase(markersPanelList.get(i))) {
						b = i;
						countB ++;
					}
				}
				if(countA < 1){
					a = markersPanelList.size() + intCnt;
					intCnt ++;
				}
				if(countB < 1){
					b = markersPanelList.size() + intCnt;
					intCnt ++;
				}
				return a - b;
			}
		};
		Collections.sort(alleleList, markerList);
		return alleleList;
	}
}
