package com.dafeng.upgradeapp.ui;

import android.view.View;

public class ag implements View.OnClickListener {
	private IBaseAppFragment a;

	public ag(IBaseAppFragment app) {
		this.a = app;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.a.showAction(v);
	}

}
