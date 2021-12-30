package WinBuilder;
import java.sql.*;

public interface School {
	public String register(Student student) throws Exception;
	public String register_teachers(Teacher teacher) throws Exception;
	public Boolean teacher_login(String email, String password) throws Exception;
	public Boolean student_login(String regno) throws Exception;
	public void register_marks(String student_name, String regno, String subject,int mark, String email) throws Exception;
	public void delete_student(String regno) throws Exception;
//	public String show_marks();
}
