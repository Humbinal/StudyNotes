抽象类、接口常常与设计模式紧密相连。

###抽象类
先看一个关键字“abstract”，我们知道它是抽象的意思。所谓抽象，说的直白一点就是同一件事情，有不同的实现。
比如呼吸这个动作，人需要肺呼吸，而鱼需要鳃呼吸。代码实现如下：

	abstract class CommonMode{
		abstract void breathe(String name);
	}
	class Fish extends CommonMode{
		@Override
		void breathe(String name) {
			System.out.println("鱼呼吸用"+name);
		}
	}
	class Person extends CommonMode{
			@Override
			void breathe(String name) {
			System.out.println("人呼吸用"+name);
		}
	}
	public class Test {
		public static void main(String[] args) {
			CommonMode fish = new Fish();
			CommonMode person = new Person();
			fish.breathe("鳃");
			person.breathe("肺");
		}
	}

这样我们就不难理解所谓”抽象化”，就是指从具体问题中，提取出具有共性的模式，再使用通用的解决方法加以处理。需要注意的是abstract修饰符只能修饰类或方法。

修饰类时该类就成了抽象类，**抽象类**用四个字就可以概括：有得有失。所谓“得”就是增加了一个可以包含抽象方法(由子类实现)的功能，所谓“失”就是不能创建实例，而其它的功能普通类有的抽象类都有(如：定义普通方法、初始化快、内部类等)，需要补充的是抽象类的构造器主要是提供其子类的构造器调用。

**抽象方法**：使用abstract修饰，它没有方法体，而且它必须由子类重写，由于子类必须重写抽象父类的方法，所以abstract不能与final(有final修饰的方法，意味着不能被重写)同时出现，而且子类继承了(抽象)父类，意味着abstract不能与final同时出现；除此之外，abstract还不能与static、private(private意味着不能被子类访问)同时存在。注意子类要实现抽象类的所有抽象方法，否则子类也是抽象类。抽象类的作用主要是与“模板模式”联系在一起，以后会在设计模式中讲到，这里就不细说了。

为加深理解，再举一例：

	abstract class ParentClass {
		//定义抽象方法
		abstract void learn(String name);
		//抽象父类的构造器
		ParentClass(){
			System.out.println("父类无参构造器被调用！");
		}
		{
			System.out.println("实例初始化块被调用！");
		}
		static{
			System.out.println("类初始化块被调用！");
		}
		//定义内部类(下面会讲到)
		class A{
		}
		//定义普通方法
		public void info(){
			System.out.println("抽象父类中的info方法被调用！");
		}
	}
	class SubClass extends ParentClass{
		//当执行这个方法时，会调用抽象父类的构造器
		public SubClass(){}
		@Override
		void learn(String name) {
			System.out.println("我在学习"+name);
		}
		public void test(){
			System.out.println("子类test方法被调用！");
		}
	}
	public class AbstractClassTest {
		public static void main(String[] args) {
			//说明：new SubClass()会创建一个SubClass类的对象存放在堆内存中,
			//并让pc这个引用变量指向这个刚创建的对象,这个就是我们常听到的父类引用指向子类对象。
			//当我们只写new SubClass(); 来创建子类对象,我们从打印语句中我们可以看到：父类无参构造器被调用！
			//这说明父类构造器主要是提供其子类的构造器调用
			ParentClass pc=new SubClass();
			//因为子类的实例完全可以当成父类的对象使用,并且子类SubClass重写了抽象父类的learn方法,所以当然可以调用
			pc.learn("Java");
			pc.info();
			//当我们写pc.test();我们会看到编译器会报错，因为抽象父类ParentClass中根本没有test方法被定义
		}
	}

###接口(interface)

接口的作用非常丰富，接口往往是和设计模式结合在一起的。接口是从多个相似的类中提取出来的一种规范，接口可认为是一种彻底的抽象类。接口定义的语法格式如下：

	[修饰符] interface 接口名{
	//可以有属性定义、抽象方法(有方法的话只能是抽象的)、内部类/内部接口/内部枚举定义
	}

