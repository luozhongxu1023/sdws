package cn.Ebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.Ebook.entity.Product;
import cn.Ebook.util.DBConnection;



//商品操作
public class ProductDao {
	private Connection connection=null;//定义数据库连接对象
	private PreparedStatement pStatement=null; //定义数据库访问对象
	private DBConnection jdbc=null; //面向本数据库的连接对象
	
	public ProductDao() {//连接本数据库
		jdbc=new DBConnection();
		connection=jdbc.connection;
	} 

	// 全部查询
	public ArrayList<Product> selectAllProducts() {
		ArrayList<Product> list =new ArrayList<Product>();
		Product e =null;
		try {
			pStatement=connection.prepareStatement("select*from tb_products order by id DESC");
			ResultSet rs=pStatement.executeQuery();
			while(rs.next()) {
				e=new Product();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPrice(rs.getDouble(3));
				e.setCategory(rs.getString(4));
				e.setPnum(rs.getInt(5));
				e.setImgurl(rs.getString(6));
				e.setDescription(rs.getString(7));
					list.add(e);
			}
		}
		catch(SQLException ex) {
			System.out.println("数据库访问失败");
		}
		return list;
	}

	// 通过商品名称筛选商品
		public ArrayList<Product> SearchProductByName(String search) {
			ArrayList<Product> list =new ArrayList<Product>();
			Product e =null;
				try {
					pStatement=connection.prepareStatement("select * from tb_products where name like'%"+search+"%' order by id DESC");
					ResultSet rs=pStatement.executeQuery();
					while(rs.next()) {
						e=new Product();
						e.setId(rs.getInt(1));
						e.setName(rs.getString(2));
						e.setPrice(rs.getDouble(3));
						e.setCategory(rs.getString(4));
						e.setPnum(rs.getInt(5));
						e.setImgurl(rs.getString(6));
						e.setDescription(rs.getString(7));
							list.add(e);
					}
				
		}catch(SQLException ex) {
			System.out.print("数据库访问失败");
		}return list;
		}
	
		

	// 以商品的类别为条件查询信息
	public ArrayList<Product> selectProductsByCategory(String category) {
		ArrayList<Product> list = new ArrayList<Product>();
		Product e = null;
		try {
			pStatement = connection.prepareStatement("select * from tb_products where category=? order by id DESC");
			pStatement.setString(1, category);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {// 给实体进行赋值
				e = new Product(); // 创建了一个新的商品实体对象
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPrice(rs.getDouble(3));
				e.setCategory(rs.getString(4));
				e.setPnum(rs.getInt(5));
				e.setImgurl(rs.getString(6));
				e.setDescription(rs.getString(7));
				list.add(e);
			}
			} catch (SQLException ex) {
			System.out.println("数据库访问失败");
		}
		return list;
	}

	
	 

	//以商品编号查询商品名称
	public Product selectOneProducts(int id) {
		Product e=new Product();
		try {
			pStatement=connection.prepareStatement("select * from tb_products where id=? order by id DESC");
			pStatement.setInt(1, id);
			ResultSet rs =pStatement.executeQuery();
			while (rs.next()) {
				e = new Product(); // 创建了一个新的商品实体对象
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPrice(rs.getDouble(3));
				e.setCategory(rs.getString(4));
				e.setPnum(rs.getInt(5));
				e.setImgurl(rs.getString(6));
				e.setDescription(rs.getString(7));
			}
		}catch(SQLException ex) {
			
		}return e;
	
	}
	
	
		// 以商品的编号为条件查询单个商品 信息
	public String selectOneNameByProductId(int id) {
		String name=null;
		try {
			pStatement=connection.prepareStatement("select * from tb_products where id=?");
			pStatement.setInt(1, id);
			ResultSet resultSet=pStatement.executeQuery();
			while(resultSet.next()) {
				name=resultSet.getString(7);
			}
		}catch(Exception e) {
	 }return name;
	}
	//根据商品的ID修改商品的销售数量
	public boolean updateAProductSoldNumber(int  pnum,int id) {
		try {
			pStatement=connection.prepareStatement("update tb_products set pnum=pnum+? where id=?");
			pStatement.setInt(1, pnum);
			pStatement.setInt(2, id);
			pStatement.executeUpdate();
			pStatement.close();
			return true;
		}catch(Exception ex) {
			System.out.println("更新数据包商品销量失败!");
			System.out.println(ex.getMessage());
			return false;
		}
	}
}
