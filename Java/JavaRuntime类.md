Runtime����JDK���ṩ��һ������ģʽ����
1.����ʹ�ö���ʽ�ĵ���ģʽ
2.�����exec��������ִ��dos����

import java.io.IOException;

/*
 * Runtime:ÿ�� Java Ӧ�ó�����һ�� Runtime ��ʵ����ʹӦ�ó����ܹ��������еĻ��������ӡ�
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
		runtime.exec("cmd /c start http://www.baidu.com");//ʹ��ϵͳĬ�����������ַ
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