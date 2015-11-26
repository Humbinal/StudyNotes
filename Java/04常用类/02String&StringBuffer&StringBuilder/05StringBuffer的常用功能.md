1.StringBuffer的添加功能

    public StringBuffer append(String str):可以把任意类型数据添加到字符串缓冲区里面,并返回字符串缓冲区本身
    public StringBuffer insert(int offset,String str):在指定位置把任意类型的数据插入到字符串缓冲区里面,并返回字符串缓冲区本身
 
Demo:

    public class StringBufferDemo {
    	public static void main(String[] args) {
    		// 创建字符串缓冲区对象
    		StringBuffer sb = new StringBuffer();
    		// public StringBuffer append(String str)
    		// StringBuffer sb2 = sb.append("hello");
    		// System.out.println("sb:" + sb);
    		// System.out.println("sb2:" + sb2);
    		// System.out.println(sb == sb2); // true
    		// 一步一步的添加数据
    		// sb.append("hello");
    		// sb.append(true);
    		// sb.append(12);
    		// sb.append(34.56);
    		// 链式编程
    		sb.append("hello").append(true).append(12).append(34.56);
    		System.out.println("sb:" + sb);
    		// public StringBuffer insert(int offset,String
    		// str):在指定位置把任意类型的数据插入到字符串缓冲区里面,并返回字符串缓冲区本身
    		sb.insert(5, "world");
    		System.out.println("sb:" + sb);
    	}
    }
    
2.StringBuffer的删除功能

    public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身
    public StringBuffer delete(int start,int end):删除从指定位置开始指定位置结束的内容，并返回本身
    
Demo:

    public class StringBufferDemo {
    	public static void main(String[] args) {
    		// 创建对象
    		StringBuffer sb = new StringBuffer();
    		// 添加功能
    		sb.append("hello").append("world").append("java");
    		System.out.println("sb:" + sb);
    		// public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身
    		// 需求：我要删除e这个字符，肿么办?
    		// sb.deleteCharAt(1);
    		// 需求:我要删除第一个l这个字符，肿么办?
    		// sb.deleteCharAt(1);
    		// public StringBuffer delete(int start,int
    		// end):删除从指定位置开始指定位置结束的内容，并返回本身
    		// 需求：我要删除world这个字符串，肿么办?
    		// sb.delete(5, 10);//包括左侧不包括右侧
    		// 需求:我要删除所有的数据
    		sb.delete(0, sb.length());
    		System.out.println("sb:" + sb);
    	}
    }

3.StringBuffer的替换功能

    public class StringBufferDemo {
    	public static void main(String[] args) {
    		// 创建字符串缓冲区对象
    		StringBuffer sb = new StringBuffer();
    		// 添加数据
    		sb.append("hello");
    		sb.append("world");
    		sb.append("java");
    		System.out.println("sb:" + sb);
    		// public StringBuffer replace(int start,int end,String
    		// str):从start开始到end用str替换
    		// 需求：我要把world这个数据替换为"节日快乐"
    		sb.replace(5, 10, "节日快乐");
    		System.out.println("sb:" + sb);
    	}
    }
    
4.StringBuffer的反转功能

    public class StringBufferDemo {
    	public static void main(String[] args) {
    		// 创建字符串缓冲区对象
    		StringBuffer sb = new StringBuffer();
    		// 添加数据
    		sb.append("我是字符串");
    		System.out.println("sb:" + sb);
    		// public StringBuffer reverse()
    		sb.reverse();
    		System.out.println("sb:" + sb);
    	}
    }    
    
5.StringBuffer的截取功能

    注意返回值类型不再是StringBuffer本身了
    public String substring(int start)
    public String substring(int start,int end)

Demo：

    public class StringBufferDemo {
    	public static void main(String[] args) {
    		// 创建字符串缓冲区对象
    		StringBuffer sb = new StringBuffer();
    		// 添加元素
    		sb.append("hello").append("world").append("java");
    		System.out.println("sb:" + sb);
    		// 截取功能
    		// public String substring(int start)
    		String s = sb.substring(5);
    		System.out.println("s:" + s);
    		System.out.println("sb:" + sb);//sb本身不变
    		// public String substring(int start,int end)
    		String ss = sb.substring(5, 10);
    		System.out.println("ss:" + ss);
    		System.out.println("sb:" + sb);
    	}
    }

6.String和StringBuffer的相互转换

Demo：

    public static void main(String[] args) {
    	// String -->StringBuffer
    	String s = "hello";
    	// 注意：不能把字符串的值直接赋值给StringBuffer
    	// StringBuffer sb = "hello";
    	// StringBuffer sb = s;
    	// 方式1:通过构造方法
    	StringBuffer sb = new StringBuffer(s);
    	// 方式2：通过append()方法
    	StringBuffer sb2 = new StringBuffer();
    	sb2.append(s);
    	System.out.println("sb:" + sb);
    	System.out.println("sb2:" + sb2);
    	System.out.println("---------------");
    	// StringBuffer -->String
    	StringBuffer buffer = new StringBuffer("java");
    	// String(StringBuffer buffer)//String类的一个构造方法
    	// 方式1:通过构造方法
    	String str = new String(buffer);
    	// 方式2：通过toString()方法
    	String str2 = buffer.toString();
    	System.out.println("str:" + str);
    	System.out.println("str2:" + str2);
    }

