package com.luoyu.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.luoyu.qiudai.util.VariableUtil;

public class Network {

	HttpPost mPost = null;
	DefaultHttpClient mHttpClient = null;
	static List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
	HttpResponse response = null;
	boolean lock = false;

	String JSESSIONID = null;
	/** 连接超时 **/
	boolean connectionTimeOut = false;

	private static Network instance = new Network();

	private Network() {
	}

	/**
	 * 设置网络连接超时
	 * 
	 * @param second
	 *            超时秒数
	 */
	public void setConnTimeOut(int second) {
		BasicHttpParams httpParams = new BasicHttpParams();

		/** 设置网络超时 **/
		HttpConnectionParams.setConnectionTimeout(httpParams, second * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, second * 1000);
		mHttpClient = new DefaultHttpClient(httpParams);
	}

	/**
	 * 利用单例模式初始化网络访问组件
	 * 
	 * @param url
	 *            访问路径
	 * @param second
	 *            超时时间，单位：秒
	 * @return 一个单例的网络连接对象
	 */
	public static Network GetSingletonInstance(String url, int second) {
		/** 单例 **/
		if (instance == null) {
			instance = new Network();
		}
		if (url != null) {
			instance.mPost = new HttpPost(url);

		} else {
			instance.mPost = new HttpPost();
		}

		instance.setConnTimeOut(second);
		pairs = new ArrayList<BasicNameValuePair>();
		return instance;
	}

	/**
	 * 设置网络访问的url给单例实体
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		if (instance.mPost != null) {
			instance.mPost = new HttpPost(url);
		} else {
			URI uri = null;
			try {
				uri = new URI(url);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			instance.mPost.setURI(uri);
		}

	}

	/**
	 * 非单例模式初始化网络访问组件
	 * 
	 * @param url
	 *            访问路径
	 * @param second
	 *            超时时间，单位：秒
	 * @return 一个非单例的网络连接对象
	 */
	public static Network NewInstance(String url, int second) {
		/** 单例 **/
		Network newInstance = new Network();
		newInstance.mPost = new HttpPost(url);
		newInstance.setConnTimeOut(second);
		return newInstance;
	}

	/**
	 * 添加需要传入服务器的参数
	 * 
	 * @param name
	 *            参数的名称（唯一）
	 * @param value
	 *            参数的值
	 */
	public void addParms(String name, String value) {
		if (!lock) {
			pairs.add(new BasicNameValuePair(name, value));
		}
	}

	public void send() {
		// 先加锁，防止重复提交等
		if (!lock) {
			lock = true;
			connectionTimeOut = false;
			try {
				mPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
				/** 解决发送时的中文乱码 **/
				mPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

				if (null == JSESSIONID) {

					mPost.setHeader("Cookie", "JSESSIONID=" + JSESSIONID);

				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			try {
				response = mHttpClient.execute(mPost);
				pairs.clear();

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (ConnectTimeoutException e) {
				connectionTimeOut = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 信息发送完毕，解锁
		lock = false;
	}

	public String receive() {

		/** 若超时。直接返回超时结果码 **/
		if (connectionTimeOut) {
			return VariableUtil.CONNECT_TIME_OUT + "";
		}

		String result = null;
		if (response != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			try {
				/** 加入"UTF-8"解决接收时的中文乱码 **/
				result = EntityUtils.toString(response.getEntity(), "UTF-8").toString();
				CookieStore mCookieStore = mHttpClient.getCookieStore();

				List<Cookie> cookies = mCookieStore.getCookies();
				for (Cookie cookie : cookies) {
					if ("JSESSIONID".equals(cookie.getName())) {

						JSESSIONID = cookie.getValue();
						System.out.println("SESSIONID:" + JSESSIONID);
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("访问网络失败");
			result = VariableUtil.CONNECT_FAILED + "";
		}
		return result;
	}

	/**
	 * 一次完整的网络访问，包括请求发送与接收
	 * 
	 * @return
	 */
	public String connect() {
		send();
		return receive();
	}
}
