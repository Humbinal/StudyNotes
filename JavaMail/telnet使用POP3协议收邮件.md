======================��ʼPOP3���Ų���==========================

[crazywill@localhost crazywill]$ telnet pop.163.com 110                #telnet��¼110�˿�
Trying 202.108.5.104...
Connected to pop.163.com.
Escape character is '^]'.
+OK Welcome to coremail Mail Pop3 Server (163com[20050206])
USER crazywill                                                     # �û���
+OK core mail
PASS mypassword                                             # ��¼����
+OK 254 message(s) [27676669 byte(s)]
STAT                                                                      # �鿴����״̬
+OK 254 27676669
LIST                                                                         # �ʼ��б�
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

TOP 254  0                                                    # �鿴ָ���ʼ����ʼ�ͷ��0��ʾ�鿴�����ʼ�ͷ��������������ʾ���Ʒ��ض����С�
+OK core mail
Received: from smtp.63.com (unknown [58.252.70.158])
        by smtp5 (Coremail) with SMTP id wKjREDrA9gIfFqlEjCnRAg==.29062S4;
        Mon, 03 Jul 2006 21:07:18 +0800 (CST)
TO: crazywill@163.com
FROM : cccc@163.com                                                   # ���Ｔǰ�淢��ʱα���һ���ٷ�������Ϣ��ƽʱ��������ֻ��ʾ�����
SUBJECT: test by telnet/smtp                                        # �ʼ�����
Message-Id: <44A91687.0E6F6C.07562>
Date: Mon, 3 Jul 2006 21:07:19 +0800 (CST)
Sender: crazywill@163.com                                            # �����������ķ����ˣ�����α�졣

.
RETR 254                                                     # ��ȡָ���ʼ�
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

DELE 254                                                       # ɾ����254���ʼ�
+OK core mail
STAT                                                             # �鿴����״̬
+OK 253 27676315
QUIT                                                              # �˳�
+OK core mail
Connection closed by foreign host.
[crazywill@localhost crazywill]$ 
