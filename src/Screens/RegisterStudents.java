package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WinBuilder.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
public class RegisterStudents extends JFrame {

	private JPanel contentPane;
	private final JLabel lblRegisterStudent = new JLabel("REGISTER STUDENT");
	private JTextField firstNametextField;
	private JTextField lastNametextField;
	private JTextField regNumbertextField;
	private JTextField ageTextField;
	private JComboBox genderBox, classes;
	private JFrame error;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterStudents frame = new RegisterStudents();
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
	public RegisterStudents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		lblRegisterStudent.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRegisterStudent.setBounds(5, 5, 440, 15);
		lblRegisterStudent.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRegisterStudent);
		
		// first name
		JLabel first_name = new JLabel("First Name");
		first_name.setBounds(35, 32, 95, 15);
		contentPane.add(first_name);
		
		firstNametextField = new JTextField();
		firstNametextField.setBounds(148, 32, 114, 19);
		contentPane.add(firstNametextField);
		firstNametextField.setColumns(10);
		
		// last name
		JLabel last_name = new JLabel("Last Name");
		last_name.setBounds(35, 56, 95, 15);
		contentPane.add(last_name);
		 
		lastNametextField = new JTextField();
		lastNametextField.setBounds(148, 56, 114, 19);
		contentPane.add(lastNametextField);
		lastNametextField.setColumns(10);
		
		// registration number
		JLabel reg_number = new JLabel("Reg number");
		reg_number.setBounds(35, 80, 114, 15);
		contentPane.add(reg_number);
		
		regNumbertextField = new JTextField();
		regNumbertextField.setBounds(148, 80, 114, 19);
		contentPane.add(regNumbertextField);
		regNumbertextField.setColumns(10);
		
		// gender
		JLabel gender = new JLabel("Gender");
		gender.setBounds(35, 104, 70, 15);
		contentPane.add(gender);
		
		genderBox = new JComboBox();
		genderBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		genderBox.setBounds(148, 104, 114, 24);
		contentPane.add(genderBox);
		
		// age
		JLabel age = new JLabel("Age");
		age.setBounds(35, 130, 70, 15);
		contentPane.add(age);
		
		ageTextField = new JTextField();
		ageTextField.setBounds(148, 130, 114, 19);
		contentPane.add(ageTextField);
		ageTextField.setColumns(10);
		
		// class
		JLabel stclass = new JLabel("Class");
		stclass.setBounds(35, 157, 70, 15);
		contentPane.add(stclass);
		
		// buttons
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(Color.GREEN);
		btnSubmit.setBounds(92, 202, 83, 25);
		contentPane.add(btnSubmit);
		
		// ComboBox for classes 
		classes = new JComboBox();
		classes.setModel(new DefaultComboBoxModel(new String[] {"P1", "P2", "P3", "P4", "P5", "P6", "P7"}));
		classes.setBounds(148, 152, 114, 27);
		contentPane.add(classes);
		 
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNametextField.getText().toString();
				String lastName = lastNametextField.getText().toString();
				String regNumber = regNumbertextField.getText().toString();
				String gender = String.valueOf(genderBox.getSelectedItem());
				String age = ageTextField.getText().toString(); 
//				String subject = String.valueOf(subjectBox.getSelectedItem());
//				String studentClass = stclass.getText().toString();
				String student_class = classes.getSelectedItem().toString();
				try {	
					if (Integer.parseInt(age) > 0 && Integer.parseInt(age) < 17) {
					Student student = new Student(firstName, lastName, regNumber, gender, student_class, Integer.parseInt(age));
					StudentManager manager = new StudentManager(); 
					if(manager.register(student) == "exists") {
						JOptionPane.showMessageDialog(error,"Student already exists.","Error",JOptionPane.WARNING_MESSAGE);
					}else {
					manager.register(student);
//					String getMessage = JOptionPane.showInputDialog(error,"Enter your message");
					JOptionPane.showMessageDialog(error,"Student registered.");
					}
					}else {
						JOptionPane.showMessageDialog(error,"Please provide a valid age.","Error",JOptionPane.WARNING_MESSAGE);
					}
//					dispose(); // close register Students
//					RegisteredStudents rs = new RegisteredStudents();
//					rs.setVisible(true);
					 
				} catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(236, 202, 83, 25);
		contentPane.add(btnBack);
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close register Students
				TeacherHome home = new TeacherHome();
			
				home.setVisible(true);
			}
		});	
	}
}
