package com.luoyu.qiudai.register.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luoyu.qiudai.register.model.Dormitory;
import com.luoyu.qiudai_android.R;

public class DormiBuildingSelectAdapter extends BaseAdapter{
	
	private Context context;
	
	private List<Dormitory> list;
	
	public DormiBuildingSelectAdapter(Context context,List<Dormitory> list){
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		TextView dormibuildingName;

	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Dormitory getItem(int arg0) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_dormibuilding_select, null);

			holder = new ViewHolder();
			holder.dormibuildingName = (TextView) convertView.findViewById(R.id.dormibuildingName);
			

			/** 把查找的view缓存起来方便多次重用 **/
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Dormitory dormibuilding = list.get(position);
		
		
		holder.dormibuildingName.setText(dormibuilding.getDormibuildingname());
		

		return convertView;
	}
	
	

}
