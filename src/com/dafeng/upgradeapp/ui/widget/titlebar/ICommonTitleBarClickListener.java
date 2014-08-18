package com.dafeng.upgradeapp.ui.widget.titlebar;

public abstract interface ICommonTitleBarClickListener {
	public abstract void onBackButtonClicked();

	public abstract void onRightButtonClicked();

	public abstract interface OnFilePickActivityTitleListener {
		public abstract void onBackClick();

		public abstract void onSelectAllBtnClick();
	}
}
