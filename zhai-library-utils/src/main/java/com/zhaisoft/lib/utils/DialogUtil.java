package com.zhaisoft.lib.utils;

import android.content.DialogInterface;

import java.lang.reflect.Field;

public class DialogUtil {
	public static void setDialogClose(DialogInterface dialog, boolean close) {
		try {
			Field field = dialog.getClass().getSuperclass()
					.getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(dialog, close); // flas表示不关闭
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
