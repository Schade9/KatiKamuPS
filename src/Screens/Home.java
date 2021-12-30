package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Home extends JFrame {

	private JPanel contentPane;
	private JButton regButton, loginButton;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(183, 31, 78, 16);
		contentPane.add(lblNewLabel);
		
		regButton = new JButton("Register");
		regButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close registration page
				Register register = new Register();
				register.setVisible(true);	
			}
		}); 
		regButton.setBounds(144, 141, 117, 29);
		contentPane.add(regButton);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close registration page
				Login login = new Login();
				login.setVisible(true);	
			}
		});
		loginButton.setBounds(266, 171, 117, 29);
		loginButton.setVisible(false);
		contentPane.add(loginButton);
		
		lblNewLabel_1 = new JLabel("Login as : ");
		lblNewLabel_1.setBounds(73, 87, 88, 16);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice = comboBox.getSelectedItem().toString();
				if(choice == "Teacher") {
					dispose(); // close home page
					Login login = new Login();
					login.setVisible(true);	
				}else if(choice == "Student") {
					dispose(); // close home page
					StudentLogin login = new StudentLogin();
					login.setVisible(true);	
				}else if(choice == "Admin") {
					dispose(); // close home page
					AdminLogin login = new AdminLogin();
					login.setVisible(true);	
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Teacher", "Student", "Admin"}));
		comboBox.setBounds(209, 83, 106, 27);
		contentPane.add(comboBox);
		

	}
}
