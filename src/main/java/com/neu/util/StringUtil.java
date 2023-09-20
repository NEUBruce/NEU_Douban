package com.neu.util;

import java.util.Collection;

public class StringUtil {
	public static String connectString(Collection<String> stringList,
			String split) {
		StringBuilder builder = new StringBuilder();
		boolean flag = false;

		for (String s : stringList) {
			if (flag)
				builder.append(split);
			else {
				flag = true;
			}

			builder.append(s);
		}
		return builder.toString();
	}

	//判断字符串是否为null或者""
	public static boolean isEmpty(String str){
		return str==null || "".equals(str);
	}

	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
}