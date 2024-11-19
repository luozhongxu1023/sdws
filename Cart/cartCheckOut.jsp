<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.Ebook.entity.*"%>
<%--导入StringUitl用于手动生成订单编号 --%>
<%@page import="cn.Ebook.util.StringUitl" %>
<%
	java.util.Date date = new java.util.Date();
	User user = (User) session.getAttribute("user");
	String orderId=StringUitl.createOrderId();
	//将orderId存至session,作为订单编号和cartToOrder.jsp保存到订单表和订单详细表中
	session.setAttribute("orderId",orderId);
%>
<html>
	<script language="javascript">
		function checkEmpty(form) {
		for (i = 0; i < form.length; i++) {
			if (form.elements[i].value == "") {
				alert("表单信息不能为空");
				return false;
			}
		}
	}
</script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>${titleName}</title>

		<style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
</style>
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
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;收银结账
					</div>
					
				</td>
			</tr>
			</table>
					<form name="form" method="post" action="cartToOrder.jsp"
						onSubmit="checkEmpty(form)">
						<table width="30%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td height="30" colspan="2">
									<div align="center" class="style1">
										注意：请您不要恶意提交订单!
									</div>
								</td>
							</tr>
							<tr>
								<td height="30">
									<div align="center">
										订单编号：
									</div>
								</td>
								<td>
									&nbsp;
									<input type="hidden" name="order_id" value="<%=orderId%>"><%=orderId%>
								</td>
							</tr>
							<tr>
								<td width="25%" height="30">
									<div align="center">
										用户帐号：
									</div>
								</td>
								<td width="75%">
									&nbsp;
									<input type="text" name="name" value="<%=user.getUsername()%>">
									<input type="hidden" name="user_id" value="<%=user.getId()%>">
								</td>
							</tr>
							<tr>
								<td height="30">
									<div align="center">
										收件姓名：
									</div>
								</td>
								<td>
									&nbsp;
									<input type="text" name="realName">
									</td>
							</tr>
							<tr>
								<td height="30">
									<div align="center">
										邮寄地址：
									</div>
								</td>
								<td>
									&nbsp;
									<input type="text" name="address" >
								</td>
							</tr>
							<tr>
								<td height="30">
									<div align="center">
										联系电话：
									</div>
								</td>
								<td>
									&nbsp;
									<input type="text" name="mobile" value="<%=user.getTelephone()%>">
								</td>
							</tr>
							
							<tr>
								<td height="30">
									<div align="center">
										应付金额：
									</div>
								</td>
								<td>
									&nbsp;
									<input type="text" name="totalPrice" value=<%=session.getAttribute("totalPrice") %>>
								</td>
							</tr>
							<tr>
								<td height="30">
									<div align="center">
										付款方式：
									</div>
								</td>
								<td>
									&nbsp;
									<select name="paymentMode">
										<option value="">
											请选择
										</option>
										<option value="银行付款" >
											银行付款
										</option>
										<option value="邮政付款">
											邮政付款
										</option>
										<option value="现金支付" selected="selected">
											现金支付
										</option>
									</select>
								</td>
							</tr>

							<tr>
								<td height="30">
									<div align="center">
										送货方式：
									</div>
								</td>
								<td>
									&nbsp;
									<select name="deliveryMethod">
										<option value="">
											请选择
										</option>
										<option value="普通邮寄" selected="selected">
											普通邮寄
										</option>
										<option value="特快专递">
											特快专递
										</option>
										<option value="EMS专递方式">
											EMS专递方式
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td height="60">
									<div align="center">
										备注信息：
									</div>
								</td>
								<td>
									&nbsp;
									<textarea name="memo"></textarea>
								</td>
							</tr>
						</table>
						<br>
						<input type="submit" name="Submit2" value="提交">
						&nbsp;
						<input type="reset" name="reset" value="清除">
						&nbsp;
						<input type="button" name="back" value="返回"
							onClick="javasrcipt:history.go(-1)">
					</form>
				</td>
			</tr>
		</table>
		<jsp:include page="../client/common/foot.jsp" />
	</body>
</html>
