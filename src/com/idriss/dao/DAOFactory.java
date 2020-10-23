package com.idriss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOFactory {
	private static Connection connection;
	private static final String DB_NAME = "Test";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static final String DB_HOST = "jdbc:mysql://localhost/"+DB_NAME;
	
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DAOFactory.connection = DriverManager.getConnection(DB_HOST,DB_USER,DB_PASSWORD);
		}catch(ClassNotFoundException e) {
			System.out.println("Error Driver: "+e);
		}catch(SQLException e) {
			System.out.println("Error DataBase: "+e);
		}
		
		return connection;
	}

}
