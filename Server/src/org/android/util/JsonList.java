package org.android.util;

import java.util.ArrayList;
//ʵ�ַ���˵��ͻ��˵�json���ݸ�ʽ�����ݷ���
import java.util.List;

public class JsonList {
	//Ҫ���а�ȫ����֤
	private List<Object> listItems = new ArrayList<Object>();
	//constructor
	public JsonList()
	{
		
	}
	//add 
	public void addObject(Object o) {
		listItems.add(o);
	}
	//����json���ݵ����ݸ�ʽ
	@Override
	public String toString()
	{
		StringBuffer sbf = new StringBuffer();
		sbf.append("[");
		for(int i=0;i<listItems.size();i++)
		{
			sbf.append(listItems.get(i).toString());
			if(i!=listItems.size()-1)
				sbf.append(",");
		}
		sbf.append("]");
		return sbf.toString();
	}
}
