<%@ page language="java" import="java.util.*,com.humbinal.domain.Book"
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

<title>欢迎光临网上售书系统！</title>

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
		<h1 id="title">欢迎光临网上售书系统！</h1>
		<a href="/shopping/index.jsp" class="btn btn-primary" id="logout">退出</a>
		<a href="/shopping/GoMyCart?type=showMyCart" class="btn btn-primary" id="showCart">查看购物车</a>
			
		<table class="table table-hover">
			<tr>
				<th>书名</th>
				<th>价格（￥）</th>
				<th>出版社</th>
				<th>购买</th>
			</tr>
			<%
				ArrayList arrayList = (ArrayList) request.getAttribute("books");
				for (int i = 0; i < arrayList.size(); i++) {
					Book book = (Book) arrayList.get(i);
			%>

			<tr>
				<td><%=book.getName()%></td>
				<td><%=book.getPrice()%></td>
				<td><%=book.getPublishHouse()%></td>
				<td><a href="/shopping/GoMyCart?type=add&id=<%=book.getId() %>">购买</a></td>
			</tr>
			<%
				}
			%>
			
		</table>
		<nav>
		<ul class="pagination">
			<li class="disabled"><a href="#" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
			</a></li>
			<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a>
			</li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					<span class="sr-only">Next</span>
			</a></li>
		</ul>
		</nav>
	</div>
</body>
</html>
