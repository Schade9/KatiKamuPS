package WinBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminManager implements Admin{
	dbConnection db = new dbConnection();
	public Boolean login(String username, String password) throws Exception{
		Connection con = db.getConnection();
		Statement smt = con.createStatement();

		String sql = "SELECT * FROM admin WHERE username='"+username+"' AND password ='"+password+"'";
		
		ResultSet rs = smt.executeQuery(sql);
		
		if(rs.first()) {
			return true;
		}else {
			return false;
		}

	}
}
