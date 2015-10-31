# ServletContext 

1.ServletContext 是在服务器
2.ServletContext 是被所有客户端共享
3.ServletContext 是当web应用启动的时候，自动创建
4.ServletContext 当web应用关闭/tomcat关闭/对web应用reload 会造成servletContext销毁.

获取:
  this.getServletContext();  this.getServletConfig().getServletContext();
添加属性:
  servletcontext.setAttribute(string,object);
取出属性
  servletcontext.getAttribute(“属性名”)
删除
  setvletContext.removeAttribute(“属性名”);

 	ServletContext的应用

(1)	获取WEB应用的初始化参数
<!-- 如果希望所有的servlet都可以访问该配置. -->
<context-param>
	<param-name>name</param-name>
	<param-value>scott</param-value>
</context-param>
如何获取
String val= this.getServletContext().getInitParameter("name");

(2)	使用ServletContext实现跳转
//目前我们跳转到下一个页面
		//1 response.sendRedirect("/web应用名/资源名");
		//2 request.getRequestDispatcher("/资源名").forward(request, response);
		/*
		 * 区别1. getRequestDispatcher 一个跳转发生在web服务器 sendRedirect发生在浏览器
		 *     2. 如果request.setAttribute("name","顺平") 希望下一个页面可以使用 属性值，则使用 getRequestDispatcher
		 *	   3. 如果session.setAttribute("name2","顺平3"), 希望下一个页面可以使用 属性值，则两个方法均可使用,但是建议使用 getRequestDispatcher
		 *     4. 如果我们希望跳转到本web应用外的一个url,应使用sendRedirect
		 */
		//3.这种方法和2一样
		this.getServletContext().getRequestDispatcher("/资源url").forward(request, response);
(3)	读取文件，和获取文件全路径
//首先读取到文件
		InputStream inputStream=this.getServletContext().getResourceAsStream("dbinfo.properties");

		//创建Properties
		Properties pp=new Properties();
		pp.load(inputStream);
		
		out.println("name="+pp.getProperty("username"));

*如果文件放在src目录下；则使用类加载器
//如果文件放在src目录下，我们应该使用类加载器来读取
		InputStream is=Servlet5.class.getClassLoader().getResourceAsStream("dbinfo.properties")
	

//获取文件全路径
//如果读取到一个文件的全路径
		String path=this.getServletContext().getRealPath("/imgs/Sunset.jpg");
		out.println("paht = "+path);
		

网站计数器增强：
我们建立一个文件recoder.txt文件，用于保存访问量,可以可以保证稳定增长.
实现方法
建立InitServlet ,用于初始化我的Servletcontext,和在关闭tomcat时保存访问量

	package com.hsp;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	public class InitServlet extends HttpServlet {
		/**
		 * Destruction of the servlet. <br>
		 */
		public void destroy() {
			System.out.println("init servlet destory()被调用..");
			//把ServletContext值重新保存到文件.
			//从record.txt文件中，读取浏览量
			//1.首先得到该文件真实路径
			String filePath=this.getServletContext().getRealPath("record.text");
			//2.打开文件
			try {
				FileWriter filewriter=new FileWriter(filePath);
				//为了读取我们转为BufferedReader
				BufferedWriter bufferedWriter=new BufferedWriter(filewriter);
				//从ServletContext读取访问量
				String nums=(String) this.getServletContext().getAttribute("nums");
				//重新写会文件
				bufferedWriter.write(nums);
				//一定要关闭流
				bufferedWriter.close();
				filewriter.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//关闭...
			}
		}
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			this.doGet(request, response);
		}
		public void init() throws ServletException {
			// Put your code here
			//从record.txt文件中，读取浏览量
			//1.首先得到该文件真实路径
			String filePath=this.getServletContext().getRealPath("record.text");
			//2.打开文件
			try {
				FileReader fileReader=new FileReader(filePath);
				//为了读取我们转为BufferedReader
				BufferedReader bufferedReader=new BufferedReader(fileReader);
				String nums=bufferedReader.readLine();
				//把nums添加到Servletcontext
				this.getServletContext().setAttribute("nums", nums);
				//一定要关闭流
				bufferedReader.close();
				fileReader.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

当用户登录一次我们取出ServletContext取出，并+1,
	//向servletContext中添加属性
	//1.先取出
	String nums=(String) this.getServletContext().getAttribute("nums");
	
	//如果有，则取出+1
	this.getServletContext().setAttribute("nums", (Integer.parseInt(nums)+1)+"");   
在Mange页面显示
	String nums=this.getServletContext().getAttribute("nums").toString();
	out.println("该管理页面被访问了 "+nums+" 次");   
问：如果我们的tomcat异常退出，怎么办.
使用线程，定时把ServletContext的值，刷新到recorder.txt 比如10min.

