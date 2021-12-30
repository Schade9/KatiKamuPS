package WinBuilder;
import java.sql.*;
 
public class dbConnection {
	public Connection getConnection() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");	
		String username = "root";
		String password ="";
		String url = "jdbc:mysql://localhost/Students?useSSL=false";
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
}

 