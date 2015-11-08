package com.humbinal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humbinal.service.BookService;
import com.humbinal.service.MyCart;

/**
 * Servlet implementation class GoMyCart
 * 该控制器处理购物车商品
 */
@WebServlet("/GoMyCart")
public class GoMyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoMyCart() {
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
		
		//接收type值，判断用户请求
		
		String type=request.getParameter("type");
		
		if (type.equals("add")) {
			
			//接收用户想要 购买的书的id
			String id=request.getParameter("id");
			//System.out.println(id);
			//何时创建购物车？？（当用户登录后为其分配一个购物车）
			//取出购物车并添加书进去
			MyCart myCart=(MyCart) request.getSession().getAttribute("myCart");
			//添加上商品到购物车
			myCart.addBook(id);
			//跳转到购物车页面
			//将购物车数据取出给下个页面显示
			//request.setAttribute("booklist", myCart.showMyCart());
			//request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			response.sendRedirect("/shopping/GoShowMyCart");
			//request.getRequestDispatcher("/WEB-INF/myCart.jsp").forward(request, response);					
		} else if (type.equals("delete")) {
			//从购物车删除商品
			String id=request.getParameter("id");
			MyCart myCart=(MyCart) request.getSession().getAttribute("myCart");
			myCart.deleteBook(id);
			
			request.setAttribute("booklist", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			request.getRequestDispatcher("/WEB-INF/myCart.jsp").forward(request, response);
		}else if (type.equals("showMyCart")) {
			
			MyCart myCart=(MyCart) request.getSession().getAttribute("myCart");
			request.setAttribute("booklist", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			request.getRequestDispatcher("/WEB-INF/myCart.jsp").forward(request, response);
		} else if (type.equals("update")) {
			//获取更新的书籍信息
			String bookIds[]=request.getParameterValues("bookId");
			String bookCounts[]=request.getParameterValues("bookCount");
			//更新购买书籍的数量
			MyCart myCart=(MyCart) request.getSession().getAttribute("myCart");
			for (int i = 0; i < bookCounts.length; i++) {
				//System.out.println(bookIds[i]+"    "+bookCounts[i]);
				myCart.updateBook(bookIds[i], bookCounts[i]);
			}
			
			request.setAttribute("booklist", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			request.getRequestDispatcher("/WEB-INF/myCart.jsp").forward(request, response);
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
