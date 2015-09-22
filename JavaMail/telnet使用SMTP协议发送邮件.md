在Win7, Vista, Windows Server 2008 R2上, telnet不是默认安装的. 
首先需要安装telnet：Start->Control Panel->Programs->Turn Windows Features on or off->Telnet Client.

开始发信操作：

		telnet smtp.126.com 25     #telnet登录25端口
		220 126.com Anti-spam GT for Coremail System (126com[20140526])  #服务器返回此行表示连接成功
		elho hello        #跟服务器握手
		此步可能失败 会提示 ： 重新发送便可成功返回如下
		250-mail
		250-PIPELINING
		250-AUTH LOGIN PLAIN
		250-AUTH=LOGIN PLAIN
		250-coremail 1Uxr2xKj7kG0xkI17xGrU7I0s8FY2U3Uj8Cz28x1UUUUU7Ic2I0Y2Ur1NfnQUCa0xDr
		UUUUj
		250-STARTTLS
		250 8BITMIME

		auth login    #认证登录
		334 dXNlcm5hbWU6      #返回334表示认证成功，接下来直接发送用户名和密码
		dGVzdEAxMjYuY29t     #用户名，注意需要使用base64编码发送[Base64编解码](http://base64.xpcha.com/)，此字符串对应test@126.com用户名
		334 UGFzc3dvcmQ6     #服务器返回334，代表用户名正确
		bXlwYXNzd29yZA==     #发送密码仍旧是BASE64
		235 Authentication successful  #服务器返回235代表成功，如果失败就是用户名密码不对应或者编码不对

		#接下来开始发送邮件
		mail from:<test@126.com>  #此处注意使用刚才登陆的用户，否则会返回553 You are not authorized to send mail, authentication is required(不可伪造发送邮件);
		250 Mail OK  #正确后服务器返回250
		pcrt to:<test2@126.com>  #邮件接收者
		250 Mail OK  #正确后服务器返回250
		data     #发送data指令给服务器代表我要设置邮件内容
		354 End data with <CR><LF>.<CR><LF> 服务器返回354，提示你如何结束编辑
		to:test2@126.com       #此处的TO，FROM，等内容，可以随便造假,可以骗人但骗不了懂得查看邮件源码的人。
		from:test@126.com
		subject:test

		test telnet smtp.      #邮件正文内容，与Header部分空一行开始写
		.                      #邮件写完，换行，以一个英文句点加回车结果。
		250 Mail OK queued as smtp10,wKjADQ2ApxRnnqBE0CWaEw==.38326S3    #服务器返回250 表示发送成功。
		noop    #空语句，不执行任何操作，一般用来保持和服务器连接，不要掉线
		250 OK
		quit  # 退出
		221 Bye


