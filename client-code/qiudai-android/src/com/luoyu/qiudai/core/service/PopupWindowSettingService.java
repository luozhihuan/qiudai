package com.luoyu.qiudai.core.service;


import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;

public class PopupWindowSettingService{

	private int screen_w;

	private int screen_h;

	private PopupWindow pWindow;

	private Animation animation;

	private LayoutInflater inflater;

	private View layoutView;

	/**
	 * 由于本service类使用相对复杂，故在此处编写一个使用本类的调用顺序，方便理解 注：本方法用于讲解，并不建议使用
	 */
	public void recommendCallOrderStep(Activity activity, View view, int layoutViewId, View showAsDropDownOfView) {
		/** 获取屏幕分辨率对象dm，可以通过dm.widthPixels 和dm.heightPixels来得到屏幕的长宽分辨率 **/
		DisplayMetrics dm = getDisplayMetrics(activity);
		// int[] loc = caculateTheLocationOfView(view);
		/** 获取一个动画，前面四个参数是该动画的执行轨迹（动画执行轨迹是动画从设定的坐标出现并运到到另一个设定的坐标） **/
		/** 最后一个参数是动画执行时间单位是毫秒 **/
		animation = getTranslateAnimation(0, 0, -700, 0, 300);
		/** 生成布局实体 **/
		layoutView = generateLayoutViewDirectly(activity, layoutViewId);
		/** 获取PopupWindow对象 **/
		pWindow = getInitedPopuWindow(layoutView, 100, 30, 100);
		setViewAppearAnimation(layoutView, animation);
		showAsDropDown(pWindow, showAsDropDownOfView, 0, 2);

	}

	/**
	 * 使用布局生成器生成布局
	 * 
	 * @param activity
	 * @param inflater
	 *            布局生成器
	 * @param layoutViewId
	 *            布局id
	 * @return 布局实体对象
	 */
	public View generateLayoutView(Activity activity, LayoutInflater inflater, int layoutViewId) {
		return inflater.from(activity).inflate(layoutViewId, null);
	}

