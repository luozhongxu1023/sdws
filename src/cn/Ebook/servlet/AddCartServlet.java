package cn.Ebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.Ebook.Cart.CartGoods;


/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//往购物车添加商品
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		float goodsprice=Float.parseFloat(request.getParameter("price"));

		int buyGoodsNumber=1;
		CartGoods cartgoods=new CartGoods();
		cartgoods.ID=goodsId;
		cartgoods.number=1;
		cartgoods.price=goodsprice;
		boolean flag=true;

		Vector cart =(Vector) session.getAttribute("_CART_");
		if(cart==null){
			cart=new Vector();
		}else{
			for(int i =0;i<cart.size();i++){
				CartGoods boughtGoods=(CartGoods) cart.elementAt(i);
				if(boughtGoods.ID==cartgoods.ID){
					boughtGoods.number++;
					cart.setElementAt(boughtGoods, i);
					flag=false;
				}
			}
		}
		if(flag)
			cart.add(cartgoods);
		session.setAttribute("_CART_", cart);
		PrintWriter out=response.getWriter();
		out.println("<script language='javascript'>alert('加入购物车成功!');window.close();</script>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
