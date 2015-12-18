package com.zhaisoft.lib.utils.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class AsyncBitmapLoader {
	private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

	public AsyncBitmapLoader() {
	}

	public Bitmap loadBitmap(final String imageUrl, final ImageCallback callback) {
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Bitmap> softReference = imageCache.get(imageUrl);
			if (softReference.get() != null) {
				return softReference.get();
			}
		}
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				callback.imageLoaded((Bitmap) msg.obj, imageUrl);
			}
		};
		new Thread() {
			public void run() {
				Bitmap bitmap = loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Bitmap>(bitmap));
				handler.sendMessage(handler.obtainMessage(0, bitmap));
			};
		}.start();
		return null;
	}

	protected Bitmap loadImageFromUrl(String imageUrl) {
		try {

			// return ImageUtil.decodeFile(mCategoryCacheFileSrc, 0, true);

			return BitmapFactory.decodeFile(imageUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Bitmap loadBitmap(final String localimageUrl,final String remoteUrl,final String target_dir,final String filename, final ImageCallback callback) {
		if (imageCache.containsKey(localimageUrl)) {
			SoftReference<Bitmap> softReference = imageCache.get(localimageUrl);
			if (softReference.get() != null) {
				return softReference.get();
			}
		}
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				callback.imageLoaded((Bitmap) msg.obj, localimageUrl);
			}
		};
		new Thread() {
			public void run() {
				Bitmap bitmap = loadImageFromUrl(localimageUrl,remoteUrl,target_dir,filename);
				imageCache.put(localimageUrl, new SoftReference<Bitmap>(bitmap));
				handler.sendMessage(handler.obtainMessage(0, bitmap));
			};
		}.start();
		return null;
	}

	protected Bitmap loadImageFromUrl(String localUrl,String remoteUrl,String _target_dir,String filename) {
		try {

			// 如果不存在的话,下载到本地

			File file = new File(localUrl);
			if (!file.exists()) {
				com.zhaisoft.lib.utils.image.DownloadImage.downloadImage(remoteUrl,_target_dir, filename); // 远程路径,本地路径,文件名
			}

			return com.zhaisoft.lib.utils.ImageUtil.decodeFile(localUrl, 0, true);

			// return BitmapFactory.decodeFile(imageUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public interface ImageCallback {
		public void imageLoaded(Bitmap imageDrawable, String imageUrl);
	}

	public void clear() {
		if (imageCache != null) {
			imageCache.clear();
		}

	}
}
