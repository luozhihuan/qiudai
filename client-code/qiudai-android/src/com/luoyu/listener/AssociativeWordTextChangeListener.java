package com.luoyu.listener;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.widget.ListView;

import com.luoyu.qiudai.register.adapter.AssociativeWordsAdapter;
import com.luoyu.qiudai.register.controller.AssociativeWordController;
import com.luoyu.qiudai.register.model.University;

public class AssociativeWordTextChangeListener extends TextChangeListener{
	
	private Context context;
	
	private ListView listView;
	
	private List<University> defaultList;
	
	private AssociativeWordController associativeWordController;
	
	public AssociativeWordTextChangeListener(Context context,ListView listView,AssociativeWordController associativeWordController,View view,List<University> defaulList) {
		
		super(view);
		this.context = context;
		this.listView = listView;
		this.associativeWordController = associativeWordController;
		this.defaultList = defaulList;
	}
	
	
	
	@Override
	public void afterTextChanged(Editable arg0) {
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		
	}

	
	@Override
	public void onTextChanged(CharSequence charSeqence, int arg1, int arg2,int arg3) {
		if(m_View != null){
			super.onTextChanged(charSeqence, arg1, arg2, arg3);
		}
		
		
		if (charSeqence.length() >= 1) {
			
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					List<University> list = (List<University>)msg.obj;
					if(list != null){
					AssociativeWordsAdapter adapter = new AssociativeWordsAdapter(
							context, list);
					listView.setAdapter(adapter);
					listView.setVisibility(View.VISIBLE);
					}
					super.handleMessage(msg);
				}
			};
			Thread thread = new Thread(new MyRunnable(handler,charSeqence.toString()));
			thread.start();
			
		}else if(charSeqence.length() == 0){
			if(defaultList != null){
			AssociativeWordsAdapter adapter = new AssociativeWordsAdapter(
					context, defaultList);
			listView.setAdapter(adapter);
			listView.setVisibility(View.VISIBLE);
			}else{
				
				AssociativeWordsAdapter adapter = new AssociativeWordsAdapter(
						context, new ArrayList<University>());
				listView.setAdapter(adapter);
				listView.setVisibility(View.VISIBLE);
			}
		}
		
		
	}
	
	class MyRunnable implements Runnable{
		String word;
		Handler handler;
		public MyRunnable(Handler handler,String word){
			this.word = word;
			this.handler = handler;
		}
		@Override
		public void run() {
			Message msg = new Message();
			msg.obj = associativeWordController.getAssociativeUniversitiesBySendTextingWord(word);
//System.out.println("msg.obj"+msg.obj);			
			handler.sendMessage(msg);
		}
	}
	

}
