<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.Ebook.dao.*" %>
<%@page import="cn.Ebook.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	// 前台根据商品ID查询商品的详细信息
	ProductDao dao = new ProductDao();
	int id=Integer.valueOf(request.getParameter("id"));//把链接中传递的参数id由String转成int
	Product goods = dao.selectOneProducts(id);
	%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="form" method="post" action="<%=request.getContextPath() %>/addcart">
	<div align="center"><p class="style1">查看商品详细信息</p></div>
	<table width="820" border="2" align="center" bordercolor="#FFFFFF"
		bordercolorlight="#FFFFFF"		bordercolordark="#819BBC">
		<tr>
			<td width="36%" rowspan="4" height="120">
			 <div align="center">
			   <input name="pricture" type="image"	src="<%="/Ebook/"+goods.getImgurl()%>" 
                         width="200" height="280">
			 </div>
</td>
		    <td width="64%" height="30">
			 <div align="center">
			 <table width="71%" height="20" border="0" align="center" > 
			  <tr>
				 <td>商品名称：<%=goods.getName()%> <input type="hidden"
					 name="goodsId" value="<%=goods.getId()%>" />
				 </td>
			 </tr>
			 </table> 
		   </div>
		 </td>
	  </tr>
	   <tr>
		 <td height="30">	<div align="center">
			<table width="71%" border="0" align="center">
	  <tr>
		<td>
		商品价格：<%=goods.getPrice() %> <input type="hidden"
					 name="price" value="<%=goods.getPrice()%>" />
		</td>
	</tr>
</table>
</div>
</td>
</tr>
<tr>
<td height="30"> <div align="center">
<table width="71%" border="0" align="center">
<tr>	
<td>简&nbsp;&nbsp;介：<%=goods.getDescription()%></td>
</tr>
</table></div>
</td>
</tr>
<tr align="center">
	<td height="30"><img src="/Ebook/client/images/1.jpg"
			onClick="window.close()"> &nbsp;&nbsp; 
	  <input type="image" src="/Ebook/client/images/2.jpg"
 name="Submit" value="放入购物车">
	</td>
</tr>
</table>
</form>
</body>
</html>