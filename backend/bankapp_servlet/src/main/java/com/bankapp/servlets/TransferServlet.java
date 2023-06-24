	package com.bankapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bankapp.exception.BankAccountNotFoundException;
import com.bankapp.model.connectionfactory.ConnectionFactoryMySQL;
import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.dao.AccountDaoJDBC;
import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.AccountServiceImp;
import com.bankapp.utils.HeaderUtils;
import com.bankapp.utils.Messages;

@WebServlet("/api/transfer")
@MultipartConfig
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static long fromAccountNumber;
	private static long toAccountNumber;
	private static double amount;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HeaderUtils.setCommonHeaders(response);
		PrintWriter writer = response.getWriter();
		String fromAccountNumberString = request.getParameter("fromAccountNumber");
		String toAccountNumberString = request.getParameter("toAccountNumber");
		String amountString = request.getParameter("amount");
		String password = request.getParameter("accountPassword");
		String passwordAgain = request.getParameter("passwordAgain");
		try {
			fromAccountNumber = Long.parseLong(fromAccountNumberString);
			toAccountNumber = Long.parseLong(toAccountNumberString);
			amount = Double.parseDouble(amountString);
		} catch (NumberFormatException e) {
			LOGGER.error("Error parsing parameters: " + e.getMessage(), e);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
			return;
		}

		try {
			System.out.println("From Account: " + accountService.getAccount(fromAccountNumber));
			accountService.transfer(fromAccountNumber, toAccountNumber, amount, password);
		} catch(BankAccountNotFoundException be) {
			writer.println("Incorrect Bank Account Number");
			return;
		} catch (SQLException e) {
			LOGGER.error("Error transferring funds: " + e.getMessage(), e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error transferring funds");
			writer.println("Funds Transfer Failed");
			return;
		}
		response.setStatus(HttpServletResponse.SC_OK);
		writer.println("Funds Transferred Successfully");
		writer.flush();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}