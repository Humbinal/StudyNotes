JSTL

Apache提供的标签库，
jar包：jstl-1.2.jar，如果使用MyEclipse，它会自动帮我们导入jar包，无需自己导入，如果没有使用MyEclipse那么需要自行导入。

------------------

#核心标签库

导入JSTL核心标签库
<%taglib prefix="c" uri="http://java.sun.com/jstl/core"%>


<c:set> 
* <c:set var="a" value="hello"/> 创建名为a,值为hello的域属性，范围：page
* <c:set var="a" value="hello" scope="session"/> 范围为session

<c:out>
* <c:out value="aaa"/> 输出字符串aaa
* <c:out value="${aaa"/> 输出域属性aaa，其中与${aaa}相同
* <c:out value="${aaa}" default="xxx"/>如果${aaa}不存在，那么输出xxx字符串
* <c:out value="${aaa}" escapeXml="true"/>如果${aaa}中包含特殊字符，那么转义它。这可以防止javascript攻击

<c:remove>
* <c:remove var="a"/> 删除名为a的域属性
* <c:remove var="a" scope="page"/> 删除page域中名为a的域属性

<c:url>
* <c:url value="/AServlet"/> 输出URL：/项目名/AServlet
* <c:url value="/AServlet" var="url" scope="page"/> 把生成的url保存到page域中，而不会输出
* <c:url value="/AServlet">：输出URL：/项目名/AServlet?username=%xx%xx%xx%xx%xx%xx，其中张三会被URL编码
   <c:param name="username" value="张三"/>
  </c:url/>

<c:if>
* <c:if test="${条件}"> 当条件为true时执行标签体内容
    hello
  </c:if>

<c:choose>
* <c:choose>
    <c:when test="${条件1}">a</c:when>
    <c:when test="${条件2}">b</c:when>
    <c:when test="${条件3}">c</c:when>
    <c:otherwise>d</c:otherwise>
  </c:choose>

  等同与：
  if() {
  } esle if() {
  } esle if() {
  } else if() {
  } else {
  }

-------------

<c:forEach>

可以用来遍历数组、List、Map、

1. 计数循环

<c:forEach begin="1" end="10" var="i">
 ${i}
</c:forEach>
等同于
for(int i = 1; i <= 10; i++) {
  out.println(i);
}


<c:forEach begin="1" end="10" var="i" step="2">
 ${i}
</c:forEach>
等同于
for(int i = 1; i <= 10; i+=2) {
  out.println(i);
}

-------------

2. 遍历数组

<%
String[] names = {"zhangSan", "liSi", "wangWu", "zhaoLiu"};
pageContext.setAttribute("ns", names);
%>
<c:forEach var="item " items="${ns } ">
	<c:out value="name: ${item } "/><br/>
</c:forEach>


-------------

3. 遍历List

<%
	List<String> names = new ArrayList<String>();
	names.add("zhangSan");
	names.add("liSi");
	names.add("wangWu");
	names.add("zhaoLiu");
	pageContext.setAttribute("ns", names);
%>
<c:forEach var="item" items="${ns }"> 
	<c:out value="name: ${item }"/><br/>
</c:forEach>

-------------

4. 遍历Map

<%
	Map<String,String> stu = new LinkedHashMap<String,String>();
	stu.put("number", "N_1001");
	stu.put("name", "zhangSan");
	stu.put("age", "23");
	stu.put("sex", "male");
	pageContext.setAttribute("stu", stu);
%>
<c:forEach var="item " items="${stu }">
	<c:out value="${item.key }: ${item.value } "/><br/>
</c:forEach>


-------------

5. 循环状态对象

循环状态对象是用来说明循环的状态的，属性如下：
count：int类型，当前以遍历元素的个数；
index：int类型，当前元素的下标；
first：boolean类型，是否为第一个元素；
last：boolean类型，是否为最后一个元素；
current：Object类型，表示当前项目。

<c:forEach var="item" items="${ns }" varStatus="vs" >
	<c:if test="${vs.first } ">第一行：</c:if>
	<c:if test="${vs.last } ">最后一行：</c:if>
	<c:out value="第${vs.count } 行: "/>
	<c:out value="[${vs.index } ]: "/>
	<c:out value="name: ${vs.current } "/><br/>
