package com.dafeng.upgradeapp.ui;

import com.dafeng.upgradeapp.R;
import com.dafeng.upgradeapp.dao.CustomApp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MyAppFragment extends BaseAppFragment implements IMyAppFragment {

	@SuppressWarnings("unused")
	private LayoutInflater inflater;
	private LinearLayout bottomBarView;
	// private Object mBottomEmptyView;
	private Button buttonDownload;
	private Button buttonShare;
	private Button buttonPush;
	private Button buttonDelete;
	private Button buttonMore;

	private EditText editTextPkgName;

	protected void initView(View paramView) {
		super.initView(paramView);
		this.inflater = ((LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE));
		initSearchAndRankListener();
		ViewStub localViewStub = (ViewStub) paramView
				.findViewById(R.id.bottom_view_stub);
		localViewStub.setLayoutResource(R.layout.my_netdisk_bottom_bar);
		localViewStub.inflate();
		setupBottomBar();
	}

	private void initSearchAndRankListener() {
		// cm localcm = new cm(this);
		// this.mListView.setSearchAndRankListener(localcm);
	}

	private void setupBottomBar() {
		this.bottomBarView = ((LinearLayout) findViewById(R.id.root_bottom_bar));
		this.bottomBarView.setVisibility(View.GONE);
		this.buttonDownload = ((Button) findViewById(R.id.btn_to_download));
		// this.buttonDownload.setOnClickListener(new co(this));
		this.buttonShare = ((Button) findViewById(R.id.btn_to_share));
		// this.buttonShare.setOnClickListener(new cp(this));
		this.buttonPush = ((Button) findViewById(R.id.btn_to_push));
		// this.buttonPush.setOnClickListener(new cq(this));
		this.buttonDelete = ((Button) findViewById(R.id.btn_to_delete));
		this.buttonDelete.setOnClickListener(new cr(this));
		this.buttonMore = ((Button) findViewById(R.id.btn_more));
		// this.buttonMore.setOnClickListener(new cs(this));
		((Button) findViewById(R.id.btn_to_rename)).setVisibility(8);
		this.buttonDownload.setEnabled(false);
		this.buttonShare.setEnabled(false);
		this.buttonPush.setEnabled(false);
		this.buttonMore.setEnabled(false);
	}

	@Override
	public void cancelEditMode() {
		super.cancelEditMode();
		this.bottomBarView.setVisibility(View.GONE);
	}

	protected void changeListToEditMode() {
		super.changeListToEditMode();
		Animation localAnimation = AnimationUtils.loadAnimation(getActivity(),
				R.anim.bottom_bar_show);
		this.bottomBarView.setVisibility(View.VISIBLE);
		this.bottomBarView.startAnimation(localAnimation);
	}

	@Override
	protected void updateTitleBar() {
		this.mTitleBarHelper.c(1);
		// this.mTitleBarHelper.setCenterButtonLabel(getCategoryTitleName());
		this.mTitleBarHelper.setCenterLabel(getResources().getString(
				R.string.app_name));
		this.mTitleBarHelper.setRightLayoutVisible(true);
	}

	public void back() {
		super.back();
		// if ((this.uploadDialog != null) && (this.uploadDialog.isShowing())) {
		// this.uploadDialog.dismiss();
		// this.uploadDialog = null;
		// }
		// if ((this.mSortPopupWindow != null) && (this.mSortPopupWindow.a())) {
		// this.mSortPopupWindow.b();
		// }
	}

	private void add() {
		editTextPkgName = new EditText(getActivity());
		new AlertDialog.Builder(this.getActivity())
				.setTitle(R.string.please_input_pkg_name)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(editTextPkgName)
				.setPositiveButton(R.string.confirm, new cn(this))
				.setNegativeButton(R.string.cancel, null).show();
	}

	private void mod(CustomApp app) {
		editTextPkgName = new EditText(getActivity());
		editTextPkgName.setText(app.getPackageName());
		new AlertDialog.Builder(this.getActivity())
				.setTitle(R.string.please_input_pkg_name)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(editTextPkgName)
				.setPositiveButton(R.string.confirm, new cm(this))
				.setNegativeButton(R.string.cancel, null).show();
	}

	@Override
	protected void onItemClickAction(CustomApp customApp) {
		mod(customApp);
	}

	@Override
	public void onRightButtonClicked() {
		if (this.isViewMode) {
			add();
			return;
		}
		super.onRightButtonClicked();
	}

	@Override
	public boolean addPkg() {
		// TODO Auto-generated method stub
		if (cusAppDaoImpl.insert(editTextPkgName.getText().toString())) {
			reFreshListView();
			return true;
		}
		return false;
	}

	@Override
	public boolean delPkg() {
		// TODO Auto-generated method stub
		if (cusAppDaoImpl.del(getItemIds())) {
			reFreshListView();
			delSuccess();
			return true;
		}
		return false;
	}

	@Override
	public boolean modPkg() {
		if (cusAppDaoImpl.mod(curCustomApp, editTextPkgName.getText()
				.toString())) {
			reFreshListView();
			return true;
		}
		return false;
	}
}
