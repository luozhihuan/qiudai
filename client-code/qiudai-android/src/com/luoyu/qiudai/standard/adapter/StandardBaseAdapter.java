package com.luoyu.qiudai.standard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StandardBaseAdapter extends BaseAdapter {

	public Context context;
	/** 已选中的position **/
	public int selectedPosition;
	/** 上次选中的position **/
	private int lastSelectedPosition;
	/** 当前选中的position **/
	private int selectingPosition;

	public StandardBaseAdapter() {
	}

	public StandardBaseAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public int getLastSelectedPosition() {
		return lastSelectedPosition;
	}

	public void setLastSelectedPosition(int lastSelectedPosition) {
		this.lastSelectedPosition = lastSelectedPosition;
	}

	public int getSelectingPosition() {
		return selectingPosition;
	}

	public void setSelectingPosition(int selectingPosition) {
		this.selectingPosition = selectingPosition;
	}

}
