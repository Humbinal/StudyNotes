======================开始POP3收信操作==========================

[crazywill@localhost crazywill]$ telnet pop.163.com 110                #telnet登录110端口
Trying 202.108.5.104...
Connected to pop.163.com.
Escape character is '^]'.
+OK Welcome to coremail Mail Pop3 Server (163com[20050206])
USER crazywill                                                     # 用户名
+OK core mail
PASS mypassword                                             # 登录密码
+OK 254 message(s) [27676669 byte(s)]
STAT                                                                      # 查看邮箱状态
+OK 254 27676669
LIST                                                                         # 邮件列表
+OK 254 27676669
1 2468
2 21945
3 33136
4 2071
5 3364
6 18906
7 3136
8 24764
.................

TOP 254  0                                                    # 查看指定邮件的邮件头，0表示查看整个邮件头，其它正整数表示限制返回多少行。
+OK core mail
Received: from smtp.63.com (unknown [58.252.70.158])
        by smtp5 (Coremail) with SMTP id wKjREDrA9gIfFqlEjCnRAg==.29062S4;
        Mon, 03 Jul 2006 21:07:18 +0800 (CST)
TO: crazywill@163.com
FROM : cccc@163.com                                                   # 这里即前面发信时伪造的一个假发送人信息，平时正常操作只显示这个。
SUBJECT: test by telnet/smtp                                        # 邮件主题
Message-Id: <44A91687.0E6F6C.07562>
Date: Mon, 3 Jul 2006 21:07:19 +0800 (CST)
Sender: crazywill@163.com                                            # 这里是真正的发送人，不可伪造。

.
RETR 254                                                     # 获取指定邮件
+OK 354 octets
Received: from smtp.63.com (unknown [58.252.70.158])
        by smtp5 (Coremail) with SMTP id wKjREDrA9gIfFqlEjCnRAg==.29062S4;
        Mon, 03 Jul 2006 21:07:18 +0800 (CST)
TO: crazywill@163.com
FROM : cccc@163.com
SUBJECT: test by telnet/smtp
Message-Id: <44A91687.0E6F6C.07562>
Date: Mon, 3 Jul 2006 21:07:19 +0800 (CST)
Sender: crazywill@163.com
test, just a test.
.

DELE 254                                                       # 删除第254封邮件
+OK core mail
STAT                                                             # 查看邮箱状态
+OK 253 27676315
QUIT                                                              # 退出
+OK core mail
Connection closed by foreign host.
[crazywill@localhost crazywill]$ 
