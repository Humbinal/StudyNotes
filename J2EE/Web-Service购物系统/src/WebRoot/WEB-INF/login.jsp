<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎登录网上售书系统！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="WebContent/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="WebContent/css/style.css">

  </head>
  
  <body>
	<div class="container">
		<h1>欢迎光临网上售书系统！</h1>
		<form action="/shopping/GoHall" method="post">

			<div class="form-group row">
				<label for="inputUsername" class="col-sm-1 form-control-label ">用户ID</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="inputUsername"
						name="userId" />
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-1 form-control-label">密
					码</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="inputPassword"
						name="password" />
				</div>
			</div>
			<input type="button" class="btn btn-warning" value="取消" /> <input
				type="submit" class="btn btn-primary" value="登录" />
	</div>
</body>
</html>
