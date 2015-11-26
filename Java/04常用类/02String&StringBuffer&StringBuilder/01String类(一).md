###1. String类概述

public final class String
extends Object
implements Serializable, Comparable<String>, CharSequence

String 类代表字符串。
Java 程序中的所有字符串字面值（如 "abc" ）都作为此类的实例实现（字符串字面值"abc"也可以看成是一个字符串对象）。

字符串是常量；它们的值在创建之后不能更改。字符串缓冲区支持可变的字符串。因为 String 对象是不可变的，所以可以共享。例如： 

	String str = "abc";
	 
 等效于： 

    char data[] = {'a', 'b', 'c'};
    String str = new String(data);

###2. 主要构造方法

public String():空构造
public String(byte[] bytes):把字节数组转成字符串
public String(byte[] bytes,int index,int length):把字节数组的一部分转成字符串
public String(char[] value):把字符数组转成字符串
public String(char[] value,int index,int count):把字符数组的一部分转成字符串
public String(String original):把字符串常量值转成字符串

###3. 方法

public int length()：返回此字符串的长度。

构造方法Demo：

	public class StringDemo {
		public static void main(String[] args) {
			// public String():空构造
			String s1 = new String();
			System.out.println("s1:" + s1);
			System.out.println("s1.length():" + s1.length());
			System.out.println("--------------------------");

			// public String(byte[] bytes):把字节数组转成字符串
			byte[] bys = { 97, 98, 99, 100, 101 };
			String s2 = new String(bys);
			System.out.println("s2:" + s2);
			System.out.println("s2.length():" + s2.length());
			System.out.println("--------------------------");

			// public String(byte[] bytes,int index,int length):把字节数组的一部分转成字符串
			// 我想得到字符串"bcd"
			String s3 = new String(bys, 1, 3);
			System.out.println("s3:" + s3);
			System.out.println("s3.length():" + s3.length());
			System.out.println("--------------------------");

			// public String(char[] value):把字符数组转成字符串
			char[] chs = { 'a', 'b', 'c', 'd', 'e', '爱', '林', '亲' };
			String s4 = new String(chs);
			System.out.println("s4:" + s4);
			System.out.println("s4.length():" + s4.length());
			System.out.println("--------------------------");

			// public String(char[] value,int index,int count):把字符数组的一部分转成字符串
			String s5 = new String(chs, 2, 4);
			System.out.println("s5:" + s5);
			System.out.println("s5.length():" + s5.length());
			System.out.println("--------------------------");
			
			//public String(String original):把字符串常量值转成字符串，该方法意义不大，一般使用下面的方法
			String s6 = new String("abcde");
			System.out.println("s6:" + s6);
			System.out.println("s6.length():" + s6.length());
			System.out.println("--------------------------");
			
			//字符串字面值"abc"也可以看成是一个字符串对象。
			String s7 = "abcde";
			System.out.println("s7:"+s7);
			System.out.println("s7.length():"+s7.length());
		}
	}

字符串的特点：一旦被赋值，就不能改变。（值不可变，而不是引用不可变）

	public class StringDemo {
		public static void main(String[] args) {
			String s = "hello";
			s += "world";
			System.out.println("s:" + s); // helloworld
		}
	}

String s = new String(“hello”);和String s = “hello”;的区别?	

前者会创建2个对象，后者创建1个对象。

==:比较引用类型比较的是地址值是否相同;
equals:比较引用类型.默认也是比较地址值是否相同，而String类重写了equals()方法，比较的是内容是否相同。

	public class StringDemo2 {
		public static void main(String[] args) {
			String s1 = new String("hello");
			String s2 = "hello";

			System.out.println(s1 == s2);// false
			System.out.println(s1.equals(s2));// true
		}
	}

	public class StringDemo3 {
		public static void main(String[] args) {
			String s1 = new String("hello");
			String s2 = new String("hello");
			System.out.println(s1 == s2);// false
			System.out.println(s1.equals(s2));// true

			String s3 = new String("hello");
			String s4 = "hello";
			System.out.println(s3 == s4);// false
			System.out.println(s3.equals(s4));// true

			String s5 = "hello";
			String s6 = "hello";
			System.out.println(s5 == s6);// true
			System.out.println(s5.equals(s6));// true
		}
	}
	
	/*
	 * 看程序写结果
	 * 字符串如果是变量相加，先开空间，在拼接。
	 * 字符串如果是常量相加，是先加，然后在常量池找，如果有就直接返回，否则，就创建。
	 */
	public class StringDemo4 {
		public static void main(String[] args) {
			String s1 = "hello";
			String s2 = "world";
			String s3 = "helloworld";
			System.out.println(s3 == s1 + s2);// false
			System.out.println(s3.equals((s1 + s2)));// true

			System.out.println(s3 == "hello" + "world");// true 这个特别注意
			System.out.println(s3.equals("hello" + "world"));// true

			// 通过反编译看源码，我们知道这里已经做好了处理。
			// System.out.println(s3 == "helloworld");
			// System.out.println(s3.equals("helloworld"));
		}
	}
