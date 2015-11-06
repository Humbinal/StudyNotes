###1. StringBuffer类简介

public final class StringBuffer
extends Object
implements Serializable, CharSequence

线程安全的可变字符序列。一个类似于 String 的字符串缓冲区，但不能修改。虽然在任意时间点上它都包含某种特定的字符序列，但通过某些方法调用可以改变该序列的长度和内容。 

线程安全问题：在多线程部分学习
安全--同步--
不安全--不同步--

StringBuffer和String的区别?
前者长度和内容可变，后者不可变。
如果使用前者做字符串的拼接，不会浪费太多的资源。

###2. 构造方法