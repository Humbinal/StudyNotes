## JDBC是什么?

JDBC API是一个Java API，可以访问任何类型表列数据，特别是存储在关系数据库中的数据。JDBC代表Java数据库连接。

JDBC库中所包含的API任务通常与数据库使用：

- 连接到数据库

- 创建SQL或MySQL语句

- 在数据库中执行SQL或MySQL查询

- 查看和修改记录

## 创建JDBC应用程序:

参与建立一个JDBC应用程序，主要有以下六个步骤：

###1. 导入包:
这需要你有软件包包含了数据库编程所需的JDBC类。大多数情况下，使用import java.sql.* 就足够了，如下所示：

	//STEP 1. Import required packages
	import java.sql.*;
	
###2. 注册JDBC驱动程序：
这需要初始化驱动程序，这样就可以打开与数据库的通信信道。以下是代码片段实现这一目标：

	//STEP 2: Register JDBC driver
	Class.forName("com.mysql.jdbc.Driver");
	
###3. 打开一个连接：
这需要使用DriverManager.getConnection()方法来创建一个Connection对象，它代表一个物理连接的数据库，如下所示：

	//STEP 3: Open a connection
	//  Database credentials
	static final String USER = "username";
	static final String PASS = "password";
	System.out.println("Connecting to database...");
	conn = DriverManager.getConnection(DB_URL,USER,PASS);

###4. 执行一个查询：
这需要使用一个对象类型Statement或PreparedStatement构建，并提交一个SQL语句到数据库。如下：

	//STEP 4: Execute a query
	System.out.println("Creating statement...");
	stmt = conn.createStatement();
	String sql;
	sql = "SELECT id, first, last, age FROM Employees";
	ResultSet rs = stmt.executeQuery(sql);

如果有一个SQL UPDATE，INSERT或DELETE语句，那么需要下面的代码片段：

	//STEP 4: Execute a query
	System.out.println("Creating statement...");
	stmt = conn.createStatement();
	String sql;
	sql = "DELETE FROM Employees";
	ResultSet rs = stmt.executeUpdate(sql);

###5. 从结果集中提取数据：
这一步是必需的情况下，从数据库中获取数据。可以使用适当的ResultSet.getXXX()方法来检索的数据结果如下：

	//STEP 5: Extract data from result set
	while(rs.next()){
		//Retrieve by column name
		int id  = rs.getInt("id");
		int age = rs.getInt("age");
		String first = rs.getString("first");
		String last = rs.getString("last");

		//Display values
		System.out.print("ID: " + id);
		System.out.print(", Age: " + age);
		System.out.print(", First: " + first);
		System.out.println(", Last: " + last);
	}

清理环境：
应该明确地关闭所有的数据库资源，对依赖于JVM的垃圾收集如下：

//STEP 6: Clean-up environment
rs.close();
stmt.close();
conn.close();