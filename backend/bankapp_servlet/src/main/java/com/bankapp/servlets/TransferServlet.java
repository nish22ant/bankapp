package com.bankapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bankapp.model.connectionfactory.ConnectionFactoryMySQL;
import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.dao.AccountDaoJDBC;
import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.AccountServiceImp;
import com.bankapp.utils.HeaderUtils;

@WebServlet("/api/transfer")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferServlet.class);

	private AccountService accountService;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			AccountDao accountDao = new AccountDaoJDBC(ConnectionFactoryMySQL.getConnection());
			accountService = new AccountServiceImp(accountDao);
		} catch (SQLException e) {
			LOGGER.error("Error initializing TransferServlet: " + e.getMessage(), e);
			throw new ServletException("Error initializing TransferServlet", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fromAccountNumberString = request.getParameter("fromAccountNumber");
		String toAccountNumberString = request.getParameter("toAccountNumber");
		String amountString = request.getParameter("amount");
		String password = request.getParameter("accountPassword");
		String passwordAgain = request.getParameter("passwordAgain");

		long fromAccountNumber;
		long toAccountNumber;
		double amount;

		try {
			fromAccountNumber = Long.parseLong(fromAccountNumberString);
			toAccountNumber = Long.parseLong(toAccountNumberString);
			amount = Double.parseDouble(amountString);
		} catch (NumberFormatException e) {
			LOGGER.error("Error parsing parameters: " + e.getMessage(), e);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
			return;
		}

		HeaderUtils.setCommonHeaders(response);
		response.setContentType("text/html");

		try {
			accountService.transfer(fromAccountNumber, toAccountNumber, amount, password);
		} catch (SQLException e) {
			LOGGER.error("Error transferring funds: " + e.getMessage(), e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error transferring funds");
			return;
		}
		PrintWriter writer = response.getWriter();
		writer.println("Funds transferred successfully");
		writer.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}