package com.humbinal.domain;

public class Book {

	/*id int primary key AUTO_INCREMENT,//自增长
	name varchar(50) not null,
	author varchar(100) not null,
	publishHouse varchar(100) not null,
	price int not null,
	bookCount int default 1000 not null*/
	private int id;
	private String name;
	private String author;
	private String publishHouse;
	private float price;
	private int bookCount;//表示库存
	private int shoppingCount=1; //购买的数量，用于购物车中数量的展示，数据库不存储该字段
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishHouse() {
		return publishHouse;
	}
	public void setPublishHouse(String publishHouse) {
		this.publishHouse = publishHouse;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public int getShoppingCount() {
		return shoppingCount;
	}
	public void setShoppingCount(int shoppingCount) {
		this.shoppingCount = shoppingCount;
	}
	

}
