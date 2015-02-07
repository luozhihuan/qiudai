package com.luoyu.qiudai.core.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luoyu.qiudai.core.model.impl.SpecialOfferChoice;
import com.luoyu.qiudai.standard.adapter.StandardBaseAdapter;
import com.luoyu.qiudai_android.R;

public class SpecialOfferChoiceAdapter extends SelectionAdapter {

	
	List<SpecialOfferChoice> specialOfferChoiceList;
	
	static class ViewHolder {
		TextView specialOfferChoiceName;
	}
	
	public SpecialOfferChoiceAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return specialOfferChoiceList.size();
	}

	@Override
	public SpecialOfferChoice getItem(int position) {
		// TODO Auto-generated method stub
		return specialOfferChoiceList.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_special_offer_choice_selection, null);

			hold.specialOfferChoiceName = (TextView) convertView.findViewById(R.id.txt_special_offer_choice_name);
			convertView.setTag(hold);
		} else {
			hold = (ViewHolder) convertView.getTag();
		}
		hold.specialOfferChoiceName.setText(getItem(position).getSpecialOfferChoiceName());

		if(selectedPosition==position){
			hold.specialOfferChoiceName.setTextColor(context.getResources().getColor(R.color.wasSelectedWordColor));
//			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		}else{
			hold.specialOfferChoiceName.setTextColor(context.getResources().getColor(R.color.selectionWordColor));
//			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		}
		return convertView;
	}

	@Override
	public void setList(List list) {
		setSpecialOfferChoiceList(list);
	}
	
	
	
	public void setSpecialOfferChoiceList(List<SpecialOfferChoice> specialOfferChoiceList){
		this.specialOfferChoiceList = specialOfferChoiceList;
	}
	
	
	public int getSelectedPosition() {
		return selectedPosition;
	}

}
