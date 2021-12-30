package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WinBuilder.StudentManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login extends JFrame {
	
	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField passwordtextField;
	private JPasswordField passwordField;
	private JFrame error;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}
	

	/**
	 * Create frame
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN AS TEACHER");
		lblLogin.setBounds(154, 20, 147, 15);
		contentPane.add(lblLogin);
		
		// username
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(74, 52, 98, 15);
		contentPane.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(187, 50, 114, 19);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		// password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(74, 93, 85, 15);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 91, 114, 19);
		contentPane.add(passwordField);

		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.GREEN); 
		btnLogin.setBounds(87, 144, 85, 25);
		contentPane.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String email = emailTextField.getText().toString();
					String password = passwordField.getText().toString();
					error = new JFrame();
	
					StudentManager b = new StudentManager(); 
					
					b.teacher_login(email, password);
					if(b.teacher_login(email, password) == true) {
						dispose(); // close registration page
						TeacherHome home = new TeacherHome();
						home.name = b.teacher;
						home.setVisible(true);	
						System.out.println("Successful login");
					}else {
						//When user enters wrong credentials
						JOptionPane.showMessageDialog(error,"Invalid login credentials","Error",JOptionPane.WARNING_MESSAGE);
						System.out.println("Unsuccessful");
					}
					 
				} catch(Exception d) {
					System.out.println(d.getMessage());
				}
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(255, 144, 85, 25);
		contentPane.add(btnReset);
		
		JButton homeBtn = new JButton("Home");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close login page
				Home home = new Home();
				home.setVisible(true);	
			}
		});
		homeBtn.setBounds(174, 192, 98, 25);
		contentPane.add(homeBtn);
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emailTextField.setText("");
				passwordtextField.setText("");
			}
		});
		
		
	}
}
