一旦获得一个连接，我们可以与数据库进行交互。在JDBC Statement, CallableStatement 和 PreparedStatement 接口定义的方法和属性，使可以发送SQL或PL/SQL命令和从数据库接收数据。

它们还定义方法，帮助Java和数据库使用SQL数据类型之间转换数据的差异。

下表提供了每个接口的用途概要，了解决定使用哪个接口？


Statement：使用通用访问数据库。当在运行时使用静态SQL语句。Statement接口不能接受的参数。

PreparedStatement：当计划多次使用SQL语句。那么可以PreparedStatement接口接收在运行时输入参数。

CallableStatement：当要访问数据库中的存储过程中使用。CallableStatement对象的接口还可以接受运行时输入参数。

Statement 对象: 创建Statement对象:
在可以使用Statement对象执行SQL语句，需要使用Connection对象的createStatement( )方法创建一个，如下面的示例所示： 

Statement stmt = null;
try {
   stmt = conn.createStatement( );
   . . .
}
catch (SQLException e) {
   . . .
}
finally {
   . . .
}
一旦创建了一个Statement对象，然后可以用它来与它的三个执行方法之一执行SQL语句。

boolean execute(String SQL) : 如果ResultSet对象可以被检索返回布尔值true，否则返回false。使用这个方法来执行SQL DDL语句，或当需要使用真正的动态SQL。

int executeUpdate(String SQL) : 返回受影响的SQL语句执行的行的数目。使用此方法来执行，而希望得到一些受影响的行的SQL语句 - 例如，INSERT，UPDATE或DELETE语句。

ResultSet executeQuery(String SQL) : 返回ResultSet对象。当希望得到一个结果集使用此方法，就像使用一个SELECT语句。

关闭 Statement 对象:
正如关闭一个Connection对象来保存数据库资源，出于同样的原因，也应该关闭Statement对象。

close()方法简单的调用将完成这项工作。如果关闭了Connection对象首先它会关闭Statement对象也是如此。然而，应该始终明确关闭Statement对象，以确保正确的清除。

CallableStatement cstmt = null;
try {
   String SQL = "{call getEmpName (?, ?)}";
   cstmt = conn.prepareCall (SQL);
   . . .
}
catch (SQLException e) {
   . . .
}
finally {
   cstmt.close();
}