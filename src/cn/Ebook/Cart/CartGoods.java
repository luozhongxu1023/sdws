package cn.Ebook.Cart;

public class CartGoods {//购物车实体类
	public int ID;
	public float price;
	public int number;
	public CartGoods(int iD, float price, int number) {
		ID = iD;
		this.price = price;
		this.number = number;
	}
	public CartGoods() {
		
	}
	

}
