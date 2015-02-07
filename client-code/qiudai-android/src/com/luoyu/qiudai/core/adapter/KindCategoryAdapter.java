package com.luoyu.qiudai.core.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luoyu.qiudai.core.model.impl.GoodsKindCategory;
import com.luoyu.qiudai.standard.adapter.StandardBaseAdapter;
import com.luoyu.qiudai_android.R;

public class KindCategoryAdapter extends StandardBaseAdapter {

//	Context context;
	List<GoodsKindCategory> goodsKindCategoryList;
	

	static class ViewHolder {
		TextView kindCategoryName;
	}

	public KindCategoryAdapter(Context context) {
		super(context);
	}

	public KindCategoryAdapter(Context context, List<GoodsKindCategory> goodsKindCategoryList) {
		this.context = context;
		setgoodsKindCategoryList(goodsKindCategoryList);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsKindCategoryList.size();
	}

	@Override
	public GoodsKindCategory getItem(int position) {
		// TODO Auto-generated method stub
		return goodsKindCategoryList.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_category_selection, null);

			hold.kindCategoryName = (TextView) convertView.findViewById(R.id.txt_goods_category_name);
			convertView.setTag(hold);
		} else {
			hold = (ViewHolder) convertView.getTag();
		}
		hold.kindCategoryName.setText(getItem(position).getKindCategoryName());
		
		if(selectedPosition==position){
			hold.kindCategoryName.setTextColor(context.getResources().getColor(R.color.wasSelectedWordColor));
			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		}else{
			hold.kindCategoryName.setTextColor(context.getResources().getColor(R.color.selectionWordColor));
			convertView.setBackgroundColor(context.getResources().getColor(R.color.unSelectedBackgroundColor));
		}
		
		return convertView;
	}
	
	
	public void setgoodsKindCategoryList(List goodsKindCategoryList){
		if (goodsKindCategoryList == null) {
			this.goodsKindCategoryList = new ArrayList<GoodsKindCategory>();
		} else {
			this.goodsKindCategoryList = goodsKindCategoryList;
		}
	}

	public List<GoodsKindCategory> getGoodsKindCategoryList() {
		return goodsKindCategoryList;
	}

	public void setGoodsKindCategoryList(List<GoodsKindCategory> goodsKindCategoryList) {
		this.goodsKindCategoryList = goodsKindCategoryList;
	}

	
	
	
}
