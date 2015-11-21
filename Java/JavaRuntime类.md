Runtime类是JDK中提供的一个单例模式的类
1.该类使用饿汉式的单例模式
2.该类的exec方法可以执行dos命令

import java.io.IOException;

/*
 * Runtime:每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
 * exec(String command)
 */
public class RuntimeDemo {
	public static void main(String[] args) throws IOException {
		Runtime r = Runtime.getRuntime();
//		r.exec("winmine");
		// r.exec("notepad");
		// r.exec("calc");
//		r.exec("shutdown -s -t 10000");
		r.exec("shutdown -a");
		runtime.exec("cmd /c start http://www.baidu.com");//使用系统默认浏览器打开网址
	}
}

/*
 * class Runtime {
 * 		private Runtime() {}
 * 		private static Runtime currentRuntime = new Runtime();
 * 		public static Runtime getRuntime() {
 *       	return currentRuntime;
 *   	}
 * }
 */