package com.luoyu.qiudai.core.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luoyu.qiudai.core.model.impl.GoodsCategory;
import com.luoyu.qiudai.standard.adapter.StandardBaseAdapter;
import com.luoyu.qiudai_android.R;

/**
 * 选择商品种类的适配器
 * @author chuanrong
 *
 */
public class SelectGoodsCategoryAdapter extends SelectionAdapter {
	private List<GoodsCategory> goodsCategoryList;
	private List<GoodsCategory> nullGoodsCateList = new ArrayList<GoodsCategory>();

	
	public SelectGoodsCategoryAdapter(Context context){
		super(context);
		selectedPosition = -1;
	}
	
	public SelectGoodsCategoryAdapter(Context context, List<GoodsCategory> goodsCategoryList) {
		this(context);
		setgoodsCategoryList(goodsCategoryList);
		
	}

	public int getCount() {
		return goodsCategoryList.size();
	}

	public GoodsCategory getItem(int itm) {
		return goodsCategoryList.get(itm);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hold;
		if (convertView == null) {
			hold = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_category_selection, null);
			
			hold.txt_goods_category_name = (TextView) convertView.findViewById(R.id.txt_goods_category_name);
			convertView.setTag(hold);
		} else {
			hold = (ViewHolder) convertView.getTag();
		}
		hold.txt_goods_category_name.setText(getItem(position).getCategoryName());
		
		
		if(selectedPosition==position){
			hold.txt_goods_category_name.setTextColor(context.getResources().getColor(R.color.wasSelectedWordColor));
//			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		}else{
			hold.txt_goods_category_name.setTextColor(context.getResources().getColor(R.color.selectionWordColor));
//			convertView.setBackgroundColor(context.getResources().getColor(R.color.wasSelectedBackgroundColor));
		}
		
		return convertView;
	}

	public void setSelectItem(int i) {
		selectedPosition = i;
	}

	private static class ViewHolder {
		TextView txt_goods_category_name;
	}
	
	@Override
	public void setList(List list) {
		setgoodsCategoryList(list);
	}
	
	
	public void setgoodsCategoryList(List<GoodsCategory> goodsCategoryList){
		if(goodsCategoryList == null){
			this.goodsCategoryList = nullGoodsCateList;
		}else{
			this.goodsCategoryList = goodsCategoryList;
		}		
	}
	
}
