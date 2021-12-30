package WinBuilder;
import java.sql.*;

public class Trail {
	public static void main(String args[]) {
		try {
//		Student a = new Student("Odeke", "Trevor", "18/U/22109/PS", "Male", "Primary 4", 12);
		String a [] = {"P5","P6"};
//		Teacher t = new Teacher("Angulo", "Peter", "trevodex@gmail.com", "SST", "123456", "SST,Science");
		StudentManager b = new StudentManager(); 
		createDatabase c = new createDatabase();
//		c.create_tables();  
		c.create_admin();
//		b.register(a); 
//		System.out.println(b.student_login("18/U/22109/PS"));
//		b.register_teachers(t);
//		b.register_marks("Odeke Trevor", "18/U/22109/PS", "SST", 83,"trevodex@gmail.com");
//		dbConnection db = new dbConnection();
//		Connection con = db.getConnection();
//		System.out.println(b.update_marks(con, "18/U/22109/PS", "SST", "trevodex@gmail.com", 23));
		}catch(Exception e) {
			System.out.println("Exception occured : "+e.getMessage());
		}
	}
}
