package com.dafeng.upgradeapp;

import com.dafeng.upgradeapp.ui.widget.titlebar.CommonTitleBar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
	protected static final int NO_LAYOUT = 0;
	@SuppressWarnings("unused")
	private static final String TAG = "BaseActivity";
	// private static Stack<BaseActivity> sActivities = new Stack();
	private Handler mHandler;
	protected CommonTitleBar mTitleManager;

	protected abstract Handler initHandler();

	protected abstract void initListener(Context paramContext);

	protected abstract void initParam(Context paramContext);

	protected abstract void initView(Context paramContext);

	public Context getContext() {
		return getApplicationContext();
	}

	protected abstract int getLayoutId();

	public CommonTitleBar getTitleBar() {
		return this.mTitleManager;
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		int i = getLayoutId();
		if (i != 0) {
			setContentView(i);
		}
		initView(this);
		initParam(this);
		initListener(this);
		// addActivity(this);
		// initVolume();
		this.mHandler = initHandler();		
		ai.a(this.mHandler);
	}

	protected void onDestroy() {
		// if ((this instanceof NetdiskFilelistActivity)) {
		// VerifyCodedLockActivity.setIsShowForgetButton(true);
		// }
		if (this.mTitleManager != null) {
			this.mTitleManager.destroy();
		}
		ai.b(this.mHandler);
		// removeActivity(this);
		super.onDestroy();
	}

}
