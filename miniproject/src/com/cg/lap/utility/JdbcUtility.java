package com.cg.lap.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.lap.exception.LAPException;

public class JdbcUtility {

	static Connection connection = null;

	public static Connection getConnection() throws LAPException {
		File file = null;
		FileInputStream inputStream = null;
		Properties properties = null;

		file = new File("resources/jdbc.properties");
		try {
			inputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(inputStream);

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new LAPException("File Not Present");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new LAPException("Unable to read the file");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new LAPException("class not loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new LAPException("not connected to database");
		}

		return connection;

	}
}
