package org.android.util;

import java.util.ArrayList;
//实现服务端到客户端的json数据格式的数据发送
import java.util.List;

public class JsonList {
	//要进行安全性验证
	private List<Object> listItems = new ArrayList<Object>();
	//constructor
	public JsonList()
	{
		
	}
	//add 
	public void addObject(Object o) {
		listItems.add(o);
	}
	//创建json数据的数据格式
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
