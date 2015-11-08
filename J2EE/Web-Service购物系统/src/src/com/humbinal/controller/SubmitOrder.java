package com.humbinal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.humbinal.domain.Users;
import com.humbinal.service.MyCart;
import com.humbinal.service.OrderService;
import com.humbinal.utils.MailUtils;

/**
 * Servlet implementation class SubmitOrder
 */
@WebServlet("/SubmitOrder")
public class SubmitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrder() {
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
		MyCart myCart=(MyCart) request.getSession().getAttribute("myCart");
		Users users=(Users) request.getSession().getAttribute("loginUser");
		try {
			OrderService orderService=new OrderService();
			
			orderService.submitOrder(myCart, users);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		//发送邮件通知用户
		/*MailUtils sendMail=new MailUtils(users.getEmail(), "huangbin1020@126.com", "smtp.126.com", "huangbin1020@126.com", "xxx", "你已成功购买了商品", "感谢购物，欢迎下次再来！");
		
		if (sendMail.sendMail()) {
			request.setAttribute("email", "成功");
			request.getRequestDispatcher("/WEB-INF/orderFinish.jsp").forward(request, response);
		}else {
			request.setAttribute("email", "出现异常，客服将会和你联系以确认");			
			request.getRequestDispatcher("/WEB-INF/orderFinish.jsp").forward(request, response);
		}*/
		request.getRequestDispatcher("/WEB-INF/orderFinish.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
