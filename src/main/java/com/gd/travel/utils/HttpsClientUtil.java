package com.gd.travel.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpsClientUtil {
	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	private static SSLConnectionSocketFactory sslsf = null;
	private static PoolingHttpClientConnectionManager cm = null;
	private static SSLContextBuilder builder = null;
	public static CookieStore cookieStore = null;
	static {
		try {
			cookieStore = new BasicCookieStore();
			builder = new SSLContextBuilder();
			// 全部信任 不做身份鉴定
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(), new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(10);// max connection
			cm.setDefaultMaxPerRoute(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取httpcliet
	 * @return
	 * @throws Exception
	 */
	public static CloseableHttpClient getHttpClient() throws Exception {
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).setSSLSocketFactory(sslsf).setConnectionManager(cm).setConnectionManagerShared(true).setDefaultCookieStore(cookieStore).build();
		return httpClient;
	}

	/**
	 * 手动增加cookie
	 * @param name
	 * @param value
	 * @param domain
	 * @param path
	 */
	public void addCookie(String name, String value, String domain, String path) {
		BasicClientCookie cookie = new BasicClientCookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookieStore.addCookie(cookie);
	}

	/**
	 * @Title: doGet
	 * @Description: (httpGet请求)
	 * @param: @param url
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String doGet(String url) {
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		String returnStr = "";
		try {
			httpGet = new HttpGet(url);
			httpClient = getHttpClient();
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity responseEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200 && responseEntity != null) {
				returnStr = EntityUtils.toString(responseEntity);
			} else {
				System.out.println(response.getStatusLine().getStatusCode() + "请求失败！");
				returnStr = "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	/**
	 * @Title: doPost
	 * @Description: (httpclient post请求)
	 * @param: @param url
	 * @param: @param map
	 * @param: @param charset
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String doPost(String url, Map<String, String> map, String charset) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = getHttpClient();
			httpPost = new HttpPost(url);
			//设置参数  
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			@SuppressWarnings("rawtypes")
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				@SuppressWarnings("unchecked")
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
