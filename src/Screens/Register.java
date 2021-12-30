package Screens;

import java.io.*;
import java.util.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WinBuilder.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.*;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField fnameTextField;
	private JTextField emailtextField;
	private JPasswordField passwordField;
	private JPasswordField confirmpasswordField;
	private JFrame error;
	private JCheckBox class_1,class_2,class_3,class_4,class_5,class_6,class_7;  
	private JComboBox comboBox ;
	private JTextField lnameTextField;
	private JLabel lname_label;
	private JButton backButton;

	public String[] get_classes(JCheckBox a, JCheckBox b, JCheckBox c, JCheckBox d, JCheckBox e, JCheckBox f, JCheckBox g) {
		String [] l = {};
		List<String> arrList = new ArrayList<String>(Arrays.asList(l));
		
		if(a.isSelected()) {
			arrList.add("P1");
		}if(b.isSelected()) {
			arrList.add("P2");
		}if(c.isSelected()) {
			arrList.add("P3");
		}if(d.isSelected()) {
			arrList.add("P4");
		}if(e.isSelected()) {
			arrList.add("P5");
		}if(f.isSelected()) {
			arrList.add("P6");
		}if(g.isSelected()) {
			arrList.add("P7");
		}

		String [] arr = arrList.toArray(l);
		return arr;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ToolBar.floatingBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setBounds(184, 12, 110, 15);
		contentPane.add(lblRegister);
		
		// username
		JLabel fname_label = new JLabel("First name");
		fname_label.setBounds(6, 39, 92, 15);
		contentPane.add(fname_label);
		
		fnameTextField = new JTextField();
		fnameTextField.setBounds(127, 36, 114, 19);
		contentPane.add(fnameTextField);
		fnameTextField.setColumns(10);
		
		// email
		JLabel emaillabel = new JLabel("Email");
		emaillabel.setBounds(6, 88, 70, 15);
		contentPane.add(emaillabel);
		
		emailtextField = new JTextField();
		emailtextField.setBounds(127, 84, 114, 19);
		contentPane.add(emailtextField);
		emailtextField.setColumns(10);
		
		// password
		JLabel passwordlabel = new JLabel("Password");
		passwordlabel.setBounds(6, 111, 70, 15);
		contentPane.add(passwordlabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 108, 114, 19);
		contentPane.add(passwordField);
		
		// confirm password
		JLabel confirmPasswordlabel = new JLabel("Confirm password");
		confirmPasswordlabel.setBounds(6, 138, 128, 15);
		contentPane.add(confirmPasswordlabel);
		
		confirmpasswordField = new JPasswordField();
		confirmpasswordField.setBounds(127, 132, 117, 19);
		contentPane.add(confirmpasswordField);
		 
		JButton registerbtn = new JButton("Register");
		registerbtn.setBounds(46, 227, 117, 25);
		registerbtn.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.add(registerbtn);
		
		JLabel lblNewLabel = new JLabel("Classes taught");
		lblNewLabel.setBounds(279, 36, 111, 16);
		contentPane.add(lblNewLabel);
		
		class_1 = new JCheckBox("P1");
		class_1.setBounds(277, 52, 97, 18);
		class_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		contentPane.add(class_1);
		
		class_3 = new JCheckBox("P3");
		class_3.setBounds(277, 87, 128, 20);
		class_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		contentPane.add(class_3);
		
		class_2 = new JCheckBox("P2");
		class_2.setBounds(277, 68, 128, 19);
		class_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		contentPane.add(class_2);
		
		class_4 = new JCheckBox("P4");
		class_4.setBounds(277, 105, 45, 19);
		class_4.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		contentPane.add(class_4);
		
		class_5 = new JCheckBox("P5");
		class_5.setBounds(277, 125, 128, 19);
		class_5.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		contentPane.add(class_5);
		
		class_6 = new JCheckBox("P6");
		class_6.setBounds(277, 143, 128, 23);
		class_6.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		contentPane.add(class_6);
		
		class_7 = new JCheckBox("P7");
		class_7.setBounds(277, 164, 128, 23);
		class_7.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		contentPane.add(class_7);
		
		JLabel lblNewLabel_1 = new JLabel("Select subject");
		lblNewLabel_1.setBounds(6, 167, 92, 16);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SST", "Science", "Math", "English"}));
//		comboBox.setSelectedIndex(3);
		comboBox.setBounds(127, 163, 114, 27);
		contentPane.add(comboBox);
		
		lnameTextField = new JTextField();
		lnameTextField.setBounds(127, 60, 114, 19);
		contentPane.add(lnameTextField);
		lnameTextField.setColumns(10);
		
		lname_label = new JLabel("Last name");
		lname_label.setBounds(6, 66, 92, 15);
		contentPane.add(lname_label);
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close registration page
				Home home = new Home();
				home.setVisible(true);	
			}
		});
		backButton.setBounds(257, 225, 117, 29);
		contentPane.add(backButton);
		
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Boolean check = false;
					String fname = fnameTextField.getText().toString();
					String lname = lnameTextField.getText().toString();
					String email = emailtextField.getText().toString();
					String password1 = passwordField.getText().toString();
					String password2 = confirmpasswordField.getText().toString();
					String value = comboBox.getSelectedItem().toString();
					error = new JFrame();
					if(class_1.isSelected() == false && class_2.isSelected() == false && class_3.isSelected() == false && class_4.isSelected() == false && class_5.isSelected() == false && class_6.isSelected() == false && class_7.isSelected() == false) {
						check = true;
					}

					System.out.println(password1.length());
					if((fname.length() <= 1) ||(lname.length() <= 1) || (email.length() <= 1) || (password1.length() <= 1) || (password2.length() <= 1) || check) {
//						JOptionPane.showMessageDialog(temporaryLostComponent, this, "Passwords do not match", defaultCloseOperation);
						
						JOptionPane.showMessageDialog(error,"Please provide all the fields","Error",JOptionPane.WARNING_MESSAGE);
						System.out.println("Please provide all fields");
					}else {
						String [] c = get_classes(class_1, class_2, class_3, class_4, class_5, class_6, class_7);
						String classes = "";
						for(int a = 0; a < c.length;a++) {
							classes += ""+c[a]+"";
							System.out.print(c[a]);
							if(a != c.length - 1) {
								classes += ",";
								System.out.print(",");
							}else {
								classes += "";
					
							}
						}
						for(String a: c) {
							System.out.println(a);
						}
						System.out.println(classes);
					if (c.length >= 3) {
						JOptionPane.showMessageDialog(error,"You can not teach more than 3 subjects","Error",JOptionPane.WARNING_MESSAGE);
					}else if (password1.equals(password2)) {
						
						Teacher t = new Teacher(fname, lname, email, value, password1, classes);
						

						StudentManager b = new StudentManager(); 

						if(b.register_teachers(t) == "exists") {
							JOptionPane.showMessageDialog(error,"Record already exists","Error",JOptionPane.WARNING_MESSAGE);
							fnameTextField.setText("");
							lnameTextField.setText("");
							emailtextField.setText("");
							passwordField.setText("");
							confirmpasswordField.setText("");
						}else {
							b.register_teachers(t);
							dispose(); // close registration page
							Login login = new Login();
							login.setVisible(true);	
						}
					} else {
						//JOptionPane.showMessageDialog(this, "Passwords do not match");
						JOptionPane.showMessageDialog(error,"Your passwords do not match","Error",JOptionPane.WARNING_MESSAGE);
						System.out.println("Passwords do not match");
						passwordField.setText("");
						confirmpasswordField.setText("");
					}
					
				}
	
					
				} catch(Exception ex) {
					System.out.println("Exception : "+ex.getMessage());
				}
			}
		});
	}
}
