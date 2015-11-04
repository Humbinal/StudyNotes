<%@page import="com.humbinal.domain.Book"%>
<%@ page language="java" import="java.util.*,com.humbinal.domain.Users"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'myOrder.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="WebContent/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="WebContent/css/style.css">

</head>

<body>
	<div class="container">
		<h1>我的订单</h1>
		<table class="table">
			<tr>
				<td colspan="2"><h3>我的用户信息</h3></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><%=((Users) session.getAttribute("loginUser")).getName()%></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><%=((Users) session.getAttribute("loginUser")).getEmail()%></td>
			</tr>
			<tr>
				<td>用户级别</td>
				<td><%=((Users) session.getAttribute("loginUser")).getGrade()%></td>
			</tr>
		</table>
		<table class="table table-hover">
			<tr>
				<th>书籍编号</th>
				<th>书名</th>
				<th>出版社</th>
				<th>价格（￥）</th>
				<th>数量</th>
			</tr>
			<%
				ArrayList arrayList = (ArrayList) request.getAttribute("orderInfo");
				for (int i = 0; i < arrayList.size(); i++) {
					Book book = (Book) arrayList.get(i);
			%>
			<tr>
				<th><%=book.getId()%></th>
				<th><%=book.getName()%></th>
				<th><%=book.getPublishHouse()%></th>
				<th><%=book.getPrice()%></th>
				<th><%=book.getShoppingCount()%></th>
			</tr>
			<%
				}
			%>
		</table>
		<h2>总价格：<span>${totalPrice}</span></h2>
		<a href="/shopping/SubmitOrder" class="btn btn-success">确认订单</a>
	</div>
</body>
</html>
