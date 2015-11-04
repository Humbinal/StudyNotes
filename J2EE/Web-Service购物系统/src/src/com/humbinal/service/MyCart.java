package com.humbinal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.humbinal.domain.Book;

public class MyCart {

	HashMap<String, Book> hashMap=new HashMap<String, Book>();
	
	//添加书
	public void addBook(String id){
		if (hashMap.containsKey(id)) {
			//如果该书已经购买过了，那么数量加一
			Book book=hashMap.get(id);
			int shoppingCount= book.getShoppingCount();
			book.setShoppingCount(shoppingCount+1);
		}else {
			hashMap.put(id, new BookService().getBookById(id));
		}
	}
	
	//计算购物车商品总价
	public float getTotalPrice() {
		float totalPrice=0.0f;
		ArrayList<Book> arrayList=new ArrayList<Book>();
		Iterator iterator=hashMap.keySet().iterator();
		while (iterator.hasNext()) {
			//取出书号
			String id=(String) iterator.next();
			//根据书号取出价格
			Book book=hashMap.get(id);
			totalPrice+= book.getPrice()*book.getShoppingCount();
		}
		return totalPrice;
	}
	
	/*public void addBook(String id,Book book){
		if (hashMap.containsKey(id)) {
			//如果该书已经购买过了，那么数量加一
			int shoppingCount= book.getShoppingCount();
			book.setShoppingCount(shoppingCount+1);
			hashMap.put(id, book);
		}else {
			hashMap.put(id, book);
		}
		
	}*/
	//删除书
	public void deleteBook(String id){
		hashMap.remove(id);
	}
	
	//更新书
	public void updateBook(String id ,String numbers){
		Book book=hashMap.get(id);
		book.setShoppingCount(Integer.parseInt(numbers));
	}
	
	//清空购物车
	public void cleanBook() {
		hashMap.clear();
		
	}
	
	//返回购物车中所有商品的信息
	public ArrayList showMyCart() {
		ArrayList<Book> arrayList=new ArrayList<Book>();
		//遍历HashMap
		Iterator iterator=hashMap.keySet().iterator();
		while (iterator.hasNext()) {
			//取出key
			String id=(String) iterator.next();
			//取出Book
			Book book =hashMap.get(id);
			arrayList.add(book);
		}
		return arrayList;
	};
	
	
}
