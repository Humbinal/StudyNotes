>两只猫： 
>猫1： 花色，1岁，叫小花 
>猫2：白色，2岁，叫小白 
>问：当输入猫的名字时，输出猫的颜色和岁数

当用过程式语言时候，会发现，变量很分散。   
此时引入类：   
可以提取一些共同的东西，比如提取猫类。  

做一个类

	class Cat{  //类名首字母大写
		int age；
		String name;
		String color;
	}
	
创建一个对象

	Cat cat1 = new Cat();
	cat1.age = 1;
	cat1.name = "小花"；
	cat1.color = "花色";
	
- 类是抽象的、概念的、代表一类事物，比如人类、猫类…… 
- 对象是具体的，实际的，代表一个具体事物 
- 类是对象的模板，对象是类的一个个体，实例

对象赋值

	Person a = new Person();
	a.age = 10;
	a.name = "Jone";
	Person b;
	b = a;
	System.out.println(b.age);
	
会输出：10  
为什么么？？！！   
Person a = new Person();  执行之后，内存就会分配一片空间给a；a指向这个空间。   
a.age = 10;  
a.name = “Jone”； 这两句就会在指定的地方赋值。  
Person b; 这句仅仅是声明了b这个变量，但是没有new 空间 。  
b = a; b也指向a所指向的空间。  

	b.age = 9;
	
很自然地，这个也导致了a的age也是9；   
因为他们指向的是同一个空间。（类似于指针）

>对象总是在内存中存在的 
程序员使用类模板创建对象。（new 对象，然后存在内存中） 
内存中能存多少对象，取决于内存的大小。 
但是大小是有限的，所以要进行垃圾回收。java虚拟机自动回收。但是有时候也会发生内存泄露。

刚才只是定义了一些变量，也就是成员变量，接下来介绍成员方法。

###成员方法

	class Person{
		int age;
		String name;

		public void speak(){
			System.out.println("I am a good man!");
		}
	}
	
>java 命名规范 
方法名首字母小写，类名首字母大写。 
myCry 或者 my_cry

	访问修饰符 返回类型 方法名 （参数列表）{
		函数体；
	}
	
###构造方法（构造函数）

在创建对象的时候，可以初始化一些属性。

构造方法特点

1. 构造方法名和类名相同
2. 没有返回值
3. 在创建一个新的对象的时候，系统会自动的调用该类的构造方法完成对新对象的初始化。
4. 一个类可以有多个构造方法
5. 每个类都有一个默认的构造方法（在你不写构造方法的时候）

        class Person{
    		int age;
    		String name;
    		
    		public Person(int age, String name){
    			age = age;
    			name = name;
    		}
	    }

此时，创建的时候就需要指定值

	Person p1 = new Person(1,"Jack");
	
其实，之前就有一个默认的构造方法。不含参数。系统自动生成的。你不写的时候就会调用这个，当你写一个的时候，就覆盖了这个默认构造方法。

	class Person{
		int age;
		String name;
		//默认构造方法
		public Person(){
		}   
		public Person(int age, String name){
			age = age;
			name = name;
		}
	}
	
一个类可以有多个不同的构造方法(重载)。比如：

	class Person{
		int age;
		String name;
		//默认构造方法
		public Person(){
		}   
		public Person(int age, String name){
			age = age;
			name = name;
		}
		public Person(String name){
			name = name;
		}
	}

调用哪个构造方法取决于所给的参数匹配哪个。	