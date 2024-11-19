package cn.Ebook.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	 /*加载数据库驱动*/
	
	   /*指定数据库连接字符串*/
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/ebookdb?userSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
	public Connection connection =null;
	public DBConnection() {
		try {
			Class.forName(dbDriver).newInstance();
			connection = DriverManager.getConnection(url,"root","lzx1023");
		}catch(Exception ex) {
			System.out.println("数据库加载失败");
		}
	}
	
}
