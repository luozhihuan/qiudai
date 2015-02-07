package com.hust.qiudai.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import com.hust.qiudai.web.util.VariableUtil;

public class LoginServlet extends HttpServlet {
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("哈哈哈："+request.getParameter(VariableUtil.EMAIL_PARAMETER_NAME));
		System.out.println(request.getParameter(VariableUtil.PASSWORD_PARAMETER_NAME));
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/service/login/login/login");
		dispatcher.forward(request, response);
		
	}
}



