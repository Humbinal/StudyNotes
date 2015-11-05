##String类常用功能（方法）

###1. String类的判断功能

boolean equals(Object obj):比较字符串的内容是否相同,区分大小写
boolean equalsIgnoreCase(String str):比较字符串的内容是否相同,忽略大小写
boolean contains(String str):判断大字符串中是否包含小字符串
boolean startsWith(String str):判断字符串是否以某个指定的字符串开头
boolean endsWith(String str):判断字符串是否以某个指定的字符串结尾
boolean isEmpty():判断字符串是否为空。

	/*
	 * 注意：
	 * 		字符串内容为空和字符串对象为空。
	 * 		String s = "";//字符串内容为空
	 * 		String s = null;//字符串对象为空
	*/

	public class StringDemo {
		public static void main(String[] args) {
			// 创建字符串对象
			String s1 = "helloworld";
			String s2 = "helloworld";
			String s3 = "HelloWorld";

			// boolean equals(Object obj):比较字符串的内容是否相同,区分大小写
			System.out.println("equals:" + s1.equals(s2));
			System.out.println("equals:" + s1.equals(s3));
			System.out.println("-----------------------");

			// boolean equalsIgnoreCase(String str):比较字符串的内容是否相同,忽略大小写
			System.out.println("equals:" + s1.equalsIgnoreCase(s2));
			System.out.println("equals:" + s1.equalsIgnoreCase(s3));
			System.out.println("-----------------------");

			// boolean contains(String str):判断大字符串中是否包含小字符串
			System.out.println("contains:" + s1.contains("hello"));
			System.out.println("contains:" + s1.contains("hw"));
			System.out.println("-----------------------");

			// boolean startsWith(String str):判断字符串是否以某个指定的字符串开头
			System.out.println("startsWith:" + s1.startsWith("h"));
			System.out.println("startsWith:" + s1.startsWith("hello"));
			System.out.println("startsWith:" + s1.startsWith("world"));
			System.out.println("-----------------------");

			// boolean endsWith(String str):判断字符串是否以某个指定的字符串结尾这个自己玩
			System.out.println("endsWith:" + s1.endsWith("h"));
			System.out.println("endsWith:" + s1.endsWith("hello"));
			System.out.println("endsWith:" + s1.endsWith("world"));
			System.out.println("-----------------------");

			// boolean isEmpty():判断字符串是否为空。
			System.out.println("isEmpty:" + s1.isEmpty());//false

			String s4 = "";
			String s5 = null;
			System.out.println("isEmpty:" + s4.isEmpty());//true
			// NullPointerException
			// s5对象不存在，所以不能调用方法，空指针异常
			System.out.println("isEmpty:" + s5.isEmpty());
		}
	}

###2. String类的获取功能

int length():获取字符串的长度。
char charAt(int index):获取指定索引位置的字符
int indexOf(int ch):返回指定字符在此字符串中第一次出现处的索引。

	//为什么这里是int类型，而不是char类型?
	//原因是：'a'和97其实都可以代表'a'
 
int indexOf(String str):返回指定字符串在此字符串中第一次出现处的索引。
int indexOf(int ch,int fromIndex):返回指定字符在此字符串中从指定位置后第一次出现处的索引。
int indexOf(String str,int fromIndex):返回指定字符串在此字符串中从指定位置后第一次出现处的索引。
String substring(int start):从指定位置开始截取字符串,默认到末尾。
String substring(int start,int end):从指定位置开始到指定位置结束截取字符串。

	public class StringDemo {
		public static void main(String[] args) {
			// 定义一个字符串对象
			String s = "helloworld";

			// int length():获取字符串的长度。
			System.out.println("s.length:" + s.length());//10
			System.out.println("----------------------");

			// char charAt(int index):获取指定索引位置的字符
			System.out.println("charAt:" + s.charAt(7));//r
			System.out.println("----------------------");

			// int indexOf(int ch):返回指定字符在此字符串中第一次出现处的索引。
			System.out.println("indexOf:" + s.indexOf('e'));//1
			System.out.println("----------------------");

			// int indexOf(String str):返回指定字符串在此字符串中第一次出现处的索引。
			System.out.println("indexOf:" + s.indexOf("owo"));//4
			System.out.println("----------------------");

			// int indexOf(int ch,int fromIndex):返回指定字符在此字符串中从指定位置后第一次出现处的索引。
			System.out.println("indexOf:" + s.indexOf('l', 4));//8
			System.out.println("indexOf:" + s.indexOf('k', 4)); // -1
			System.out.println("indexOf:" + s.indexOf('l', 40)); // -1
			System.out.println("----------------------");

			// 自己练习：int indexOf(String str,int
			// fromIndex):返回指定字符串在此字符串中从指定位置后第一次出现处的索引。

			// String substring(int start):从指定位置开始截取字符串,默认到末尾。包含start这个索引
			System.out.println("substring:" + s.substring(5));//world
			System.out.println("substring:" + s.substring(0));//helloworld
			System.out.println("----------------------");

			// String substring(int start,int
			// end):从指定位置开始到指定位置结束截取字符串。包括start索引但是不包end索引
			System.out.println("substring:" + s.substring(3, 8));//lowor
			System.out.println("substring:" + s.substring(0, s.length()));//helloworld
		}
	}

