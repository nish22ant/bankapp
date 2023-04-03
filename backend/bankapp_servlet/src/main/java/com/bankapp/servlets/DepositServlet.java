package com.bankapp.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

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
		String accountPasswordString = request.getParameter("accountPassword");
		String amountString = request.getParameter("amount");
		InputStream inputStream = request.getPart("selectedFile").getInputStream();
		byte[] bytes = null;
		try {
			long accountNumber = Long.parseLong(accountNumberString);
			long chequeNumber = Long.parseLong(chequeNumberString);
			long amount = Long.parseLong(amountString);
			bytes = IOUtils.toByteArray(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	

}
