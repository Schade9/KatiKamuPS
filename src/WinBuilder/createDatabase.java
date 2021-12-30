package WinBuilder;

import java.sql.*;
import java.security.*;

public class createDatabase {
	dbConnection db = new dbConnection();

	public void database() throws Exception {
		Connection con = db.getConnection();
		Statement stmt = con.createStatement();
		//Creating database 
		String sql1 = "CREATE DATABASE Students";
		stmt.executeUpdate(sql1);
	}
	 
	//Creates the different tables
	public void create_tables() throws Exception {
		Connection con = db.getConnection();
		Statement stmt = con.createStatement();
		
		//	Creating students table
		String sql2 = "CREATE TABLE students (id int AUTO_INCREMENT primary key, "
				+ "first_name varchar(255), "
				+ "last_name varchar(255), "
				+ "registration_num varchar(255) UNIQUE,"
				+ "gender varchar(255),"
				+ "current_class varchar(255),"
				+ "age int(20))";
		stmt.execute(sql2);
		
		//Creating administrator tables
		String sql3 = "CREATE TABLE admin (id int AUTO_INCREMENT primary key ,"
				+ "username varchar(255),"
				+ "password varchar(255))";
		stmt.execute(sql3);
		 
		//Creating a table for student's marks
		String sql4 = "CREATE TABLE marks (id int AUTO_INCREMENT primary key,"
				+ "registration_num varchar(255) UNIQUE,"
				+ "student_name varchar(255),"
				+ "class varchar(255)," 
				+ "Science VARCHAR(20) NULL DEFAULT '--',"
				+ "grade1 VARCHAR(10) NULL DEFAULT '--',"
				+ "SST VARCHAR(20) NULL DEFAULT '--',"
				+ "grade2 VARCHAR(10) NULL DEFAULT '--',"
				+ "Math VARCHAR(20) NULL DEFAULT '--',"
				+ "grade3 VARCHAR(10) NULL DEFAULT '--',"
				+ "English VARCHAR(20) NULL DEFAULT '--',"
				+ "grade4 VARCHAR(10) NULL DEFAULT '--',"
				+ "aggregate VARCHAR(3) NULL DEFAULT '--')";
		stmt.execute(sql4);
		 
		// Creating a table for teachers
		String sql5 = "CREATE TABLE teachers (id int AUTO_INCREMENT primary key,"
				+ "first_name varchar(255),"
				+ "last_name varchar(255),"
				+ "email varchar(255),"
				+ "password varchar(255),"
				+ "classes varchar(200),"
				+ "subjects varchar(255))";
//		
		stmt.execute(sql5);
		

	}
	public void create_timetables() throws Exception {
		Connection con = db.getConnection();
		Statement stmt = con.createStatement();
		String day1 = "INSERT INTO timetable1 (Days) VALUES ('Monday')";
		String day2 = "INSERT INTO timetable1 (Days) VALUES ('Tuesday')";
		String day3 = "INSERT INTO timetable1 (Days) VALUES ('Wednesday')";
		String day4 = "INSERT INTO timetable1 (Days) VALUES ('Thursday')";
		String day5 = "INSERT INTO timetable1 (Days) VALUES ('Friday')";
		
		stmt.execute(day1);
		stmt.execute(day2);
		stmt.execute(day3);
		stmt.execute(day4);
		stmt.execute(day5);
	}
	
