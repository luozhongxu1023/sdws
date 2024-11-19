<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.Ebook.entity.*" %>
 <%
	//查询当前用户所有的订单
	Integer sign = null;
	List list = null;
	//获取当前用户所有订单
	list = (List) request.getAttribute("list");	
	int pageNumber = list.size(); // 计算出有多少条记录
	//System.out.println("pagenumber:"+pageNumber);
	int maxPage = pageNumber; // 计算有多少页数
	String strNumber = (String) request.getAttribute("i");
	int number = 0;
	int n=5;
	if (maxPage % n== 0) {
		maxPage = maxPage / n;
	} else {
		maxPage = maxPage / n + 1;
	}
	if (strNumber == null) {
		number = 0;
	} else {
		number = Integer.parseInt(strNumber);
	}
	int start = number * n;//开始条数
	int over = (number + 1) * n;//结束条数
	int count = pageNumber - over;//还剩多少条记录
	if (count <= 0) {
		over = pageNumber;
	}
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/client/css/main.css"
	type="text/css" />
</head>
<body class="main">
	<jsp:include page="../client/common/head.jsp" />
	<jsp:include page="../client/common/menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
						${bean.category}
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp; 图书列表
					</div>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td colspan="5">
								<h1>商品目录</h1>
								<%-- <p align="right">共<%=pageNumber %>种商品</p> --%>
								<div style="margin-top: 20px">
									<img
										src="${pageContext.request.contextPath }/client/images/productlist.gif"
										width="100%" height="38" />
								</div>
							</td>
						</tr>
						<tr>
						<tr>
							<%for (int i = start; i < over; i++) { //遍历结果集合
					 		Product e = (Product) list.get(i);
						%>								
								<td>															
									<div class="divbookpic">
										 <p>
											<img src="<%="/Ebook" + e.getImgurl()%>"
									width="115" height="129" border="0"  />
											
										</p>
									</div>
								<div class="divlisttitle">
										书名：<%=e.getName() %>
											<br />售价：￥<%=e.getPrice() %>
									</div>
									<%
									if (session.getAttribute("user") != null || session.getAttribute("id") != null) {
								%>
								<!-- <a href="#" >查看商品详细信息</a>  -->
								<a href="#"
									onClick="window.open('product/showProductById.jsp?id=<%=e.getId()%>','','width=900,height=450');">查看详细内容</a>
							<% 
									} else {
								%>
								登录后才能购买
								<%
									}
								%>
								</td>
								
								<%} %>
							</tr>
							
						</table>					
<!-- 分页导航 -->
		<div align="center">
			<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td width="13%">共为<%=maxPage%>页
					</td>
					<td width="18%">共有<%=pageNumber%>条记录
					</td>
					<td width="26%">当前为第<%=number + 1%>页
					</td>
					<td width="15%">
						<%
							if ((number + 1) == 1) {
						%> 上一页 <%
							} else {
						%> <a href="showproductservlet?selectAction=all&i=<%=number - 1%>">上一页</a>
					</td>
					<%
						}
					%>
					<td width="14%">
						<%
							if (maxPage <= (number + 1)) {
						%> 下一页 <%
							} else {
						%> <a href="showproductservlet?selectAction=all&i=<%=number + 1%>">下一页</a>
					</td>
					<%
						}
					%>
				</tr>
			</table>
		</div>
	<jsp:include page="../client/common/foot.jsp" />
</body>
</html>
