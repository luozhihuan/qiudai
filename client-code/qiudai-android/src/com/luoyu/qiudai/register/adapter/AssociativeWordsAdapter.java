package com.luoyu.qiudai.register.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luoyu.qiudai.register.model.University;
import com.luoyu.qiudai_android.R;


public class AssociativeWordsAdapter extends BaseAdapter {


	private Context context;

	private List<University> list;

	static class ViewHolder {
		TextView associativeWord;

	}

	public AssociativeWordsAdapter(Context context, List<University> list) {
		this.context = context;
		this.list = list;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public University getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		// convertView中装了一些老view，方便复用
		if (convertView == null) {
			// 通过该方法获取到context这个view的布局
			convertView = LayoutInflater.from(context).inflate(R.layout.item_associative_words, null);

			holder = new ViewHolder();
			holder.associativeWord = (TextView) convertView.findViewById(R.id.associativeWord);
			

			/** 把查找的view缓存起来方便多次重用 **/
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		University University = list.get(position);
		
		
		holder.associativeWord.setText(University.getUniversityName());
		

		return convertView;
	}
	
	
	
	

}
