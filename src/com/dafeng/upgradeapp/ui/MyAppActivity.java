package com.dafeng.upgradeapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import com.dafeng.upgradeapp.BaseActivity;
import com.dafeng.upgradeapp.R;
import com.dafeng.upgradeapp.service.DownLoader;
import com.dafeng.upgradeapp.ui.widget.titlebar.CommonTitleBar;

public class MyAppActivity extends BaseActivity {

	public static final String TAG = "MyAppActivity";

	@Override
	protected Handler initHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initListener(Context paramContext) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initParam(Context paramContext) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView(Context paramContext) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_netdisk);
		this.mTitleManager = new CommonTitleBar(this);
		this.mTitleManager.setBackLayoutVisible(false);
		this.mTitleManager.setCenterLabel(getResources().getString(
				R.string.app_name));
		this.mTitleManager.setRightLabel(R.string.add);
	}

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void serviceInit() {
		Intent intent = new Intent(this, DownLoader.class);
		startService(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Intent intent = new Intent(this, DownLoader.class);
		// this.stopService(intent);
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		initFragment();
		serviceInit();
	}

	public void initFragment() {
		FragmentTransaction localFragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		// if (getIntent().getExtras() == null) {
		// }
		// for (Object localObject = new MyNetdiskFragment();; localObject = new
		// OpenNetdiskFragment()) {
		// localFragmentTransaction.add(2131230876, (Fragment) localObject,
		// "MyNetdiskFragment");
		// localFragmentTransaction.commit();
		// return;
		// }
		Object localObject = new MyAppFragment();
		localFragmentTransaction.add(R.id.content, (Fragment) localObject,
				"MyNetdiskFragment");
		localFragmentTransaction.commit();
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if ((KeyEvent.KEYCODE_BACK == paramKeyEvent.getKeyCode())
				&& (paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
			getFragment().back();
			return true;
		}
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

	private MyAppFragment getFragment() {
		return (MyAppFragment) getSupportFragmentManager().findFragmentByTag(
				"MyNetdiskFragment");
	}

	public void doClick(View v) {
		getFragment().changeListToEditMode();
	}
}
