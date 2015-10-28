##jsp的基本语法


###1.指令元素 --用于从jsp发送一个信息到容器，比如设置全局变量,文字编码,引入包等

(1). page指令   <%@ page contentType="text/html;charset=utf-8"%>

page指令常用属性：  
	
	language：定义要使用的脚本语言，目前只能是“java”， 即language="java"。
    
	import： 和一般的Java import意义一样，用于引入要使用的类，只是用逗号“,”隔开包或者类列表。默认省略，即不引入其他类或者包。例如，import="java.io.*,java.util.Hashtable"。
    
	session：指定所在页面是否参与HTTP会话。默认值为true，session="true"。   
	buffer：指定到客户输出流的缓冲模式。如果为none，则不缓冲；如果指定数值，那么输出就用不小于这个值的缓冲区进行缓冲。与autoFlash一起使用。默认不小于8KB，根据不同的服务器可设置。例如，buffer="64kb"。    
	autoFlash：如果为true缓冲区满时，到客户端输出被刷新；如果为false缓冲区满时，出现运行异常，表示缓冲区溢出。默认为true，例如autoFlash="true"。
    
	info：关于JSP页面的信息，定义一个字符串，可以使用servlet.getServletInfo()获得。 默认省略。例如，info="测试页面"。   
	isErrorPage：表明当前页是否为其他页的errorPage目标。如果被设置为true，则可以使用exception对象。相反，如果被设置为false，则不可以使用exception对象。默认为false，例如，isErrorPage="true"。
    
	errorPage：定义此页面出现异常时调用的页面。默认忽略，例如errorPage="error.jsp"。   
	isThreadSafe：用来设置JSP文件是否能多线程使用。如果设置为true，那么一个JSP能够同时处理多个用户的请求；相反，如果设置为false，一个JSP只能一次处理一个请求。例如，isThreadSafe="true"。
    
	contentType：指定页面编码 定义JSP字符编码和页面响应的MIME类型。TYPE=MIME TYPE;charset=CHARSET。默认为TYPE=text/html,CHARSET=iso8859-1。例如contentType="text/html;charset=gb2312"
    
	pageEncoding：指定Servlet引擎以什么编码翻译。JSP页面的字符编码 ，默认值为pageEncoding="iso-8859-1"，例如pageEncoding="gb2312"。    
	isELIgnored：指定EL（表达式语言）是否被忽略。如果为true，则容器忽略"${}"表达式的计算。默认值由web.xml描述文件的版本确定，servlet2.3以前的版本将忽略。例如isELIgnored="true"。

(2). include指令   <%@ include file="filename"%>

该指令用于引入一个文件(通常是JSP文件)，JSP引擎会把两个JSP文件翻译成一个servlet文件，因此也称为静态引入。
被引入的jsp文件只需要保留page指令即可，<html><body>...均省略

	在jsp中，只有3种功能可以将外部内容包含到JSP文档中：
	a. jsp:include动作
	b. include指令
	c. jsp:plugin动作


(3). taglib指令 --这个指令允许在jsp页面使用自定义的标签  <%@ taglib uri="uri" prefix="prefixOfTag" >   <myTag:yourTag num1="123">


###2.脚本元素 --在一定程度上可以理解就是java的片段scriplet

(1). scriplet    <% java代码 %>
(2). 表达式      <%=java表达式%> <%=rs.getString(1)%>
(3). declaration 声明
   <%! 变量声明    %>比如:<%! int count=10%>
   <%! 函数声明    %>比如： 
   
					   <%! int fun(int num){  
							int result=0;
							for(int i=0;i<num;i++){
								result=result+i;
							}
						}
					   %>

特别说明：

	<%! int i=90;%> //改jsp对应的servlet的成员变量，jsp就是一个类
    <% int i=90;%> //局部变量
    <h3>输出i</h3>
    <% out.print(i); %> //输出90
    <%= i*3-20 %>  //表达式，输出230

	<%  %> 定义的是局部的变量的，<%!  %>是定义的全局变量方法
	<%!   %>里声明的变量和方法都是作为类的属性和方法存在的，<%   %>里不能声明方法。  而<%  %>里声明的变量则是作为_jspService这个方法的内部属性


###3.动作元素 --动作元素是使用xml语法写的,是jsp规定的一系列标准动作

1. <jsp:useBean> //创建一个javabean实例
2. <jsp:setProperty> //给一个javabean实例设置初始值
3. <jsp:param> //给一个jsp设置参数，常常与<jsp:include>结合使用
	<jsp:include file="info.jsp">
	<jsp:param name="parameter1" value="parameterValue"/>
	<jsp:param name="parameter2" value="parameterValue"/>
	</jsp:include>
4. <jsp:getProperty>   //取得一个javabean实例的成员变量    
5. <jsp:include> //引入另外一个文件
6. <jsp:plugin> //使用插件
7. <jsp:forward> //转发
8. <jsp:fallback> //

<jsp:forward>



jsp的运行原理：
如果是第一次访问，jsp文件被服务器翻译成对应的java文件（servlet）然后在被编译成.class文件并加载到内存中。所以第一次访问速度比较慢，但是第二次访问时速度就会很快了.
