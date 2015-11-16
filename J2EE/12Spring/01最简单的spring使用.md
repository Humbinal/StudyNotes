1.引入jar包

需要使用spring的jar包和commons-logging的jar包

2.创建UserService类

	package com.humbinal.service;
	public class UserService {
		private String username;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void say() {
			System.out.println("Hello"+this.username);
		}
	}

3.编写applicationContext.xml存放在src目录下

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans" 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:aop="http://www.springframework.org/schema/aop" 
		xmlns:tx="http://www.springframework.org/schema/tx"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
	 http://www.springframework.org/schema/beans/spring-beans-2.0.xsd 
	 http://www.springframework.org/schema/tx 
	 http://www.springframework.org/schema/tx/spring-tx-2.0.xsd 
	 http://www.springframework.org/schema/aop 
	 http://www.springframework.org/schema/aop/spring-aop-2.0.xsd ">

	<bean id="userService" class="com.humbinal.service.UserService">
		<property name="username">
			<value>张三</value>
		</property>
		
	</bean>
		
	</beans>

4.编写测试类测试

	package com.humbinal.spring;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
	import com.humbinal.service.UserService;
	public class Test {
		public static void main(String[] args) {
			//使用spring，得到applicationCotext
			ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
			UserService userService= (UserService) applicationContext.getBean("userService");
			userService.say();
		}

	}
