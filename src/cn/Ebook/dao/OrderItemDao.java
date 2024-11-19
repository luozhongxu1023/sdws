package cn.Ebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.Ebook.entity.OrderItem;
import cn.Ebook.util.DBConnection;


//订单明细表的操作
public class OrderItemDao {
	private Connection connection=null;//定义数据库连接对象
	private PreparedStatement pStatement=null; //定义数据库访问对象
	private DBConnection jdbc=null; //面向本数据库的连接对象
	
	public OrderItemDao(){
		jdbc=new DBConnection();
		connection=jdbc.connection;
	}
	public boolean insertOrderItem(OrderItem form) {
		try {
				pStatement = connection.prepareStatement("insert into tb_orderitem(order_id,product_id,buynum) values (?,?,?)");
				pStatement.setString(1, form.getOrderId());
				pStatement.setInt(2, form.getProductid());
				pStatement.setInt(3,form.getBuynum());
				pStatement.executeUpdate();
				pStatement.close();
				return true;
			} catch (SQLException ex) {
				System.out.println("数据库访问失败");
				return false;
			    }
		}
	
	public void deleteOrderItems(String orderId) {
		// TODO Auto-generated method stub
		
	}
	//按订单号查询订单明细
			public List selectOrderDetailByNumber(String orderId) {
		//存放返回指定ID订单明细集合List[OrderItemEntity]
				List list = new ArrayList();
				OrderItem orderDetail = null;	//存放订单某一购买商品的明细
				ProductDao goodsDao = new ProductDao();
				try {
					pStatement = connection.prepareStatement(
						 "select * from tb_order_item where order_id=?");
					pStatement.setString(1, orderId);			//传递参数orderId值
					ResultSet rs = pStatement.executeQuery();	//执行查询返回RS
					while (rs.next()) {			//循环每一字段，存至商品明细对象
						orderDetail = new OrderItem();				
						orderDetail.setOrderId(rs.getString(2));
						orderDetail.setProductid(rs.getInt(3));
						orderDetail.setBuynum(rs.getInt(4));
						list.add(orderDetail);//将某一商品明细对象添加至list
						}
				} catch (SQLException ex) {
				}
				return list;
			}

}
