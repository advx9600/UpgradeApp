package com.dafeng.upgradeapp.ui;

import android.view.View;

public interface IBaseAppFragment {
	public void showAction(View view);
	public long[] getItemIds();
	public void delSuccess();
}
