package com.luoyu.qiudai.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.luoyu.qiudai_android.R;

public class ActivitySkipHelperUtil {

	/** 由左向右跳转 **/
	public static int LEFT_TO_RIGHT = 0;
	/** 由右向左跳转 **/
	public static int RIGHT_TO_LEFT = 1;

	/**
	 * 跳转动画由一边向另一边跳转，跳转方式为finish当前Acitity来返回到之前的Activity
	 * 
	 * @param curActivity
	 *            当前Activity
	 * @param orientation
	 *            跳转方式，参数为LEFT_TO_RIGHT是由左向右，，RIGHT_TO_LEFT是由右向左
	 */
	public static void finishCurActivityBackToPrevActivitySideToSide(Activity curActivity, int orientation) {
		curActivity.finish();

		if (orientation == LEFT_TO_RIGHT) {
			curActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		} else if (orientation == RIGHT_TO_LEFT) {
			curActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		}
	}

	/**
	 * Activity跳转，效果为从一边到另一边，跳转后fromActivity不销毁
	 * 
	 * @param curActivity
	 *            起始Activity
	 * @param newActivity
	 *            到达Activity
	 */
	public static void skipCurActivityToNewActivitySideToSide(Activity curActivity, Class<?> newActivity, int orientation) {

		curActivity.startActivity(new Intent(curActivity, newActivity));

		if (orientation == LEFT_TO_RIGHT) {
			curActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		} else if (orientation == RIGHT_TO_LEFT) {
			curActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		}
	}

	/**
	 * 从当前Activity跳转到一个新的activity ，无效果,跳转后curActivity销毁
	 * 
	 * @param curActivity
	 *            起始Activity
	 * @param newActivity
	 *            到达Activity
	 */
	public static void skipCurActivityToNewActivityWithOutAnyAnimWithFinishCurActivity(Context curActivity, Class<?> newActivity) {
		Intent intent = new Intent(curActivity, newActivity);
		curActivity.startActivity(intent);
		((Activity) curActivity).finish();
	}

	/**
	 * 从当前Activity跳转到一个新的activity，效果为从左至右滑动,跳转后fromActivity会销毁
	 * 
	 * @param fromActivity
	 *            起始Activity
	 * @param toActivity
	 *            到达Activity
	 */
	public static void skipCurActivityToNewActivitySideToSideWithFinishCurActivity(Context fromActivity, Class<?> toActivity, int orientation) {

		skipCurActivityToNewActivityWithOutAnyAnimWithFinishCurActivity(fromActivity, toActivity);

		if (orientation == LEFT_TO_RIGHT) {
			((Activity) fromActivity).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		} else if (orientation == RIGHT_TO_LEFT) {
			((Activity) fromActivity).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		}

	}

	/**
	 * 跳转到启动器桌面
	 * @param activity
	 */
	public static void skipToLauncherDeskTop(Activity activity) {
		Intent home = new Intent(Intent.ACTION_MAIN);
		home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		home.addCategory(Intent.CATEGORY_HOME);
		activity.startActivity(home);
	}

}
