package Screens;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.sql.*;

import WinBuilder.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class showStudents extends JFrame {
	
	private JFrame error,results;
	private JPanel contentPane;
	private DefaultTableModel studentTable;
	private JTable table, resultTable, currentTable;
	private JScrollPane pane, pane2;
	private JTextField name, fname, regno;
	private JTextField txtSearch;
	private JComboBox comboBox, selectClassBox, genderBox;
	private JButton btnNewButton;
	private Boolean showSearch = true, showGender = false;

	/**
	 * Launch the application.
	 */
	StudentManager b = new StudentManager();
	private JButton backBtn;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showStudents frame = new showStudents();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("Exception : "+ e.getMessage());
				}
			}
		});
	} 
	private void tableClicked(MouseEvent evt, JTable search_table){	
		String fname = search_table.getValueAt(search_table.getSelectedRow(), 0).toString();
		name.setText(fname);
		System.out.println(fname);
	}
	
	private JTable showTable(DefaultTableModel new_table) {
			resultTable = new JTable(new_table);
			System.out.println(new_table.getRowCount());
			resultTable.addMouseListener(new MouseAdapter() {
			  	   
			  	     public void mousePressed(MouseEvent e) {
			  	     		tableClicked(e, resultTable);
			  	     	} 
			  	     });
			contentPane.setLayout(null);
			resultTable.setShowGrid(true);
			resultTable.setShowHorizontalLines(true);
			resultTable.setShowVerticalLines(true);
			resultTable.setGridColor(Color.BLACK);
			
			
			pane = new JScrollPane(resultTable);
			pane.setBounds(6, 43, 440, 158);

			 contentPane.add(pane);
			 
			 name = new JTextField();
			 name.setBounds(18, 213, 130, 26);
			 contentPane.add(name);
			 name.setColumns(10);
			 
			 JButton saveButton = new JButton("Save");
			 saveButton.setBounds(170, 213, 117, 29);
			 saveButton.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		System.out.println(name.getText().toString());

			 	}
			 });
			 contentPane.add(saveButton);
			 
			 txtSearch = new JTextField();
			 txtSearch.setBounds(18, 6, 130, 26);
			 txtSearch.setText("Search ...");
			 txtSearch.setVisible(showSearch);
			 contentPane.add(txtSearch);
			 txtSearch.setColumns(10);
	 		 
			 comboBox = new JComboBox();
			 comboBox.setBounds(155, 7, 117, 27);
			 comboBox.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
					String option = comboBox.getSelectedItem().toString();
					if(option == "Gender") {
				 		showGender = true;
						genderBox.setVisible(true);
						txtSearch.setVisible(false);
						selectClassBox.setVisible(false);
					}else if(option == "Class") {
						selectClassBox.setVisible(true);
						genderBox.setVisible(false);
						txtSearch.setVisible(false);
					}
					else {
						genderBox.setVisible(false);
						txtSearch.setVisible(true);
						selectClassBox.setVisible(false);
					}
			 	}
			 });

			 comboBox.setModel(new DefaultComboBoxModel(new String[] {"Reg number", "Gender", "Age", "Class"}));
			 contentPane.add(comboBox);
			 
			 genderBox = new JComboBox();
			 genderBox.setBounds(18, 7, 130, 27);
			 genderBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
			 genderBox.setVisible(showGender);
			 contentPane.add(genderBox);
			 
			 btnNewButton = new JButton("Search");
			 btnNewButton.setBounds(270, 6, 94, 29);
			 btnNewButton.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		String option = comboBox.getSelectedItem().toString();
			 		if(option == "Reg number") {
			 		String search = txtSearch.getText().toString();
			 		System.out.println(search);
			 		if(search.length() <= 6) {
						JOptionPane.showMessageDialog(error,"Please provide a valid search value","Error",JOptionPane.WARNING_MESSAGE);
			 		}else {
			 			try {
			 				if(b.getRegTable(search,"","").getRowCount() == 0) {
			 					JOptionPane.showMessageDialog(error,"No search values","Error",JOptionPane.WARNING_MESSAGE);
			 				}else {
			 				showTable(b.getRegTable(search,"",""));
			 				}
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							System.out.println("Exception caught" + e1.getMessage());
						}
			 		}
			 	}else if(option == "Gender") {
			 		String gender_opt = genderBox.getSelectedItem().toString();
			 		System.out.println(gender_opt);
			 		try {
						if(b.genderTable(gender_opt).getRowCount() == 0) {
							JOptionPane.showMessageDialog(error,"No search values","Error",JOptionPane.WARNING_MESSAGE);
						}else {
//							table.setVisible(false);
							dispose();
							showStudents show = new showStudents("","",gender_opt);
							show.setVisible(true);

						} 
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Exception : "+e1.getMessage());
					}
			 	}else if(option == "Class") {
			 		try {
			 		String class_opt = selectClassBox.getSelectedItem().toString();
					if(b.getRegTable("", class_opt,"").getRowCount() == 0) {
						JOptionPane.showMessageDialog(error,"No search values","Error",JOptionPane.WARNING_MESSAGE);
					}else {
//						table.setVisible(false);
						dispose();
						showStudents show = new showStudents("",class_opt,"");
						show.setVisible(true);

					} 
			 		}catch(Exception e1) {
			 			System.out.println("Exception : " +e1.getMessage());
			 		}
			 	}
			 	}
			 });
			 contentPane.add(btnNewButton);
			 
			 backBtn = new JButton("< Back");
			 backBtn.setBounds(325, 213, 94, 29);
			 backBtn.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
					dispose(); // close registration page
					TeacherHome home = new TeacherHome();
					home.setVisible(true);	
			 	}
			 });
			 contentPane.add(backBtn);
			 
			 selectClassBox = new JComboBox();
			 selectClassBox.setBounds(18, 6, 130, 26);
			 selectClassBox.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 	}
			 });
			 selectClassBox.setModel(new DefaultComboBoxModel(StudentManager.classes_taught));
			 selectClassBox.setVisible(false);
			 contentPane.add(selectClassBox);
			 
			 btnNewButton_1 = new JButton("refresh");
			 btnNewButton_1.setBounds(367, 7, 79, 26);
			 btnNewButton_1.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 	try {
					dispose();
					showStudents show = new showStudents();
					show.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Exception : "+e1.getMessage());
				}
			 	}
			 });
			 btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			 contentPane.add(btnNewButton_1);
			 
			 return resultTable;
	}
	
	private void optionClicked(MouseEvent evt){				 		
		String option = comboBox.getSelectedItem().toString();
		System.out.println("Hello");
	}
	
	/**
	 * Create the frame.
	 */
	public showStudents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			dbConnection db = new dbConnection();
			Connection con = db.getConnection();
			
			String sql = "SELECT * FROM students";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String columns[] = { "First_name", "Last_name", "Reg_no", "Gender", "Class", "Age" };
			String data[][] = new String[7][6];
			
			int i = 0;
			while(rs.next()) {
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String regno = rs.getString("registration_num");
				String gender = rs.getString("gender");
				String current_class = rs.getString("current_class");
				int age = rs.getInt("age");
//				StudentManager.available_classes[i] = rs.getString("current_class");
				 data[i][0] = fname + "";
				 data[i][1] = lname + "";
				 data[i][2] = regno + "";
				 data[i][3] = gender + "";
				 data[i][4] = current_class + "";
				 data[i][5] = age + "";
				 i++;
			}
			
