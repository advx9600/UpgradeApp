package com.dafeng.upgradeapp.provider;

import com.dafeng.upgradeapp.dao.CustomAppDao;
import com.dafeng.upgradeapp.dao.DaoMaster;
import com.dafeng.upgradeapp.dao.DaoSession;
import com.dafeng.upgradeapp.db.DB;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CustomAppProvider extends ContentProvider {
	public static final String PROVIDER_NAME = "com.dafeng.upgradeapp.provider.CustomAppProvider";
	public static final String URL = "content://" + PROVIDER_NAME + "/friends";

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = DB.getWritableDb(this.getContext());
		DaoMaster daoMaster = new DaoMaster(db);
		DaoSession daoSession = daoMaster.newSession();
		CustomAppDao cusAppDao = daoSession.getCustomAppDao();
		Cursor cursor = db.query(cusAppDao.getTablename(),
				cusAppDao.getAllColumns(), null, null, null, null, null);		
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
