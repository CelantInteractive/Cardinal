package main.java.com.celantinteractive.authentication;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class DatabaseHandler {

	public static Connection connection;

	public String GetPasswordFromUUID(String UUID) {
		getMySqlConnection();

		String proc = "{ call getPasswordFromUUID(?,?) }";
		String password = null;

		try {
			CallableStatement cs = connection.prepareCall(proc);
			cs.setString(1, UUID);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.executeQuery();

			password = cs.getString(2);

		} catch (SQLException e) {
                    e.printStackTrace();
		}

		return password;

	}

	public String GetUUIDFromUsername(String Username) {
		getMySqlConnection();

		String proc = "{ call getUUIDFromUsername(?,?) }";
		String UUID = null;

		try {
			CallableStatement cs = connection.prepareCall(proc);
			cs.setString(1, Username);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.executeQuery();

			UUID = cs.getString(2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return UUID;

	}

	public boolean CreateSession(String uuid, String accessToken,
			String clientToken) {
		getMySqlConnection();

		String proc = "{ call createSession(?,?,?) }";

		boolean succeeded = true;

		try {
			CallableStatement cs = connection.prepareCall(proc);
			cs.setString(1, uuid);
			cs.setString(2, clientToken);
			cs.setString(3, accessToken);

			cs.executeQuery();
		} catch (SQLException e) {
			succeeded = false;
		}

		return succeeded;
	}
	
	public boolean RefreshSession(String newAccessToken, String accessToken, String clientToken) {
		getMySqlConnection();
		
		String proc = "{ call refreshSession(?,?,?)";
		
		boolean succeeded = true;
		
		try {
			CallableStatement cs = connection.prepareCall(proc);
			cs.setString(1, newAccessToken);
			cs.setString(2, accessToken);
			cs.setString(3, clientToken);
			
			cs.executeQuery();
		} catch (SQLException e) {
			succeeded = false;
		}
		
		return succeeded;
	}

	public void getMySqlConnection() {

		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://5.9.6.34/orbitauth?noAccessToProcedureBodies=true";
		String username = "auth";
		String password = "bdjyAzXUxAw9v8Ar";

		try {
			Class.forName(driver);

			if (connection == null) {
				Connection conn = DriverManager.getConnection(url, username,
						password);
				connection = conn;
			} else if (connection.isClosed()) {
				Connection conn = DriverManager.getConnection(url, username,
						password);
				connection = conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
