package org.cloud.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AndroidDBMActivity extends Activity {
    /*Called when the activity is first created. 
     *this is the first user can see
     *author:lupeng email:lianlupeng@gmail.com
     */
	//定义前台用到的控件
	private EditText ipEdit;
	private Button ipButton;
	private EditText userName;
	private EditText userPwd;
	private Button userButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //获取控件
        ipEdit = (EditText)findViewById(R.id.ipedit);
        ipButton =(Button)findViewById(R.id.ipbutton);
        userName =(EditText)findViewById(R.id.username);
        userPwd = (EditText)findViewById(R.id.userpwd);
        userButton=(Button)findViewById(R.id.userbutton);
       //添加事件处理
        ipButton.setOnClickListener(new buttonClick());
        userButton.setOnClickListener(new buttonClick());
    }
    //添加按钮单击事件
    class buttonClick implements OnClickListener
    {
		public void onClick(View button) {
			if(button.equals(ipButton))
			{
				String ipString = ipEdit.getText().toString().trim();
				System.out.println(ipString);
			}
			else {
					String username = userName.getText().toString().trim();
			        String userpwd = userPwd.getText().toString().trim();
			        System.out.println("userinfo:"+username+","+userpwd);
			        showDialogLayoyt(AndroidDBMActivity.this);
			}
		}
    }
    //定义实现弹出对话框的方法
    private void showDialogLayoyt(Context context)
    {
    	LayoutInflater inflater = LayoutInflater.from(this);
    	final View layoutView = inflater.inflate(R.layout.error, null);
    	final TextView textView =(TextView)layoutView.findViewById(R.id.errortext);
    	final AlertDialog.Builder builder = new AlertDialog.Builder(context);
    	builder.setTitle("登录失败，亲!");
    	builder.setView(layoutView);
    	builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int arg1) {
				System.out.println("有待学习"+textView.getText().toString());
			}
		});
    	builder.show();
    }
}