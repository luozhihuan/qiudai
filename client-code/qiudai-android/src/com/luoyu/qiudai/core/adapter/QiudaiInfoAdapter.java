package com.luoyu.qiudai.core.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luoyu.qiudai.core.model.impl.QiudaiDisplayInfo;
import com.luoyu.qiudai.core.service.LimitedTimeForDisplayService;
import com.luoyu.qiudai.util.ImageLoaderUtil;
import com.luoyu.qiudai_android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class QiudaiInfoAdapter extends BaseAdapter {

	Context context;
	List<QiudaiDisplayInfo> qiudaiDisplayInfoList;

	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;
	private SimpleDateFormat sdf;
	private LimitedTimeForDisplayService limitedTimeForDisplayService;

	static class ViewHolder {
		ImageView publisher_head;
		TextView delivery_limited_time;
		TextView publisher_address;
		TextView fee;
		TextView publish_time;
	}

	public QiudaiInfoAdapter(Context context, List<QiudaiDisplayInfo> qiudaiDisplayInfoList) {
		this.context = context;
		this.qiudaiDisplayInfoList = qiudaiDisplayInfoList;
		imageLoader = ImageLoaderUtil.initImaeLoaderAndConfigured(context);
		options = ImageLoaderUtil.BuilderDisplayImageOptions();
		sdf = new SimpleDateFormat("HH:mm");
		limitedTimeForDisplayService = LimitedTimeForDisplayService.getSingletonStance();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return qiudaiDisplayInfoList.size();
	}

	@Override
	public QiudaiDisplayInfo getItem(int index) {
		// TODO Auto-generated method stub
		return qiudaiDisplayInfoList.get(index);
	}

	@Override
	public long getItemId(int id) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		// convertView中装了一些老view，方便复用
		if (convertView == null) {
			// 通过该方法获取到context这个view的布局
			convertView = LayoutInflater.from(context).inflate(R.layout.item_qiudai_info_display, null);

			holder = new ViewHolder();
			/** 获取“发布者头像”控件 **/
			holder.publisher_head = (ImageView) convertView.findViewById(R.id.img_publisher_head);
			/** 获取“递送截止时间”的控件 **/
			holder.delivery_limited_time = (TextView) convertView.findViewById(R.id.txt_delivery_limited_time);
			/** 获取“信息发布者的地址”控件 **/
			holder.publisher_address = (TextView) convertView.findViewById(R.id.txt_publisher_address);
			/** 获取“跑路费”控件 **/
			holder.fee = (TextView) convertView.findViewById(R.id.txt_fee);
			/** 信息发布时间 **/
			holder.publish_time = (TextView) convertView.findViewById(R.id.txt_publish_time);

			// 把查找的view缓存起来方便多次重用
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 将图片按照options设置方式放入holder的ivPreview中
		imageLoader.displayImage(getItem(position).getPublisherHeadImgUrl(), holder.publisher_head, options);

		/** 获取该条求带信息的发布时间 **/
		Date publishTime = getItem(position).getPublishTime();
		String dateStr = sdf.format(publishTime);

		// String limitedTime = getLimitedTimeForDisplay(publishTime,
		// getItem(position).getDiliveryLimitedTime());
		String limitedTime = limitedTimeForDisplayService.getLimitedTimeForDisplay(publishTime, getItem(position).getDiliveryLimitedTime());

		/** 设置递送截止时间的内容 **/
		holder.delivery_limited_time.setText(limitedTime + "前送达");

		/** 设置跑路费的内容 **/
		holder.fee.setText("跑路费为：" + getItem(position).getFee() + "");

		/** 设置信息发布者地址的内容 **/
		holder.publisher_address.setText("地址是：" + getItem(position).getPublisherAddress() + "");

		/** 设置发布时间 **/
		holder.publish_time.setText("今日 " + dateStr + " 发布");

		// getItem(position).get

		return convertView;
	}
	//
	// private String getLimitedTimeForDisplay(Date publishTime, int
	// limitedTime) {
	// int publishMinutes = publishTime.getMinutes();
	// int publishHours = publishTime.getHours();
	// // int limitedTime = getItem(position).getDiliveryLimitedTime();
	//
	// int totalMinutes = limitedTime + publishMinutes;
	// publishMinutes = totalMinutes % 60;
	// if (publishMinutes != totalMinutes) {// 说明进位
	// publishHours = (publishHours + 1) % 24;
	// }
	//
	// StringBuffer result = new StringBuffer(publishHours + "点");
	// if (0 <= publishMinutes && publishMinutes < 10) {
	// result.append("0" + publishMinutes+"分");
	// } else {
	// result.append("" + publishMinutes+"分");
	// }
	//
	// return result.toString();
	// }
}