	public void all_timetables() throws Exception{
		Connection con = db.getConnection();
		Statement stmt = con.createStatement();
		
		String sql = "CREATE TABLE timetable1 (id int AUTO_INCREMENT primary key,"
				+ "Days varchar(255),"
				+ "lesson1 varchar(255) NULL DEFAULT '--',"
				+ "lesson2 varchar(255) NULL DEFAULT '--',"
				+ "lesson3 varchar(255) NULL DEFAULT '--',"
				+ "lesson4 varchar(244) NULL DEFAULT '--')";
		
		stmt.execute(sql);
		
		String day1 = "INSERT INTO timetable1 (Days) VALUES ('Monday')";
		String day2 = "INSERT INTO timetable1 (Days) VALUES ('Tuesday')";
		String day3 = "INSERT INTO timetable1 (Days) VALUES ('Wednesday')";
		String day4 = "INSERT INTO timetable1 (Days) VALUES ('Thursday')";
		String day5 = "INSERT INTO timetable1 (Days) VALUES ('Friday')";
		
		stmt.execute(day1);
		stmt.execute(day2);
		stmt.execute(day3);
		stmt.execute(day4);
		stmt.execute(day5);
		
		String sql1 = "CREATE TABLE timetable2 (id int AUTO_INCREMENT primary key,"
				+ "Days varchar(255),"
				+ "lesson1 varchar(255) NULL DEFAULT '--',"
				+ "lesson2 varchar(255) NULL DEFAULT '--',"
				+ "lesson3 varchar(255) NULL DEFAULT '--',"
				+ "lesson4 varchar(244) NULL DEFAULT '--')";
		stmt.execute(sql1);
		
		String aday1 = "INSERT INTO timetable2 (Days) VALUES ('Monday')";
		String aday2 = "INSERT INTO timetable2 (Days) VALUES ('Tuesday')";
		String aday3 = "INSERT INTO timetable2 (Days) VALUES ('Wednesday')";
		String aday4 = "INSERT INTO timetable2 (Days) VALUES ('Thursday')";
		String aday5 = "INSERT INTO timetable2 (Days) VALUES ('Friday')";
		
		stmt.execute(aday1);
		stmt.execute(aday2);
		stmt.execute(aday3);
		stmt.execute(aday4);
		stmt.execute(aday5);
		
		String sql2 = "CREATE TABLE timetable3 (id int AUTO_INCREMENT primary key,"
				+ "Days varchar(255),"
				+ "lesson1 varchar(255) NULL DEFAULT '--',"
				+ "lesson2 varchar(255) NULL DEFAULT '--',"
				+ "lesson3 varchar(255) NULL DEFAULT '--',"
				+ "lesson4 varchar(244) NULL DEFAULT '--')";
		stmt.execute(sql2);
		
		String bday1 = "INSERT INTO timetable3 (Days) VALUES ('Monday')";
		String bday2 = "INSERT INTO timetable3 (Days) VALUES ('Tuesday')";
		String bday3 = "INSERT INTO timetable3 (Days) VALUES ('Wednesday')";
		String bday4 = "INSERT INTO timetable3 (Days) VALUES ('Thursday')";
		String bday5 = "INSERT INTO timetable3 (Days) VALUES ('Friday')";
		
		stmt.execute(bday1);
		stmt.execute(bday2);
		stmt.execute(bday3);
		stmt.execute(bday4);
		stmt.execute(bday5);
		
		String sql3 = "CREATE TABLE timetable4 (id int AUTO_INCREMENT primary key,"
				+ "Days varchar(255),"
				+ "lesson1 varchar(255) NULL DEFAULT '--',"
				+ "lesson2 varchar(255) NULL DEFAULT '--',"
				+ "lesson3 varchar(255) NULL DEFAULT '--',"
				+ "lesson4 varchar(244) NULL DEFAULT '--')";
		stmt.execute(sql3);
		
		String cday1 = "INSERT INTO timetable4 (Days) VALUES ('Monday')";
		String cday2 = "INSERT INTO timetable4 (Days) VALUES ('Tuesday')";
		String cday3 = "INSERT INTO timetable4 (Days) VALUES ('Wednesday')";
		String cday4 = "INSERT INTO timetable4 (Days) VALUES ('Thursday')";
		String cday5 = "INSERT INTO timetable4 (Days) VALUES ('Friday')";
		
		stmt.execute(cday1);
		stmt.execute(cday2);
		stmt.execute(cday3);
		stmt.execute(cday4);
		stmt.execute(cday5);
		
		String sql4 = "CREATE TABLE timetable5 (id int AUTO_INCREMENT primary key,"
				+ "Days varchar(255),"
				+ "lesson1 varchar(255) NULL DEFAULT '--',"
				+ "lesson2 varchar(255) NULL DEFAULT '--',"
				+ "lesson3 varchar(255) NULL DEFAULT '--',"
				+ "lesson4 varchar(244) NULL DEFAULT '--')";
		stmt.execute(sql4);
		
		String dday1 = "INSERT INTO timetable5 (Days) VALUES ('Monday')";
		String dday2 = "INSERT INTO timetable5 (Days) VALUES ('Tuesday')";
		String dday3 = "INSERT INTO timetable5 (Days) VALUES ('Wednesday')";
		String dday4 = "INSERT INTO timetable5 (Days) VALUES ('Thursday')";
		String dday5 = "INSERT INTO timetable5 (Days) VALUES ('Friday')";
		
		stmt.execute(dday1);
		stmt.execute(dday2);
		stmt.execute(dday3);
		stmt.execute(dday4);
		stmt.execute(dday5);
		
		String sql5 = "CREATE TABLE timetable6 (id int AUTO_INCREMENT primary key,"
				+ "Days varchar(255),"
				+ "lesson1 varchar(255) NULL DEFAULT '--',"
				+ "lesson2 varchar(255) NULL DEFAULT '--',"
				+ "lesson3 varchar(255) NULL DEFAULT '--',"
				+ "lesson4 varchar(244) NULL DEFAULT '--')";
		stmt.execute(sql5);
		
		String eday1 = "INSERT INTO timetable6 (Days) VALUES ('Monday')";
		String eday2 = "INSERT INTO timetable6 (Days) VALUES ('Tuesday')";
		String eday3 = "INSERT INTO timetable6 (Days) VALUES ('Wednesday')";
		String eday4 = "INSERT INTO timetable6 (Days) VALUES ('Thursday')";
		String eday5 = "INSERT INTO timetable6 (Days) VALUES ('Friday')";
		
		stmt.execute(eday1);
		stmt.execute(eday2);
		stmt.execute(eday3);
		stmt.execute(eday4);
		stmt.execute(eday5);
		
		String sql6 = "CREATE TABLE timetable7 (id int AUTO_INCREMENT primary key,"
				+ "Days varchar(255),"
				+ "lesson1 varchar(255) NULL DEFAULT '--',"
				+ "lesson2 varchar(255) NULL DEFAULT '--',"
				+ "lesson3 varchar(255) NULL DEFAULT '--',"
				+ "lesson4 varchar(244) NULL DEFAULT '--')";
		stmt.execute(sql6);
		
		String fday1 = "INSERT INTO timetable7 (Days) VALUES ('Monday')";
		String fday2 = "INSERT INTO timetable7 (Days) VALUES ('Tuesday')";
		String fday3 = "INSERT INTO timetable7 (Days) VALUES ('Wednesday')";
		String fday4 = "INSERT INTO timetable7 (Days) VALUES ('Thursday')";
		String fday5 = "INSERT INTO timetable7 (Days) VALUES ('Friday')";
		
		stmt.execute(fday1);
		stmt.execute(fday2);
		stmt.execute(fday3);
		stmt.execute(fday4);
		stmt.execute(fday5);
		
	}
	public void create_admin() throws Exception {
		Connection con = db.getConnection();
		Statement stmt = con.createStatement();
		
		String sql1 = "CREATE TABLE admin (id INT AUTO_INCREMENT primary key,"
				+ "username VARCHAR(255),"
				+ "password VARCHAR(255))";
		stmt.execute(sql1);
		
		String sql = "INSERT INTO admin VALUES (1,'Trevor','admin123')";
		stmt.execute(sql);
	}
}
