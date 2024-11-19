package cn.Ebook.entity;

//定义订单实体类
public class Order {
	private String orderId;
	private float Money;
	private String ReceiverAddress;
	private String ReceiverName;
	private String ReceiverPhone;
	private int Paystate;
	private String OrderTime;
	private String Username;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public float getMoney() {
		return Money;
	}
	public void setMoney(float Money) {
		this.Money = Money;
	}
	public String getReceiverAddress() {
		return ReceiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		ReceiverAddress = receiverAddress;
	}
	public String getReceiverName() {
		return ReceiverName;
	}
	public void setReceiverName(String receiverName) {
		ReceiverName = receiverName;
	}
	public String getReceiverPhone() {
		return ReceiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		ReceiverPhone = receiverPhone;
	}
	public int getPaystate() {
		return Paystate;
	}
	public void setPaystate(int paystate) {
		Paystate = paystate;
	}
	public String getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	
	
}
