##1.输出hello，world

print()函数也可以接受多个字符串，用逗号“,”隔开，就可以连成一串输出：

    >>>print('The quick brown fox', 'jumps over', 'the lazy dog')
    The quick brown fox jumps over the lazy dog
print()会依次打印每个字符串，遇到逗号“,”会输出一个空格.

print()也可以打印整数，或者计算结果：

    >>>print(300)
    300
    >>>print(100 + 200)
    300
因此，我们可以把计算100 + 200的结果打印得更漂亮一点：

    >>>print('100 + 200 =', 100 + 200)
    100 + 200 = 300
注意，对于100 + 200，Python解释器自动计算出结果300，但是，'100 + 200 ='是字符串而非数学公式，Python把它视为字符串。

##2.输入

如果要让用户从电脑输入一些字符怎么办？Python提供了一个input()，可以让用户输入字符串，并存放到一个变量里。比如输入用户的名字：

    >>>name = input()
    Michael
当你输入name = input()并按下回车后，Python交互式命令行就在等待你的输入了。这时，你可以输入任意字符，然后按回车后完成输入。

输入完成后，不会有任何提示，Python交互式命令行又回到>>>状态了。那我们刚才输入的内容到哪去了？答案是存放到name变量里了。可以直接输入name查看变量内容：

    >>>name
    'Michael'

要打印出name变量的内容，除了直接写name然后按回车外，还可以用print()函数：

    >>>print(name)
    Michael
有了输入和输出，我们就可以把上次打印'hello, world'的程序改成有点意义的程序了：

    >>>name = input()
    print('hello,', name)
运行上面的程序，第一行代码会让用户输入任意字符作为自己的名字，然后存入name变量中；第二行代码会根据用户的名字向用户说hello，比如输入Michael：

    C:\Workspace> python hello.py
    Michael
    hello, Michael
但是程序运行的时候，没有任何提示信息告诉用户：“嘿，赶紧输入你的名字”，这样显得很不友好。幸好，input()可以让你显示一个字符串来提示用户，于是我们把代码改成：

    name = input('please enter your name: ')
    print('hello,', name)
再次运行这个程序，你会发现，程序一运行，会首先打印出please enter your name:，这样，用户就可以根据提示，输入名字后，得到hello, xxx的输出：

    C:\Workspace> python hello.py
    please enter your name: Michael
    hello, Michael
每次运行该程序，根据用户输入的不同，输出结果也会不同。

##3.Python的字符编码

在最新的Python 3版本中，字符串是以Unicode编码的，也就是说，Python的字符串支持多语言，例如：

	>>>print('包含中文的str')
	包含中文的str
对于单个字符的编码，Python提供了ord()函数获取字符的整数表示，chr()函数把编码转换为对应的字符：

	>>>ord('A')
	65
	>>>ord('中')
	20013
	>>>chr(66)
	'B'
	>>>chr(25991)
	'文'
如果知道字符的整数编码，还可以用十六进制这么写str：

	>>>'\u4e2d\u6587'
	'中文'
两种写法完全是等价的。

由于Python的字符串类型是str，在内存中以Unicode表示，一个字符对应若干个字节。如果要在网络上传输，或者保存到磁盘上，就需要把str变为以字节为单位的bytes。

Python对bytes类型的数据用带b前缀的单引号或双引号表示：

x = b'ABC'
要注意区分'ABC'和b'ABC'，前者是str，后者虽然内容显示得和前者一样，但bytes的每个字符都只占用一个字节。

以Unicode表示的str通过encode()方法可以编码为指定的bytes，例如：

	>>> 'ABC'.encode('ascii')
	b'ABC'
	>>> '中文'.encode('utf-8')
	b'\xe4\xb8\xad\xe6\x96\x87'
	>>> '中文'.encode('ascii')
	Traceback (most recent call last):
	File "<stdin>", line 1, in <module>
	UnicodeEncodeError: 'ascii' codec can't encode characters in position 0-1: ordinal not in range(128)
纯英文的str可以用ASCII编码为bytes，内容是一样的，含有中文的str可以用UTF-8编码为bytes。含有中文的str无法用ASCII编码，因为中文编码的范围超过了ASCII编码的范围，Python会报错。

在bytes中，无法显示为ASCII字符的字节，用\x##显示。

反过来，如果我们从网络或磁盘上读取了字节流，那么读到的数据就是bytes。要把bytes变为str，就需要用decode()方法：

	>>> b'ABC'.decode('ascii')
	'ABC'
	>>> b'\xe4\xb8\xad\xe6\x96\x87'.decode('utf-8')
	'中文'

##4.Python格式化方法
Python格式化方式和C语言是一致的，用%实现，举例如下：

	>>> 'Hello, %s' % 'world'
	'Hello, world'
	>>> 'Hi, %s, you have $%d.' % ('Michael', 1000000)
	'Hi, Michael, you have $1000000.'
你可能猜到了，%运算符就是用来格式化字符串的。在字符串内部，%s表示用字符串替换，%d表示用整数替换，有几个%?占位符，后面就跟几个变量或者值，顺序要对应好。如果只有一个%?，括号可以省略。

常见的占位符有：

%d	整数
%f	浮点数
%s	字符串
%x	十六进制整数
其中，格式化整数和浮点数还可以指定是否补0和整数与小数的位数：

	>>> '%2d-%02d' % (3, 1)
	' 3-01'
	>>> '%.2f' % 3.1415926
	'3.14'
