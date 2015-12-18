package com.zhaisoft.lib.utils.image;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImage {

	
	private final static String TAG = "DownloadImage";
	private final static int CONNECTION_TIMEOUT = 30000;
	private final static int SOCKET_TIMEOUT = 30000;
	
	static int i =0;

	public static void downloadImage(final String imageUrl,
			final String _savePath) {

		downloadImage(imageUrl, _savePath, "");
	}

	public static void downloadImage(final String imageUrl,
			final String _savePath, final String _filename) {

		Log.i(TAG,"下载"+(i++));
		String imgSrcUrl = imageUrl;
		String savePath = _savePath;
    
		int index = imgSrcUrl.lastIndexOf(".");
		if (index == -1) {
			return;
		}
		String ext = imgSrcUrl.substring(index);
		
		String fileName ="";
		
		if(_filename.equals("")){
			fileName= savePath+ext;
		}else{
			
			fileName= _savePath+"/"+_filename;
		}
		try {
			URL url = new URL(imgSrcUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(CONNECTION_TIMEOUT);
			connection.setReadTimeout(SOCKET_TIMEOUT);
			DataInputStream in = new DataInputStream(connection
					.getInputStream());

			File savePathFile = new File(savePath);
			if (!savePathFile.exists())
				savePathFile.mkdirs();

			File resourceFile = new File(fileName);
			if (!resourceFile.exists()) {
				resourceFile.createNewFile();
			}
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					resourceFile));
			byte[] bf = new byte[4096];
			int count = 0;
			while ((count = in.read(bf)) > 0) {
				out.write(bf, 0, count);
			}
			out.close();
			in.close();
			connection.disconnect();

		} catch (Exception e) {
			Log.i(TAG, e.toString());
		}

	}

}
