package com.luoyu.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @author yu 2014.8.7
 *该类用于当点击 某个控件是，将另外（也可以使本身）一个控件的文本内容置为空
 */
public class ResetTextOnClickListener implements OnClickListener{

	//需要设置的TextView 
	TextView m_txtView;
	
	//自定义提示消息到TextView
	String str_Tips = "";
	
	/**
	 * 该函数用于指定需要置空的文本控件
	 * @param txtView 待置空控件
	 */
	public ResetTextOnClickListener(TextView txtView) {
		// TODO Auto-generated constructor stub
		
		m_txtView = txtView;
	}
	
	/**
	 * 该函数用于指定需要置空的文本控件，并在该控件上指定值
	 * @param txtView 待置空的文本控件
	 * @param tips 指定的值
	 */
	public ResetTextOnClickListener(TextView txtView,String tips) {
		// TODO Auto-generated constructor stub
		
		m_txtView = txtView;
		str_Tips = tips;
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		m_txtView.setText(str_Tips);
		
	}
	
}
