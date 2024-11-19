package cn.Ebook.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 字符串工具类
 */
public class StringUitl {
	public static Random random = new Random();
	/**
	 * 获取当前时间字符串
	 * @return 当前时间字符串
	 */
	public static String getStringTime(){
		Date date = new Date();//获取当前系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//设置格式化格式
		return sdf.format(date);//返回格式化后的时间
	}
	/**
	 * 生成订单号
	 * @return 订单号
	 */
	public static String createOrderId(){
		StringBuffer sb = new StringBuffer();//定义字符串对象
		sb.append(getStringTime());//向字符串对象中添加当前系统时间
		for (int i = 0; i < 3; i++) {//随机生成3位数
			sb.append(random.nextInt(9));//将随机生成的数字添加到字符串对象中
		}
		return sb.toString();//返回字符串
	}

}
