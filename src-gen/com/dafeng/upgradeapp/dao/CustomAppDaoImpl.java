package com.dafeng.upgradeapp.dao;

import android.app.Activity;
import android.widget.Toast;

public class CustomAppDaoImpl {
	private Activity con;
	private CustomAppDao dao;
	private String lastErr = "";

	public CustomAppDaoImpl(Activity context, CustomAppDao dao) {
		this.con = context;
		this.dao = dao;
	}

	public boolean insert(String pkgName) {
		pkgName = pkgName.trim();
		if (pkgName.length() == 0) {
			showErr("长度不能为空");
			return false;
		}

		if (dao.queryRaw(
				" where " + CustomAppDao.Properties.PackageName.columnName
						+ "=?", new String[] { pkgName }).size() > 0) {
			showErr("包名已经存在");
			return false;
		}

		CustomApp app = new CustomApp();
		app.setPackageName(pkgName);
		dao.insert(app);
		return true;
	}

	private void showErr(String err) {
		setLastErr(err);
		Toast.makeText(con, err, Toast.LENGTH_SHORT).show();
	}

	public String getLastErr() {
		return lastErr;
	}

	protected void setLastErr(String lastErr) {
		this.lastErr = lastErr;
	}

	public boolean del(long[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			dao.deleteByKey(ids[i]);
		}
		return true;
	}

	public boolean mod(CustomApp app, String pkgName) {
		// TODO Auto-generated method stub
		pkgName = pkgName.trim();
		if (pkgName.length() == 0) {
			showErr("长度不能为空");
			return false;
		}

		if (app.getPackageName().equals(pkgName)) {
			return true;
		}

		app.setPackageName(pkgName);

		if (dao.queryRaw(
				" where " + CustomAppDao.Properties.PackageName.columnName
						+ "=?", new String[] { pkgName }).size() > 0) {
			showErr("包名已经存在");
			return false;
		}

		dao.update(app);

		return true;
	}

}
