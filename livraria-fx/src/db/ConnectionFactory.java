package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/projeto_livraria?useSSL=false&useTimezone=true&serverTimezone=UTC";
		
		try {
			return DriverManager.getConnection(url, "root", "12345678");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta, e agora?");
		connection.close();
	}
}
