package com.dafeng.upgradeapp.ui;

import android.content.DialogInterface;

public class cn implements DialogInterface.OnClickListener {
	private IMyAppFragment a;

	public cn(IMyAppFragment myAppFragment) {
		// TODO Auto-generated constructor stub
		a = myAppFragment;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		this.a.addPkg();
	}

}