</c:forEach>


------------------

#格式化标签库

导入JSTL格式化标签库

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Date date = new Date();
	pageContext.setAttribute("d", date);
%>
<fmt:formatDate value="${d }" pattern="yyyy-MM-dd HH:mm:ss "/>

---------

<%
	double d1 = 3.5;
	double d2 = 4.4; 
	pageContext.setAttribute("d1", d1);
	pageContext.setAttribute("d2", d2);
%>
<fmt:formatNumber value="${d1 }" pattern="0.00 "/><br/>
<fmt:formatNumber value="${d2 }" pattern="#.## "/>

* pattern：0.00，表示小数不足两位时，使用0补足两位
* pattern：#.##，表示小数不足两位时，有几位显示几位，不会补足


============================
============================
============================


#自定义标签

自定义标签：

1. 实现Tag接口，即传统标签。不建议使用！
2. 实现SimpleTag接口，即简单标签。建议使用！

JavaWeb中还提供了SimpleTagSupport类，继承它要比实现SimpleTag接口方便。

-----------------

步骤：
1. 标签处理类：继承SimpleTagSupport类
public class HelloTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		// 获取JspContext对象，再获取out对象，再向页面输出
		// 获取到的JspContext其实就是当前页面的pageContext对象
		this.getJspContext().getOut().write("<p>Hello SimpleTag!</p>") ;
	}
}

2. 标签描述符文件(tld)

/WEB-INF/tlds/itcast.tld

<?xml version="1.0" encoding="UTF-8"?>
<taglib version="2.0" xmlns="http://java.sun.com/xml/ns/j2ee"
	xmlns:xml="http://www.w3.org/XML/1998/namespace" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee 
						http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd ">

	<tlib-version>1.0</tlib-version> 
	<short-name>itcast</short-name> 
	<uri>http://www.itcast.cn/tags</uri> 
	<tag> 
		<name>hello</name> <!--标签名称-->
		<tag-class>cn.itcast.tag.HelloTag</tag-class> <!--标签处理类名称-->
		<body-content>empty</body-content> <!--标签体为空，即空标签-->
	</tag>
</taglib>

3. jsp页面中使用自定义标签

<%@ taglib prefix="it"  uri="/WEB-INF/hello.tld"  %>
......
<it:hello/>

----------------------

有标签体的标签

1. 标签处理类
public class HelloTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		PageContext pc = (PageContext) this.getJspContext();
		HttpServletRequest req = (HttpServletRequest) pc.getRequest();
		String s = req.getParameter("exec");
		if(s != null && s.endsWith("true")) {
			// 获取标签体对象
			JspFragment body = this.getJspBody() ;
			// 执行标签体
			body.invoke (null);
		}

	}
}

2. tld

	<tag>
		<name>hello</name>
		<tag-class>cn.itcast.tags.HelloTag</tag-class>
		<body-content>scriptless</body-content> <!--标签体内容不可以是java脚本，但可以是el、jstl等-->
	</tag>

----------------------

不执行标签下面的页面内容

	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().print("<h1>只能看到我！</h1>");
		throw new SkipPageException();
	}

----------------------

带有属性的标签

public class IfTag extends SimpleTagSupport {
	private boolean test;//设置属性，提供getter/setter方法
	public boolean isTest() {
		return test;
	}
	public void setTest (boolean test) {
		this.test = test;
	}
	@Override
	public void doTag() throws JspException, IOException {
		if(test) {//如果test为true，执行标签体内容
			this.getJspBody().invoke(null);
		} 
	}
}

	<tag> 
		<name>if</name> 
		<tag-class>cn.itcast.tag.IfTag</tag-class> 
		<body-content>scriptless</body-content>
		<!--部署属性-->
		<attribute> 
			<name>test</name> <!--属性名-->
			<required>true</required> <!--属性是否为必须的-->
			<rtexprvalue>true</rtexprvalue> <!--属性值是否可以为EL或JSTL等-->
		</attribute> 
	</tag>
