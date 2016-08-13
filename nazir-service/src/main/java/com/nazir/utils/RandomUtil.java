package com.nazir.utils;

import java.util.Random;

/**
 * @author luogm
 *
 */
public class RandomUtil {

	/**
	 * 获取随机n位数字，以字符串形式返回
	 * @return
	 */
	public static String getRandomNumString(int num) {
		Random random = new Random();
		String result="";
		for(int i=0; i<num; i++){
			result+=random.nextInt(10);
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomUtil.getRandomNumString(6));
	}
}
