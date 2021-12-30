package timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

import WinBuilder.StudentManager;
import WinBuilder.dbConnection;


public class Timetable {
	public static String day_chosen = "Monday";
	dbConnection db = new dbConnection();
	
	//Return assigned index of a subject, needed for comboBoxes
	public int showIndex(String subject) {
		if(subject.equals("--")) {
			return 0;
		}
		else if(subject.equals("Science")) {
			return 1;
		}else if(subject.equals("SST")) {
			return 2;
		}else if(subject.equals("Math")) {
			return 3;
		}else if(subject.equals("English")) {
			return 4;
		}else {
			return 5;
		}
	}
	
	//Return the different names of tables in the database for the different classes
	public String getTable(String tclass) {
		String table = "";
        if(tclass == "P1" || tclass == ""){
        	table = "timetable1";
        }else if(tclass == "P2") {
        	table = "timetable2";	
        }else if((tclass == "P3")) {
        	table = "timetable3";	
        }else if(tclass.equals("P4")) {
        	table = "timetable4";	
        }else if(tclass.equals("P5")) {
        	table = "timetable5";	
        }else if(tclass.equals("P6")) {
        	table = "timetable6";	
        }else if(tclass.equals("P7")) {
        	table = "timetable7";	
        }
		return table;
	}
	
	//Create timetable to be displayed on theUI
	public DefaultTableModel getTimeTable(String tclass) throws Exception{
		Connection con = db.getConnection();
		String sql = "";
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Days");
        dm.addColumn("8:00 am-10:00 am");
        dm.addColumn("10:00 am-12:00 am");
        dm.addColumn("2:00 pm-3:00 pm");
        dm.addColumn("3:00 pm-4:00 pm");
        
        //Return different queries basing on the name of the class 
        if(tclass.equals("P1") || tclass.equals("")){
        sql = "SELECT * FROM timetable1";
        }else if(tclass.equals("P2")) {
        sql = "SELECT * FROM timetable2";	
        }else if(tclass.equals("P3")) {
        sql = "SELECT * FROM timetable3";	
        }else if(tclass.equals("P4")) {
        sql = "SELECT * FROM timetable4";
        }else if(tclass.equals("P5")) {
        sql = "SELECT * FROM timetable5";	
        }else if(tclass.equals("P6")) {
         sql = "SELECT * FROM timetable6";	
        }else if(tclass.equals("P7")) {
         sql = "SELECT * FROM timetable7";	
        }
        PreparedStatement s = con.prepareStatement(sql); 
        ResultSet rs1 = s.executeQuery(sql);
        while(rs1.next()) {
//        	String id = rs1.getString(1);
            String day = rs1.getString(2);
            String lesson1 = rs1.getString(3);
            String lesson2 = rs1.getString(4);
            String lesson3 = rs1.getString(5);
            String lesson4 = rs1.getString(6);

            System.out.println(lesson1);
            System.out.println(lesson2);
 
            dm.addRow(new String[]{day, lesson1, lesson2, lesson3, lesson4});
        }
        
        return dm;
        
	}
	public void updateTimeTable(String current_class,String day, String lesson1, String lesson2, String lesson3, String lesson4) throws Exception{
		Connection con = db.getConnection();
		String sql = "UPDATE "+getTable(current_class)+" SET lesson1 = ?, lesson2 = ?, lesson3 = ?, lesson4 = ? WHERE Days = ?";
		PreparedStatement pmt = con.prepareStatement(sql);
		pmt.setString(1, lesson1);
		pmt.setString(2, lesson2);
		pmt.setString(3, lesson3);
		pmt.setString(4, lesson4);
		pmt.setString(5, day);
		pmt.execute();
	}
	
	@SuppressWarnings("null")
	public int [] getIndex(String name, String student_class) throws Exception{
		String sql = "";
		int [] indexes = {1,2,3,4};
//		int i =0;
		Connection con = db.getConnection();
		if(student_class.equals("P1")) {
			sql = "SELECT * FROM timetable1 WHERE Days = '"+name+"'";
		}else if(student_class.equals("P2")) {
			sql = "SELECT * FROM timetable2 WHERE Days = '"+name+"'";
	        }else if((student_class == "P3")) {
	        sql = "SELECT * FROM timetable3 WHERE Days = '"+name+"'";	
	        }else if(student_class.equals("P4")) {
	       	sql = "SELECT * FROM timetable4 WHERE Days = '"+name+"'";
	        }else if(student_class.equals("P5")) {
	       	sql = "SELECT * FROM timetable5 WHERE Days = '"+name+"'";	
	        }else if(student_class.equals("P6")) {
	       	sql = "SELECT * FROM timetable6 WHERE Days = '"+name+"'";
	        }else if(student_class.equals("P7")) {
	       	sql = "SELECT * FROM timetable7 WHERE Days = '"+name+"'";
	        }
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.first()) {
			System.out.println(rs.getString("lesson1"));
			indexes[0] = showIndex(rs.getString("lesson1"));
			indexes[1] = showIndex(rs.getString("lesson2"));
			indexes[2] = showIndex(rs.getString("lesson3"));
			indexes[3] = showIndex(rs.getString("lesson4"));
		}
		System.out.println(indexes[2]);
		return indexes;
	}
}
