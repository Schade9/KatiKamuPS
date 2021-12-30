package WinBuilder;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class StudentManager implements School, Marks, Search{
	dbConnection db = new dbConnection();
	public String teacher;
	
	//Store details of the logged in teacher
	public static String email;
	public static String name;
	public static String subject;
	
	//Store details of the logged student
	public static String student_name;
	public static String student_regno;
	public static String student_class;
	
	public static String[] classes_taught, available_classes;
	public static int aggregate = 0;
//	public static String aggregate = 0;
	public static ArrayList<String> lesson = new ArrayList<>();
	//Check if the student registration number is valid
	public Boolean check_regno(String regno) throws Exception{
		Connection con = db.getConnection();
		Statement smt = con.createStatement();
		String sql = "SELECT * FROM students WHERE registration_num='"+regno+"'";
		ResultSet rs = smt.executeQuery(sql);
		return rs.next();
	}
	
	//Check if the teacher is assigned to a subject
	public Boolean check_subject(String email, String subject) throws Exception{
		Connection con = db.getConnection();
		Statement smt = con.createStatement();
		String sql1 = "SELECT * FROM teachers WHERE email='"+email+"' AND subjects='"+subject+"'";
		ResultSet rs = smt.executeQuery(sql1);
		return rs.next(); 
	} 
	//Return grade basing on marks
	public String grade(int mark) throws Exception{
		if(mark >= 90) {
			return "D1"; 
		}else if(mark >= 80) {
			return "D2";
		}else if(mark >= 70) {
			return "C3";
		}else if(mark >= 60) {
			return "C4";
		}else if(mark >= 50) {
			return "C5";
		}else if(mark >= 40) {
			return "P6";
		}else {
			return "F9";
		}
	}
	//Give columns for grades for specific subjects
	public String getGradeColumn(String subject) {
		if(subject.equals("Science")) {
			return "grade1";
		}else if(subject.equals("SST")) {
			return "grade2";
		}else if(subject.equals("Math")) {
			return "grade3";
		}else if(subject.equals("English")) {
			return "grade4";
		}else {
			return "grade5";
		}
	}
	
	//Assign points according to the grade
	public int getPoints(String grade) {
		if(grade.equals("D1")) {
			return 1;
		}else if(grade.equals("D2")) {
			return 2;
		}else if(grade.equals("C3")) {
			return 3;
		}else if(grade.equals("C4")) {
			return 4;
		}else if(grade.equals("C5")) {
			return 5;
		}else if(grade.equals("P6")) {
			return 6;
		}else if(grade.equals("F9")) {
			return 9;
		}else {
			return 10;
		}

	}

	
	//Regsiter students
	public String register(Student student) throws Exception{

		Connection con = db.getConnection();
		
		Statement smt = con.createStatement();

		String sql = "SELECT * FROM students WHERE registration_num='"+student.registrationNumber+"'";
		
		registration_table(student);
		ResultSet rs = smt.executeQuery(sql);
		//Check if the registration number already exists
		if (rs.first()){
			return "exists";
		}else {
			//Register new student
			String query = "INSERT INTO students (first_name, last_name, registration_num, gender, current_class, age) VALUES (?,?,?,?,?,?)";
			System.out.println("Proceed");
			PreparedStatement pmt = con.prepareStatement(query);
			pmt.setString(1, student.firstName);
			pmt.setString(2, student.lastName);
			pmt.setString(3, student.registrationNumber);
			pmt.setString(4, student.gender);
			pmt.setString(5, student.current_class);
			pmt.setInt(6, student.age);
			pmt.execute();
			con.close();
			return "success";
		}
	}
	
	//Login students with registration number
	public Boolean student_login(String regno) throws Exception{
		Connection con = db.getConnection();
		Statement smt = con.createStatement();

		String sql = "SELECT * FROM students WHERE registration_num='"+regno+"'";
		
		ResultSet rs = smt.executeQuery(sql);
		//Return true if the registration number matches
		if(rs.first()) {
			StudentManager.student_name = rs.getString("first_name") +" "+ rs.getString("last_name");
			StudentManager.student_regno = rs.getString("registration_num");
			StudentManager.student_class = rs.getString("current_class");
			return true;
		}else {
			return false;
		}
	}
	//Register teachers,takes an object of teacher
	public String register_teachers(Teacher teacher) throws Exception{
		Connection con = db.getConnection();
		
		Statement smt = con.createStatement();

		String sql = "SELECT * FROM teachers WHERE email='"+teacher.email+"'";
		
		ResultSet rs = smt.executeQuery(sql);
		//Checking if the email already exists	
		if (rs.first()){

			return "exists";
		}else {
			//Register new teacher
  
//			Array arr = con.createArrayOf("VARCHAR", teacher.classes);
			String query = "INSERT INTO teachers (first_name, last_name, email, password, subjects, classes) VALUES (?,?,?,?,?,?)";
			PreparedStatement pmt = con.prepareStatement(query);
			pmt.setString(1, teacher.first_name);
			pmt.setString(2, teacher.last_name);
			pmt.setString(3, teacher.email);
			pmt.setString(4, teacher.password);
			pmt.setString(5, teacher.subjects);
			pmt.setString(6, teacher.classes);
			pmt.execute();
			con.close();
			return "success";
		}
	}
	//Login teachers with email and password
	public Boolean teacher_login(String email, String password) throws Exception{
		Connection con = db.getConnection();
		Statement smt = con.createStatement();

		String sql = "SELECT * FROM teachers WHERE email='"+email+"' AND password='"+password+"'";
		
		ResultSet rs = smt.executeQuery(sql); 
		
		//Return true if the credentials are matching those in the database
		if(rs.first()) {
			StudentManager.name = rs.getString("first_name") +" "+ rs.getString("last_name");
			StudentManager.email = email;
			StudentManager.subject = rs.getString("subjects");
			return true;
		}else {
			return false;
		}
	}
	//Insert student records into the table of marks
	public void registration_table(Student student) throws Exception {
		Connection con = db.getConnection();
		String query = "INSERT INTO marks (registration_num, student_name, class) VALUES (?,?,?)";
		PreparedStatement pmt = con.prepareStatement(query);
		pmt.setString(1, student.registrationNumber);
		pmt.setString(2, student.firstName +" "+student.lastName);
		pmt.setString(3, student.current_class);
		pmt.execute();
	
	} 
	
	//Register the marks of students 
	public void register_marks(String student_name, String regno, String subject, int mark, String email) throws Exception {
		Connection con = db.getConnection();
		
		Statement smt = con.createStatement();
		//Stores the name of the column for the subject being registered
		String grade_name = getGradeColumn(subject);
		String sql = "SELECT * FROM students WHERE registration_num='"+regno+"'";

		String sql1 = "SELECT * FROM teachers WHERE email='"+email+"' AND subjects='"+subject+"'";
		ResultSet rs = smt.executeQuery(sql1);
		String my_grade = grade(mark);
//		Boolean check = rs1.next();
		//Validate the teacher to see if they teach the subject
		if(rs.next()) { 
			rs = smt.executeQuery(sql);
		if (rs.next()) {
			String query = "INSERT INTO marks (registration_num, student_name,"+subject+","+grade_name+") VALUES (?,?,?,?)";
			PreparedStatement pmt = con.prepareStatement(query);
			pmt.setString(1, regno);
			pmt.setString(2, student_name);
			pmt.setInt(3, mark);
			pmt.setString(4, my_grade);
			pmt.execute();
			rs.close();
		}else { 
			System.out.println("Invalid registration number");
		}

	}else {
		System.out.println("You do not teach this subject");
	}
	}
	
	//Method to delete student
	public void delete_student(String regno) throws Exception{
		Connection con = db.getConnection();
		
		Statement smt = con.createStatement();
		String sql = "SELECT * FROM students WHERE registration_num='"+regno+"'";
		ResultSet rs = smt.executeQuery(sql);
		if(rs.next()) {
			String query = "DELETE FROM students WHERE registration_num= ?";
			PreparedStatement pmt = con.prepareStatement(query);
			pmt.setString(1, regno);
			pmt.execute();
			
		}else {
			System.out.println("unregistered");
		} 
	}

	public String delete_marks(String regno, String subject) throws Exception {
		Connection con = db.getConnection();
		if(check_regno(regno)){
			String query = "DELETE FROM marks WHERE registration_num= ?";
			PreparedStatement pmt = con.prepareStatement(query);
			pmt.setString(1, regno);
			pmt.execute();
			return "sucess";
		}else {
			return "there";
		}

	}
   
	//Update student's marks
	public String update_marks(String regno, String subject, String email,int mark) throws Exception {
		Connection con = db.getConnection();
		String column_name = getGradeColumn(subject);
		System.out.println(column_name);
		System.out.println(subject);		
		int science = 0, sst = 0, math = 0, english = 0;
		//Validate that the teacher teaches the subject in question
		if(check_subject(email, subject)) {
			//Query to update marks
		String query = "UPDATE marks SET "+StudentManager.subject+" = ?,"+column_name+" = ? WHERE registration_num = ?";
		
		PreparedStatement pmt = con.prepareStatement(query);
		pmt.setInt(1, mark);
		pmt.setString(2, grade(mark));
		pmt.setString(3, regno);
		pmt.execute();
		
		String query1 = "SELECT * FROM marks WHERE registration_num = '"+regno+"'";

		Statement smt = con.createStatement();
		ResultSet rs = smt.executeQuery(query1);
		while(rs.next()) { 
			//Assigning weights to subjects incase the column of grades is not empty
			if(!(rs.getString("grade1").equals("--"))) {
				String grade1 = rs.getString("grade1");
				 science = getPoints(grade1);
			}if(!(rs.getString("grade2").equals("--"))) {
				String grade2 = rs.getString("grade2");
				sst = getPoints(grade2);
			}if(!(rs.getString("grade3").equals("--"))) {
				String grade3 = rs.getString("grade3");
				math = getPoints(grade3);
			}if(!(rs.getString("grade4").equals("--"))) {
				String grade4 = rs.getString("grade4");
				english = getPoints(grade4);
			}
			if(!(rs.getString("grade1").equals("--")) && !(rs.getString("grade2").equals("--")) && !(rs.getString("grade3").equals("--")) && !(rs.getString("grade4").equals("--"))) {
				//Store the total points and store them in the aggregates column in the marks table
				int aggregate = science + sst + math + english;
				System.out.println(aggregate);
//				String new
				String query2 = "UPDATE marks SET aggregate = "+aggregate+" WHERE registration_num = '"+regno+"'";
				smt.execute(query2);
//				con.close();
			}
		}
		return "success";
		}else {
		return "invalid";	
		}

	}
	
	public void updateStudents(String fname, String lname, String regno, String student_class, String age, String old_regno) throws Exception{
		Connection con = db.getConnection();
		 
		String query = "UPDATE students SET first_name = ?, last_name = ?, registration_num = ?, current_class = ?, age = ? WHERE registration_num = ?";

		PreparedStatement pmt = con.prepareStatement(query);
		pmt.setString(1, fname);
		pmt.setString(2, lname); 
		pmt.setString(3, regno);
		pmt.setString(4, student_class);
		pmt.setString(5, age);
		pmt.setString(6, old_regno);
		pmt.execute();
		String query2 = "UPDATE marks SET student_name = '"+fname+" "+lname+"',class = '"+student_class+"' WHERE registration_num = '"+old_regno+"'";
		System.out.println(query2);
		Statement smt = con.createStatement();
		smt.execute(query2);
	}
	
	//Return a table of students which takes filters of registration number, class and gender or return a table without filters
	public DefaultTableModel getRegTable(String regno, String student_class, String student_gender) throws Exception{
		Connection con = db.getConnection();
		
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("First_name");
        dm.addColumn("Last_name");
        dm.addColumn("Reg_no");
        dm.addColumn("Gender");
        dm.addColumn("Class");
        dm.addColumn("Age");
        String sql = "";
        if(!(regno.equals(""))) {
        sql = "SELECT * FROM students WHERE registration_num='"+regno+"'";
        }else if(!(student_class.equals(""))) {
        sql = "SELECT * FROM students WHERE current_class='"+student_class+"'";	
        }else if(!(student_gender.equals(""))) {
        sql = "SELECT * FROM students WHERE gender='"+student_gender+"'";		
        }
        else {
        sql = "SELECT * FROM students";	
        }
        PreparedStatement s = con.prepareStatement(sql);
        ResultSet rs = s.executeQuery(sql);
        
        while(rs.next()) {
        	String id = rs.getString(1);
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String registration_num = rs.getString("registration_num");
            String gender = rs.getString("gender");
            String current_class = rs.getString("current_class");
            String age = rs.getString("age");
            
            dm.addRow(new String[]{first_name, last_name, registration_num, gender, current_class,age});
        }
        
        return dm;
	}
	

	@Override
	public DefaultTableModel genderTable(String gen) throws Exception {
		Connection con = db.getConnection();
		
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("First_name");
        dm.addColumn("Last_name");
        dm.addColumn("Reg_no");
        dm.addColumn("Gender");
        dm.addColumn("Class");
        dm.addColumn("Age");
        
        String sql = "SELECT * FROM students WHERE gender='"+gen+"'";
        
        PreparedStatement s = con.prepareStatement(sql);
        ResultSet rs = s.executeQuery(sql);
        
        while(rs.next()) {
        	String id = rs.getString(1);
            String first_name = rs.getString(2);
            String last_name = rs.getString(3);
            String registration_num = rs.getString(4);
            String gender = rs.getString(5);
            String current_class = rs.getString(6);
            String age = rs.getString(7);
            
            dm.addRow(new String[]{id, first_name, last_name, registration_num, gender, current_class, age});
        }
        
        return dm;

	}
	
	
	public DefaultTableModel resultsTable(String regno, String class_student, String name_student, String student_grade) throws Exception{
		Connection con = db.getConnection();
		String class_taught = "";
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Registration_num");
        dm.addColumn("Student_name");
        dm.addColumn(StudentManager.subject);
        dm.addColumn("Class");
        dm.addColumn("Grade");
        String grade_name = getGradeColumn(StudentManager.subject);
        String sql = "";
        String sql1 = "SELECT classes FROM teachers WHERE email='"+StudentManager.email+"'";
        
    	Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery(sql1);
        while(rs.next()) {
        	class_taught = rs.getString(1);
        }        
        System.out.println(class_taught);
        //Array of classes taught by a teacher
        String[] names = class_taught.split(",");
        StudentManager.classes_taught = names;
        String other = "";
        //Create query for filtering marks for only subjects that the logged in teacher teaches
        for(int a =0; a < names.length; a++) {
        	if(a == 0) {
        		other += "class = '"+names[a]+"' ";
        	}
        	else if(a == (names.length - 1)) {
        		other += "OR class = '"+names[a]+"'";
        	}else {
        		other += "OR class = '"+names[a]+"' ";
        	}
        }
        
        System.out.println(other);
        System.out.println(names);
        //Change SQL query basing on the filter required
        if(regno.equals("") && class_student.equals("") && name_student.equals("") && student_grade.equals("")) {
        sql = "SELECT registration_num, student_name,"+StudentManager.subject+","+grade_name+",class FROM marks WHERE "+other;
        }else if(!(regno.equals(""))) {
        	sql = "SELECT registration_num, student_name,"+StudentManager.subject+","+grade_name+",class FROM marks WHERE registration_num = '"+regno+"' AND "+other;	
        }else if(!(class_student.equals(""))) {
        	sql = "SELECT registration_num, student_name,"+StudentManager.subject+","+grade_name+",class FROM marks WHERE class = '"+class_student+"'AND "+other;	
        }else if(!(name_student.equals(""))) {
        	sql = "SELECT registration_num, student_name,"+StudentManager.subject+","+grade_name+",class FROM marks WHERE student_name = '"+name_student+"'AND "+other;	
        }else if(!(student_grade.equals(""))) {
        	sql = "SELECT registration_num, student_name,"+StudentManager.subject+","+grade_name+",class FROM marks WHERE "+getGradeColumn(StudentManager.subject)+" = "+student_grade+"AND "+other;	
        }
        System.out.println(sql);
        PreparedStatement s = con.prepareStatement(sql); 
        ResultSet rs1 = s.executeQuery(sql);
        while(rs1.next()) {
//        	String id = rs1.getString(1);
            String registration_num = rs1.getString(1);
            String student_name = rs1.getString(2);
            String subject = rs1.getString(StudentManager.subject);
            String student_class = rs1.getString("class");
            String grade = rs1.getString(grade_name);

            System.out.println(registration_num);
            System.out.println(student_class);
            System.out.println(grade);
            dm.addRow(new String[]{registration_num, student_name, subject, student_class ,grade});
        }
        
        return dm;
        
	}
	
	public void SetMarks(String regno, int mark) throws Exception{
		String df = "UPDATE marks SET "+subject+"= ?,grade = ? WHERE registration_num = ?";
		String query = "UPDATE marks SET "+StudentManager.subject+"=?,grade = ? WHERE registration_num = ?";
	}
	 
	@Override
	public DefaultTableModel ageTable(int age) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public DefaultTableModel gradeTable(String grade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DefaultTableModel StudentMarks(String regno) throws Exception{
		Connection con = db.getConnection();
		
        DefaultTableModel dm = new DefaultTableModel();
//        dm.addColumn("Name");
        dm.addColumn("Science");
        dm.addColumn("Grade");
        dm.addColumn("SST");
        dm.addColumn("Grade");
        dm.addColumn("Math");
        dm.addColumn("Grade");
        dm.addColumn("English");
        dm.addColumn("Grade");

        
        String sql = "SELECT * FROM marks WHERE registration_num='"+regno+"'";
        
        PreparedStatement s = con.prepareStatement(sql);
        ResultSet rs = s.executeQuery(sql);
        
        while(rs.next()) {

            String student_name = rs.getString("student_name");
            String student_class = rs.getString("class");
            String science = rs.getString("Science");
            String sst = rs.getString("SST");
            String math = rs.getString("Math");
            String english = rs.getString("English");
            String grade1 = rs.getString("grade1");
            String grade2 = rs.getString("grade2");
            String grade3 = rs.getString("grade3");
            String grade4 = rs.getString("grade4");
            StudentManager.aggregate = rs.getInt("aggregate");
            dm.addRow(new String[]{science, grade1 ,sst ,grade2 ,math ,grade3 ,english , grade4});
        }
        
        return dm;
	}
	
	public void changePassword(String new_password) {
		
	}

}
