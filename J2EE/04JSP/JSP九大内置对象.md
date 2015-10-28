##1.jsp的九大内置对象【是jsp的核心】

(1) out //向客户端输出数据,字节流
out.println("");

(2) request serlvet中的 HttpServletRequest//接受客户端的http请求
getParameter(String name); //name表示表单的参数名
getParameterValues(String name);//使用得到是String []
setAttribute(String name,Object obj);//设置名字为name的obj, 值为obj
getAttribute(String name);//返回由name指定的属性值,如果不存在就返回null;
getCookie();

(3) response//封装jsp的产生的回应
addCookie(Cookie cookie);
sendRedirect("./welcome.jsp"); 

(4) session //用于保存用户的信息，跟踪用户的行为
setAttribute(String name,Object obj);
getAttribute(String name);

(5) application //多个用户共享该对象,可以做计数器.
  
(6) pageContext //代表jsp页面的上下文，生命周期很短，只在本页面有效

(7) exception//代表运行时的一个异常.
getMessage();

(8) page//代表jsp这个实例本身(使用比较少)

(9) config//代表jsp对应的servlet的配置.可以得到web.xml中的参数


