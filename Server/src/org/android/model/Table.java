package org.android.model;
//���һ��model
public class Table {
	
	private String tableName ;
	private int studentid ;
	private String studentnunber;
	private int studentage ;
	//set and get method
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	

	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getStudentage() {
		return studentage;
	}
	public void setStudentage(int studentage) {
		this.studentage = studentage;
	}
	//��json���ݸ�ʽ����student����Ϣ
	@Override
	public String toString()
	{
		StringBuffer sbf = new StringBuffer();
		sbf.append("{");
		sbf.append("\"tablename\":").append("\""+getTableName()+"\"").append(",").append("\"studentid\":").append(getStudentid()).
		append(",").append("\"studentnumber\":").append("\""+getStudentnunber()+"\"").append(",").append("\"studentage\":").append(getStudentage()).append("}");
		return sbf.toString();
	}
	public String getStudentnunber() {
		return studentnunber;
	}
	public void setStudentnunber(String studentnunber) {
		this.studentnunber = studentnunber;
	}
}
