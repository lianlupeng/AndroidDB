package org.android.util;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.android.model.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DataBaseInfo {

	//获取数据库连接
	public Connection getConn()
	{
		String url = "jdbc:mysql://localhost:3306/android";
		String pwd = "lupenglian";
		String user = "root";
		 try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        Connection conn = null;
	        try {
	            conn = (Connection) DriverManager.getConnection(url,user,pwd);

	        } catch (SQLException ex) {
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	        }
	        return conn;
	}
	//执行用户验证操作
	public boolean vefify(User user) throws SQLException
	{
		boolean flag = false;
		Statement stmt = (Statement) getConn().createStatement();
		ResultSet rs = stmt.executeQuery("select * from user");
		while(rs.next())
		{
			if(rs.getString("username").equals(user.getUsername())&&rs.getString("userpwd").equals(user.getUserpwd()))
			{
				flag = true;
				break;
			}
			System.out.println(rs.getString("username"));
			System.out.println(rs.getString("userpwd"));
		}
		return flag;
	}
}
