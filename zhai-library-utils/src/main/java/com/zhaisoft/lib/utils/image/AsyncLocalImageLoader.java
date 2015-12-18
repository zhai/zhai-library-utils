package com.zhaisoft.lib.utils.image;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import com.zhaisoft.lib.utils.ImageUtil;

import java.lang.ref.SoftReference;
import java.util.HashMap;


public class AsyncLocalImageLoader {

	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncLocalImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	public Drawable loadDrawable(final String imageUrl,final int maxNumOfPixels,
			final ImageCallback imageCallback) {
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			
			if (drawable != null) {
				return drawable;
			}
		}
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
			}
		};
		new Thread() {
			@Override
			public void run() {
				Drawable drawable = loadImageFromUrl(imageUrl,maxNumOfPixels);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}
	
	
	//根据指定大小缩放 add- Michael 
//	public Drawable loadDrawable(final String imageUrl,final int maxNumOfPixels,final int width,final int height,
//			final ImageCallback imageCallback) {
//		if (imageCache.containsKey(imageUrl)) {
//			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
//			Drawable drawable = softReference.get();
//			
//			if (drawable != null) {
//				return drawable;
//			}
//		}
//		final Handler handler = new Handler() {
//			public void handleMessage(Message message) {
//				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
//			}
//		};
//		new Thread() {
//			@Override
//			public void run() {
//				Drawable drawable = loadImageFromUrl(imageUrl,maxNumOfPixels,width,height);
//				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
//				Message message = handler.obtainMessage(0, drawable);
//				handler.sendMessage(message);
//			}
//		}.start();
//		return null;
//	}

	public static Drawable loadImageFromUrl(String url,int maxNumOfPixels) {
		Bitmap bm = ImageUtil.decodeFile(url, maxNumOfPixels);
		if(bm==null||bm.isRecycled())
			return null;
		return new BitmapDrawable(bm);
		//return BitmapDrawable.createFromPath(url);
	}
	
	//根据指定大小缩放   add by michael 
//	public static Drawable loadImageFromUrl(String url,int maxNumOfPixels,int width,int height) {
//		Bitmap bm = ImageUtil.decodeFile(url, maxNumOfPixels,width,height);
//		if(bm!=null&&bm.isRecycled())
//			return null;
//		return new BitmapDrawable(bm);
//		//return BitmapDrawable.createFromPath(url);
//	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}

}
