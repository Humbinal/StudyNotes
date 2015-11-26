假设我们正在开发一个支持多国语言的Web应用程序，要求系统能够根据客户端的系统的语言类型返回对应的界面：英文的操作系统返回英文界面，而中文的操作系统则返回中文界面——这便是典型的i18n国际化问题。
国际化(internationalization)又称为 i18n(读法为i 18 n，据说是因为internationalization(国际化)这个单词从i到n之间有18个英文字母，i18n的名字由此而来)。
对于有国际化要求的应用系统，我们不能简单地采用硬编码的方式编写用户界面信息、报错信息等内容，而必须为这些需要国际化的信息进行特殊处理。简单来说，就是为每种语言提供一套相应的资源文件，并以规范化命名的方式保存在特定的目录中，由系统自动根据客户端语言选择适合的资源文件。

###基础知识

“国际化信息”也称为“本地化信息”，一般需要两个条件才可以确定一个特定类型的本地化信息，它们分别是“语言类型”和“国家/地区的类型”。如中文本地化信息既有中国大陆地区的中文，又有中国台湾、中国香港地区的中文，还有新加坡地区的中文。Java通过java.util.Locale类表示一个本地化对象，它允许通过语言参数和国家/地区参数创建一个确定的本地化对象。
语言参数使用ISO标准语言代码表示，这些代码是由ISO-639标准定义的，每一种语言由两个小写字母表示。在许多网站上都可以找到这些代码的完整列表，下面的网址是提供了标准语言代码的信息：[http://www.loc.gov/standards/iso639-2/php/English_list.php](http://www.loc.gov/standards/iso639-2/php/English_list.php)。
国家/地区参数也由标准的ISO国家/地区代码表示，这些代码是由ISO-3166标准定义的，每个国家/地区由两个大写字母表示。用户可以从以下网址查看ISO-3166的标准代码：[http://www.iso.ch/iso/en/prods-services/iso3166ma/02iso-3166-code-lists/list-en1.html](http://www.iso.ch/iso/en/prods-services/iso3166ma/02iso-3166-code-lists/list-en1.html)。
　　表5-2给出了一些语言和国家/地区的标准代码：
![这里写图片描述](http://img.blog.csdn.net/20140707220606359)

###Locale

java.util.Locale是表示语言和国家/地区信息的本地化类，它是创建国际化应用的基础。下面给出几个创建本地化对象的示例：

	//①带有语言和国家/地区信息的本地化对象    
	Locale locale1 = new Locale("zh","CN");     
	    
	//②只有语言信息的本地化对象    
	Locale locale2 = new Locale("zh");     
	    
	//③等同于Locale("zh","CN")    
	Locale locale3 = Locale.CHINA;     
	    
	//④等同于Locale("zh")    
	Locale locale4 = Locale.CHINESE;     
	    
	//⑤获取本地系统默认的本地化对象    
	Locale locale5= Locale.getDefault(); 
   
用户既可以同时指定语言和国家/地区参数定义一个本地化对象①，也可以仅通过语言参数定义一个泛本地化对象②。Locale类中通过静态常量定义了一些常用的本地化对象，③和④处就直接通过引用常量返回本地化对象。此外，用户还可以获取系统默认的本地化对象，如⑤所示。
　　在测试时，如果希望改变系统默认的本地化设置，可以在启动JVM时通过命令参数指定：java -Duser.language=en -Duser.region=US MyTest。

###本地化工具类

JDK的java.util包中提供了几个支持本地化的格式化操作工具类：NumberFormat、DateFormat、MessageFormat。下面，我们分别通过实例了解它们的用法：
　　
NumberFormat：

	Locale locale = new Locale("zh", "CN");    
	NumberFormat currFmt = NumberFormat.getCurrencyInstance(locale);    
	double amt = 123456.78;    
	System.out.println(currFmt.format(amt));  
　　
上面的实例通过NumberFormat按本地化的方式对货币金额进行格式化操作，运行实例，输出以下信息：

	￥123,456.78 
	 
DateFormat：

	Locale locale = new Locale("en", "US");    
	Date date = new Date();    
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);    
	System.out.println(df.format(date)); 
	 
通过DateFormat#getDateInstance(int style,Locale locale)方法按本地化的方式对日期进行格式化操作。该方法第一个入参为时间样式，第二个入参为本地化对象。运行以上代码，输出以下信息：

	Jan 8, 2007 
	 
MessageFormat在NumberFormat和DateFormat的基础上提供了强大的占位符字符串的格式化功能，它支持时间、货币、数字以及对象属性的格式化操作。下面的实例演示了一些常见的格式化功能：
　　
MessageFormat：

	//①信息格式化串    
	String pattern1 = "{0}，你好！你于 {1} 在工商银行存入 {2} 元。";  
	String pattern2 = "At {1,time,short} On {1,date,long}，{0} paid {2,number, currency}.";  
	  
	//②用于动态替换占位符的参数    
	Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};  
	  
	//③使用默认本地化对象格式化信息    
	String msg1 = MessageFormat.format(pattern1, params);  
	  
	//④使用指定的本地化对象格式化信息    
	MessageFormat mf = new MessageFormat(pattern2, Locale.US);  
	String msg2 = mf.format(params);  
	System.out.println(msg1);  
	System.out.println(msg2);  

pattern1是简单形式的格式化信息串，通过{n}占位符指定动态参数的替换位置索引，{0}表示第一个参数，{1}表示第二个参数，以此类推。
pattern2格式化信息串比较复杂一些，除参数位置索引外，还指定了参数的类型和样式。从pattern2中可以看出格式化信息串的语法是很灵活的，一个参数甚至可以出现在两个地方：如 {1,time,short}表示从第二个入参中获取时间部分的值，显示为短样式时间；而{1,date,long}表示从第二个入参中获取日期部分的值，显示为长样式时间。关于MessageFormat更详细的使用方法，请参见JDK的Javadoc。
在②处，定义了用于替换格式化占位符的动态参数，这里，我们使用到了JDK5.0自动装包的语法，否则必须采用封装类表示基本类型的参数值。
在③处，通过MessageFormat的format()方法格式化信息串。它使用了系统默认的本地化对象，由于我们是中文平台，因此默认为Locale.CHINA。而在④处，我们显式指定MessageFormat的本地化对象。
运行上面的代码，输出以下信息：

	John，你好！你于 14-7-7 下午11:29 在工商银行存入 1,000 元。  
	At 11:29 PM On July 7, 2014，John paid $1,000.00.  
	
如果应用系统中某些信息需要支持国际化功能，则必须为希望支持的不同本地化类型分别提供对应的资源文件，并以规范的方式进行命名。国际化资源文件的命名规范规定资源名称采用以下的方式进行命名：

　　<资源名>_<语言代码>_<国家/地区代码>.properties
　　
其中，语言代码和国家/地区代码都是可选的。<资源名>.properties命名的国际化资源文件是默认的资源文件，即某个本地化类型在系统中找不到对应的资源文件，就采用这个默认的资源文件。
<资源名>_<语言代码>.properties命名的国际化资源文件是某一语言默认的资源文件，即某个本地化类型在系统中找不到精确匹配的资源文件，将采用相应语言默认的资源文件。
举一个例子：假设资源名为resource，则语言为英文，国家为美国，则与其对应的本地化资源文件命名为resource_en_US.properties。信息在资源文件以属性名/值的方式表示：

	greeting.common=How are you!  
	greeting.morning = Good morning!  
	greeting.afternoon = Good Afternoon! 
 
对应语言为中文，国家/地区为中国大陆的本地化资源文件则命名为resource_zh_ CN.properties，资源文件内容如下：

	greeting.common=\u60a8\u597d\uff01  
	greeting.morning=\u65e9\u4e0a\u597d\uff01  
	greeting.afternoon=\u4e0b\u5348\u597d\uff01  
	
本地化不同的同一资源文件，虽然属性值各不相同，但属性名却是相同的，这样应用程序就可以通过Locale对象和属性名精确调用到某个具体的属性值了。

读者可能已经注意到，上面中文的本地化资源文件内容采用了特殊的编码表示中文字符，这是因为资源文件对文件内容有严格的要求：只能包含ASCII字符。所以必须将非ASCII字符的内容转换为Unicode代码的表示方式。如上面中文的resource_zh_CN.properties资源文件的三个属性值分别是“您好！”、“早上好！”和“下午好！”三个中文字符串对应的Unicode代码串。

如果在应用开发时，直接采用Unicode代码编辑资源文件是很不方便的，所以，通常我们直接使用正常的方式编写资源文件，在测试或部署时再采用工具进行转换。JDK在bin目录下为我们提供了一个完成此项功能的native2ascii工具，它可以将中文字符的资源文件转换为Unicode代码格式的文件，命令格式如下：

　　native2ascii [-reverse] [-encoding 编码] [输入文件 [输出文件]]
　　
resource_zh_CN.properties包含中文字符并且以UTF-8进行编码，假设将该资源文件放到d:\目录下，通过下面的命令就可以将其转换为Unicode代码的形式：

	D:\>native2ascii -encoding utf-8 d:\resource_zh_CN.properties  
	d:\resource_zh_CN_1.properties
		  
由于原资源文件采用UTF-8编码，所以必须显式通过-encoding指定编码格式。
通过native2ascii命令手工转换资源文件，不但在操作上不方便，转换后资源文件中的属性内容由于采用了ASCII编码，阅读起来也不方便。很多IDE开发工具都有属性编辑器的插件，插件会自动将资源文件内容转换为ASCII形式的编码，同时以正常的方式阅读和编辑资源文件的内容，这给开发和维护带来了很大的便利。
对于MyEclipse来说，使用MyEclipse Properties Editor编辑资源属性文件；
对于Intellij IDEA来说，无须安装任何插件就自然支持资源属性文件的这种编辑方式了。
如果应用程序中拥有大量的本地化资源文件，直接通过传统的File操作资源文件显然太过笨拙。Java为我们提供了用于加载本地化资源文件的方便类java.util.ResourceBoundle。

ResourceBoundle为加载及访问资源文件提供便捷的操作，下面的语句从相对于类路径的目录中加载一个名为resource的本地化资源文件：

	ResourceBundle rb = ResourceBundle.getBundle("com/baobaotao/i18n/resource", locale)  

通过以下的代码即可访问资源文件的属性值：

	rb.getString("greeting.common")
	   
来看下面的实例：

	ResourceBundle rb1 = ResourceBundle.getBundle("com/baobaotao/i18n/resource", Locale.US);  
	ResourceBundle rb2 = ResourceBundle.getBundle("com/baobaotao/i18n/resource", Locale.CHINA);  
	System.out.println("us:"+rb1.getString("greeting.common"));  
	System.out.println("cn:"+rb2.getString("greeting.common"));
	  
rb1加载了对应美国英语本地化的resource_en_US.properties资源文件；而rb2加载了对应中国大陆中文的resource_zh_CN.properties资源文件。运行上面的代码，将输出以下信息：

	us:How are you!  
	cn:你好！
	  
加载资源文件时，如果不指定本地化对象，将使用本地系统默认的本地化对象。所以，在中文系统中，ResourceBundle.getBundle("com/baobaotao/i18n/resource")语句也将返回和代码清单5-14中rb2相同的本地化资源。

ResourceBundle在加载资源时，如果指定的本地化资源文件不存在，它按以下顺序尝试加载其他的资源：本地系统默认本地化对象对应的资源→默认的资源。
上面的例子中，假设我们使用ResourceBundle.getBundle("com/baobaotao/i18n/resource",Locale.CANADA)加载资源，由于不存在resource_en_CA.properties资源文件，它将尝试加载resource_zh_CN.properties的资源文件，假设resource_zh_CN.properties资源文件也不存在，它将继续尝试加载resource.properties的资源文件，如果这些资源都不存在，将抛出java.util.MissingResourceException异常。

###在资源文件中使用格式化串

在上面的资源文件中，属性值都是一般的字符串，它们不能结合运行时的动态参数构造出灵活的信息，而这种需求是很常见的。要解决这个问题很简单，只须使用带占位符的格式化串作为资源文件的属性值并结合使用MessageFormat就可以满足要求了。
上面的例子中，我们仅向用户提供一般性问候，下面我们对资源文件进行改造，通过格式化串让问候语更具个性化：

	greeting.common=How are you!{0},today is {1}  
	greeting.morning = Good morning!{0},now is {1 time short}  
	greeting.afternoon = Good Afternoon!{0} now is {1 date long}  

将该资源文件保存在fmt_resource_en_US.properties中，按照同样的方式编写对应的中文本地化资源文件fmt_resource_zh_CN.properties。
下面，我们联合使用ResourceBoundle和MessageFormat得到美国英文的本地化问候语：

	//①加载本地化资源    
	ResourceBundle rb1 =     
	             ResourceBundle.getBundle("com/baobaotao/i18n/fmt_ resource",Locale.US);     
	ResourceBundle rb2 =     
	              ResourceBundle.getBundle("com/baobaotao/i18n/fmt_ resource",Locale.CHINA);    
	Object[] params = {"John", new GregorianCalendar().getTime()};    
       
	String str1 = new MessageFormat(rb1.getString("greeting.common"),Locale.US).format(params);    
	String str2 =new MessageFormat(rb2.getString("greeting.morning"),Locale.CHINA).format(params);    
	String str3 =new MessageFormat(rb2.getString("greeting.afternoon"),Locale.CHINA).format(params);    
	System.out.println(str1);    
	System.out.println(str2);    
	System.out.println(str3);
  
运行以上的代码，将输出以下信息：

	How are you!John,today is 1/9/07 4:11 PM  
	早上好！John，现在是下午4:11  
	下午好！John，现在是2007年1月9日  

###MessageSource

Spring定义了访问国际化信息的MessageSource接口，并提供了几个易用的实现类。首先来了解一下该接口的几个重要方法：
String getMessage(String code, Object[] args, String defaultMessage, Locale locale) code表示国际化资源中的属性名；args用于传递格式化串占位符所用的运行期参数；当在资源找不到对应属性名时，返回defaultMessage参数所指定的默认信息；locale表示本地化对象；

	String getMessage(String code, Object[] args, Locale locale)  throws NoSuchMessageException

与上面的方法类似，只不过在找不到资源中对应的属性名时，直接抛出NoSuchMessageException异常；

	String getMessage(MessageSourceResolvable resolvable, Locale locale)  throws NoSuchMessageException

MessageSourceResolvable 将属性名、参数数组以及默认信息封装起来，它的功能和第一个接口方法相同。

####MessageSource的类结构

　　MessageSource分别被HierarchicalMessageSource和ApplicationContext接口扩展，这里我们主要看一下HierarchicalMessageSource接口的几个实现类，如图5-7所示：
![这里写图片描述](http://img.blog.csdn.net/20140708005830578)
HierarchicalMessageSource接口添加了两个方法，建立父子层级的MessageSource结构，类似于前面我们所介绍的HierarchicalBeanFactory。该接口的setParentMessageSource (MessageSource parent)方法用于设置父MessageSource，而getParentMessageSource()方法用于返回父MessageSource。

HierarchicalMessageSource接口最重要的两个实现类是ResourceBundleMessageSource和ReloadableResourceBundleMessageSource。它们基于Java的ResourceBundle基础类实现，允许仅通过资源名加载国际化资源。ReloadableResourceBundleMessageSource提供了定时刷新功能，允许在不重启系统的情况下，更新资源的信息。StaticMessageSource主要用于程序测试，它允许通过编程的方式提供国际化信息。而DelegatingMessageSource是为方便操作父MessageSource而提供的代理类。

####ResourceBundleMessageSource

该实现类允许用户通过beanName指定一个资源名（包括类路径的全限定资源名），或通过beanNames指定一组资源名。在前面的代码清单中，我们通过JDK的基础类完成了本地化的操作，下面我们使用ResourceBundleMessageSource来完成相同的任务。读者可以比较两者的使用差别，并体会Spring所提供的国际化处理功能所带给我们的好处：
通过ResourceBundleMessageSource配置资源

	<bean id="myResource"    
	class="org.springframework.context.support.ResourceBundleMessageSource">  
    <!--①通过基名指定资源，相对于类根路径-->  
    <property name="basenames">  
       <list>  
          <value>com/baobaotao/i18n/fmt_resource</value>  
       </list>  
    </property>  
  </bean>  
   
启动Spring容器，并通过MessageSource访问配置的国际化资源，如下代码清单所示：
访问国际化消息：ResourceBundleMessageSource：

	String[] configs = {"com/baobaotao/i18n/beans.xml"};    
	ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);    
	    
	//①获取MessageSource的Bean    
	MessageSource ms = (MessageSource)ctx.getBean("myResource");     
	Object[] params = {"John", new GregorianCalendar().getTime()};    
	    
	//②获取格式化的国际化信息    
	String str1 = ms.getMessage("greeting.common",params,Locale.US);    
	String str2 = ms.getMessage("greeting.morning",params,Locale.CHINA);    
	String str3 = ms.getMessage("greeting.afternoon",params,Locale.CHINA);    
	System.out.println(str1);    
	System.out.println(str2);    
	System.out.println(str3);
	  
比较代码清单中的代码，我们发现最主要的区别在于我们无须再分别加载不同语言、不同国家/地区的本地化资源文件，仅仅通过资源名就可以加载整套的国际化资源文件。此外，我们无须显式使用MessageFormat操作国际化信息，仅通过MessageSource# getMessage()方法就可以完成操作了。这段代码的运行结果与前面的代码的运行结果完全一样。

####ReloadableResourceBundleMessageSource

前面，我们提到该实现类比之于ResourceBundleMessageSource的唯一区别在于它可以定时刷新资源文件，以便在应用程序不重启的情况下感知资源文件的变化。很多生产系统都需要长时间持续运行，系统重启会给运行带来很大的负面影响。这时，通过该实现类就可以解决国际化信息更新的问题。请看下面的配置：
通过ReloadableResourceBundleMessageSource配置资源：

	<bean id="myResource"     
	lass="org.springframework.context.support.ReloadableResourceBundleMessageSource">    
	<property name="basenames">    
	      <list>    
	        <value>com/baobaotao/i18n/fmt_resource</value>    
	      </list>    
	   </property>    
	   <!--① 刷新资源文件的周期，以秒为单位-->    
	   <property name="cacheSeconds" value="5"/>     
	 </bean>
   
在上面的配置中，我们通过cacheSeconds属性让ReloadableResourceBundleMessageSource每5秒钟刷新一次资源文件（在真实的应用中，刷新周期不能太短，否则频繁的刷新将带来性能上的负面影响，一般不建议小于30分钟）。cacheSeconds默认值为-1表示永不刷新，此时，该实现类的功能就蜕化为ResourceBundleMessageSource的功能。

我们编写一个测试类对上面配置的ReloadableResourceBundleMessageSource进行测试：

	String[] configs = {"com/baobaotao/i18n/beans.xml"};    
	ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);    
	    
	MessageSource ms = (MessageSource)ctx.getBean("myResource");    
	Object[] params = {"John", new GregorianCalendar().getTime()};    
	    
	for (int i = 0; i < 2; i++) {    
	    String str1 = ms.getMessage("greeting.common",params,Locale.US);        
	    System.out.println(str1);    
	    Thread.currentThread().sleep(20000); //①模拟程序应用，在此期间，我们更改资源文件     
	}   
　　
