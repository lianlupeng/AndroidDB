package org.android.model;
//�û���һ��model
public class User {

	private String username ;
	private String userpwd ;
	//get set
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	//����û�����Ϣ
	@Override
	public String toString()
	{
		StringBuffer sbf = new StringBuffer();
		sbf.append("{");
		sbf.append("\"username:\":").append("\""+getUsername()+"\"").append(",").append("\"userpwd\":").append("\""+getUserpwd()+"\"").append("}");
		return sbf.toString();
	}
}
