<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="cn.Ebook.Cart.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Vector cart=(Vector)session.getAttribute("_CART_");//获取现有的购物车cart
		Vector newcart=new Vector();//定义一个新购物车newcart
		for(int i=0;i<cart.size();i++){//遍历cart
			CartGoods cartGoods=(CartGoods)cart.elementAt(i);//取出各件购物车中的商品
			String num=request.getParameter("num"+i);//取出每件商品的购买数量，此处的+号是一个连接符，num0，num1……
			try{
			int newnum=Integer.parseInt(num);
			cartGoods.number=newnum;
	 
			if(newnum!=0){//如果数量为0则不添加到购物车，相当于删除此件商品
				newcart.addElement(cartGoods);}
			}catch(Exception e){
				out.println("<script language='javascript'>alert('您输入的数量不是有效的整数!');history.back(); </script>");
				return;
			}
		}
		session.setAttribute("_CART_",newcart);
		response.sendRedirect("cart.jsp");
	%>
</body>
</html>