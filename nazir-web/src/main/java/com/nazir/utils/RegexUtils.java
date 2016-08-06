package com.nazir.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @Type RegexUtils
 * @Desc
 * @Version V1.0
 */
public class RegexUtils {

	/**
	 * 正则表达式匹配
	 * @param pattern	正则表达式
	 * @param value		匹配字符串
	 * @return
	 */
	public static boolean regexMatch(String pattern, String value) {
		if (StringUtils.isBlank(pattern) || StringUtils.isBlank(value)) {
			return false;
		}

		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(value);
		return matcher.matches();
	}

}
