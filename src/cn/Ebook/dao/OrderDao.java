package cn.Ebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.Ebook.entity.Order;
import cn.Ebook.util.DBConnection;

import sun.text.resources.cldr.om.FormatData_om;

//订单表的操作
public class OrderDao {
	private Connection connection=null;//定义数据库连接对象
	private PreparedStatement pStatement=null; //定义数据库访问对象
	private DBConnection jdbc=null; //面向本数据库的连接对象
	
	public OrderDao(){
		jdbc=new DBConnection();
		connection=jdbc.connection;
	}
	public boolean insertOrder(Order form) {
		try {
			pStatement=connection.prepareStatement("insert into tb_orders values (?,?,?,?,?,?,?,?)");
			pStatement.setString(1,form.getOrderId());
			pStatement.setFloat(2,form.getMoney());
			pStatement.setString(3, form.getReceiverAddress());
			pStatement.setString(4,form.getReceiverName());
			pStatement.setString(5,form.getReceiverPhone());
			pStatement.setInt(6, form.getPaystate());
			pStatement.setString(7, form.getOrderTime());
			pStatement.setString(8, form.getUsername());
			
			pStatement.executeUpdate();
			pStatement.close();
			return true;
		}catch (SQLException ex) {
			System.out.println("数据库访问失败666");
			System.out.println(ex.getMessage());
			return false;
		}
	}
	public List selectOrderByName(String name) {
		List list=new ArrayList();
		try {
			pStatement=connection.prepareStatement("select * from tb_orders where username=?");
			pStatement.setString(1,name);
			ResultSet rs=pStatement.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOrderId(rs.getString(1));
				order.setMoney(rs.getFloat(2));
				order.setReceiverAddress(rs.getString(3));
				order.setReceiverName(rs.getString(4));
				order.setReceiverPhone(rs.getString(5));
				order.setPaystate(rs.getInt(6));
				order.setOrderTime(rs.getString(7));
				order.setUsername(rs.getString(8));
				
				list.add(order);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}return list;
	}
	
	
	public Order selectOrderByNumber(String orderId){
		   Order order=null;
		    try {
		    	 pStatement = connection.prepareStatement("select * from tb_orders where id=?");
		    	 pStatement.setString(1,orderId);
		         ResultSet rs = pStatement.executeQuery();
		         while(rs.next()) {
		        	 order.setOrderId(rs.getString(1));
						order.setMoney(rs.getFloat(2));
						order.setReceiverAddress(rs.getString(3));
						order.setReceiverName(rs.getString(4));
						order.setReceiverPhone(rs.getString(5));
						order.setPaystate(rs.getInt(6));
						
						
		     	order.setOrderTime(rs.getString(7));
				order.setUsername(rs.getString(8));
		                             }   }
		         catch (SQLException ex){
		     }       
		         
		    return order;  
		    }
	


public List selectAllOrder() {
	List list=new ArrayList();
	try {
		pStatement=connection.prepareStatement("select * from tb_orders");
		ResultSet rs=pStatement.executeQuery();
		while (rs.next()) {
			Order order=new Order();
			order.setOrderId(rs.getString(1));
			order.setMoney(rs.getFloat(2));
			order.setReceiverAddress(rs.getString(3));
			order.setReceiverName(rs.getString(4));
			order.setReceiverPhone(rs.getString(5));
			order.setPaystate(rs.getInt(6));
			order.setOrderTime(rs.getString(7));
			order.setUsername(rs.getString(8));
		    list.add(order);
		}
	}catch (SQLException ex) {
		ex.printStackTrace();
	}
	return list;
}


public boolean deleteOrder(String orderId) {
	OrderItemDao orderItem=new OrderItemDao();
	orderItem.deleteOrderItems(orderId);
	try {
		pStatement=connection.prepareStatement("delete from tb_orders where id=?");
		pStatement.setString(1, orderId);
		pStatement.executeUpdate();
		pStatement.close();
		return true;
	}catch (SQLException ex) {
		return false;
	}
	
}
	
}