统计一个字符串中大写字母字符，小写字母字符，数字字符出现的次数。
	
	if(ch>='0' && ch<='9') numberCount++
	if(ch>='a' && ch<='z') smallCount++
	if(ch>='A' && ch<='Z') bigCount++

###3. String类的转换功能

byte[] getBytes():把字符串转换为字节数组。
char[] toCharArray():把字符串转换为字符数组。
static String valueOf(char[] chs):把字符数组转成字符串。
static String valueOf(int i):把int类型的数据转成字符串。
//注意：String类的valueOf方法(重载方法)可以把任意类型的数据转成字符串。
String toLowerCase():把字符串转成小写。
String toUpperCase():把字符串转成大写。
String concat(String str):把字符串拼接。

	public class StringDemo {
		public static void main(String[] args) {
			// 定义一个字符串对象
			String s = "JavaSE";

			// byte[] getBytes():把字符串转换为字节数组。
			byte[] bys = s.getBytes();
			for (int x = 0; x < bys.length; x++) {
				System.out.println(bys[x]);//74 97 118 97 83 69
			}
			System.out.println("----------------");

			// char[] toCharArray():把字符串转换为字符数组。
			char[] chs = s.toCharArray();
			for (int x = 0; x < chs.length; x++) {
				System.out.println(chs[x]);//JavaSE
			}
			System.out.println("----------------");

			// static String valueOf(char[] chs):把字符数组转成字符串。
			String ss = String.valueOf(chs);
			System.out.println(ss);//JavaSE
			System.out.println("----------------");

			// static String valueOf(int i):把int类型的数据转成字符串。
			int i = 100;
			String sss = String.valueOf(i);
			System.out.println(sss);//100
			System.out.println("----------------");

			// String toLowerCase():把字符串转成小写。
			System.out.println("toLowerCase:" + s.toLowerCase());//javase
			System.out.println("s:" + s);//JavaSE
			// System.out.println("----------------");
			// String toUpperCase():把字符串转成大写。
			System.out.println("toUpperCase:" + s.toUpperCase());//JAVASE
			System.out.println("----------------");

			// String concat(String str):把字符串拼接。
			String s1 = "hello";
			String s2 = "world";
			String s3 = s1 + s2;
			String s4 = s1.concat(s2);
			System.out.println("s3:"+s3);//helloworld
			System.out.println("s4:"+s4);//helloworld
		}
	}
	
首字母大写：

		String result = s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase());
		

###4. String类的其他功能

替换功能：
String replace(char old,char new)
String replace(String old,String new)

去除字符串两空格	
String trim()

按字典顺序比较两个字符串  
int compareTo(String str)
int compareToIgnoreCase(String str)

根据给定正则表达式的匹配拆分此字符串 
public String[] split(String regex)

例如，字符串 "boo:and:foo" 使用这些表达式可生成以下结果： 

	Regex 	结果 
	: 		{ "boo", "and", "foo" } 
	o 		{ "b", "", ":and:f" } 



	public class StringDemo {
		public static void main(String[] args) {
			// 替换功能
			String s1 = "helloworld";
			String s2 = s1.replace('l', 'k');
			String s3 = s1.replace("owo", "ak47");
			System.out.println("s1:" + s1);
			System.out.println("s2:" + s2);
			System.out.println("s3:" + s3);
			System.out.println("---------------");

			// 去除字符串两空格
			String s4 = " hello world  ";
			String s5 = s4.trim();
			System.out.println("s4:" + s4 + "---");
			System.out.println("s5:" + s5 + "---");

			// 按字典顺序比较两个字符串
			String s6 = "hello";
			String s7 = "hello";
			String s8 = "abc";
			String s9 = "xyz";
			System.out.println(s6.compareTo(s7));// 0
			System.out.println(s6.compareTo(s8));// 7
			System.out.println(s6.compareTo(s9));// -16
			
			//字符串的拆分
			String s="boo:and:foo";
			String s1[]=s.split(":");
			for (int i = 0; i < s1.length; i++) {
				System.out.println(s1[i]);//boo  and  foo
			}
		}
	}