7.把数组拼接成一个字符串

Demo：

    public class StringBufferTest2 {
    	public static void main(String[] args) {
    		// 定义一个数组
    		int[] arr = { 44, 33, 55, 11, 22 };
    		// 定义功能
    		// 方式1：用String做拼接的方式
    		String s1 = arrayToString(arr);
    		System.out.println("s1:" + s1);
    		// 方式2:用StringBuffer做拼接的方式
    		String s2 = arrayToString2(arr);
    		System.out.println("s2:" + s2);
    	}
    	// 用StringBuffer做拼接的方式
    	public static String arrayToString2(int[] arr) {
    		StringBuffer sb = new StringBuffer();
    		sb.append("[");
    		for (int x = 0; x < arr.length; x++) {
    			if (x == arr.length - 1) {
    				sb.append(arr[x]);
    			} else {
    				sb.append(arr[x]).append(", ");
    			}
    		}
    		sb.append("]");
    		return sb.toString();
    	}
    	// 用String做拼接的方式
    	public static String arrayToString(int[] arr) {
    		String s = "";
    		s += "[";
    		for (int x = 0; x < arr.length; x++) {
    			if (x == arr.length - 1) {
    				s += arr[x];
    			} else {
    				s += arr[x];
    				s += ", ";
    			}
    		}
    		s += "]";
    		return s;
    	}
    }


8.把字符串反转

Demo：

    public class StringBufferTest3 {
    	public static void main(String[] args) {
    		// 键盘录入数据
    		Scanner sc = new Scanner(System.in);
    		System.out.println("请输入数据：");
    		String s = sc.nextLine();
    		// 方式1：用String做拼接
    		String s1 = myReverse(s);
    		System.out.println("s1:" + s1);
    		// 方式2：用StringBuffer的reverse()功能
    		String s2 = myReverse2(s);
    		System.out.println("s2:" + s2);
    	}
    	// 用StringBuffer的reverse()功能
    	public static String myReverse2(String s) {
    		// StringBuffer sb = new StringBuffer();
    		// sb.append(s);
    		// StringBuffer sb = new StringBuffer(s);
    		// sb.reverse();
    		// return sb.toString();
    		// 简易版
    		return new StringBuffer(s).reverse().toString();
    	}
    	// 用String做拼接
    	public static String myReverse(String s) {
    		String result = "";
    		char[] chs = s.toCharArray();
    		for (int x = chs.length - 1; x >= 0; x--) {
    			// char ch = chs[x];
    			// result += ch;
    			result += chs[x];
    		}
    		return result;
    	}
    }
    
9.判断一个字符串是否是对称字符串

Demo：

    /*
     * 判断一个字符串是否是对称字符串
     * 例如"abc"不是对称字符串，"aba"、"abba"、"aaa"、"mnanm"是对称字符串
     * 
     * 分析：
     * 		判断一个字符串是否是对称的字符串，我只需要把
     * 			第一个和最后一个比较
     * 			第二个和倒数第二个比较
     * 			...
     * 		比较的次数是长度除以2。
     */
    public class StringBufferTest4 {
    	public static void main(String[] args) {
    		// 创建键盘录入对象
    		Scanner sc = new Scanner(System.in);
    		System.out.println("请输入一个字符串：");
    		String s = sc.nextLine();
    		// 一个一个的比较
    		boolean b = isSame(s);
    		System.out.println("b:" + b);
    		
    		//用字符串缓冲区的反转功能
    		boolean b2 = isSame2(s);
    		System.out.println("b2:"+b2);
    	}
    	
    	public static boolean isSame2(String s) {
    		return new StringBuffer(s).reverse().toString().equals(s);
    	}
    	
    	// public static boolean isSame(String s) {
    	// // 把字符串转成字符数组
    	// char[] chs = s.toCharArray();
    	//
    	// for (int start = 0, end = chs.length - 1; start <= end; start++, end--) {
    	// if (chs[start] != chs[end]) {
    	// return false;
    	// }
    	// }
    	//
    	// return true;
    	// }
    	public static boolean isSame(String s) {
    		boolean flag = true;
    		// 把字符串转成字符数组
    		char[] chs = s.toCharArray();
    		for (int start = 0, end = chs.length - 1; start <= end; start++, end--) {
    			if (chs[start] != chs[end]) {
    				flag = false;
    				break;
    			}
    		}
    		return flag;
    	}
    }












    
