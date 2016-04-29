package com.hawk.saa.util;

import android.view.Gravity;
import android.widget.Toast;

import com.hawk.saa.application.AppApplication;

/**
 * 弹出消息工具类
 */
public class ToastTool {
	private static Toast mToast;

	/**
	 * 使连续显示Toast时不会重复堆叠显示，不会延迟显示
	 * @param msg
	 */
	public static void show(String msg) {
		if (null == mToast) {
			mToast = Toast.makeText(AppApplication.getInstance(), msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	/**
	 * 停止显示Toast
	 */
	public static void cencelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}

	/**
	 * 使连续显示Toast时不会重复堆叠显示，不会延迟显示，显示在屏幕中央
	 * @param msg
	 */
	public static void showInCenter(String msg) {

		if (null == mToast) {
			mToast = Toast.makeText(AppApplication.getInstance(), msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.show();
	}
}
