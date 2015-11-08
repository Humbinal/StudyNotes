#动作标签（动作元素） 
--动作元素是使用xml语法写的,是jsp规定的一系列标准动作

1. <jsp:useBean> //创建一个javabean实例
2. <jsp:setProperty> //给一个javabean实例设置初始值
3. <jsp:param> //给一个jsp设置参数，常常与<jsp:include>结合使用
	<jsp:include papge="info.jsp">
	<jsp:param name="parameter1" value="parameterValue"/>
	<jsp:param name="parameter2" value="parameterValue"/>
	</jsp:include>
4. <jsp:getProperty>   //取得一个javabean实例的成员变量    
5. <jsp:include> //引入另外一个文件
6. <jsp:plugin> //使用插件
7. <jsp:forward> //转发
8. <jsp:fallback> //

<jsp:forward file="">
在jsp开发过程中，我们通常把jsp放入WEB-INF目录中，目的是为了防止用户直接访问这些jsp文件。
在WebRoot下我们放置一个入口页面，将它用来转发
<jsp:forword file="/WEB-INF/xx.jsp"></jsp:forword>

<%@ include file="" %> 静态引入
<jsp:include page="" ></jsp:forword> 动态引入
相同点：把一个文件引入到另一个文件
区别：静态引入 把两个jsp翻译成一个servlet，所以被引入的文件不要包含body、html等公共元素
动态引入 两个jsp会分别翻译成servlet，所以可以包含html、body等

jsp的运行原理：
如果是第一次访问，jsp文件被服务器翻译成对应的java文件（servlet）然后在被编译成.class文件并加载到内存中。所以第一次访问速度比较慢，但是第二次访问时速度就会很快了.

<jsp:param>：它用来作为forward和include的子标签！用来给转发或包含的页面传递参数！

