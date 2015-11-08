=========================================

JavaBean

javaBean的规范：
  1. 必须要有一个默认构造器
  2. 提供get/set方法，如果只有get方法，那么这个属性是只读属性！
  3. 属性：有get/set方法的成员，还可以没有成员，只有get/set方法。属性名称由get/set方法来决定！而不是成员名称！
  4. 方法名称满足一定的规范，那么它就是属性！boolean类型的属性，它的读方法可以是is开头，也可以是get开头！


内省：
  内省类 --> Bean信息 --> 属性描述符 --> 属性的get/set对应的Method！ --- > 可以反射了！

-----------------------

commons-beanutils，它是依赖内省完成！
  * 导包：
    > commons-beanutils.jar
    > commons-logging.jar

BeanUtils.getProperty(Object bean, String propertyName)
BeanUtils.setProperty(Object bean, String propertyName, String propertyValue)
BeanUtils.populate(Map map, Object bean)

CommontUtils.toBean(Map map, Class class)

-----------------------

jsp中与javaBean相关的标签！

* <jsp:useBean> --> 创建或查询bean
  * <jsp:useBean id="user1" class="cn.itcast.domain.User" scope="session"/> 在session域中查找名为user1的bean，如果不存在，创建之
  * <jsp:useBean id="user1" class="cn.itcast.domain.User" scope="session"/>
* <jsp:setProperty>
  * <jsp:setProperty property="username" name="user1" value="admin"/> 设置名为user1的这个javabean的username属性值为admin
* <jsp:getProperty>
  * <jsp:getProperty property="username" name="user1"/> 获取名为user1的javabean的名为username属性值

=========================================



	package com.humbinal.domain;

	/*
	 * 必须要为成员提供get/set方法（两者只提供一个也是可以的！）
	 * 必须要有默认构造器！（没参的！）
	 * 一般对于具有get/set方法的成员变量称之为属性
	 * 
	 * 其实就算一个属性没有对应的成员变量，只有get/set方法也是可以的！
	 *   属性的名称就是get/set方法去除get/set后，再把首字母小写了！
	 */
	public class Person {
		private String name;
		private int age;
		private String gender;
		private boolean bool;
		
		public boolean isBool() {
			return bool;
		}

		public void setBool(boolean bool) {
			this.bool = bool;
		}

		public String getId() {
			return "fdsafdafdas";
		}

		public String getName() {
			return name;
		}

		public void setName(String username) {
			this.name = username;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + ", gender=" + gender
					+ "]";
		}

		public Person() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Person(String name, int age, String gender) {
			super();
			this.name = name;
			this.age = age;
			this.gender = gender;
		}

	}
	
	package com.humbinal.domain;
	import java.lang.reflect.InvocationTargetException;
	import java.util.HashMap;
	import java.util.Map;

	import org.apache.commons.beanutils.BeanUtils;
	import org.junit.Test;

	import com.humbinal.utils.CommonUtils;

	public class Demo1 {
		@Test
		public void fun1() throws Exception {
			String className = "cn.itcast.domain.Person";
			Class clazz = Class.forName(className);
			Object bean = clazz.newInstance();
			
			BeanUtils.setProperty(bean, "name", "张三");
			BeanUtils.setProperty(bean, "age", "23");
			BeanUtils.setProperty(bean, "gender", "男");
			BeanUtils.setProperty(bean, "xxx", "XXX");
			
			String age = BeanUtils.getProperty(bean, "age");
			System.out.println(age);
			System.out.println(bean);
		}
		
		/*
		 * 把map中的属性直接封装到一个bean中　
		 * 
		 * Map: {"username":"zhangSan", "password","123"}
		 * 我们要把map的数据封装到一个javabean中！要求map的key与bean的属性名相同！
		 */
		@Test
		public void fun2() throws Exception {
			Map<String,String> map = new HashMap<String,String>();
			map.put("username", "zhangSan");
			map.put("password", "123");
			
			User user = new User();
			BeanUtils.populate(user, map);
			
			System.out.println(user);
		}
		
		@Test
		public void fun3() {
			Map<String,String> map = new HashMap<String,String>();
			map.put("username", "zhangSan");
			map.put("password", "123");
			
			/*
			 * request.getParameterMap();
			 */
			
			User user = CommonUtils.toBean(map, User.class);
			System.out.println(user);
		}
	}
	
	
	package com.humbinal.utils;

	import java.util.Map;
	import java.util.UUID;

	import org.apache.commons.beanutils.BeanUtils;

	public class CommonUtils {
		/**
		 * 生成不重复的32位长的大写字符串
		 * @return
		 */
		public static String uuid() {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}
		
		/**
		 * 把map转换成指定类型的javaBean对象
		 * @param map
		 * @param clazz
		 * @return
		 */
		public static <T> T toBean(Map map, Class<T> clazz) {
			try {
				/*
				 * 1. 创建指定类型的javabean对象
				 */
				T bean = clazz.newInstance();
				/*
				 * 2. 把数据封装到javabean中
				 */
				BeanUtils.populate(bean, map);
				/*
				 * 返回javabean对象
				 */
				return bean;
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	