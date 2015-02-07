package com.luoyu.qiudai.register.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luoyu.qiudai.register.model.Campus;
import com.luoyu.qiudai_android.R;

public class CampusSelectAdapter extends BaseAdapter{
	
	private Context context;
	
	private List<Campus> list;
	
	public CampusSelectAdapter(Context context,List<Campus> list){
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		TextView campusName;

	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Campus getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		// convertView中装了一些老view，方便复用
		if (convertView == null) {
			// 通过该方法获取到context这个view的布局
			convertView = LayoutInflater.from(context).inflate(R.layout.item_campus_select, null);

			holder = new ViewHolder();
			holder.campusName = (TextView) convertView.findViewById(R.id.campusName);
			

			/** 把查找的view缓存起来方便多次重用 **/
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Campus campus = list.get(position);
		
		
		holder.campusName.setText(campus.getCampusName());
		

		return convertView;
	}
	
	

}