如果你不太确定应该用什么，%s永远起作用，它会把任何数据类型转换为字符串：

	>>> 'Age: %s. Gender: %s' % (25, True)
	'Age: 25. Gender: True'
有些时候，字符串里面的%是一个普通字符怎么办？这个时候就需要转义，用%%来表示一个%：

	>>> 'growth rate: %d %%' % 7
	'growth rate: 7 %'

##5.使用list和tuple

Python内置的一种数据类型是列表：list。list是一种有序的集合，可以随时添加和删除其中的元素。

比如，列出班里所有同学的名字，就可以用一个list表示：

	>>> classmates = ['Michael', 'Bob', 'Tracy']
	>>> classmates
	['Michael', 'Bob', 'Tracy']
变量classmates就是一个list。用len()函数可以获得list元素的个数：

	>>> len(classmates)
	3
用索引来访问list中每一个位置的元素，记得索引是从0开始的：

	>>> classmates[0]
	'Michael'
	>>> classmates[1]
	'Bob'
	>>> classmates[2]
	'Tracy'
	>>> classmates[3]
	Traceback (most recent call last):
	File "<stdin>", line 1, in <module>
	IndexError: list index out of range
当索引超出了范围时，Python会报一个IndexError错误，所以，要确保索引不要越界，记得最后一个元素的索引是len(classmates) - 1。

如果要取最后一个元素，除了计算索引位置外，还可以用-1做索引，直接获取最后一个元素：

	>>> classmates[-1]
	'Tracy'
以此类推，可以获取倒数第2个、倒数第3个：

	>>> classmates[-2]
	'Bob'
	>>> classmates[-3]
	'Michael'
	>>> classmates[-4]
	Traceback (most recent call last):
	File "<stdin>", line 1, in <module>
	IndexError: list index out of range
当然，倒数第4个就越界了。

list是一个可变的有序表，所以，可以往list中追加元素到末尾：

	>>> classmates.append('Adam')
	>>> classmates
	['Michael', 'Bob', 'Tracy', 'Adam']
也可以把元素插入到指定的位置，比如索引号为1的位置：

	>>> classmates.insert(1, 'Jack')
	>>> classmates
	['Michael', 'Jack', 'Bob', 'Tracy', 'Adam']
要删除list末尾的元素，用pop()方法：

	>>> classmates.pop()
	'Adam'
	>>> classmates
	['Michael', 'Jack', 'Bob', 'Tracy']
要删除指定位置的元素，用pop(i)方法，其中i是索引位置：

	>>> classmates.pop(1)
	'Jack'
	>>> classmates
	['Michael', 'Bob', 'Tracy']
要把某个元素替换成别的元素，可以直接赋值给对应的索引位置：

	>>> classmates[1] = 'Sarah'
	>>> classmates
	['Michael', 'Sarah', 'Tracy']
list里面的元素的数据类型也可以不同，比如：

	>>> L = ['Apple', 123, True]
list元素也可以是另一个list，比如：

	>>> s = ['python', 'java', ['asp', 'php'], 'scheme']
	>>> len(s)
	4
要注意s只有4个元素，其中s[2]又是一个list，如果拆开写就更容易理解了：

	>>> p = ['asp', 'php']
	>>> s = ['python', 'java', p, 'scheme']
要拿到'php'可以写p[1]或者s[2][1]，因此s可以看成是一个二维数组，类似的还有三维、四维……数组，不过很少用到。

如果一个list中一个元素也没有，就是一个空的list，它的长度为0：

	>>> L = []
	>>> len(L)
	0

另一种有序列表叫元组：tuple。tuple和list非常类似，但是tuple一旦初始化就不能修改，比如同样是列出同学的名字：

	>>> classmates = ('Michael', 'Bob', 'Tracy')
现在，classmates这个tuple不能变了，它也没有append()，insert()这样的方法。其他获取元素的方法和list是一样的，你可以正常地使用classmates[0]，classmates[-1]，但不能赋值成另外的元素。

不可变的tuple有什么意义？因为tuple不可变，所以代码更安全。如果可能，能用tuple代替list就尽量用tuple。

tuple的陷阱：当你定义一个tuple时，在定义的时候，tuple的元素就必须被确定下来，比如：

	>>> t = (1, 2)
	>>> t
	(1, 2)
如果要定义一个空的tuple，可以写成()：

	>>> t = ()
	>>> t
	()
但是，要定义一个只有1个元素的tuple，如果你这么定义：

	>>> t = (1)
	>>> t
	1
定义的不是tuple，是1这个数！这是因为括号()既可以表示tuple，又可以表示数学公式中的小括号，这就产生了歧义，因此，Python规定，这种情况下，按小括号进行计算，计算结果自然是1。

所以，只有1个元素的tuple定义时必须加一个逗号,，来消除歧义：

	>>> t = (1,)
	>>> t
	(1,)
Python在显示只有1个元素的tuple时，也会加一个逗号,，以免你误解成数学计算意义上的括号。

最后来看一个“可变的”tuple：

	>>> t = ('a', 'b', ['A', 'B'])
	>>> t[2][0] = 'X'
	>>> t[2][1] = 'Y'
	>>> t
	('a', 'b', ['X', 'Y'])
要创建一个内容也不变的tuple怎么做？那就必须保证tuple的每一个元素本身也不能变。