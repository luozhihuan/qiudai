package com.luoyu.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

/**
 * 
 * @author yu
 *该类用于当文本的内容改变（有文本）时，弹出对应的提示信息控件；
 *当文本内容为空的时候，隐藏提示信息控件；
 */

public class TextChangeListener  implements TextWatcher{
	
	View m_View;
	
	public TextChangeListener(View view) {
		m_View = view;
	}
	
	
	@Override
	public void afterTextChanged(Editable arg0) {
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		
	}

	@Override
	public void onTextChanged(CharSequence charSeqence, int arg1, int arg2,
			int arg3) {
		
		if (charSeqence.length()>0) {
			m_View.setVisibility(View.VISIBLE);
		}
		else {
			m_View.setVisibility(View.INVISIBLE);
		}
	}
}
