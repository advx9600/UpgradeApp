package com.dafeng.upgradeapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dafeng.upgradeapp.dao.DaoMaster;
import com.dafeng.upgradeapp.dao.DaoMaster.DevOpenHelper;

public class DB {
	public static SQLiteDatabase getWritableDb(Context act){
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(act, "notes-db.db",
				null);
		return helper.getWritableDatabase();
	}
}
