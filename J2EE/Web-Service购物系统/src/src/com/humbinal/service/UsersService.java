package com.humbinal.service;

import java.util.ArrayList;

import com.humbinal.domain.Users;
import com.humbinal.utils.SqlHelper;


//用于处理业务逻辑的类
//处理和users表相关的逻辑
public class UsersService {

	//验证用户是否合法的方法
	public boolean checkUser(Users user){
		//到数据库验证用户
		String sql="select * from users where id=? and password=?";
		String paras[]={String.valueOf(user.getId()),user.getPassword()};
		ArrayList arrayList=new SqlHelper().executeQuery(sql, paras);
		if (arrayList.size()==0) {
			return false;
		} else {
			Object [] objects=(Object[]) arrayList.get(0);
			//把对象数组封装到Users对象
			user.setEmail((String)objects[3]);
			user.setGrade(Integer.parseInt(objects[5].toString()));
			user.setName((String)objects[1]);
			return true;
		}
		
	}
}
