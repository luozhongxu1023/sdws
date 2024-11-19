<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.Ebook.entity.User"%> 
<script type="text/javascript">
//退出确认框
function confirm_logout() {   
    var msg = "您确定要退出登录吗？";   
    if (confirm(msg)==true){   
    return true;   
    }else{   
    return false;   
    }   
} 
</script>
<%
String shoppinglink = "/Ebook/client/common/privilege.jsp";
String orderlink="/Ebook/client/common/privilege.jsp";
%>
<%
	User user=null;
	if(session.getAttribute("user")!=null){
		//user是用户登录后存放在session对象上的属性名
		user=(User)session.getAttribute("user");
		shoppinglink="/Ebook/Cart/cart.jsp";
		orderlink="/Ebook/findOrderByUser?orderAction=orderlist";
		}
%>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/index.jsp">
					<img src="${pageContext.request.contextPath}/client/images/logo.png" width="200" height="60" border="0" /> 
				</a>
			</td>
			<td style="text-align:right">
				<img src="${pageContext.request.contextPath}/client/images/cart.gif" width="26" height="23" style="margin-bottom:-4px" />
				   &nbsp;
				  <a href="<%=shoppinglink%>">购物车</a> 
				| <a href="<%=orderlink%>">我的帐户</a>
				| <a href="${pageContext.request.contextPath}/client/login.jsp">用户登录</a>
				<% 
				 user = (User) request.getSession().getAttribute("user");
				if(null == user){
				%>
				| <a href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a>							
				<% 	
				}else{
				%>
				| <a href="${pageContext.request.contextPath}/logoutservlet" onclick="javascript:return confirm_logout()">用户退出</a>
				<br><br><br>欢迎您： ${user.username}
				<% 	
				}
				%>			
			</td>		
		</tr>
	</table>
</div>