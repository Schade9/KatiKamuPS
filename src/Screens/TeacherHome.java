package Screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WinBuilder.*;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherHome extends JFrame {

	private JPanel contentPane;
	public String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherHome frame = new TeacherHome();
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
	
	public TeacherHome() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome "+ StudentManager.name);
		welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		welcomeLabel.setBounds(133, 27, 208, 16);
		contentPane.add(welcomeLabel);
		 
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close registration page
				RegisterStudents register = new RegisterStudents();
				register.setVisible(true);	
			}
		});
		registerBtn.setBounds(148, 57, 117, 29);
		contentPane.add(registerBtn);
		
		JButton viewBtn = new JButton("View students");
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close registration page
				showStudents show = new showStudents();
				show.setVisible(true);
			}
		});
		viewBtn.setBounds(148, 98, 117, 29);
		contentPane.add(viewBtn);
		
		JButton marksBtn = new JButton("Register marks");
		marksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose(); // close registration page
			RegisterMarks marks = new RegisterMarks();
			marks.setVisible(true);	
			}
		});
		marksBtn.setBounds(133, 139, 144, 29);
		contentPane.add(marksBtn);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close registration page
				Login login = new Login();
				login.setVisible(true);	
			}
		});
		logoutBtn.setBounds(148, 179, 117, 29);
		contentPane.add(logoutBtn);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