修饰符：public/省略；接口名：每个单词首字母大写，推荐接口用“形容词”。field默认有3个修饰符：public static final；抽象方法默认有2个修饰符：public abstract；内部类/内部接口/内部枚举定义默认也有2个修饰符：public static；注意属性声明时必须指定初始值，原因是final修饰的类变量只能在声明时、静态初始化块中指定初始值，但接口不包含初始化块，所以属性声明时必须指定初始值，并且推荐其属性变量所有字母全部大写。下面通过实例说明：

	interface Identifiable{
		void insert(String name);
	}
	interface A{
		//接口里的属性声明必须指定初始值
		int VALUE = 100;
		//接口里面可以有内部接口
		interface B{
			int INNERVALUE = 20;
		}
	}
	//一个接口可以有N个直接父接口
	interface IRechargeable extends Identifiable,A{
		void charge(String name);
	}
	class Usb implements Identifiable{
		@Override
		public void insert(String name) {
			System.out.println("插入"+name);
		}
	}
	//子类可以实现多个接口
	class DataLine implements Identifiable,IRechargeable{
		@Override
		public void insert(String name) {
			System.out.println("插入"+name);
		}
		@Override
		public void charge(String name) {
			System.out.println("数据线为"+name+"充电");
		}
	}
	public class InterfaceTest {
		public static void main(String[] args) {
			Identifiable usb = new Usb();
			IRechargeable dl = new DataLine();
			usb.insert("U盘");
			dl.insert("数据线");
			dl.charge("手机");
			System.out.println(IRechargeable.VALUE);
			System.out.println(A.B.INNERVALUE);
		}
	}

一般我们在想要实现的功能少时用抽象类；功能多时用接口。

###内部类

内部类(在界面编程点击处理事件中经常使用)也称寄生类，实质就是把一个类作为类的成员放在类里面定义。听起来有点绕，下面通过实例来说明：

	//在外部类里面访问非静态内部类
	public class Outer {
		int value = 1;
		class Inner{
		int value = 2;
		public void info(){
			int value = 3;
			//由于info方法里面的局部变量,所以此时输出的value为3
			System.out.println("value="+value);
			//由于this在方法中代表调用该方法的对象,所以value为该Inner类对象的属性值2
			System.out.println("value="+this.value);
			//由于该方法所在的类时没有static修饰的内部类，
			//所以该类必须寄生在“外部类”的实例里，这里的Outer.this就代表了外部类的实例
			//关于this，你只要记住一点就行：方法是谁的，就用谁来调用
			System.out.println("value="+Outer.this.value);
			//比如下面的调用：Outer.this 内部类寄生在外部类的实例里面，
			//既然Outer.this代表外部类(宿主)的实例，那么当然可以访问外部类的成员(包括field、方法)。
			Outer.this.test2();
			}
		}
		public void test(){
			new Inner().info();
		}
		public void test2(){
			System.out.println("外部类的成员方法test2被调用!");
		}
		public static void main(String[] args) {
			Outer outer = new Outer();
			outer.test();
		}
	}


	//在外部类里面访问静态内部类
	public class Outer {
		int value = 1;
		static class Inner{
			int value = 2;
			public void info(){
				int value = 3;
				System.out.println("value="+this.value);
			}
		}
		public static void main(String[] args) {
			new Inner().info();
		}
	}

	//在外部类外面访问非静态内部类
	class Out{
		int value = 1;
		class In{
			int value = 2;
			public void info(){
			int value = 3;
			System.out.println("value="+value);
			System.out.println("value="+this.value);
			System.out.println("value="+Out.this.value);
			}
		}
	}
	public class Test {
		public static void main(String[] args) {
		//注意：没有static修饰的内部类，必须寄生在“外部类”的实例里
		Out.In in = new Out().new In();
		in.info();
		}
	}

	//在外部类外面访问静态内部类
	class Out{
		int value = 1;
		static class In{
			int value = 2;
			public void info(){
				int value = 3;
				System.out.println("value="+value);
				System.out.println("value="+this.value);
				//静态内部类也属于静态成员，因此它不能访问外部类的非静态成员(包括属性、方法)。所以下面写法是错的
				//System.out.println("value="+Out.this.value);
			}
		}
	}
	public class Test {
		public static void main(String[] args) {
			//注意：有static修饰的内部类属于外部类本身，这是只需将外部类看成是内部类的包名即可
			Out.In in = new Out.In();
			in.info();
		}
	}

