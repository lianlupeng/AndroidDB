package org.android.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.android.model.Table;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//实现数据库的操作
public class DataBaseOperation {

	//获取连接connection
	private static Connection conn = new DataBaseInfo().getConn();
	private static String sql = null;
	private static PreparedStatement pstm = null;  
	private static ResultSet rst = null;
	//实现查询的功能
	public static  JsonList Query(String tablename)
	{
		JsonList json = new JsonList();
		
		//System.out.print(tablename);
		//创建sql语句的时候应特别注意
		sql = "select * from "+tablename;
		try {
			//pstm.setString(1, tablename);
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rst = pstm.executeQuery();
			while(rst.next())
			{
				Table map = new Table();
				map.setStudentid(rst.getInt(1));
				map.setStudentnunber(rst.getString(2));
				map.setStudentage(rst.getInt(3));
				json.addObject(map);
			}
			
		} catch (SQLException e) {
			System.out.println("query exception--------------->");
			e.printStackTrace();
		}finally
		{
			try {
				pstm.close();
				rst.close();
			} catch (SQLException e) {
				System.out.println("释放资源出现异常--------------->");
				e.printStackTrace();
			}
		}
		return json;
	}
	//实现插入功能
	public static boolean Inser(String tablename,String tableindexs[])
	{
		return true;
	}
	//实现删除操作
	public static boolean Update(String tablename,String tableindexs[])
	{
		return true;
	}
	//实现删除操作
	public static boolean Delete(String tablename,int id)
	{
		return true;
	}
}
