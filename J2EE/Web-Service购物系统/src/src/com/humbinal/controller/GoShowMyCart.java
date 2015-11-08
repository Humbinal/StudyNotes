package com.humbinal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humbinal.service.MyCart;

/**
 * Servlet implementation class GoShowMyCart
 */
@WebServlet("/GoShowMyCart")
public class GoShowMyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoShowMyCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		
		MyCart myCart=(MyCart) request.getSession().getAttribute("myCart");
		//添加上商品到购物车
		//跳转到购物车页面
		//将购物车数据取出给下个页面显示
		request.setAttribute("booklist", myCart.showMyCart());
		request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
		request.getRequestDispatcher("/WEB-INF/myCart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
