<%@ page language="java" import="java.util.*,com.humbinal.domain.Book" pageEncoding="UTF-8"%>
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

<title>My JSP 'myCart.jsp' starting page</title>

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
		<h1 id="title">我的购物车</h1>
		<a href="/shopping/GoHall" class="btn btn-primary" id="backToHall">返回购物大厅</a>
		<form action="/shopping/GoMyCart?type=update" method="post">
			<table class="table table-hover" id="myCart">
				<tr>
					<th>书籍编号</th>
					<th>书名</th>
					<th>价格（￥）</th>
					<th>出版社</th>
					<th>数量</th>
					<th>是否购买</th>
				</tr>
				<% //从request中取出商品信息
					ArrayList arrayList=(ArrayList)request.getAttribute("booklist");
					for(int i=0;i<arrayList.size();i++){
						Book book=(Book)arrayList.get(i);
					%>	
					<tr>
					<td><%=book.getId() %><input type="hidden" name="bookId" value="<%=book.getId()%>"></td>
					<td><%=book.getName() %></td>
					<td><%=book.getPrice() %></td>
					<td><%=book.getPublishHouse() %></td>
					<td><input type="text" name="bookCount" value="<%=book.getShoppingCount() %>" /></td>
					<td><a href="/shopping/GoMyCart?type=delete&id=<%=book.getId() %>">删除</a></td>
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

			<input type="submit" class="btn btn-primary" value="更新购物车"> <span>总价：<span
				class="red">${totalPrice}</span>元
			</span>
		</form>
		<input type="button" class="btn btn-warning" value="清空购物车"></input> 
		<a href="/shopping/GoMyOrder" class="btn btn-success">提交订单</a>

	</div>
</body>
</html>