//			for(String i1: StudentManager.available_classes) {
//				if(!StudentManager.lesson.contains(i1)) {
//					StudentManager.lesson.add(i1);
//				}
//			}
			
	  		 studentTable = new DefaultTableModel(data, columns);
//	  	     table = new JTable(studentTable);
	  		 table = showTable(b.getRegTable("", "",""));
//				table.addMouseListener(new MouseAdapter() {
//				  	   
//			  	     public void mousePressed(MouseEvent e) {
//			  	     		tableClicked(e, table);
//			  	     	} 
//			  	     });
//	  	     table = new JTable(b.getRegTable("", "",""));
//	  	     table.addMouseListener(new MouseAdapter() {
//	  	     	@Override
//	  	     	public void mousePressed(MouseEvent e) {
//	  	     		tableClicked(e, table);
//	  	     	}  
//	  	     });
//	  	     table.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
//		 		TableColumn column = null;
//		 		for(int a = 0; a < 5; a++) {
//		 			column = table.getColumnModel().getColumn(a);
//		 			if(i == 2) {
//		 				column.setPreferredWidth(100);
//		 			}else {
//		 				column.setPreferredWidth(50);
//		 			}
//		 		}
//			 contentPane.setLayout(null);
//			 table.setShowGrid(true);
//			 table.setShowHorizontalLines(true);
//			 table.setShowVerticalLines(true);
//			 table.setGridColor(Color.BLACK);
//			 pane = new JScrollPane(table);
//			 pane.setBounds(6, 43, 440, 158);
//
//			 contentPane.add(pane);
			 
			
		}catch(Exception e) {
			System.out.println("Exception : "+e.getMessage());
		}

	}
	public showStudents(String regno, String student_class, String student_gender) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			showTable(b.getRegTable(regno, student_class,student_gender));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
