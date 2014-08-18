package com.dafeng.upgradeapp.service;

import java.util.List;

import com.dafeng.upgradeapp.dao.CustomApp;
import com.dafeng.upgradeapp.dao.CustomAppDao;
import com.dafeng.upgradeapp.dao.DaoMaster;
import com.dafeng.upgradeapp.dao.DaoSession;
import com.dafeng.upgradeapp.db.DB;
import com.dafeng.upgradeapp.util.AutoUpdateApk2;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class DownLoader extends Service {
	private SQLiteDatabase db;
	private CustomAppDao cusAppDao;
	private DaoMaster daoMaster;
	private DaoSession daoSession;

	private boolean mIsExist = false;
	private static long HOUR = 3600 * 1000;
	private long SLEEP_DUR = 3 * HOUR;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			checkUpdate();
		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		db = DB.getWritableDb(this);
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		cusAppDao = daoSession.getCustomAppDao();

		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						if (mIsExist)
							break;
						handler.sendEmptyMessage(0);
						Thread.sleep(SLEEP_DUR);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	protected void checkUpdate() {
		List<CustomApp> list = cusAppDao.queryRaw("");
		for (int i = 0; i < list.size(); i++) {
			AutoUpdateApk2 aa = new AutoUpdateApk2(this, list.get(i)
					.getPackageName());
			aa.checkUpdatesManually();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		db.close();
		mIsExist = true;
	}

	public static void log(String string2) {
		// TODO Auto-generated method stub
		android.util.Log.i("DownLoader", string2);
	}

}
