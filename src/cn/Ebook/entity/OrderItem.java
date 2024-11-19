package cn.Ebook.entity;
//定义订单详情实体类
public class OrderItem {

	private String OrderId;
	private int Productid;
	private int Buynum;
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public int getProductid() {
		return Productid;
	}
	public void setProductid(int productid) {
		Productid = productid;
	}
	public int getBuynum() {
		return Buynum;
	}
	public void setBuynum(int buynum) {
		Buynum = buynum;
	}
	
	
}
