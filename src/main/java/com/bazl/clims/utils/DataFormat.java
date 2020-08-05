package com.bazl.clims.utils;

import java.math.BigDecimal;

public class DataFormat {

	static String CHN_NUMBER[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
	static String CHN_UNIT[] = {"", "十", "百", "千"};          //权位
	static String CHN_UNIT_SECTION[] = {"", "万", "亿", "万亿"}; //节权位

	/**
	 * 将double型数据格式化为科学计数法，返回值为String类型
	 * 
	 * @param number
	 *            源数据
	 * @param totalLength
	 *            格式化后的总长度(整数部分长度+小数部分长度)
	 * @param integerLength
	 *            格式化后整数部分长度(0表示结果以"0.xxEx"格式返回，整数部分始终为0)
	 * @param fixed
	 *            格式化后总长度是否为固定值(true表示长度固定，用0填充；false表示长度可变)
	 * @return 科学计数法
	 */
	public static String formatDecimal(double number, int totalLength,
			int integerLength, boolean fixed) {
		if (totalLength < 0 || integerLength < 0 || totalLength < integerLength) {
			return null;
		}

		StringBuffer pattern = new StringBuffer();
		// if integer-length is 0, then the decimal is formated to "0.xxxEx"
		if (integerLength == 0) {
			pattern.append("'0'");
		}
		// else create integer part with the given length
		for (int i = 0; i < integerLength; i++) {
			pattern.append("0");
		}
		// add symbol "."
		if (totalLength > integerLength) {
			pattern.append(".");
		}
		// create decimal part with the given length ( = total-length minus
		// integer-length)
		for (int i = 0; i < totalLength - integerLength; i++) {
			pattern.append(fixed ? "0" : "#");
		}
		// create the power part
		pattern.append("E0");

		java.text.DecimalFormat formator = new java.text.DecimalFormat(pattern
				.toString());
		return formator.format(number);
	}

	/**
	 * 
	 * @param str
	 *            累计PI值
	 * @return 累计PI值得科学技术发
	 */
	public static  String StringChange(String str) {
		String[] a = str.split("\\.");
		String RetrurnString = null;
		if (a.length > 1) {
			if (a[1].indexOf("E") != -1) {
				if (a[0].length() > 1) {
					RetrurnString = a[0].substring(0, 1)
							+ "."
							+ a[0].substring(1, 2)
							+ "E"
							+ ((a[0].length() - 1) + Integer.parseInt(a[1]
									.split("E")[1]));
				} else {
					RetrurnString = str.substring(0, 3) + "E"
							+ a[1].split("E")[1];
				}
			} else {
				if (a[0].length() > 1) {
					RetrurnString = a[0].substring(0, 1) + "."
							+ a[0].substring(1, 2) + "E" + (a[0].length() - 1);
				} else {
					RetrurnString = str.substring(0, 3);
				}
			}
		} else {
			RetrurnString = "0";
		}
		return RetrurnString;
	}

	/**
	 * 阿拉伯数字转换为中文数字的核心算法实现。
	 * @param num 为需要转换为中文数字的阿拉伯数字，是无符号的整形数
	 * @return
	 */
	public static String NumberToChn(int num) {
		StringBuffer returnStr = new StringBuffer();
		Boolean needZero = false;
		//节权位的位置
		int pos=0;
		if(num==0){
			//如果num为0，进行特殊处理。
			returnStr.insert(0,CHN_NUMBER[0]);
		}
		while (num > 0) {
			int section = num % 10000;
			if (needZero) {
				returnStr.insert(0, CHN_NUMBER[0]);
			}
			String sectionToChn = SectionNumToChn(section);
			//判断是否需要节权位
			sectionToChn += (section != 0) ? CHN_UNIT_SECTION[pos] : CHN_UNIT_SECTION[0];
			returnStr.insert(0, sectionToChn);
			//判断section中的千位上是不是为零，若为零应该添加一个零。
			needZero = ((section < 1000 && section > 0) ? true : false);
			pos++;
			num = num / 10000;
		}
		return returnStr.toString();
	}

	/**
	 * 将四位的section转换为中文数字
	 * @param section
	 * @return
	 */
	public static String SectionNumToChn(int section) {
		StringBuffer returnStr = new StringBuffer();
		int unitPos = 0;       //节权位的位置编号，0-3依次为个十百千;

		Boolean zero = true;
		while (section > 0) {

			int v = (section % 10);
			if (v == 0) {
				if ((section == 0) || !zero) {
					zero = true; /*需要补0，zero的作用是确保对连续的多个0，只补一个中文零*/
					//chnStr.insert(0, chnNumChar[v]);
					returnStr.insert(0, CHN_NUMBER[v]);
				}
			} else {
				zero = false; //至少有一个数字不是0
				StringBuffer tempStr = new StringBuffer(CHN_NUMBER[v]);//数字v所对应的中文数字
				tempStr.append(CHN_UNIT[unitPos]);  //数字v所对应的中文权位
				returnStr.insert(0, tempStr);
			}
			unitPos++; //移位
			section = section / 10;
		}
		return returnStr.toString();
	}

    public static String convertDoubleToString(double val) {
        BigDecimal bd = new BigDecimal(String.valueOf(val));
        return bd.stripTrailingZeros().toPlainString();
    }
	public static void main(String args[]) {
		double a = 1.676788778;
		System.out.println(NumberToChn(2));
		System.out.println(formatDecimal(a, 3, 1, false));
	}
}
