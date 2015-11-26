Object类是所有类的超类。所有对象（包括数组）都实现这个类的方法。 

构造方法:Object() 

常用方法：

1. public int hashCode() 

返回该对象的哈希码值。支持此方法是为了提高哈希表（例如 java.util.Hashtable 提供的哈希表）的性能。

哈希值是根据哈希算法计算出的值，该值和地址有关，但是不是实际的地址值。

实际上，由Object类定义的hashCode方法确实会针对不同的对象返回不同的整数。（这一般是通过将该对象的内部地址转换成一个整数来实现的，但是 JavaTM 编程语言不需要这种实现技巧。） 

2. public final Class<?> getClass()

返回此 Object 的运行时类。返回的 Class 对象是由所表示类的 static synchronized 方法锁定的对象。

实际结果类型是 Class<? extends |X|>，其中 |X| 表示清除表达式中的静态类型，该表达式调用 getClass。 例如，以下代码片段中不需要强制转换：

	Number n = 0; 
	Class<? extends Number> c = n.getClass(); 

实例：显示Student类的类名

	Student s = new Student();
	String str2  = s.getClass().getName();
	System.out.println(str2);//返回包名.类名
	
3. public String toString()

返回该对象的字符串表示。通常，toString 方法会返回一个“以文本方式表示”此对象的字符串。结果应是一个简明但易于读懂的信息表达式。建议所有子类都重写此方法。 
Object 类的 toString 方法返回一个字符串，该字符串由类名（对象是该类的一个实例）、at 标记符“@”和此对象哈希码的无符号十六进制表示组成。换句话说，该方法返回一个字符串，它的值等于： 

	getClass().getName() + '@' + Integer.toHexString(hashCode())

重写该方法：（把所有成员变量的值组成返回即可,使用MyEclipse自动生成）

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

4. public boolean equals(Object obj)

== 比较：

基本类型：比较的是值是否相等
引用类型：比较的是地址值是否相等

equals 比较:

引用类型：默认情况下，比较的是地址值。（不过，我们可以根据情况自己重写该方法。一般重写都是自动生成，比较对象的成员变量值是否相同）


指示其他某个对象是否与此对象“相等”。 
equals 方法在非空对象引用上实现相等关系： 

自反性：对于任何非空引用值 x，x.equals(x) 都应返回 true。 
对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。 
传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。 
一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。 
对于任何非空引用值 x，x.equals(null) 都应返回 false。 
Object 类的 equals 方法实现对象上差别可能性最大的相等关系；即，对于任何非空引用值 x 和 y，当且仅当 x 和 y 引用同一个对象时，此方法才返回 true（x == y 具有值 true）。 

注意：当此方法被重写时，通常有必要重写 hashCode 方法，以维护 hashCode 方法的常规协定，该协定声明相等对象必须具有相等的哈希码。

返回：如果此对象与 obj 参数相同，则返回 true；否则返回 false。

源码：

  	public boolean equals(Object obj) {
		return (this == obj);
	}

这个方法，默认情况下比较的是地址值。比较地址值一般来说意义不大，所以我们要重写该方法。（依然可以使用MyEclipse自动生成）

一般都是用来比较对象的成员变量值是否相同。
重写的代码优化：提高效率，提高程序的健壮性。

重写：

	//	@Override
	//	public boolean equals(Object obj) {
	//		// return true;
	//		//这里要改进，根据这里比较的成员变量来决定返回true还是false
	//		//这里其实要比价的就是name和age
	//		//但是，name是String类型的，而String是引用类型的，所以，在这里不能直接用==比较，应该用equals()比较
	//		//String的equals()方法是重写自Object类的，比较的是字符串的内容是否相同
	//		//this -- s1
	//		//obj -- s2
	//		//我们要使用的是学生类的特有成员变量,所以要向下转型
	//		Student s = (Student)obj; //s -- obj -- s2;
	//		if(this.name.equals(s.name) && this.age == s.age) {
	//			return true;
	//		}else {
	//			return false;
	//		}
	//	}

优化方案：

	@Override
	public boolean equals(Object obj) {
		//为了提高效率
		if(this == obj){
			return true;
		}
		
		//为了提供程序的健壮性
		//我先判断一下，obj是不是学生的一个对象，如果是，再做向下转型，如果不是，直接返回false。
		//这个时候，我们要判断的是对象是否是某个类的对象?
		//记住一个格式：对象名 instanceof 类名
		//表示：判断该对象名是否是该类名一个对象
		if(!(obj instanceof Student)){
			return false;
		}
		//如果是就继续
		
		Student s = (Student)obj;
		//System.out.println("同一个对象，还需要向下转型并比较吗?");
		return this.name.equals(s.name) && this.age == s.age;
	}
	

使用MyEclipse自动生成：

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

5. protected void finalize() throws Throwable

当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。用于垃圾回收，但是什么时候回收不确定。


6. protected Object clone() throws CloneNotSupportedException

创建并返回此对象的一个副本，由于是protected修饰的，所以必须重写才能在别的类访问。

重写：

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

Cloneable:此类实现了 Cloneable 接口，以指示 Object.clone() 方法可以合法地对该类实例进行按字段复制。 
这个接口是标记接口，告诉我们实现该接口的类就可以实现对象的复制了。

	public class Student implements Cloneable{}//允许学生类克隆

克隆示例：

	public class StudentDemo {
		public static void main(String[] args) throws CloneNotSupportedException {
			//创建学生对象
			Student s = new Student();
			s.setName("name1");
			s.setAge(27);
			
			//克隆学生对象
			Object obj = s.clone();
			Student s2 = (Student)obj;
			System.out.println("---------");
			
			System.out.println(s.getName()+"---"+s.getAge());
			System.out.println(s2.getName()+"---"+s2.getAge());
			
			//以前的做法
			Student s3 = s;
			System.out.println(s3.getName()+"---"+s3.getAge());
			System.out.println("---------");
			
			//其实是有区别的
			s3.setName("name2");
			s3.setAge(30);
			System.out.println(s.getName()+"---"+s.getAge());
			System.out.println(s2.getName()+"---"+s2.getAge());
			System.out.println(s3.getName()+"---"+s3.getAge());
			
		}
	}