结构化查询语言（SQL）是一种标准化的语言，它允许你在数据库上执行操作，如创建项目，读取内容，内容更新和删除条目。

SQL是所有可能会使用几乎任何数据库支持，它允许独立于底层数据库的写入数据库的代码。

一些基本的SQL语法：

###创建数据库：
CREATE DATABASE语句用于创建一个新的数据库。语法是：

    SQL> CREATE DATABASE DATABASE_NAME;
例子：
下面的SQL语句创建一个名为EMP数据库：

    SQL> CREATE DATABASE EMP;
###删除数据库：
使用DROP DATABASE语句用于删除现有的数据库。语法是：

    SQL> DROP DATABASE DATABASE_NAME;
注意：要创建或删除，应该有数据库服务器上管理员权限的数据库。请注意，删除数据库将所有损失存储在数据库中的数据。

###创建表：
CREATE TABLE语句用于创建一个新表。语法是：

    SQL> CREATE TABLE table_name
    (
       column_name column_data_type,
       column_name column_data_type,
       column_name column_data_type
       ...
    );
例子：
下面的SQL语句创建一个有四个栏位名为Employees表：

    SQL> CREATE TABLE Employees
    (
       id INT NOT NULL,
       age INT NOT NULL,
       first VARCHAR(255),
       last VARCHAR(255),
       PRIMARY KEY ( id )
    );
###删除表：
DROP TABLE语句用于删除现有的表。语法是：

    SQL> SELECT column_name, column_name, ...
         FROM table_name
         WHERE conditions;
WHERE子句可以使用比较操作符例如=, !=, <, >, <=, >=,以及BETWEEN和LIKE操作符。

例子：
下面的SQL语句从Employees表选择age第一个和最后一列名where id =100：

    SQL> SELECT first, last, age 
         FROM Employees 
         WHERE id = 100;
下面的SQL语句从Employees表，其中第一列选择age，第一列包含 Zara:

    SQL> SELECT first, last, age 
         FROM Employees 
         WHERE first LIKE '%Zara%';
###UPDATE 数据:
UPDATE语句用于更新数据。UPDATE语法为：

    SQL> UPDATE table_name
         SET column_name = value, column_name = value, ...
         WHERE conditions;
WHERE子句可以使用比较操作符例如=，！=，<，>，<=，和>=，以及BETWEEN和LIKE操作符。

例子：
下面的SQL的UPDATE语句更改其ID为100的员工的age列：

    SQL> DELETE FROM Employees WHERE id=100;