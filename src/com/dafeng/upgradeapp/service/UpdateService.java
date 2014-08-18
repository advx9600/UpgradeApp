package com.dafeng.upgradeapp.service;

import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class UpdateService {

	public static boolean testUrl(String path) {
		try {
			// path = "http://10.1.0.45/one/update.xml";
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			InputStream is = conn.getInputStream();
			is.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean testPkg(String path, String pkg) {
		boolean isEqual = false;
//		try {
//			URL url = new URL(path);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setConnectTimeout(3000);
//			InputStream is = conn.getInputStream();
//			UpdateInfo info = getUpdataInfo(is);
//			if (info.getPkgName().equals(pkg)) {
//				isEqual = true;
//			}
//			is.close();
//			conn.disconnect();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		return isEqual;
	}

	public static UpdateInfo getUpdataInfo(String xmlString) throws Exception {
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(new StringReader(xmlString));
		int type = parser.getEventType();
		UpdateInfo info = new UpdateInfo();// 实体
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_TAG:
				if ("version".equals(parser.getName())) {
					info.setVersion(parser.nextText()); // 获取版本号
				} else if ("url".equals(parser.getName())) {
					info.setUrl(parser.nextText()); // 获取要升级的APK文件
				} else if ("description".equals(parser.getName())) {
					info.setDescription(parser.nextText()); // 获取该文件的信息
				} else if ("pkgName".endsWith(parser.getName())) {
					info.setPkgName(parser.nextText());
				}
				break;
			}
			type = parser.next();
		}

		log(info.getVersion() + ",url:" + info.getUrl() + ",description:"
				+ info.getDescription() + ",pkg:" + info.getPkgName());
		return info;
	}
	
	// public static boolean testPing(String path) {
	// return false;
	// }

	public static void log(String msg) {
		android.util.Log.i("UpdateService", msg);
	}

}
