package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import WinBuilder.StudentManager;
import timetable.Timetable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentTimetable extends JFrame {
	Timetable timetable = new Timetable();
	private JPanel contentPane;
	private JTable resultTable;
	private JScrollPane pane2;
	private JLabel heading;
	private JButton backBtn;

	private void showTable(DefaultTableModel table) {
		resultTable = new JTable(table);

//		resultTable.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				tableClicked(e); 
//			}
//			
//		});
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		TableColumn column = null;
 		for(int a = 0; a < 5; a++) {
 			column = resultTable.getColumnModel().getColumn(a);

 				column.setPreferredWidth(300);
 			
 		}
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		resultTable.setShowGrid(true);
		resultTable.setShowHorizontalLines(true);
		resultTable.setShowVerticalLines(true);
		resultTable.setGridColor(Color.BLACK);
		pane2 = new JScrollPane(resultTable);
	    pane2.setBounds(22, 54, 478, 160);

		contentPane.add(pane2);
		
		heading = new JLabel(StudentManager.student_class+" TIMETABLE");
		heading.setBounds(207, 26, 182, 16);
		contentPane.add(heading);
		
		backBtn = new JButton("< BACK");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StudentHome home = new StudentHome();
				home.setVisible(true);
			}
		});
		backBtn.setBounds(207, 220, 117, 29);
		contentPane.add(backBtn);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentTimetable frame = new StudentTimetable();
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
	public StudentTimetable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			showTable(timetable.getTimeTable(StudentManager.student_class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