还有一种非静态内部类派生子类：由于子类的构造器必须调用父类构造器一次，因此必须在子类构造器中使用宿主对象来调用它的构造器。这个用的比较少，这里就不细谈了。
下面讲一下匿名内部类：当程序创建匿名内部类时，会立即创建匿名内部类(实现类)的实例。

定义语法格式如下：

	new 接口() | 父类构造器(参数){
	//类体部分。
	};

使用规则：
1.匿名内部类必须显式的继承一个父类，或实现一个接口。
2.匿名内部类必须实现接口或抽象类中所有的抽象方法。
3.匿名内部类不能有构造器而且程序以后无法再访问它，因为它没有类名。
实例说明如下：

	interface Identifiable {
		void insert(String name);
	}
	public class Test {
		public static void main(String[] args) {
			//此处相当于创建了Identifiable匿名的实现类,并创建了匿名内部类的实例
			//将实现类的实例赋值给接口变量，这是典型的“向上转型”
			Identifiable idf = new Identifiable(){
			@Override
			public void insert(String name) {
					System.out.println("插入"+name);
					}
				};
			idf.insert("U盘");
		}
	}

局部类：在当前方法里面定义，它只在该方法里有效，你可以把它当做一个局部变量，这个很少用，了解即可。

###枚举类：
枚举类是一种实例数固定的类，既然是实例固定，那当然不能创建实例。定义语法格式如下：

	修饰符 enum 枚举名{
	//立即在第一行列出该枚举的所有实例（但实际上是创建枚举实例,会默认调用无参构造器）。
	}

--修饰符 public|省略| abstract|final（这两个必须出现一个），默认final；
构造器无论是否使用private修饰，默认总是private。下面举例说明：

	public enum Test {
		// 列出所有枚举值,也就是该枚举类的所有可能的实例;
		// 下面相当于:Test MALE = new Test("男");
		MALE("男"), FEMALE("女");
		private String name;
		Test(String name) {
			this.name = name;
		}
		{
			System.out.println("实例初始化快被调用~~");
		}
		class A {}
		public void info() {
			System.out.println("普通的info方法~" + this.name);
		}
	public static void main(String[] args) {
			//Test.FEMAIL会调用构造方法,枚举类中有几个实例就调用几次构造方法
			//通过上一章，我们知道初始化快会在构造方法被调用之前调用(其实就是把初始化快放在了构造方法的第一行)
			//运行一下，我们会看到“实例初始化快被调用”被执行了两次
			Test.MALE.info();
		}
	}

下面再看一个实现接口的枚举：

	interface Directionable {
			void pointDirection();
		}
		//实现接口的枚举，如果直接实现所有的抽象方法，则此时枚举类就不再是抽象枚举。
	public enum Test implements Directionable {
		//下面这4个实例,相当于是public static final修饰的
		EAST, WEST, SOUTH, NORTH;
		@Override
		public void pointDirection() {
			System.out.println("指向" + this);
		}
		public static void main(String[] args) {
			Test.SOUTH.pointDirection();
		}
	}

看了上了两个实例，枚举类是不是很简单呢，呵呵。下面再看一个难一点的。

	//抽象枚举需要创建匿名内部类
	public enum Test {
		// 当枚举类是抽象类时,还需要立即创建匿名内部类的实例
		//这里的ADD和ADD()其实是一样的,都是创建实例并实现抽象方法。
		ADD {
				@Override
				public double eval(double m, double n) {
					return m + n;
				}
			}, SUB {
				@Override
				public double eval(double m, double n) {
					return m - n;
				}
			}, MULTI {
				@Override
				public double eval(double m, double n) {
					return m * n ;
				}
			}, DIV {
				@Override
				public double eval(double m, double n) {
					return m / n;
				}
		};
		// 如果枚举里面已经有了抽象方法,该枚举类默认就有了abstract修饰,此时该枚举类就没有了final修饰
		public abstract double eval(double m,double n);
		public static void main(String[] args) {
			System.out.println(Test.MULTI.eval(9, 9));
		}
	}
