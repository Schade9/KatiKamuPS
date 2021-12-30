package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import WinBuilder.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegisterMarks extends JFrame {
	private JFrame error;
	private JPanel contentPane;
	private JTable table, resultTable;
	private JScrollPane pane, pane2;
	private JFormattedTextField marksField;
	private JTextField searchtxt;
	private String regno,marks;
	/**
	 * Launch the application.
	 */ 
	private void tableClicked(MouseEvent evt){	
		regno = resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString();
		marks = resultTable.getValueAt(resultTable.getSelectedRow(), 2).toString();
		searchtxt.setText(marks); 
		System.out.println(marks); 
	}
	StudentManager manager = new StudentManager();
	private JButton backBtn;
	private JTextField searchField;
	private JButton btnNewButton;
	private JButton refreshBtn;
	private JComboBox filterBox;
	private JComboBox classBox;

	
	private void showTable(DefaultTableModel table) {
		resultTable = new JTable(table);

		resultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableClicked(e); 
			}
			
		});
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		resultTable.setShowGrid(true);
		resultTable.setShowHorizontalLines(true);
		resultTable.setShowVerticalLines(true);
		resultTable.setGridColor(Color.BLACK);
		pane2 = new JScrollPane(resultTable);
	    pane2.setBounds(6, 43, 440, 158);

		contentPane.add(pane2);
		 
		 JLabel lblNewLabel = new JLabel("Enter marks");
		 lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		 lblNewLabel.setBounds(17, 225, 85, 16);
		 contentPane.add(lblNewLabel);
		 
		 searchtxt = new JTextField();
		 searchtxt.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 searchtxt.setBounds(104, 222, 49, 21);
		 contentPane.add(searchtxt);
		 searchtxt.setColumns(10);
		 
		 JButton saveBtn = new JButton("Save");
		 saveBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
//		 		String marks = marksField.getText();

		 		try {
		 		String mark1 = searchtxt.getText().toString();
		 		int new_mark = Integer.parseInt(mark1);
		 		System.out.println(marks);
		 		if(new_mark > 0 && new_mark <=100) {
		 			System.out.println("Valid");
		 			manager.update_marks(regno, StudentManager.subject, StudentManager.email, new_mark);
					dispose(); // close registration page
					RegisterMarks home = new RegisterMarks();
					home.setVisible(true);	
		 			
		 		}else {
		 			JOptionPane.showMessageDialog(error,"Please provide a valid mark","Error",JOptionPane.WARNING_MESSAGE);
		 		}
		 		}catch(NumberFormatException e1) {
		 			JOptionPane.showMessageDialog(error,"Please provide a number","Error",JOptionPane.WARNING_MESSAGE);
		 			System.out.println("Exception : "+ e1.getMessage());
		 		} 
		 		catch(Exception ex) {
		 			System.out.println("Exception : "+ ex.getMessage());
		 		}
		 	}
		 });
		 saveBtn.setBounds(169, 220, 117, 29);
		 contentPane.add(saveBtn);
		 
		 NumberFormat format = NumberFormat.getInstance();
		 NumberFormatter formatter = new NumberFormatter(format);
		 formatter.setValueClass(Integer.class);
		 formatter.setMinimum(1);
		 formatter.setMaximum(100);
//		 formatter.setAllowsInvalid(false);
//		 formatter.setCommitsOnValidEdit(false);

		 
		 
		 marksField = new JFormattedTextField(formatter);
		 //Pop up menu
