package com.humbinal.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humbinal.utils.VerifyCode;

/**
 * Servlet implementation class GetVerifyCode
 */
@WebServlet("/GetVerifyCode")
public class GetVerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVerifyCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 1.生成图片
		 * 2.保存图片的验证码到Session
		 * 3.返回图片
		 */	
		VerifyCode verifyCode=new VerifyCode();
		BufferedImage bufferedImage=verifyCode.getImage();
		request.getSession().setAttribute("verifyCode", verifyCode.getText());
		//返回图片给客户端
		verifyCode.output(bufferedImage, response.getOutputStream());
			
			
				
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
