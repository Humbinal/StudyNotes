package cn.itcast.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 向客户端发送json串
		 */
		String str = "{\"name\":\"zhangSan\", \"age\":18, \"sex\":\"male\"}";
		response.getWriter().print(str);
		System.out.println(str);
	}
}
