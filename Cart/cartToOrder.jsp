<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.Ebook.Cart.CartGoods"%>
<%@ page import="cn.Ebook.entity.*"%>
<%@ page import="cn.Ebook.dao.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>${titleName}</title>

	</head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="../client/common/head.jsp" />
	<jsp:include page="../client/common/menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath}/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;收银结账
					</div>
					
				</td>
			</tr>
			</table>
					<% request.setCharacterEncoding("utf-8");//解决乱码问题
						//将客户购物车中数据提交至订单
						Order order = new Order();//定义订单实体order
						OrderDao orderDao = new OrderDao();
						OrderItemDao orderDetailDao = new OrderItemDao();
						OrderItem orderDetail = new OrderItem();//定义订单明细orderDetail
						ProductDao goodsDao = new ProductDao();

						String orderId = session.getAttribute("orderId").toString();
						//先添加订单表
						order.setOrderId(orderId);
						order.setMoney(Float.valueOf(session.getAttribute("totalPrice").toString()));
						order.setReceiverAddress(request.getParameter("address"));
						order.setReceiverName(request.getParameter("realName"));
						order.setReceiverPhone(request.getParameter("mobile"));
						order.setPaystate(0);
						//将订单时间格式化为"yyyy-MM-dd HH:mm:ss",存至当前时间字段中
						java.util.Date date = new java.util.Date();
						java.text.SimpleDateFormat orderTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						order.setOrderTime(orderTime.format(date));
						order.setUsername(request.getParameter("name"));	
						
						//存至订单数据表
						orderDao.insertOrder(order);

						//将订单涉及的商品添加到该订单相关联的明细表
						Vector cart = (Vector) session.getAttribute("_CART_");
						for (int i = 0; i < cart.size(); i++) {
							CartGoods cartGoods = (CartGoods) cart.elementAt(i);
							//这一订单明细中所有商品的订单号均一样，同订单表中的订单号
							orderDetail.setOrderId(orderId);
							
							orderDetail.setProductid(new Integer(cartGoods.ID));
							
							orderDetail.setBuynum(cartGoods.number);
							//修改该商品销量，用于后期统计销售排行
							goodsDao.updateAProductSoldNumber(cartGoods.number,new Integer(cartGoods.ID));
							orderDetailDao.insertOrderItem(orderDetail);
						}

						session.removeAttribute("_CART_");//清空购物车
					
					%>
					
					<table width="80%" border="0" align="center">
						<tr>
							<td height="100"> </td>
						</tr>
						<tr>
							<td align="center">
								<font color="orange" size="5">已经生成订单，编号:<%=orderId%></font>
							</td>
						</tr>
						<tr>
					s		<td height="100"> </td>
						</tr>
					</table>
					<jsp:include page="../client/common/foot.jsp" />
	</body>


</html>

