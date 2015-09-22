目前流行的Web服务器有很多，但是web服务器是如何实现的呢？对于这个问题web开发者关注的并不多。其实一个简单的web服务器的实现并不是十分复杂的。使用java基础知识可以简单的实现一个来了解复杂的web服务器背后的技术原理。
下面是代码：
	import java.io.*;
	import java.net.*;
	public class WebServer{
		public static void main(String[] args) throws Exception{
			ServerSocket ss=new ServerSocket(80);
			System.out.println("WebServer listened at 127.0.0.1:80");
			while(true){
				Socket s =ss.accept();		
				System.out.println("one connected");
				OutputStream os=s.getOutputStream();
				BufferedReader br=new BufferedReader(new FileReader("D:\\index.html"));
				String line="";
				while((line=br.readLine())!=null){
					os.write(line.getBytes());
				}
				br.close();
				os.close();
				s.close();
			}
		}
		
	}
	
index.html
	<html>
	<body>
	<h1>hello,web!</h1>
	</body>
	</html>

这个东西也就仅供我们玩一下，没太大的价值。当然，我们还可以讲这个程序改写的更强大，甚至变成一个完备的WebServer。
最关键的是从上面的代码和最终在浏览器看到的效果我们可以理解Web的工作原理。
>浏览器只不过按照http协议的要求，把请求封装成标准格式，打包发送到web服务器上。web服务器将请求收集起来，按照http协议的规定将请求解析，然后把对应的内容找到，又一次按照http协议的要求封装起来，返回给浏览器端。浏览器接下来把这些返回的内容解析，并渲染出来。

