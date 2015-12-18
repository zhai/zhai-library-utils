package com.zhaisoft.lib.utils.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class AsyncThumbnailImageLoader {

	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncThumbnailImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	public Drawable loadDrawable(final Context context,final int resType,final String id,
			final boolean fetchDefault,final ImageCallback imageCallback) {
		if (imageCache.containsKey(id)) {
			SoftReference<Drawable> softReference = imageCache.get(id);
			Drawable drawable = softReference.get();
			if (!fetchDefault||drawable != null) {
				return drawable;
			}
		}
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded((Drawable) message.obj, id);
			}
		};
		new Thread() {
			@Override
			public void run() {
//				Drawable drawable = fetchDefault?
//						loadImageFromDbDefault(context,resType,id):
//						loadImageFromDb(context,resType,id);
//				imageCache.put(id, new SoftReference<Drawable>(drawable));
//				Message message = handler.obtainMessage(0, drawable);
//				handler.sendMessage(message);
			}
		}.start();
		return null;
	}

//	public static Drawable loadImageFromDbDefault(Context context,int resType,String id) {
//		Bitmap bmp = ResourceManager.getThumbnail4Db(context, resType, id);
//		return new BitmapDrawable(bmp);
//	}
//	public static Drawable loadImageFromDb(Context context,int resType,String id) {
//		Bitmap bmp = ResourceThumbnailProviderClient.ThumbnailResource
//		.getBitmapById(context, id);
//		return bmp!=null?new BitmapDrawable(bmp):null;
//	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}

}
