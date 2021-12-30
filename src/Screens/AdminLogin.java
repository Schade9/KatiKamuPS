package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WinBuilder.AdminManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	AdminManager manager = new AdminManager();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setBounds(100, 71, 83, 16);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(219, 66, 130, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password : ");
		passwordLabel.setBounds(100, 119, 83, 16);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(219, 114, 130, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText().toString();
				String password = passwordField.getText().toString();
				try {
				if(manager.login(username, password)) {
					dispose();
					AdminTimeTable table = new AdminTimeTable();
					table.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null,"Invalid credentials.","Error",JOptionPane.WARNING_MESSAGE);
				}
				}catch(Exception e1) {
					System.out.println("Exception : "+e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(149, 183, 117, 29);
		contentPane.add(btnNewButton);
	}
}
