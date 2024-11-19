<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.Ebook.Cart.*" %>
<%@page import="cn.Ebook.dao.*" %>
<%
	ProductDao dao = new ProductDao();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
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
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
					</div>
					
				</td>
			</tr>
			<tr>
			<td>
			<div align="center">
					<br>
					<%
						if (session.getAttribute("_CART_") == null) {
					%>
					您还没有购物！！
					<%
						} else {
					%>
				</div>
			</td>
			</tr>
		</table>
		<form method="post" action="cartModify.jsp" name="form">
					<table width="92%" border="1" align="center" cellpadding="0"
						cellspacing="0" bordercolor="#FFFFFF" frame="box">
						<tr>
							<td width="5%" height="28">
								<div align="left">序号</div>
							</td>
							<td width="40%">
								<div align="left">商品名称</div>
							</td>
							<td width="18%">
								<div align="left">商品价格</div>
							</td>
							<td width="19%">
								<div align="left">商品数量</div>
							</td>
							<td width="18%">
								<div align="left">商品金额</div>
							</td>
						</tr>
						<%
							float sum = 0;//商品总金额									
								Vector cart = (Vector) session.getAttribute("_CART_");
								for (int i = 0; i < cart.size(); i++) {//累计所有商品付款总额
									CartGoods cartGoods = (CartGoods) cart.elementAt(i);
									sum = sum + cartGoods.number * cartGoods.price;
						%>
						<tr>
							<td align="left" width="5%"><%=i + 1%></td><!-- 展示序号=下标+1 -->
							<td align="left" width="40%"><%=dao.selectOneNameByProductId(new Integer(cartGoods.ID))%>
							</td>
							<td align="left" width="18%"><%=cartGoods.price%>元</td>
							<td align="left" width="19%"><input name="num<%=i%>"
								size="7" type="text" value="<%=cartGoods.number%>"
								onBlur="check(this.form)"></td>
							<td align="center" width="18%"><%=cartGoods.number * cartGoods.price%>元</td>
						</tr>
						<script language="javascript">
			
			function check(myform){
				if(isNaN(myform.num<%=i%>.value) || myform.num<%=i%>.value.indexOf('.',0)!=-1){
					alert("请不要输入非法字符");myform.num<%=i%>
							.focus();
									return;
								}
								if (myform.num<%=i%>
							.value == "") {
									alert("请输入修改的数量");
									myform.num
						<%=i%>
							.focus();
									return;
								}
								myform.submit();
							}
						</script>
						<%
							}
						%>
					</table>
				</form>
				<table width="100%" height="52" lign="center" cellpadding="0"
					cellspacing="0">
					<tr align="center" valign="middle">
						<td height="10">&nbsp;</td>
						<td width="24%" height="10" colspan="-3" align="left">&nbsp;</td>
					</tr>
					<tr align="center" valign="middle">
						<td height="21" class="tableBorder_B1">&nbsp;</td>
						<td height="21" colspan="-3" align="left">合计总金额：￥<%=sum%> <%
 	session.setAttribute("totalPrice", sum);
 %></td>
					</tr>
					<tr align="center" valign="middle">
						<td height="21" colspan="2">
						<a href="../Cart/cartCheckOut.jsp">去收银台结账</a> |
						<a href="../Cart/cartClear.jsp">清空购物车</a>
						</td>
					</tr>
				</table> 
				<%
 	}
 %></td>
		</tr>
	</table>
	</div>
<jsp:include page="../client/common/foot.jsp" />
</body>
</html>
