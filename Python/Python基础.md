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
