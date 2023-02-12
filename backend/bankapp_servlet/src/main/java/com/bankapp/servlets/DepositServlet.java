package com.bankapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankapp.utils.HeaderUtils;

@WebServlet(urlPatterns = "/api/deposit")
public class DepositServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountNumber = request.getParameter("accountNumber");
		String amount = request.getParameter("amount");
		System.out.println(accountNumber);
		HeaderUtils.setCommonHeaders(response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(accountNumber + " : " + amount);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
