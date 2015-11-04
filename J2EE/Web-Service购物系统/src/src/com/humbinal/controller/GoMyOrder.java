package com.humbinal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humbinal.service.MyCart;

/**
 * Servlet implementation class GoMyOrder
 */
@WebServlet("/GoMyOrder")
public class GoMyOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoMyOrder() {
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
		if (myCart!=null) {
			ArrayList arrayList=myCart.showMyCart();
			float totalPrice=myCart.getTotalPrice();
			request.setAttribute("orderInfo", arrayList);
			request.setAttribute("totalPrice", totalPrice);
			request.getRequestDispatcher("WEB-INF/myOrder.jsp").forward(request, response);
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
