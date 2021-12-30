package WinBuilder;

import javax.swing.table.DefaultTableModel;

public interface Search{
	public DefaultTableModel genderTable(String gender) throws Exception;
	public DefaultTableModel ageTable(int age) throws Exception;
	public DefaultTableModel gradeTable(String grade) throws Exception;
}
