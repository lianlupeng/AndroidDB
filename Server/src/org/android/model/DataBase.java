package org.android.model;
//���ݿ��model
public class DataBase {

	private String dataBaseName ;

	//set and get method
	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	//������ݿ���Ϣ��������������
	@Override
	public String toString()
	{
		//sbf.append("\"age\":").append(age).append(",").append("\"name\":").append("\"" + name + "\"").append("}");
		StringBuffer sbf = new StringBuffer();
		sbf.append("{");
		sbf.append("\"databasename\":").append(getDataBaseName()).append("}");
		return sbf.toString();
	}
}
