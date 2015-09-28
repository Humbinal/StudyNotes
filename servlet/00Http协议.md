##1.http协议简述

①http协议是建立在tcp/ip协议基础上
②http协议全称 超文本传输协议
③http协议1.0(短连接) , 1.1(长连接) ，目前通用的是1.1版本
所谓长和短指的是持续时间;长连接(1.1) 30s ，短连接是发送完数据就断掉. 

##2.http的请求部分
基本结构:

	GET /test/hello.html HTTP/1.1 [请求行]
	Accept: */*  [消息名]  消息名：内容
	Referer: http://localhost:8080/test/abc.html  
	Accept-Language: zh-cn
	User-Agent: Mozilla/4.0 
	Accept-Encoding: gzip, deflate  
	Host: http://www.sohu.com:80
	Connection: Keep-Alive     [消息头格式 （消息名: 内容 ）
	空行
	发送的内容 [格式 : 内容名字=内容体]
	
特别说明： 并不是每一次请求的消息头都一样.]
请求方式
请求行中的GET称之为请求方式，请求方式有：POST,GET,HEAD,OPTIONS,DELETE,TRACE,PUT
常用的有：POST,GET

##3.http请求消息头
1. Accept: text/html,image/*   [告诉服务器，我可以接受 文本，网页，图片]
2. Accept-Charset: ISO-8859-1 [接受字符编码 iso-8859-1]
3. Accept-Encoding: gzip,compress [可以接受 gzip,compress压缩后数据.]
4. Accept-Language: en-us,zh-cn [浏览器支持中，英文]
5. Host: www.sohu.com:80 [我要找主机是 www.sohu.com:80]
6. If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT [ 告诉服务器，我的缓冲中有这个资源文件，该文件的时间是...]
7. Referer: http://www.sohu.com/index.jsp  [告诉服务器，我来自哪里,该消息头，常用于防止盗链]
8. User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)[告诉服务器，浏览器内核]
9. Cookie [cookie??]
10.	Connection: close/Keep-Alive   [保持连接，发完数据后，我不关闭连接]
11. Date: Tue, 11 Jul 2000 18:23:51 GMT [浏览器发送该http请求的时间]

Referer的应用(防盗链):
//获取用户浏览器Referer
	String referer=request.getHeader("Referer");
	if(referer==null||!referer.startsWith("http://localhost:8080/servlet")){
		response.sendRedirect("/servlet/Error");
		return;
	}



