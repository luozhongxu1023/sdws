package cn.Ebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.Ebook.entity.User;
import cn.Ebook.util.DBConnection;




//用户的操作
public class UserDao {
	private Connection connection=null;//定义数据库连接对象
	private PreparedStatement pStatement=null; //定义数据库访问对象
	private DBConnection jdbc=null; //面向本数据库的连接对象
	

	public UserDao() {//连接本数据库
		jdbc=new DBConnection();
		connection=jdbc.connection;
	} 
	
	  //全部查询的操作或以用户名称为条件查询信息
	  public User selectUser(String username) {
		  User user=null;
			try {
				pStatement=connection.prepareStatement("select * from tb_user where username=?");
				pStatement.setString(1, username);
				ResultSet rs=pStatement.executeQuery();
				while (rs.next()) {
					user=new User();
					user.setId(Integer.valueOf(rs.getString(1)));
					user.setUsername(rs.getString(2));
					user.setPASSWORD(rs.getString(3));
					user.setEmail(rs.getString(4));
					user.setTelephone(rs.getString(5));
				}
				}
				catch(SQLException ex) {
					System.out.println("数据库访问失败");
				}
			return user;
	  }
	  
	//添加用户注册信息
	  public boolean addUser(User user) {
		  try {
			    pStatement= connection.prepareStatement("insert into tb_user(username,PASSWORD,email,telephone) values(?,?,?,?)");
				//pSratement.setString(1,String.valueOf(user.getId()));//id不需要插入，因为数据表中的id是自动增加的
			    pStatement.setString(1,user.getUsername());
			    pStatement.setString(2,user.getPASSWORD());
			    pStatement.setString(3,user.getEmail());
			    pStatement.setString(4,user.getTelephone());		  
				pStatement.executeUpdate();
				return true;
			}
			catch(SQLException ex) {
				System.out.println(ex.getMessage());
				return false;
			}

}
}