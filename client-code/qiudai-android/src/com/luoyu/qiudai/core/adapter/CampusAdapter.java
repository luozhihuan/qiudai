package com.luoyu.qiudai.core.adapter;

import java.util.ArrayList;
import java.util.List;

import com.luoyu.qiudai.register.model.Campus;
import com.luoyu.qiudai.standard.adapter.StandardBaseAdapter;
import com.luoyu.qiudai_android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CampusAdapter extends StandardBaseAdapter {

	List<Campus> campusList;

	static class ViewHolder {
		TextView campusName;
	}

	public CampusAdapter(Context context) {
		super(context);
	}

	public CampusAdapter(Context context, List<Campus> campusList) {
		this.context = context;
		setCampusList(campusList);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return campusList.size();
	}

	@Override
	public Campus getItem(int position) {
		// TODO Auto-generated method stub
		return campusList.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hold;
		if (convertView == null) {
			hold = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_campus_selection, null);

			hold.campusName = (TextView) convertView.findViewById(R.id.txt_campus_name);
			convertView.setTag(hold);
		} else {
			hold = (ViewHolder) convertView.getTag();
		}
		hold.campusName.setText(getItem(position).getCampusName());

		if (selectedPosition == position) {
			hold.campusName.setTextColor(context.getResources().getColor(R.color.wasSelectedWordColor));
			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		} else {
			hold.campusName.setTextColor(context.getResources().getColor(R.color.selectionWordColor));
			convertView.setBackgroundColor(context.getResources().getColor(R.color.unSelectedBackgroundColor));
		}
		return convertView;
	}

	public void setCampusList(List<Campus> campusList) {
		// this.campusList = campusList;
		if (campusList == null) {
			this.campusList = new ArrayList<Campus>();
		} else {
			this.campusList = campusList;
		}
	}

	
}