在①处，我们让程序睡眠20秒钟，在这期间，我们将fmt_resource_zh_CN.properties资源文件的greeting.common键值调整为：

	---How are you!{0},today is {1}--- 
	 
我们将看到两次输出的格式化信息分别对应更改前后的内容，也即本地化资源文件的调整被自动生效了：

	How are you!John,today is 1/9/07 4:55 PM  
	---How are you!John,today is 1/9/07 4:55 PM---  

###容器级的国际化信息资源

在如图5-7所示的MessageSource类图结构中，我们发现ApplicationContext实现了MessageSource的接口。也就是说ApplicationContext的实现类本身也是一个MessageSource对象。
将ApplicationContext和MessageSource整合起来，乍一看挺让人费解的，Spring这样设计的意图究竟是什么呢？原来Spring认为：在一般情况下，国际化信息资源应该是容器级。我们一般不会将MessageSource作为一个Bean注入到其他的Bean中，相反MessageSource作为容器的基础设施向容器中所有的Bean开放。只要我们考察一下国际化信息的实际消费场所就更能理解Spring这一设计的用意了。国际化信息一般在系统输出信息时使用，如Spring MVC的页面标签，控制器Controller等，不同的模块都可能通过这些组件访问国际化信息，因此Spring就将国际化消息作为容器的公共基础设施对所有组件开放。
既然一般情况下我们不会直接通过引用MessageSource Bean使用国际信息，那如何声明容器级的国际化信息呢？我们其实在5.1.1节讲解Spring容器的内部工作机制时已经埋下了伏笔：在介绍容器启动过程时，我们通过代码清单5-1对Spring容器启动时的步骤进行剖析，④处的initMessageSource()方法所执行的工作就是初始化容器中的国际化信息资源：它根据反射机制从BeanDefinitionRegistry中找出名称为“messageSource”且类型为org.springframework.context.MessageSource的Bean，将这个Bean定义的信息资源加载为容器级的国际化信息资源。请看下面的配置：
容器级资源的配置：

	<!--①注册资源Bean,其Bean名称只能为messageSource -->    
	<bean id="messageSource"     
		      class="org.springframework.context.support.ResourceBundleMessageSource">    
	  <property name="basenames">    
	     <list>    
	       <value>com/baobaotao/i18n/fmt_resource</value>    
	     </list>    
	  </property>    
	</bean> 
	 
下面，我们通过ApplicationContext直接访问国际化信息，如下代码清单所示：

	String[] configs = {"com/baobaotao/i18n/beans.xml"};    
	ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);    
	//①直接通过容器访问国际化信息    
	Object[] params = {"John", new GregorianCalendar().getTime()};    
	     
	String str1 = ctx.getMessage("greeting.common",params,Locale.US);    
	String str2 = ctx.getMessage("greeting.morning",params,Locale.CHINA);       
	System.out.println(str1);    
	System.out.println(str2);
	  
运行以上代码，输出以下信息：

	How are you!John,today is 1/9/07 5:24 PM  
	早上好！John，现在是下午5:24
	  
假设MessageSource Bean名字没有命名为“messageSource”，以上代码将抛出NoSuchMessageException异常。