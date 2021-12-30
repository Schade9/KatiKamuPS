package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WinBuilder.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentLogin extends JFrame {

	private JPanel contentPane;
	private JTextField regNoTxt;
	
	StudentManager manager = new StudentManager();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
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
	public StudentLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login as student");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel.setBounds(160, 23, 114, 16);
		contentPane.add(lblNewLabel);
		
		regNoTxt = new JTextField();
		regNoTxt.setBounds(191, 88, 147, 29);
		contentPane.add(regNoTxt);
		regNoTxt.setColumns(10);
		
		JLabel regNoLabel = new JLabel("Registration no.");
		regNoLabel.setBounds(65, 93, 114, 16);
		contentPane.add(regNoLabel);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
 
				try {
					if(manager.student_login(regNoTxt.getText().toString())) {
						dispose(); // close registration page
						StudentHome home = new StudentHome();
						home.setVisible(true);	
					}else {
						 JOptionPane.showMessageDialog(null, "Invalid credentials");
						 
					}
				} catch (Exception e1) { 
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginBtn.setBounds(129, 154, 147, 29);
		contentPane.add(loginBtn);
	}

}