	/**
	 * 获取布局生成器
	 * 
	 * @param activity
	 * @return 布局生成器
	 */
	public LayoutInflater getLayoutInflater(Activity activity) {
		return (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 直接生成布局
	 * 
	 * @param activity
	 * @param layoutViewId
	 * @return
	 */
	public View generateLayoutViewDirectly(Activity activity, int layoutViewId) {
		LayoutInflater layoutInflater = getLayoutInflater(activity);
		return generateLayoutView(activity, layoutInflater, layoutViewId);

	}

	/**
	 * 初始化 PopupWindow,PopupWindow类似于AlertDialog对话框， PopupWindow的位置可以随意是阻塞线程的
	 * PopupWindow的位置按照有无偏移分，可以分为偏移和无偏移两种； 按照参照物的不同，可以分为相对于某个控件（Anchor锚）和相对于父控件。
	 * 
	 * @param view
	 * @param w_size_scale
	 *            占屏幕宽的比例分子
	 * @param h_size_scale
	 *            占屏幕高的比例分子
	 * @param full_scale
	 *            屏幕比例分母
	 */
	public void initPopuWindow(View view, int w_size_scale, int h_size_scale, int full_scale) {
		/* 第一个参数弹出显示view 后两个是窗口大小 */
		pWindow = new PopupWindow(view, screen_w * w_size_scale / full_scale, screen_h * h_size_scale / full_scale);
		/* 设置背景显示（没有这一行代码就无法实现触摸该view之外的地方该view消失的功能，不清楚为什么一定要加这句话） */
		pWindow.setBackgroundDrawable(new ColorDrawable(0));

		/* 设置触摸外面时消失 */
		pWindow.setOutsideTouchable(true);

		/* 设置系统动画 */
		// mPopupWindow.setAnimationStyle(R.style.popwin_anim_style); //
		// 可自定义style
		pWindow.update();
		pWindow.setTouchable(true);
		/* 设置点击menu以外其他地方以及返回键退出 */
		pWindow.setFocusable(true);
		/**
		 * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
		 */
		view.setFocusableInTouchMode(true);
	}

	/**
	 * 返回一个已经初始化完成的的PopupWindow对象
	 * 
	 * @param view
	 * @param w_size_scale
	 *            占屏幕宽的比例分子
	 * @param h_size_scale
	 *            占屏幕高的比例分子
	 * @param full_scale
	 *            屏幕比例分母
	 * @return
	 */
	public PopupWindow getInitedPopuWindow(View view, int w_size_scale, int h_size_scale, int full_scale) {
		initPopuWindow(view, w_size_scale, h_size_scale, full_scale);
		return pWindow;

	}

	/**
	 * 获取屏幕尺寸并进行设置
	 */
	public void setDisplayMetric(Activity activity) {
		DisplayMetrics dm = getDisplayMetrics(activity);
		setScreenSize(dm.widthPixels, dm.heightPixels);
	}

	/**
	 * 获取屏幕尺寸并进行设置
	 */
	public void setDisplayMetric(DisplayMetrics dm) {
		setScreenSize(dm.widthPixels, dm.heightPixels);
	}

	/**
	 * 获取屏幕分辨率对象
	 * 
	 * @param activity
	 * @return 屏幕分辨率对象
	 */
	public DisplayMetrics getDisplayMetrics(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小
		return dm;
	}

	/**
	 * 计算出控件的位置，并以一个数组的形式返回，数组中包含了控件的具体位置
	 * 
	 * @return 控件的位置，以数组的形式存储
	 */
	public int[] caculateTheLocationOfView(View view) {
		int[] loc = new int[2];
		view.getLocationOnScreen(loc);
		return loc;
	}

	/**
	 * 在view控件正下方展示pWindow
	 * 
	 * @param view
	 * @param xoff
	 *            横轴偏移度
	 * @param yoff
	 *            纵轴偏移度
	 */
	public void showAsDropDown(View view, int xoff, int yoff) {
		pWindow.showAsDropDown(view, xoff, yoff);
	}

	/**
	 * 在view控件正下方展示popupWindow
	 * 
	 * @param popupWindow
	 * @param view 控件
	 * @param xoff
	 *            横轴偏移度
	 * @param yoff
	 *            纵轴偏移度
	 */
	public void showAsDropDown(PopupWindow popupWindow, View view, int xoff, int yoff) {
		popupWindow.showAsDropDown(view, xoff, yoff);
	}

	/**
	 * 
	 * @param fromXDelta
	 *            表示动画开始的点离当前View X坐标上的差值
	 * @param toXDelta
	 *            表示动画结束的点离当前View X坐标上的差值
	 * @param fromYDelta
	 *            表示动画开始的点离当前View Y坐标上的差值
	 * @param toYDelta
	 *            表示动画开始的点离当前View Y坐标上的差值
	 * @param duration
	 *            动画持续时间
	 * @return 动画实体
	 */
	public TranslateAnimation getTranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, int duration) {
		TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
		animation.setDuration(duration);
		return animation;
	}

	/**
	 * 设置view控件出现的动画
	 * 
	 * @param view
	 *            控件
	 * @param animation
	 *            动画效果
	 */
	public void setViewAppearAnimation(View view, Animation animation) {
		view.setAnimation(animation);
		view.startAnimation(animation);
	}

	/**
	 * 设置屏幕尺寸
	 * 
	 * @param screen_w
	 *            屏幕宽
	 * @param screen_h
	 *            屏幕高
	 */
	public void setScreenSize(int screen_w, int screen_h) {
		this.screen_w = screen_w;
		this.screen_h = screen_h;
	}

	public int getScreen_w() {
		return screen_w;
	}

	public void setScreen_w(int screen_w) {
		this.screen_w = screen_w;
	}

	public int getScreen_h() {
		return screen_h;
	}

	public void setScreen_h(int screen_h) {
		this.screen_h = screen_h;
	}

}
