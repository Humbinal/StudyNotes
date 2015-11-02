package com.humbinal.service;

import java.util.ArrayList;

import com.humbinal.domain.*;
import com.humbinal.utils.SqlHelper;


//用于处理业务逻辑的类
//处理和book表相关的逻辑
public class BookService {

	
	//根据书的编号返回Book实例
	public Book getBookById(String id){
		Book book=new Book();
		String sql="select * from book where id=?";
		String paras[]={id};
		ArrayList arrayList=new SqlHelper().executeQuery(sql,paras);
		if (arrayList.size()==1) {
			Object [] object=(Object[]) arrayList.get(0);
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Float.parseFloat(object[4].toString()));
			book.setBookCount(Integer.parseInt(object[5].toString()));
		}
		return book;
	}
	
	//得到所有的书籍信息，（分页）
	public ArrayList getAllBook(){
		
		String sql="select * from book where 1=?";
		String paras[]={"1"};
		ArrayList arrayList=new SqlHelper().executeQuery(sql, paras);
		ArrayList<Book> arrayList2=new ArrayList<Book>();
		for (int i = 0; i < arrayList.size(); i++) {
			Object object[]=(Object[])arrayList.get(i);
			//对结果进行二次封装
			Book book=new Book();
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Float.parseFloat(object[4].toString()));
			book.setBookCount(Integer.parseInt(object[5].toString()));
			arrayList2.add(book);
		}
		
		return arrayList2;
	}
}
