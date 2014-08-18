package com.dafeng.upgradeapp.util;

import java.util.List;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

public class PkgProcess {
	// private Context mContext;
	//
	// public PkgProcess(Context con) {
	// mContext = con;
	// }

	public static boolean isExist(Context con, String pkgName) {
		final PackageManager pm = con.getPackageManager();
		// get a list of installed apps.
		List<ApplicationInfo> pkg = pm
				.getInstalledApplications(PackageManager.GET_META_DATA);
		for (int i = 0; i < pkg.size(); i++) {
			ApplicationInfo info = pkg.get(i);
			if (info.packageName.equals(pkgName)) {
				return true;
			}

		}
		return false;
	}

	public static Drawable getLabel(Context con, String pkgName) {
		final PackageManager pm = con.getPackageManager();
		try {
			return pm.getApplicationIcon(pkgName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getAppName(Context con, String pkgName) {
		final PackageManager pm = con.getPackageManager();
		// get a list of installed apps.		
		try {
			return pm.getPackageInfo(pkgName, 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
