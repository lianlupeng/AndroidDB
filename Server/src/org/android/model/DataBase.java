package org.android.model;
//数据库的model
public class DataBase {

	private String dataBaseName ;

	//set and get method
	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	//输出数据库信息，用于连接设置
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
