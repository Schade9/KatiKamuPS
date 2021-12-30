package WinBuilder;

public class Student {
	String firstName, lastName, registrationNumber, gender, current_class;
	int age;
	 
	public Student(String fname, String lname, String regno,String gen, String grade,int age) {
		this.firstName = fname;
		this.lastName = lname;
		this.registrationNumber = regno;
		this.gender = gen;
		this.current_class = grade;
		this.age = age;
		} 
}
