<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>传智书城</title>
	<%-- 导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<!-- 导入首页轮播图css和js脚本 -->
	<link type="text/css" href="${pageContext.request.contextPath }/client/css/autoplay.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/autoplay.js"></script>
</head>

<body class="main">
	<%@include file="/client/common/head.jsp"%>
	<%@include file="/client/common/menu_search.jsp" %>
	
	<!-- 图书商场首页轮播图  start -->
	<div id="box_autoplay">
    	<div class="list">
        	<ul>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad1.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad2.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad3.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad4.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad5.jpg" width="900" height="335" /></li>
        	</ul>
    	</div>
	</div>
	
	<%@ include file="/client/common/foot.jsp" %>
</body>
</html>
