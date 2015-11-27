###1. StringBuffer类简介

    public final class StringBuffer extends Object implements Serializable, CharSequence

线程安全的可变字符序列。一个类似于String的字符串缓冲区，但不能修改。虽然在任意时间点上它都包含某种特定的字符序列，但通过某些方法调用可以改变该序列的长度和内容。
 
我们如果对字符串进行拼接操作，每次拼接，都会构建一个新的String对象，既耗时，又浪费空间。而StringBuffer就可以解决这个问题

 
线程安全问题：在多线程部分学习
安全--同步--
不安全--不同步--

StringBuffer和String的区别?
前者长度和内容可变，后者不可变。
如果使用前者做字符串的拼接，不会浪费太多的资源。

###2. 构造方法

    public StringBuffer() 
    public StringBuffer(int capacity)
    public StringBuffer(String str)

###StringBuffer的方法

    public int capacity()：返回当前容量。	理论值
    public int length():返回长度（字符数）。 实际值

Demo:

    public class StringBufferDemo {
    	public static void main(String[] args) {
    		// public StringBuffer():无参构造方法
    		StringBuffer sb = new StringBuffer();
    		System.out.println("sb:" + sb);
    		System.out.println("sb.capacity():" + sb.capacity());
    		System.out.println("sb.length():" + sb.length());
    		System.out.println("--------------------------");
    		// public StringBuffer(int capacity):指定容量的字符串缓冲区对象
    		StringBuffer sb2 = new StringBuffer(50);
    		System.out.println("sb2:" + sb2);
    		System.out.println("sb2.capacity():" + sb2.capacity());
    		System.out.println("sb2.length():" + sb2.length());
    		System.out.println("--------------------------");
    		// public StringBuffer(String str):指定字符串内容的字符串缓冲区对象
    		StringBuffer sb3 = new StringBuffer("hello");
    		System.out.println("sb3:" + sb3);
    		System.out.println("sb3.capacity():" + sb3.capacity());
    		System.out.println("sb3.length():" + sb3.length());
    	}
    }


