建立一个JDBC连接的编程主要有四个步骤：

导入JDBC包： 添加import语句到Java程序导入所需的类在Java代码中。

注册JDBC驱动程序：这一步会导致JVM加载所需的驱动程序实现到内存中，因此它可以实现JDBC请求。

数据库URL制定：这是创建格式正确的地址指向到要连接的数据库。

创建连接对象：最后，代码调用DriverManager对象的getConnection()方法来建立实际的数据库连接。

###导入JDBC包：
import 语句告诉Java编译器在哪里可以找到在代码中引用，并放置在您的源代码最开始的类。

使用标准的JDBC包，它允许选择，插入，更新和SQL表中删除数据，添加以下进口到您的源代码：

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

###注册JDBC驱动程序：
使用它之前，必须注册你的驱动程序在程序。注册驱动程序是由Oracle驱动程序的类文件被加载到内存中以便它可以被用作JDBC接口的实现过程。

需要做这个注册只能在你的程序一次。可以通过以下两种方式之一注册一个驱动程序。

###方法（I）- Class.forName()：
注册一个驱动程序中最常用的方法是使用Java的Class.forName()方法来动态加载驱动程序的类文件到内存中，它会自动将其注册。这种方法是可取的，因为它允许使驱动注册配置，便于携带。

下面的示例使用Class.forName()来注册Oracle驱动程序：

try {
   Class.forName("oracle.jdbc.driver.OracleDriver");
}
catch(ClassNotFoundException ex) {
   System.out.println("Error: unable to load driver class!");
   System.exit(1);
}
可以使用getInstance()方法来解决不兼容的JVM，但要编写了两个额外的例外情况如下：

try {
   Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
}
catch(ClassNotFoundException ex) {
   System.out.println("Error: unable to load driver class!");
   System.exit(1);
catch(IllegalAccessException ex) {
   System.out.println("Error: access problem while loading!");
   System.exit(2);
catch(InstantiationException ex) {
   System.out.println("Error: unable to instantiate driver!");
   System.exit(3);
}

###方法（二） - DriverManager.registerDriver()：
可以用它来注册一个驱动程序的第二种方法是使用staticDriverManager.registerDriver()方法。

应该，如果使用的是不兼容的JDK JVM，比如微软提供一个使用registerDriver()方法。

下面的示例使用registerDriver()来注册Oracle驱动程序：

jdbc:oracle:driver:username/password@database
所以上面的连接可以创建如下：

String URL = "jdbc:oracle:thin:username/password@amrood:1521:EMP";
Connection conn = DriverManager.getConnection(URL);

###使用数据库的URL和一个Properties对象：
第三种形式DriverManager.getConnection()方法需要一个数据库URL和一个Properties对象：

conn.close();
显式地关闭连接DBMS节约资源。