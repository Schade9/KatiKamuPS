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

import timetable.Timetable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminTimeTable extends JFrame {

	private JPanel contentPane;
	private JScrollPane pane2;
	private JTable resultTable;
	public String day ,lesson1,lesson2,lesson3,lesson4;
	public String current_class = "P1";
	
	Timetable timetable = new Timetable();
	private JLabel lblNewLabel;
	private JLabel lesson1Label;
	private JLabel lesson2Label;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JComboBox lsn1box;
	private JComboBox lsn2box;
	private JComboBox lsn3box;
	private JComboBox lsn4box;
	private JButton saveBtn;
	private static String displayClass;
	String first_lesson ="--" , second_lesson ="--", third_lesson = "--",fourth_lesson = "--";
	String search_class;
	
	int class_index = 0;
	
	private JLabel dayLabel;
	private JComboBox comboBox;
	private JLabel classHeader;
	private JButton logOutBtn;
	private void tableClicked(MouseEvent evt){	
		day = resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString();
		lesson1 = resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString();
		lesson2 = resultTable.getValueAt(resultTable.getSelectedRow(), 2).toString();
		lesson3 = resultTable.getValueAt(resultTable.getSelectedRow(), 3).toString();
		lesson4 = resultTable.getValueAt(resultTable.getSelectedRow(), 4).toString();
		dayLabel.setText(day);
		Timetable.day_chosen = day;
//		searchtxt.setText(marks); 
		try {
			int[] indexes = timetable.getIndex(day, current_class);
			System.out.println(indexes[1]);
			lsn1box.setSelectedIndex(indexes[0]);
			lsn2box.setSelectedIndex(indexes[1]);
			lsn3box.setSelectedIndex(indexes[2]);
			lsn4box.setSelectedIndex(indexes[3]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Timetable.day_chosen); 
	}
	
	private void filter(String class_name) {
		AdminTimeTable.displayClass = class_name;
		dispose();
		AdminTimeTable admin = new AdminTimeTable(class_name);
		admin.setVisible(true);	
	}
	
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
		TableColumn column = null;
 		for(int a = 0; a < 5; a++) {
 			column = resultTable.getColumnModel().getColumn(a);

 				column.setPreferredWidth(300);
 			
 		}
		contentPane.setLayout(null);
		resultTable.setShowGrid(true);
		resultTable.setShowHorizontalLines(true);
		resultTable.setShowVerticalLines(true);
		resultTable.setGridColor(Color.BLACK);
		pane2 = new JScrollPane(resultTable);
	    pane2.setBounds(23, 24, 478, 121);

		contentPane.add(pane2);
		
		lblNewLabel = new JLabel("Select day :");
		lblNewLabel.setBounds(23, 148, 71, 16);
		contentPane.add(lblNewLabel);
		
		lesson1Label = new JLabel("8:00am - 10:00am");
		lesson1Label.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lesson1Label.setBounds(23, 171, 133, 16);
		contentPane.add(lesson1Label);
		
		lesson2Label = new JLabel("10:00am - 12:00pm");
		lesson2Label.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lesson2Label.setBounds(23, 194, 133, 16);
		contentPane.add(lesson2Label);
		
		lblNewLabel_1 = new JLabel("2:00pm - 3:00pm");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(23, 217, 133, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_3 = new JLabel("3:00pm - 4:00pm");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(23, 242, 133, 16);
		contentPane.add(lblNewLabel_3);
		
		lsn1box = new JComboBox();
		lsn1box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first_lesson = lsn1box.getSelectedItem().toString();
			}
			
		});
		lsn1box.setModel(new DefaultComboBoxModel(new String[] {"--", "Science", "SST", "Math", "English"}));
		lsn1box.setBounds(156, 166, 109, 25);

		contentPane.add(lsn1box);
		
		lsn2box = new JComboBox();
		lsn2box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				second_lesson = lsn2box.getSelectedItem().toString();
			}
		});
		lsn2box.setModel(new DefaultComboBoxModel(new String[] {"--", "Science", "SST", "Math", "English"}));
		lsn2box.setBounds(156, 190, 109, 25);
		contentPane.add(lsn2box);
		
		lsn3box = new JComboBox();
		lsn3box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				third_lesson = lsn3box.getSelectedItem().toString();
			}
		});
		lsn3box.setModel(new DefaultComboBoxModel(new String[] {"--", "Science", "SST", "Math", "English"}));
		lsn3box.setBounds(156, 214, 109, 25);
		contentPane.add(lsn3box);
		
		lsn4box = new JComboBox();
		lsn4box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		fourth_lesson = lsn4box.getSelectedItem().toString();
			}
		});
		lsn4box.setModel(new DefaultComboBoxModel(new String[] {"--", "Science", "SST", "Math", "English"}));
		lsn4box.setBounds(156, 238, 109, 25);
		contentPane.add(lsn4box);
		
		saveBtn = new JButton("SAVE");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(first_lesson);
				try {
					timetable.updateTimeTable(current_class, day, first_lesson, second_lesson, third_lesson, fourth_lesson);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				AdminTimeTable admin = new AdminTimeTable(current_class);
				admin.setVisible(true);	
			}
		});
		saveBtn.setBounds(306, 204, 117, 29);
		contentPane.add(saveBtn);
		
		dayLabel = new JLabel("");
		dayLabel.setBounds(95, 148, 98, 16);
		contentPane.add(dayLabel);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search_class = comboBox.getSelectedItem().toString();
				try {
				if(search_class == "P1") {
					filter("P1");
				}else if(search_class == "P2") {
					filter("P2");
				}else if(search_class == "P3") {
					filter("P3");
				}else if(search_class == "P4") {
					filter("P4");
				}else if(search_class == "P5") {
					filter("P5");
				}else if(search_class == "P6") {
					filter("P6");
				}else if(search_class == "P7") {
					filter("P7");
				}
				}catch(Exception e1) {
					System.out.println("Exception : "+e1.getMessage());
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"P1", "P2", "P3", "P4", "P5", "P6", "P7"}));
		comboBox.setBounds(33, 0, 77, 27);
		contentPane.add(comboBox);
		
		classHeader = new JLabel(AdminTimeTable.displayClass+" TIMETABLE");
		classHeader.setBounds(160, 4, 145, 16);
		contentPane.add(classHeader);
		
		logOutBtn = new JButton("Log out");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home home = new Home();
				home.setVisible(true);
			}
		});
		logOutBtn.setBounds(427, 204, 117, 29);
		contentPane.add(logOutBtn);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTimeTable frame = new AdminTimeTable();
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
	public AdminTimeTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			showTable(timetable.getTimeTable(""));
		} catch (Exception e) {

			System.out.println("Exception : "+e.getMessage());
		}

	}
	public AdminTimeTable(String name) {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 550, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	
	try {
		showTable(timetable.getTimeTable(name));
		current_class = name;
	} catch (Exception e) {

		System.out.println("Exception : "+e.getMessage());
	}

	
}

}
