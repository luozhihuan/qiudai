package com.luoyu.qiudai.core.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.luoyu.qiudai.core.data.GoodsInfoData;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.core.service.PopupWindowSettingService;
import com.luoyu.qiudai.util.ImageLoaderUtil;
import com.luoyu.qiudai_android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GoodsInfoAdapter extends BaseAdapter {

	Context context;
	List<GoodsInfo> goodsInfoList;

	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;
	private ImageLoaderUtil imageLoaderService;
	private Animation singletonAnimation;
	private PopupWindowSettingService windowSettingService;

	/**
	 * 购物车Map，记录了当前用户所选择的商品，key是商品id，value是该商品实体
	 */
	// public static Map<Long, GoodsInfo> shoppingTrolleyMap = new HashMap<Long,
	// GoodsInfo>();

	static class ViewHolder {
		ImageView img_goods;
		TextView txt_goods_name;
		TextView txt_goods_price;
		TextView txt_goods_buy_num;
		TextView btn_goods_num_add;
		TextView btn_goods_num_minus;
		ImageButton btn_goods_selected;
	}

	public GoodsInfoAdapter(Context context, List<GoodsInfo> goodsInfoList) {
		this.context = context;
		this.goodsInfoList = goodsInfoList;
		this.imageLoaderService = new ImageLoaderUtil();
		imageLoader = imageLoaderService.initImaeLoaderAndConfigured(context);
		options = imageLoaderService.BuilderDisplayImageOptions();
		windowSettingService = new PopupWindowSettingService();

	}

	@Override
	public int getCount() {
		return goodsInfoList.size();
	}

	@Override
	public GoodsInfo getItem(int position) {
		return goodsInfoList.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_info, null);

			holder = new ViewHolder();
			/** 获取“商品图片”控件 **/
			holder.img_goods = (ImageView) convertView.findViewById(R.id.img_publisher_head_url);
			/** 获取“商品名称”的控件 **/
			holder.txt_goods_name = (TextView) convertView.findViewById(R.id.txt_delivery_limited_time);
			/** 获取“信商品价格”控件 **/
			holder.txt_goods_price = (TextView) convertView.findViewById(R.id.txt_goods_price);

			/** 获取”购买数量“控件 **/
			holder.txt_goods_buy_num = (TextView) convertView.findViewById(R.id.txt_fee);

			/** 获取”增加商品件数“按钮控件 **/
			holder.btn_goods_num_add = (TextView) convertView.findViewById(R.id.btn_goods_num_add);
			/** 获取”减少商品件数“按钮控件 **/
			holder.btn_goods_num_minus = (TextView) convertView.findViewById(R.id.btn_goods_num_minus);

			/** 获取对号的按钮控件 **/
			holder.btn_goods_selected = (ImageButton) convertView.findViewById(R.id.btn_goods_selected);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		GoodsInfo goodsInfo = getItem(position);

		// 将图片按照options设置方式放入holder的ivPreview中
		imageLoader.displayImage(goodsInfo.getGoodsImageUrl(), holder.img_goods, options);
		holder.txt_goods_name.setText(goodsInfo.getGoodsName());
		holder.txt_goods_price.setText(goodsInfo.getGoodsPrice() + "");
		holder.txt_goods_buy_num.setText(goodsInfo.getGoodsBuyNum() + "");

		
		/**此处不添加该代码会有图片混乱问题**/
		if (goodsInfo.getGoodsBuyNum() > 0) {
			holder.btn_goods_selected.setVisibility(View.VISIBLE);
		}else{
			holder.btn_goods_selected.setVisibility(View.INVISIBLE);
		}

		holder.btn_goods_num_add.setOnClickListener(new BtnOnClickListener(position, holder));
		holder.btn_goods_num_minus.setOnClickListener(new BtnOnClickListener(position, holder));
		holder.btn_goods_selected.setOnClickListener(new BtnOnClickListener(position, holder));
		return convertView;
	}

	class BtnOnClickListener implements OnClickListener {
		private int position;
		private ViewHolder viewHolder;

		public BtnOnClickListener(int position, ViewHolder viewHolder) {
			this.position = position;
			this.viewHolder = viewHolder;
		}

		@Override
		public void onClick(View view) {
			GoodsInfo goodsInfo = getItem(position);
			switch (view.getId()) {
			/** 点击添加商品 **/
			case R.id.btn_goods_num_add:
				addGoodsBuyNum(goodsInfo);
				break;
			/** 点击取消商品 **/
			case R.id.btn_goods_num_minus:
				minusGoodsBuyNum(goodsInfo);
				break;
			/** 点击清空商品 **/
			case R.id.btn_goods_selected:
				cleanGoodsBuyNum(goodsInfo);
			default:
				break;
			}
		}

		/**
		 * 对商品购买件数进行减少操作
		 * 
		 * @param goodsInfoForMinus
		 */
		private void minusGoodsBuyNum(GoodsInfo goodsInfoForMinus) {
			if (goodsInfoForMinus.getGoodsBuyNum() > 0) {
				goodsInfoForMinus.setGoodsBuyNum(goodsInfoForMinus.getGoodsBuyNum() - 1);
				viewHolder.txt_goods_buy_num.setText(goodsInfoForMinus.getGoodsBuyNum() + "");
				/** 当该商品的购买数量被用户减到0的时候就把这个商品从购物车Map中删除掉 **/
				GoodsInfo goodsInfo = GoodsInfoData.SHOPPING_TROLLEY_MAP.get(goodsInfoForMinus.getGoodsInfoId());
				if (goodsInfo != null) {
					if (goodsInfoForMinus.getGoodsBuyNum() == 0) {
						GoodsInfoData.SHOPPING_TROLLEY_MAP.remove(goodsInfo.getGoodsInfoId());
						/** 隐藏对号 **/
						viewHolder.btn_goods_selected.setVisibility(View.INVISIBLE);
					} else {
						goodsInfo.setGoodsBuyNum(goodsInfoForMinus.getGoodsBuyNum());
					}

				}

			}
		}

		/**
		 * 最商品购买件数进行添加操作
		 * 
		 * @param goodsInfoForAdd
		 */
		private void addGoodsBuyNum(GoodsInfo goodsInfoForAdd) {
			goodsInfoForAdd.setGoodsBuyNum(goodsInfoForAdd.getGoodsBuyNum() + 1);
			viewHolder.txt_goods_buy_num.setText(goodsInfoForAdd.getGoodsBuyNum() + "");
			setBuyNumAnimation(viewHolder.txt_goods_buy_num);

			if (goodsInfoForAdd.getGoodsBuyNum() > 0) {

				GoodsInfo goodsInfo = GoodsInfoData.SHOPPING_TROLLEY_MAP.get(goodsInfoForAdd.getGoodsInfoId());
				/** 显示对号 **/
				viewHolder.btn_goods_selected.setVisibility(View.VISIBLE);
				/** 当用户对商品数量从0进行增加到1时，查看该商品是否已经存在于购物车中，如果不存在则添加到购物车map中 **/
				if (null == goodsInfo) {
					GoodsInfoData.SHOPPING_TROLLEY_MAP.put(goodsInfoForAdd.getGoodsInfoId(), goodsInfoForAdd);
				} else {
					goodsInfo.setGoodsBuyNum(goodsInfoForAdd.getGoodsBuyNum());
				}
			}
		}

		/**
		 * 为购买数量在变化的时候设置动画
		 */
		private void setBuyNumAnimation(TextView textView) {
			// DisplayMetrics dm =
			// windowSettingService.getDisplayMetrics((Activity) context);
			// int[] loc = caculateTheLocationOfView(view);
			/** 获取一个动画，前面四个参数是该动画的执行轨迹（动画执行轨迹是动画从设定的坐标出现并运到到另一个设定的坐标） **/
			/** 最后一个参数是动画执行时间单位是毫秒 **/
			// Animation animation =
			// windowSettingService.getTranslateAnimation(0, 0, -700, 0, 300);
			Animation animation = getSingletonAnimation();
			animation.setDuration(300);
			textView.setAnimation(animation);
			textView.startAnimation(animation);
		}

		/**
		 * 通过单例返回一个动画效果
		 * 
		 * @return 动画效果
		 */
		private Animation getSingletonAnimation() {
			if (singletonAnimation == null) {
				singletonAnimation = new ScaleAnimation(0f, 5f, 0f, 5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			}
			return singletonAnimation;

		}

		/**
		 * 清空商品数目
		 * 
		 * @param goodsInfoForClean
		 */
		private void cleanGoodsBuyNum(GoodsInfo goodsInfoForClean) {
			goodsInfoForClean.setGoodsBuyNum(0);
			/** 隐藏对号 **/
			viewHolder.btn_goods_selected.setVisibility(View.INVISIBLE);
			viewHolder.txt_goods_buy_num.setText(goodsInfoForClean.getGoodsBuyNum() + "");
			if (goodsInfoForClean.getGoodsBuyNum() == 0) {
				GoodsInfoData.SHOPPING_TROLLEY_MAP.remove(goodsInfoForClean.getGoodsInfoId());
			}

		}
	}

	public void setGoodsInfoList(List<GoodsInfo> goodsInfoList) {
		this.goodsInfoList = goodsInfoList;
	}

}
