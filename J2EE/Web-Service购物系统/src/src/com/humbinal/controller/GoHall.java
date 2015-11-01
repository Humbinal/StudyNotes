package com.humbinal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humbinal.domain.Book;
import com.humbinal.domain.Users;
import com.humbinal.service.BookService;
import com.humbinal.service.UsersService;

/**
 * Servlet implementation class GoHall
 */
@WebServlet("/GoHall")
public class GoHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoHall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		
		//System.out.println("id="+userId+"  pass="+password);
		
		Users loginUser=new Users(Integer.parseInt(userId),password);
		//System.out.println();
		//使用业务逻辑类完成用户验证
		UsersService usersService=new UsersService();
		if (usersService.checkUser(loginUser)) {
			//验证数据符合跳转到下一页面
			//给下一个页面准备数据
			BookService bookService=new BookService();
			ArrayList<Book> arrayList=bookService.getAllBook();
			//将数据放入request域对象中，应为该域对象生命周期最短，节省开支
			request.setAttribute("books", arrayList);		
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
