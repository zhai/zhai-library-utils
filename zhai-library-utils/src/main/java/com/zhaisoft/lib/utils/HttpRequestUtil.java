package com.zhaisoft.lib.utils;


/**
 * @deprecated 暂时不使用这个类了
 */
public class HttpRequestUtil {

	// 使用办法
	/*
	 * List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
	 * nameValuePair.add(new BasicNameValuePair("isAndriod", "1"));
	 * nameValuePair.add(new BasicNameValuePair("username", mUsernameEdit
	 * .getText().toString())); nameValuePair.add(new
	 * BasicNameValuePair("password", mPasswordEdit .getText().toString()));
	 * nameValuePair.add(new BasicNameValuePair("scheme", RdpConfig.protocal));
	 * 
	 * String result = sendDataByPost(RdpConfig.protocal + "://" +
	 * mGatewayIP.getText().toString() + RdpConfig.loginPostUrl, nameValuePair);
	 */

//	public static String sendDataByPost(String url, List<NameValuePair> datas) {
//		// org.apache.http.client.HttpClient client = new DefaultHttpClient();
//		HttpClient client = createHttpClient();
//		HttpPost post = new HttpPost(url);
//		HttpResponse resp = null;
//		String result = "";
//		// post data
//		try {
//			post.setEntity(new UrlEncodedFormEntity(datas, HTTP.UTF_8));
//			resp = client.execute(post);
//			result = EntityUtils.toString(resp.getEntity());
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	private static HttpClient createHttpClient() {
//		HttpParams params = new BasicHttpParams();
//		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//		HttpProtocolParams.setContentCharset(params,
//				HTTP.DEFAULT_CONTENT_CHARSET);
//		HttpProtocolParams.setUseExpectContinue(params, true);
//
//		SchemeRegistry schReg = new SchemeRegistry();
//		schReg.register(new Scheme("http", PlainSocketFactory
//				.getSocketFactory(), 80));
//		schReg.register(new Scheme("https",
//				SSLSocketFactory.getSocketFactory(), 443));
//		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
//				params, schReg);
//
//		return new DefaultHttpClient(conMgr, params);
//	}

}
