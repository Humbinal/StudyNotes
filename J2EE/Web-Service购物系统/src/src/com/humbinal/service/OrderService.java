package com.humbinal.service;

import com.humbinal.domain.Book;
import com.humbinal.domain.Users;
import com.humbinal.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;

//负责与订单有关的操作
public class OrderService {
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	//下订单涉及两张表
	//传入的参数
	public void submitOrder(MyCart myCart,Users users) {
			
		String sql="insert into orders(userId,totalPrice,orderDate) values(?,?,CURRENT_TIMESTAMP);";
	
		//对于这个复杂、独特的问题我们可以单独写数据库操作，不使用SqlHelper。
		try {
			
			ct=DbUtils.getCon();
			//为了保证我们的订单号是稳定的，沃尔玛你需要升级数据库的事物隔离级别，升级到最高的可串行化
			ct.setAutoCommit(false);//关闭自动提交
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);//设置串行化
			
			
			ps=ct.prepareStatement(sql);
			ps.setInt(1, users.getId());
			ps.setFloat(2, myCart.getTotalPrice());
			ps.executeUpdate();
			
			//拿到刚刚生成的订单号
			sql="SELECT LAST_INSERT_ID()";
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			int orderId=0;
			if (rs.next()) {
				orderId=rs.getInt(1);
				System.out.println(orderId);
			}
			
			//把订单细节表提交到数据表orderItem
			ArrayList arrayList=myCart.showMyCart();
			for (int i = 0; i < arrayList.size(); i++) {
				Book book=(Book) arrayList.get(i);
				sql="insert into ordersItem(ordersId,bookId,bookNum) values(?,?,?)";
				ps=ct.prepareStatement(sql);
				ps.setInt(1, orderId);
				ps.setInt(2, book.getId());
				ps.setInt(3, book.getShoppingCount());
				ps.executeUpdate();
				System.out.println(book.getId());
			}
			ct.commit();
			
		} catch (Exception e) {
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			// TODO: handle exception
		}finally{
			DbUtils.close(rs, ps, ct);
		}
		
		
	}
}
