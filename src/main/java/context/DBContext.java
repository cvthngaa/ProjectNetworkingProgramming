package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
	public static Connection getConnection() {
		Connection connection = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/LTM", "root", "123456");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error while connecting to the database: " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
		return connection;
	}
	public static void main(String[] args) {
		Connection con = DBContext.getConnection();
		System.out.println("thanh cong");
	}
}
