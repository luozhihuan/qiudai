package com.luoyu.qiudai.core.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luoyu.qiudai.core.model.impl.SpecialOffer;
import com.luoyu.qiudai.standard.adapter.StandardBaseAdapter;
import com.luoyu.qiudai_android.R;

public class SpecialOfferKindAdapter extends StandardBaseAdapter {

	List<SpecialOffer> specialOfferKindList;

	static class ViewHolder {
		TextView specialOfferKindName;
	}

	public SpecialOfferKindAdapter(Context context) {
		super(context);
	}

	public SpecialOfferKindAdapter(List<SpecialOffer> specialOfferList) {
		this.specialOfferKindList = specialOfferList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return specialOfferKindList.size();
	}

	@Override
	public SpecialOffer getItem(int position) {
		// TODO Auto-generated method stub
		return specialOfferKindList.get(position);
	}

	@Override
	public long getItemId(int id) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hold;
		if (convertView == null) {
			hold = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_special_offer_kind_selection, null);

			hold.specialOfferKindName = (TextView) convertView.findViewById(R.id.txt_special_offer_kind_name);
			convertView.setTag(hold);
		} else {
			hold = (ViewHolder) convertView.getTag();
		}
		hold.specialOfferKindName.setText(getItem(position).getSpecialOfferName());

		if (selectedPosition == position) {
			hold.specialOfferKindName.setTextColor(context.getResources().getColor(R.color.wasSelectedWordColor));
			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		} else {
			hold.specialOfferKindName.setTextColor(context.getResources().getColor(R.color.selectionWordColor));
			convertView.setBackgroundColor(context.getResources().getColor(R.color.unSelectedBackgroundColor));
		}
		return convertView;
	}

	public void setSpecialOfferKindList(List<SpecialOffer> specialOfferKindList) {
		// this.campusList = campusList;
		if (specialOfferKindList == null) {
			this.specialOfferKindList = new ArrayList<SpecialOffer>();
		} else {
			this.specialOfferKindList = specialOfferKindList;
		}
	}

}
