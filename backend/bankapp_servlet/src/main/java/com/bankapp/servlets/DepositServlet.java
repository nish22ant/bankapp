package com.bankapp.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankapp.model.connectionfactory.ConnectionFactoryMongoDB;
import com.bankapp.model.connectionfactory.ConnectionFactoryMySQL;
import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.dao.AccountDaoJDBC;
import com.bankapp.model.dao.EChequeDao;
import com.bankapp.model.dao.EChequeDaoImp;
import com.bankapp.utils.HeaderUtils;
import com.mongodb.client.MongoClient;

@WebServlet(urlPatterns = "/api/deposit")
@MultipartConfig
public class DepositServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
	private Connection connection;
	private MongoClient mongoClient;
	private EChequeDao eChequeDao;
 
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			connection = ConnectionFactoryMySQL.getConnection();
			accountDao = new AccountDaoJDBC(connection);
			mongoClient = ConnectionFactoryMongoDB.getClient();
			eChequeDao = new EChequeDaoImp(mongoClient);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HeaderUtils.setCommonHeaders(response);
		String accountNumberString = request.getParameter("accountNumber");
		String chequeNumberString = request.getParameter("chequeNumber");
		String selectedFile = request.getParameter("selectedFile");
		byte[] file = Files.readAllBytes(new File(selectedFile).toPath());
		String amount = request.getParameter("amount");
		System.out.println(Arrays.toString(file));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(accountNumberString + " : " + amount);
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	

}
