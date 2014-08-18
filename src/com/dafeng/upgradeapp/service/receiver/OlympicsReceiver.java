package com.dafeng.upgradeapp.service.receiver;

import com.dafeng.upgradeapp.service.DownLoader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OlympicsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		context.startService(new Intent(context, DownLoader.class));
	}

}
