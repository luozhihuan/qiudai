package com.luoyu.qiudai.core.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.util.ImageLoaderUtil;
import com.luoyu.qiudai_android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class SingleQiudaiInfoAdapter extends BaseAdapter {

	private Context context;

	private List<GoodsInfo> goodsInfolist;

	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;

	static class ViewHolder {
		ImageView img_goods;
		TextView txt_goods_name;
		TextView txt_goods_price;
		TextView txt_goods_bought_num;
	}

	public SingleQiudaiInfoAdapter(Context context, List<GoodsInfo> goodsInfoList) {
		this.context = context;
		this.goodsInfolist = goodsInfoList;
		imageLoader = ImageLoaderUtil.initImaeLoaderAndConfigured(context);
		options = ImageLoaderUtil.BuilderDisplayImageOptions();
	}

	@Override
	public int getCount() {
		return goodsInfolist.size();
	}

	@Override
	public GoodsInfo getItem(int index) {
		return goodsInfolist.get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		// convertView中装了一些老view，方便复用
		if (convertView == null) {
			// 通过该方法获取到context这个view的布局
			convertView = LayoutInflater.from(context).inflate(R.layout.item_publisher_goods_info, null);

			holder = new ViewHolder();
			/** 获取“商品图片”控件 **/
			holder.img_goods = (ImageView) convertView.findViewById(R.id.img_publisher_head_url);
			/** 获取“商品名称”的控件 **/
			holder.txt_goods_name = (TextView) convertView.findViewById(R.id.txt_delivery_limited_time);
			/** 获取“商品价格”控件 **/
			holder.txt_goods_price = (TextView) convertView.findViewById(R.id.txt_goods_price);
			/** 获取“商品购买数量”控件 **/
			holder.txt_goods_bought_num = (TextView) convertView.findViewById(R.id.txt_fee);
			// 把查找的view缓存起来方便多次重用
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 将图片按照options设置方式放入holder的ivPreview中
		imageLoader.displayImage(getItem(position).getGoodsImageUrl(), holder.img_goods, options);
		/** 设置商品名称的内容 **/
		holder.txt_goods_name.setText("商品名称：" + getItem(position).getGoodsName());

		/** 设置商品价格的内容 **/
		holder.txt_goods_price.setText("商品价格：" + getItem(position).getGoodsPrice() + "");

		/** 设置商品购买数量内容 **/
		holder.txt_goods_bought_num.setText("购买数量：" + getItem(position).getGoodsBuyNum() + "");

		return convertView;
	}

}
