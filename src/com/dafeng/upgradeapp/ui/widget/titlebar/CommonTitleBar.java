package com.dafeng.upgradeapp.ui.widget.titlebar;


import com.dafeng.upgradeapp.R;

import android.app.Activity;

public class CommonTitleBar extends a implements ICommonTitleBarClickListener {

	private CommonTitleBar.OnFilePickActivityTitleListener a;

	public CommonTitleBar(Activity paramActivity) {
		super(paramActivity);
		initDefaultView();
		c();
	}

	private void c() {
		setBackLayoutVisible(true);
	}

	public void a() {
		setCenterTitleVisible(true);
		setRightLayoutVisible(false);
	}

	public void a(int paramInt) {
		setCenterLabel(paramInt);
	}

	public void a(
			CommonTitleBar.OnFilePickActivityTitleListener paramOnFilePickActivityTitleListener) {
		this.a = paramOnFilePickActivityTitleListener;
		setTopTitleBarClickListener(this);
	}

	public void a(String paramString) {
		setCenterLabel(paramString);
	}

	public void a(boolean paramBoolean) {
		setRightLayoutVisible(paramBoolean);
	}

	public void a(boolean paramBoolean, int paramInt1, int paramInt2) {
		// if (paramBoolean == true) {
		// }
		// for (;;) {
		// setRightLabel(paramInt1);
		// return;
		// paramInt1 = paramInt2;
		// }
	}

	public void b() {
		setCenterTitleVisible(true);
		setRightLayoutVisible(true);
	}

	public void b(int paramInt) {
		setRightLabel(paramInt);
	}

	public void b(boolean paramBoolean) {
		int i;
		if (paramBoolean == true) {
			i = R.string.select_all;
		} else {
			i = R.string.deselect_all;
		}
		setRightLabel(i);

	}

	public void c(int paramInt) {
		if (paramInt == 0) {
			setBackLayoutVisible(true);
		} else {
			setBackLayoutVisible(false);
		}
	}

	public void c(boolean paramBoolean) {
		setRightEnable(paramBoolean);
	}

	public void destroy() {
		super.destroy();
		this.a = null;
	}

	public void onBackButtonClicked() {
		if (this.a != null) {
			this.a.onBackClick();
		}
	}

	public void onRightButtonClicked() {
		if (this.a != null) {
			this.a.onSelectAllBtnClick();
		}
	}
}