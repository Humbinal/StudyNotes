##cookie技术(会话技术)
①cookie 是在服务端创建
②cookie 是保存在浏览器这端
③cookie 的生命周期可以通过 `cookie.setMaxAge(2000);` 设置
如果不设置setMaxAge则该cookie的生命周期当浏览器关闭时，就消亡.

如果重名就会替换存在的cookie值.
一个web应用可以保存多个cookie,但保存在同一个cookie文本在客户端浏览器下
cookie存放的时候是以明文方式存放，因此安全较低.,我们可以通过加密后保存.

	//先获取cookie
	// 假设我们 保存上次登录时间的cookie "lasttime" "2011-11-11 12:12:12";
	// 这里我们要考虑一个情况: 用户第一次登录 '您是第一次登录..'
	Cookie []cookies=request.getCookies();
	boolean b=false;//假设没有lasttime cookie
	if(cookies!=null){ //保证有cookie,取遍历
		for(Cookie cookie: cookies){

			//取出名
			String name=cookie.getName();
			if("lasttime".equals(name)){
				//显示
				out.println("您上次登录时间是 "+cookie.getValue());
				//更新时间
				//把当前日期保存cookie
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowTime=simpleDateFormat.format(new java.util.Date());
				Cookie mycookie=new Cookie("lasttime",nowTime);
				mycookie.setMaxAge(7*3600*24);//保存一周
				response.addCookie(mycookie);
				b=true;
				break;
			}
		}
	}
	
	if(!b){
		//没有找到
		out.println("您是第一次登录..");
		//把当前日期保存cookie
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=simpleDateFormat.format(new java.util.Date());
		Cookie cookie=new Cookie("lasttime",nowTime);
		cookie.setMaxAge(7*3600*24);//保存一周
		response.addCookie(cookie);
	}

##cookie的细节
①一个浏览器最多放入 300cookie,一个web站点，最多 20cookie,而且一个cookie大小限制子4k
②cookie生命周期:
1.cookie默认生命周期是会话级别
2.通过setMaxAge() 可以设置生命周期
setMaxAge(正数) , 即多少秒后该cookie失效
setMaxAge(0) ,删除该cookie
setMaxAge(负数), 相当于该cookie生命周期是会话级别.

案例 ：

	//先得到该cookie
		Cookie cookies[]=request.getCookies();
		for(Cookie cookie: cookies){
			if(cookie.getName().equals("id")){
				System.out.println("id");
				//删除
				cookie.setMaxAge(0);
				response.addCookie(cookie);//一定带上这句话，否则不能删除
			}
		}

特别说明: 如果该web应用只有一个cookie ，则删除该cookie后，在浏览器的临时文件夹下没有该cookie文件，如果该web应用有多个cookie,则删除一个cookie后，文件还在，只是该cookie没有


③cookie存放中文，怎么处理
存放:
String val=java.net.URLEncoder.encode("中文","utf-8");
		Cookie cookie=new Cookie("name",val);
取出:
String val=java.net.URLDecoder.decode(cookie.getValue(), "utf-8");
				out.println("name ="+val);

				
##session:

①session是存在服务器的内存中
②一个用户浏览器,独享一个session域对象
③session中的属性的默认生命周期是30min ，你可以通过 web.xml来修改
	
3种session生命周期的设置：

（1）一个是 tomcat/conf/web.xml
  <session-config>
        <session-timeout>30</session-timeout>//表示30分钟的意思
</session-config>
对所有的web应用生效

（2）另外一个就是在单个web应用的下去修改 web.xml
<session-config>
  <session-timeout>30</session-timeout>session精确到分钟,cookie精确到秒
  </session-config>
如果发生冲突，则以自己的web应用优先级高

（3）session.setMaxInactiveinterval(60)六十秒后session失效

##session和cookie生命周期小结

session中可以存放多个属性
session 可以存放对象
session.setAttribute(“name”,val) , 如果名字重复，则会替换该属性.
如果同一个用户浏览器，向session设置一个属性的时候，如果名字相同了，会替换该对象值.

验证码功能
使用(原理是使用到java的绘图技术.)
这里最重要的就是生成验证码的servlet

	package com.test;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.image.BufferedImage;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Random;
	import javax.imageio.ImageIO;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	public class CreateCode extends HttpServlet {
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// 7.禁止浏览器缓存随机图片
			response.setDateHeader("Expires", -1);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			// 6.通知客户机以图片方式打开发送过去的数据
			response.setHeader("Content-Type", "image/jpeg");
			// 1.在内存中创建一副图片
			BufferedImage image = new BufferedImage(60, 30,
					BufferedImage.TYPE_INT_RGB);
			// 2.向图片上写数据
			Graphics g = image.getGraphics();
			// 设背景色
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 60, 30);
			// 3.设置写入数据的颜色和字体
			g.setColor(Color.RED);
			g.setFont(new Font(null, Font.BOLD, 20));
			// 4.向图片上写数据
			String num = makeNum();
			//这句话就是把随机生成的数值，保存到session
			request.getSession().setAttribute("checkcode", num); 通过session就可以直接去到随即生成的验证码了
			g.drawString(num, 0, 20);
			// 5.把写好数据的图片输出给浏览器
			ImageIO.write(image, "jpg", response.getOutputStream());
		}
		//该函数时随机生成7位数字
		public String makeNum() {
			Random r = new Random();
			//9999999 可以生成7位
			String num = r.nextInt(9999) + ""; 
			StringBuffer sb = new StringBuffer();
			//如果不够4位，前面补零
			for (int i = 0; i < 4 - num.length(); i++) {
				sb.append("0");
			}
			num = sb.toString() + num;
			return num;
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}
	}

如何使用
Login.java
<img src=”/验证码的url”/>
