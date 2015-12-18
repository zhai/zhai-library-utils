package com.zhaisoft.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 
 * @author Pro.J 使用方法 if (ImeUtil.isImeShow(getContext())) {
 *         ImeUtil.hideIme((Activity) getContext()); }
 */
public class ImeUtil {

	/**
	 * ok
	 * 
	 * @param context
	 */
	public static void hideIme(Activity context) {
		if (context == null)
			return;
		final View v = context.getWindow().peekDecorView();
		if (v != null && v.getWindowToken() != null) {
			InputMethodManager imm = (InputMethodManager) context
					.getSystemService(context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

	/**
	 * ok
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isImeShow(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(context.INPUT_METHOD_SERVICE);
		return imm.isActive();
	}

	/**
	 * 测试中
	 * 
	 * @param context
	 */
	public static void showIme(Activity context) {
		if (context == null)
			return;
		final View v = context.getWindow().peekDecorView();
		if (v != null && v.getWindowToken() != null) {
			InputMethodManager imm = (InputMethodManager) context
					.getSystemService(context.INPUT_METHOD_SERVICE);
			// imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 测试中
	 * 
	 * @param context
	 */
	public static void showOrHideIme(Activity context) {
		if (context == null)
			return;
		InputMethodManager imm = ((InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE));
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
