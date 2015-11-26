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
    	// String(StringBuffer buffer)
    	// 方式1:通过构造方法
    	String str = new String(buffer);
    	// 方式2：通过toString()方法
    	String str2 = buffer.toString();
    	System.out.println("str:" + str);
    	System.out.println("str2:" + str2);
    }
    














    