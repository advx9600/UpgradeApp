package com.dafeng.upgradeapp.ui.widget.titlebar;

import android.view.View;

public class b implements View.OnClickListener {
	private a a;

	public b(a a) {
		this.a = a;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		a.mClickListener.onRightButtonClicked();
	}

}
