package org.android.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.android.model.Table;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//ʵ�����ݿ�Ĳ���
public class DataBaseOperation {

	//��ȡ����connection
	private static Connection conn = new DataBaseInfo().getConn();
	private static String sql = null;
	private static PreparedStatement pstm = null;  
	private static ResultSet rst = null;
	//ʵ�ֲ�ѯ�Ĺ���
	public static  JsonList Query(String tablename)
	{
		JsonList json = new JsonList();
		
		//System.out.print(tablename);
		//����sql����ʱ��Ӧ�ر�ע��
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
				System.out.println("�ͷ���Դ�����쳣--------------->");
				e.printStackTrace();
			}
		}
		return json;
	}
	//ʵ�ֲ��빦��
	public static boolean Inser(String tablename,String tableindexs[])
	{
		return true;
	}
	//ʵ��ɾ������
	public static boolean Update(String tablename,String tableindexs[])
	{
		return true;
	}
	//ʵ��ɾ������
	public static boolean Delete(String tablename,int id)
	{
		return true;
	}
}
