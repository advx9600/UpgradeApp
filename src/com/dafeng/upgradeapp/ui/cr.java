package com.dafeng.upgradeapp.ui;

import android.view.View;

public class cr implements View.OnClickListener {
	private IMyAppFragment a;

	public cr(IMyAppFragment myAppFragment) {
		// TODO Auto-generated constructor stub
		this.a = myAppFragment;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		a.delPkg();
	}

}
