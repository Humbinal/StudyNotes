<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'js1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
window.onload = function() {//在文档加载完成后马上执行！
	//得到btn元素
	var btn = document.getElementById("btn");
	// 给btn的click事件注册监听
	btn.onclick = function() {//在按按钮被点击时执行！
		// 获取h1元素对应的DOM对象
		var h1 = document.getElementById("h1");
		// 给h1添加内容
		h1.innerHTML = "Hello JS!!!";
	};
};
</script>
  </head>
  
  <body>
<button id="btn">点击这里</button>
<h1 id="h1"></h1>
  </body>
</html>
