package com.dafeng.upgradeapp.db;

import com.dafeng.upgradeapp.dao.CustomApp;
import com.dafeng.upgradeapp.dao.CustomAppDao;
import com.dafeng.upgradeapp.dao.DaoMaster;
import com.dafeng.upgradeapp.dao.DaoSession;

import android.database.sqlite.SQLiteDatabase;

public class ExtraDaoMater {
	public static void onCreate(SQLiteDatabase db) {
		DaoMaster daoMaster = new DaoMaster(db);
		DaoSession daoSession = daoMaster.newSession();
		CustomAppDao cusAppDao = daoSession.getCustomAppDao();
		CustomApp app = new CustomApp();
		app.setPackageName("com.csipsimple");		
		cusAppDao.insert(app);
	}
}
