###类和对象的定义
类是现实世界或思维世界中的实体在计算机中的反映，它将数据以及这些数据上的操作封装在一起。而对象是具有类类型的变量，存在于堆内存中。类是对象的抽象，而对象是类的具体实例。类是抽象的，不占用内存，而对象是具体的，占用存储空间。

###定义一个类语法格式

	[修饰符] class 类名{
		//可以有属性、方法、构造方法、初始化块、枚举类、内部类等。。
		}

注意：类的修饰符可以省略(必须在同一个包里，下面会讲到)，还可以是：public，final(不能被继承)，abstract(不能创建实例，但增加了一个可以包含抽象方法的功能)。类名必须是多个有意义的单词(首字母大写)连缀。注意修饰符之间，没有先后顺序。

属性定义的语法格式：[修饰符] 类型 field名字 [=默认值];

[修饰符] --可以省略。 
还可以是public|protected|private,static,final。

类型 --不能省略。
可以是基本类型，数组，任意类(JDK的类和自定义的类)，接口，枚举。

field名字 --不能省略。
必须是多个有意义的单词(第一个首字母小写)。
[=默认值] --可以省略。

###方法定义的语法格式：
	[修饰符] 返回值类型 方法名(多个形参声明){
	//0~N条可执行语句
	//如果方法签名中有返回值类型声明，则该方法里必须包含[有效的]return语句
	}
[修饰符] --可以省略。
还可以是public|protected|private,static,final|abstract。

返回值类型--不能省略。
---编译时，系统认为if里面的return是有可能不被执行的(不算有效的)。

方法名 --不能省略。--一般是动词，第一个首字母小写，后面首字母大写。
形参声明 --每个形参必须满足“类型”“变量”的格式。

注意：Java方法的参数传递机制只有一种：值传递。

补充一下：Java中有种形参个数可变的方法，如public void info(int... nums)：这里的nums是一个int类型数组。(调用时可不传参)每个方法最多只能有一个形参个数可变的参数(本质是数组)。形参个数可变的参数必须放在形参位置的最后。

###构造器(系统默认提供，可重载)定义的语法格式：
	[修饰符] 构造器名(0~N个形参声明){
	//0~N条可执行语句
	}
	
[修饰符] --可以省略。
还可以是public|protected|private。
构造器名 --不能省略，必须与类名相同。
构造器可认为是一种特殊的方法，作用是产生对象(通过构造器初始化类的实例)。
注意赋值属于可执行语句，要放在方法或构造器中。

###初始化块(没有名字)的语法：
	[修饰符]{
	//可执行语句
	}
修饰符 只能出现static。有static说明它是类初始化块。实例初始化块(没有修饰符)的代码，会在每次调用构造器之前被“隐式”执行。规则：位于构造器之前，而且无需传参的代码可被提取到初始化块中，所以它的优先级比构造器更高，编译时实例初始化块的代码都会被提取到构造器的“最前面”。类(static)初始化：当类被加载后，对类初始化后时被隐式执行。

一个JVM对一个类只初始化一次。当程序第一次【主动】使用该类就会初始化该类，下面几种情况会初始化该类。

A.访问了该类静态field或静态方法。
B.初始化了该类的子类--因为Java初始化一个类，永远先从最顶层父类开始初始化。
C.使用反射来Class.forName(类名字符串)
D.如果该类作为主类使用(有main方法，而且程序确实从main方法开始执行)
E.使用该类来创建对象。

###装箱和拆箱：

自动装箱：基本类型的值，可以自动当成它的包装类实例使用。它可以自动当成对象使用，也可以作为对象传入方法。自动拆箱：当有需要时，程序会自动把包装类的实例拆成基本类型的值。（如：Integer和int）

###局部变量

局部变量可以是方法里的局部变量、形参、代码块里面的局部变量(只在花括号中有效)。方法中的局部变量属于方法，修饰符只能是final,且必须初始化。局部变量本身既不属于实例，也不属于类，所以局部变量不能用static修饰。而成员变量系统会对其进行初始化。

平常我们写A a=new A();其实上我们做的是创建一个A对象(存放在堆中)，并让a这个引用变量指向A对象。当我们打印一个对象时，实际上就是打印输出该对象的toString()方法的返回值。

###修饰符：static：

有static(类的标识)修饰符修饰的成员(属性或方法)属于类本身(我们称为类成员)，反之属于类的实例(我们称为实例成员)。记住永远不要使用对象去调用static修饰的方法、属性，如果使用了的话，一定要把对象翻译成类(当程序通过实例来访问类变量时，由于类变量本身不属于实例，因此底层是委托给通过类来访问，且有static修饰的成员不能访问非static成员)。

this(在任何非static修饰的方法、构造器中可用)关键字的使用有两种：
1.this引用：当this在方法中，this代表调用该方法的对象。当this在构造器中，this代表该构造器正在初始化的对象。
2.this调用。---只能在构造器的第一行出现。

###final修饰符：
final修饰的成员变量(filed),必须由程序员执行初始化(只能被赋值一次，不可改变)。---只能在定义时、初始化块、构造器中指定初始值。如果final修饰类变量(被static修饰的成员)，则只能在定义时或类(static)初始化块中指定初始值。final修饰的局部变量：不能被重新赋值。

