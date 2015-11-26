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

3.
    
    
    
    