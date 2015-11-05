###1. Scanner简述
public final class Scanner
extends Object
implements Iterator<String>

位于java.util.Scanner

一个可以使用正则表达式来解析基本类型和字符串的简单文本扫描器。 

System类下有一个静态的字段：
public static final InputStream 

in“标准”输入流。此流已打开并准备提供输入数据。通常，此流对应于键盘输入或者由主机环境或用户指定的另一个输入源。

	构造方法：
			InputStream is = System.in;			
			Scanner(InputStream is)
	常用的格式：
			Scanner sc = new Scanner(System.in); 

	/*
	 *InputStream is = System.in;
	 * 
	 * class Demo {
	 * 		public static final int x = 10;
	 * 		public static final Student s = new Student();
	 * }
	 * int y = Demo.x;
	 * Student s = Demo.s;
	 * 构造方法：
	 * 		Scanner(InputStream source)
	 */
	 
基本用法：
	 
	import java.util.Scanner;

	public class ScannerDemo {
		public static void main(String[] args) {
			// 创建对象
			Scanner sc = new Scanner(System.in);

			int x = sc.nextInt();
			
			System.out.println("x:" + x);
		}
	}
	
###2. 基本格式

	public boolean hasNextXxx():判断是否是某种类型的元素
	public Xxx nextXxx():获取该元素

	/* 
	*举例：用int类型的方法举例
	* 	public boolean hasNextInt()
	* 	public int nextInt()
	* 
	* 注意：
	*	如果不使用hasNextInt()来判断，输入字母就会出现如下问题
	* 	InputMismatchException：输入的和你想要的不匹配
	*/
	
	public class ScannerDemo {
		public static void main(String[] args) {
			// 创建对象
			Scanner sc = new Scanner(System.in);

			// 获取数据
			if (sc.hasNextInt()) {
				int x = sc.nextInt();
				System.out.println("x:" + x);
			} else {
				System.out.println("你输入的数据有误，请输入整数");
			}
		}
	}
	
###3. 常用方法

	/*
	 * 常用的两个方法：
	 * 		public int nextInt():获取一个int类型的值
	 * 		public String nextLine():获取一个String类型的值
	 * 
	 *
	 * 获取两个数据：
	 * 		先获取一个数值，再获取一个字符串，会出现问题。
	 * 		主要原因：就是那个换行符号的问题。
	 * 如何解决呢?
	 * 		方法A:先获取一个数值后，再创建一个新的键盘录入对象获取字符串。
	 * 		方法B:把所有的数据都先按照字符串获取，然后要什么，你就对应的转换为什么。
	 */
	 
	public class ScannerDemo {
	public static void main(String[] args) {
			// 创建对象
			Scanner sc = new Scanner(System.in);

			// 获取两个int类型的值
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println("a:" + a + ",b:" + b);
			System.out.println("-------------------");

			// 获取两个String类型的值
			String s1 = sc.nextLine();
			String s2 = sc.nextLine();
			System.out.println("s1:" + s1 + ",s2:" + s2);
			System.out.println("-------------------");

			// 先获取一个字符串，再获取一个int值
			String s1 = sc.nextLine();
			int b = sc.nextInt();
			System.out.println("s1:" + s1 + ",b:" + b);
			System.out.println("-------------------");

			// 先获取一个int值，再获取一个字符串
			int a = sc.nextInt();
			String s2 = sc.nextLine();
			System.out.println("a:" + a + ",s2:" + s2);
			System.out.println("-------------------");

			//先获取一个数值后，在创建一个新的键盘录入对象获取字符串
			int a = sc.nextInt();
			Scanner sc2 = new Scanner(System.in);
			String s = sc2.nextLine();
			System.out.println("a:" + a + ",s:" + s);
		}
	}