JVM会把所有用过的对象进行缓存。final修饰的变量,它会被执行宏替换如果final修饰的变量，可以在编译时就能确定它的值，那么这个变量就不存在。final修饰的方法不能被重写。好处：禁止父类的方法被重写，避免了父类被破坏。final类不能有子类,比较安全。

###方法重载(Overload)：
同类中相同方法名的方法参数不同，与返回值类型和修饰符static无关。

###方法重写(Override)：
重写也叫覆盖：当子类从父类那里继承得到的方法不能真正满足子类的需求时,子类可以重写父类的方法。
方法名相同、参数列表相同；
子类重写的方法访问权限必须比父类方法的访问权限更大或相等；
重写方法的返回值类型必须比父类方法的返回值类型更小或相等，重写方法的声明抛出的异常必须比父类声明抛出的异常更小或相等。
注意：我们常在方法上加@Override注解，它的作用是让编译器执行检查，要求该类必须重写父类的该方法。

###包、静态导入：
包的作用其实即相当于命名空间的作用。Java要求一个类的完整类名 = 包名+类名。包名统一小写，当我们要把一个类放入包中,必须在该类的源代码中使用package来声明包,生成的class文件必须放在相应文件结构下面。普通的import是省略包，而import static 导入指定类下的静态成员(调用时可省略类名)。

###面向对象之封装：
封装就是隐藏不想被外界操作的属性、方法、构造器。提供给别人调用的方法。封装目的：简化编程，能更好地保证对象的完整性。
提到封装不得不谈：访问权限修饰符：
private --当前类访问权限。没有访问修饰符 --包访问权限。
protected --子类访问权限。
public --公共访问权限。
访问权限由小到大：private-->没有(默认)访问修饰符-->protected-->public。如果直接对属性赋值，很容易导致类的数据完整性被破坏，一般不推荐。

###面向对象之继承(extends)：
类与类之间从一般到特殊(“is a”)的关系，如苹果是一个种水果，那么苹果就可以从水果那里继承，子类继承父类(隐式地获得父类的对象)，就可以获得的属性和方法。但这样会增加耦合度(一旦父类属性或方法变了，子类也要跟着变)，所以一般推荐使用组合，继承能不用尽量不用。补充一下：组合是一种"has a"的关系可以显式地获得被包含类的对象。组合关系在运行期决定，而继承关系在编译期就已经决定了，组合是在组合类和被包含类之间的一种松耦合关系，而继承则是父类和子类之间的一种紧耦合关系。

###super关键字：
super有两种使用方式：
super限定：强制去访问父类的方法和field。--如果子类中定义了与父类同名的field，并不会被覆盖。
super调用：用于显式调用父类的构造器。
规则：子类的构造器【总会】先调用父类的构造器【一次】.
注意：super调用、this调用都必须出现在构造器的第一行，且不能重复出现。如果有this调用，子类构造器会先找到this调用所对应子类中被重载的构造器。

###面向对象之多态(Ploymorphism)：
同一种类型的变量，在执行同一个方法时，表现出多种行为特征。

举例说明1：

	/*
	*@Author:lhy
	*@Description:水果类，用作父类；苹果类从Fruit继承
	**/
	class Fruit {
		public void info() {
		System.out.println("父类的info方法被调用！");
		}
	}
	class Apple extends Fruit {
		public void info() {
		System.out.println("子类的info方法被调用前！");
		super.info();
		System.out.println("子类的info方法被调用后！");
		}
	}
	public class Test {
		public static void main(String[] args) {
		Fruit f1 = new Apple();
		Fruit f2 = new Fruit();
		f1.info();//子类的info方法被调用前！ 父类的info方法被调用！ 子类的info方法被调用后！
		f2.info();//父类的info方法被调用！
		}
	}

运行一下，可以看到同一种类型(Fruit)的变量f1，f2执行用一个方法，表现出不同的行为特征。而且子类的实例完全可以当成父类的对象使用。多态增加了Java语言的灵活性，也是和设计模式紧密相连的(之后会讲到)。

再看一个：

	class Fruit {
		protected int i=1000;
		public void test() {
			System.out.println("父类的test方法被调用！");
		}
	}
	class Apple extends Fruit {
		protected int i=10;
		public void test() {
			System.out.println("子类的test方法被调用！");
		}
	}
	public class Test {
		public static void main(String[] args) {
			Fruit f1 = new Apple();
			//当调用引用变量时，它总是呈现出它的运行时特征。
			f1.test();//子类的test方法被调用！
			System.out.println(f1.i);//1000
			/*引用类型之间，只能在具有继承关系的两个类型之间转换，否则编译时就会报错！
			强制类型转换的运算符是(类型)*/
			System.out.println(((Apple)f1).i);//10
		}
	}

###需要特别注意的：
Java的引用变量有两种类型，编译时类型(由声明它的类型决定)，运行时类型(由实际赋给该变量的类型决定)。在编译阶段：编译器并不知道引用变量实际所引用的对象的类型。

###instanceof关键字：
用来判断前面的变量所引用的对象是否为后面类型的实例。注意：instanceof前面操作数的类型要么与后面的类相同，要么与后面的类有父子关系，否则会引起编译时错误。

例如：
	
	System.out.println(f1 instanceof Fruit);//true
	System.out.println(f1 instanceof Apple);//true

<完>.