//		 JOptionPane.showMessageDialog(null, field);
		 marksField.setBounds(330, 220, 49, 26);
		 marksField.setVisible(false);
		 contentPane.add(marksField);
		 
		 backBtn = new JButton("< Back ");
		 backBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
				dispose(); // close registration page
				TeacherHome home = new TeacherHome();
				home.setVisible(true);	
		 	}
		 });
		 backBtn.setBounds(300, 220, 95, 29);
		 contentPane.add(backBtn);
		 
		 searchField = new JTextField();
		 searchField.setBounds(17, 5, 130, 26);
		 contentPane.add(searchField);
		 searchField.setColumns(10);
		 
		 btnNewButton = new JButton("Search");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		String option = filterBox.getSelectedItem().toString();
		 		if(option == "Reg_num") {
		 		String search = searchField.getText().toString();
		 		System.out.println(search);
		 		if(search.length() <= 6) {
					JOptionPane.showMessageDialog(error,"Please provide a valid search value","Error",JOptionPane.WARNING_MESSAGE);
		 		}else {
		 			try {
		 				if(manager.resultsTable(search, "", "", "").getRowCount() == 0) {		 					
		 					JOptionPane.showMessageDialog(error,"No search values","Error",JOptionPane.WARNING_MESSAGE);
		 				}else {
							dispose();
							RegisterMarks show = new RegisterMarks(search, "", "", "");
							show.setVisible(true);
		 				}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Exception caught" + e1.getMessage());
					}
		 		}
		 	}else if(option == "Class") {
		 		String class_opt = classBox.getSelectedItem().toString();
		 		System.out.println(class_opt);
		 		try {
					if(manager.resultsTable("", class_opt, "", "").getRowCount() == 0) {
						JOptionPane.showMessageDialog(error,"No search values","Error",JOptionPane.WARNING_MESSAGE);
					}else {
//						table.setVisible(false);
						dispose();
						RegisterMarks show = new RegisterMarks("", class_opt, "", "");
						show.setVisible(true);

					} 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Exception : "+e1.getMessage());
				}
		 	}else if(option == "Name") {
		 		String search = searchField.getText().toString();
		 		if(search.length() <= 6) {
					JOptionPane.showMessageDialog(error,"Please provide a valid search value","Error",JOptionPane.WARNING_MESSAGE);
		 		}else {
		 			try {
		 				if(manager.resultsTable("","",search,"").getRowCount() == 0) {
		 					JOptionPane.showMessageDialog(error,"No search values","Error",JOptionPane.WARNING_MESSAGE);
		 				}else {
//		 				showTable(manager.getRegTable(search,"",""));
						dispose();
						RegisterMarks show = new RegisterMarks("", "", search, "");
						show.setVisible(true);

		 				}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Exception caught" + e1.getMessage());
					}
		 		}
		 	}
		 	}
		 });
		 btnNewButton.setBounds(254, 5, 95, 29);
		 contentPane.add(btnNewButton);
		 
		 refreshBtn = new JButton("Refresh");
		 refreshBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		dispose();
		 		RegisterMarks page = new RegisterMarks();
		 		page.setVisible(true);
		 	}
		 });
		 refreshBtn.setBounds(351, 5, 78, 29);
		 contentPane.add(refreshBtn);
		 
		 filterBox = new JComboBox();
		 filterBox.addActionListener(new ActionListener() {
	
			 	public void actionPerformed(ActionEvent e) {
					String option = filterBox.getSelectedItem().toString();
					if(option == "Reg_no") {

						classBox.setVisible(false);
						searchField.setVisible(true);
					}else if(option == "Class") {
						classBox.setVisible(true);
						searchField.setVisible(false);
					}else if(option == "Name") {
						classBox.setVisible(false);
						searchField.setVisible(true);
					}
					else {
						searchField.setVisible(true);
						classBox.setVisible(false);
					}
			 	}
		 
		 });
		 filterBox.setModel(new DefaultComboBoxModel(new String[] {"Reg_no", "Name", "Class"}));
		 filterBox.setBounds(158, 6, 85, 27);
		 contentPane.add(filterBox);
		 
		 classBox = new JComboBox();
		 classBox.setModel(new DefaultComboBoxModel(new String[] {"P1", "P3"}));
		 classBox.setBounds(17, 6, 130, 27);
		 classBox.setVisible(false);
		 contentPane.add(classBox);
}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMarks frame = new RegisterMarks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterMarks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			showTable(manager.resultsTable("", "", "", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public RegisterMarks(String regno, String student_class, String name, String student_grade) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			showTable(manager.resultsTable(regno, student_class, name, student_grade));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
