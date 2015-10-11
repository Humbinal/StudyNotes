package com.humbinal.controller;

import java.io.IOException;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoLoginServlet
 */
@WebServlet("/DoLoginServlet")
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
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
		String userId=request.getParameter("username");
		String password=request.getParameter("password");
		
		System.out.println(userId+"  "+password );
		
		Connection ct=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			
			//1加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.得到连接
			ct=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jspusermanager","root","admin");
			//3.创建PreparedSatement
			ps=ct.prepareStatement("select * from users where userId=? and password=?");
			//给? 赋值
			ps.setObject(1, userId);
			ps.setObject(2, password);
			
			//4.执行操作
			rs=ps.executeQuery();
			//5.根据结果左处理
			if(rs.next()){
				//说明该用户合法
				request.getRequestDispatcher("/WelcomeServlet").forward(request, response);
			}else{
				request.setAttribute("err", "用户名或密码有误！");
				request.getRequestDispatcher("/LoginServlet").forward(request, response);
			}
			
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
