package com.bazl.clims.webservice.util;

import com.bazl.clims.webservice.client.libMatch.ArrayOfWSAdditionOption;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;


public class ConvertString {
	/**
	 * 转换类型
	 * 
	 * @param converString
	 *            内容
	 * @param filedName
	 *            类型
	 * @return
	 */
	public static JAXBElement<String> convertString(String converString,
			String filedName) {
		JAXBElement<String> result = new JAXBElement<String>(
				new QName(
						"http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com",
						filedName), String.class, converString);
		return result;
	}
	
	public static JAXBElement<String> convertString(String converString,String nameSapce,
			String filedName) {
		JAXBElement<String> result = new JAXBElement<String>(
				new QName(
						nameSapce,
						filedName), String.class, converString);
		return result;
	}
	
	public static JAXBElement<ArrayOfWSAdditionOption> convertArr(ArrayOfWSAdditionOption  converArr,
																  String filedName) {
		JAXBElement<ArrayOfWSAdditionOption> result = new JAXBElement<ArrayOfWSAdditionOption>(
				new QName(
						"http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com",
						filedName), ArrayOfWSAdditionOption.class, converArr);
		return result;
	}
}
