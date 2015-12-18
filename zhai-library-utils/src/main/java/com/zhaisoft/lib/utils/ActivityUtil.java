package com.zhaisoft.lib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;

import java.util.List;

public class ActivityUtil {

	public static boolean isTopActivity(Context context, String classname) {

		ActivityManager am = (ActivityManager) context
				.getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = am.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (classname.equals(tasksInfo.get(0).topActivity.getClassName())) {
				return true;
			}
		}
		return false;
	}

}
