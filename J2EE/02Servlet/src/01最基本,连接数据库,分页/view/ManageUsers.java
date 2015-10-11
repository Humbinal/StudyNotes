package com.humbinal.view;

import java.io.IOException;
import java.io.*;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageUsers
 */
@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out= response.getWriter();
		out.println("<h1>用户管理</h1>");
		
		//设计分页- pageNow   表示第几页,该变量是由用户来决定,因此变化
		//- pageSize	每页显示几条记录,由程序指定,也可以由用户定制
		//- pageCount  表示共有多少页, 该变量是计算出来->思考 怎样确定
		//- rowCount   共有多少条记录,该变量是查询数据库得到
		//
		int pageNow=1;
		String rString=request.getParameter("pageNow");
		if (rString!=null) {
			pageNow=Integer.parseInt(rString);
		}
		int pageSize=3;
		int pageCount=1;
		int rowCount=1;
		
		
		//从数据库获取数据并显示
		Connection ct=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			
			//1加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.得到连接
			ct=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jspusermanager","root","admin");
			
			//查询一共有多少行
			ps=ct.prepareStatement("select count(*) from users");
			rs=ps.executeQuery();
			rs.next();
			rowCount=rs.getInt(1);
			
			pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			
			//3.创建PreparedSatement
			String sql="select * from users order by userId limit "+(pageNow-1)*pageSize+","+pageSize;
			ps=ct.prepareStatement(sql);
				//System.out.println(sql);		
			//4.执行操作
			rs=ps.executeQuery();
			
			
			
			
			
			//展示表格
			out.println("<table border=1px cellspacing=0 bordercolor=green ><tr><th>用户ID</th><th>用户名</th><th>邮箱</th><th>级别</th></tr>");
			//5.根据结果左处理
			while(rs.next()){
				//说明该用户合法
				out.println("<tr><td>"+rs.getObject(1).toString()+"</td><td>"+rs.getObject(2).toString()+"</td><td>"+rs.getObject(4).toString()+"</td><td>"+rs.getObject(5).toString()+"</td></tr>");
			}
			out.println("</table>");
			for(int i=1;i<=pageCount;i++){
				out.println("<a href='/UserManager/ManageUsers?pageNow="+i+"'>["+i+"]</a>");
			}
			
			out.println("&nbsp;&nbsp;第"+pageNow+"页/共"+pageCount+"页");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			//关闭资源
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs=null;
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ps=null;
			}
			if(ct!=null){
				try {
					ct.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ct=null;
			}
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
