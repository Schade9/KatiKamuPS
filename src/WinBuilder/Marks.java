package WinBuilder;
import java.sql.*;

//Interface for marks
public interface Marks {
	public String delete_marks(String regno, String subject) throws Exception;
	public String update_marks(String regno, String subject, String email, int mark) throws Exception;
	
}
