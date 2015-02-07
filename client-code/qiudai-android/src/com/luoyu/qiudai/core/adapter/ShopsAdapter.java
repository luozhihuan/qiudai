package com.luoyu.qiudai.core.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luoyu.qiudai.core.model.impl.Shops;
import com.luoyu.qiudai.standard.adapter.StandardBaseAdapter;
import com.luoyu.qiudai_android.R;

public class ShopsAdapter extends SelectionAdapter {

	public ShopsAdapter(Context context) {
		super(context);
	}
	
	
	public ShopsAdapter(Context context, List<Shops> shopsList) {
		super(context);
		setShopsList(shopsList);
	}

	List<Shops> shopsList;

	static class ViewHolder {
		TextView shopsName;
	}
	
	

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return shopsList.size();
	}

	@Override
	public Shops getItem(int position) {
		// TODO Auto-generated method stub
		return shopsList.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_shops_selection, null);

			hold.shopsName = (TextView) convertView.findViewById(R.id.txt_shops_name);
			convertView.setTag(hold);
		} else {
			hold = (ViewHolder) convertView.getTag();
		}
		hold.shopsName.setText(getItem(position).getShopsName());

		if(selectedPosition==position){
			hold.shopsName.setTextColor(context.getResources().getColor(R.color.wasSelectedWordColor));
//			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		}else{
			hold.shopsName.setTextColor(context.getResources().getColor(R.color.selectionWordColor));
//			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		}
		return convertView;
	}

	
	@Override
	public void setList(List list) {
		setShopsList(list);
	}
	
	
	public void setShopsList(List<Shops> shopsList) {
		if (shopsList == null) {
			this.shopsList = new ArrayList<Shops>();
		} else {
			this.shopsList = shopsList;
		}
	}

}
