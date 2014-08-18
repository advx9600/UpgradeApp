package com.dafeng.upgradeapp.ui.widget.titlebar;

import java.lang.ref.WeakReference;



import com.dafeng.upgradeapp.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class a {
	protected WeakReference<Activity> mActivity;
	private ImageView mBackLayout;
	protected TextView mCenterButton;
	protected View mCenterButtonLayout;
	protected TextView mCenterTitle;
	protected ICommonTitleBarClickListener mClickListener;
	protected View mLeftLayout;
	protected TextView mLeftTextView;
	private ImageView mRightBtnTag;
	protected View mRightLayout;
	protected TextView mRightTextView;
	protected LinearLayout mRootLayout;

	protected a(Activity paramActivity) {
		this.mActivity = new WeakReference<Activity>(paramActivity);
	}

	public void destroy() {
		this.mActivity.clear();
		this.mLeftLayout = null;
		this.mLeftTextView = null;
		this.mCenterTitle = null;
		this.mRightLayout = null;
		this.mRightTextView = null;
		this.mBackLayout = null;
		this.mRootLayout = null;
		this.mClickListener = null;
	}

	protected View findViewById(int paramInt) {
		Activity localActivity = (Activity) this.mActivity.get();
		if (localActivity != null) {
			return localActivity.findViewById(paramInt);
		}
		return null;
	}

	public TextView getRightTextView() {
		return this.mRightTextView;
	}

	public View getRootView() {
		return this.mRootLayout;
	}

	protected void initDefaultView() {
		((ViewStub) findViewById(R.id.viewstub_mynetdisk_app_title)).inflate();
		this.mRootLayout = ((LinearLayout) findViewById(R.id.root_top_title_layout));
		this.mCenterTitle = ((TextView) findViewById(R.id.top_title_center_layout));
		this.mRightLayout = ((LinearLayout) findViewById(R.id.top_title_right_layout));
		this.mRightBtnTag = ((ImageView) findViewById(R.id.right_btn_tag));
		this.mRightLayout.setOnClickListener(new b(this));
		this.mCenterButton = ((TextView) findViewById(R.id.top_center_button_title));
		this.mRightTextView = ((TextView) findViewById(R.id.top_right_textview));
		this.mBackLayout = ((ImageView) findViewById(R.id.icon_vertical_line));
		this.mBackLayout.setOnClickListener(new c(this));
	}

	public void setBackLayoutVisible(boolean paramBoolean) {
		ImageView localImageView;
		if (this.mBackLayout != null) {
			localImageView = this.mBackLayout;
			if (!paramBoolean) {
				localImageView.setVisibility(View.GONE);
			} else {
				localImageView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setCenterButtonLabel(int paramInt) {
		if (this.mCenterButtonLayout != null) {
			this.mCenterButton.setText(paramInt);
		}
	}

	public void setCenterButtonLabel(String paramString) {
		if (this.mCenterButtonLayout != null) {
			this.mCenterButton.setText(paramString);
		}
	}

	public void setCenterButtonVisible(boolean paramBoolean) {
		View localView;
		if (this.mCenterButtonLayout != null) {
			localView = this.mCenterButtonLayout;
			if (!paramBoolean) {
				localView.setVisibility(View.GONE);
			} else {
				localView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setCenterLabel(int paramInt) {
		if (this.mCenterTitle != null) {
			this.mCenterTitle.setText(paramInt);
		}
	}

	public void setCenterLabel(String paramString) {
		if (this.mCenterTitle != null) {
			this.mCenterTitle.setText(paramString);
		}
	}

	public void setCenterTitleVisible(boolean paramBoolean) {
		TextView localTextView;
		if (this.mCenterTitle != null) {
			localTextView = this.mCenterTitle;
			if (!paramBoolean) {
				localTextView.setVisibility(View.GONE);
			} else {
				localTextView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setLeftBackground(int paramInt) {
		if (this.mLeftTextView != null) {
			this.mLeftTextView.setBackgroundResource(paramInt);
		}
	}

	public void setLeftLabel(int paramInt) {
		if (this.mLeftTextView != null) {
			this.mLeftTextView.setText(paramInt);
		}
	}

	public void setLeftLabel(String paramString) {
		if (this.mLeftTextView != null) {
			this.mLeftTextView.setText(paramString);
		}
	}

	public void setLeftLayoutVisible(boolean paramBoolean) {
		View localView;
		if (this.mLeftLayout != null) {
			localView = this.mLeftLayout;
			if (!paramBoolean) {
				localView.setVisibility(View.GONE);
			} else {
				localView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setRightBackground(int paramInt) {
		if (this.mRightTextView != null) {
			this.mRightTextView.setBackgroundResource(paramInt);
		}
	}

	public void setRightButtonTagVisible(boolean paramBoolean) {
		ImageView localView;
		if (this.mRightBtnTag != null) {
			localView = this.mRightBtnTag;
			if (!paramBoolean) {
				localView.setVisibility(View.GONE);
			} else {
				localView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setRightEnable(boolean paramBoolean) {
		if (this.mRightLayout != null) {
			this.mRightLayout.setEnabled(paramBoolean);
		}
	}

	public void setRightLabel(int paramInt) {
		if (this.mRightTextView != null) {
			this.mRightTextView.setText(paramInt);
		}
	}

	public void setRightLabel(String paramString) {
		if (this.mRightTextView != null) {
			this.mRightTextView.setText(paramString);
		}
	}

	public void setRightLabelLayout(int paramInt1, int paramInt2) {
		if (this.mRightTextView != null) {
			RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(
					paramInt1, paramInt2);
			this.mRightTextView.setLayoutParams(localLayoutParams);
		}
	}

	public void setRightLayoutVisible(boolean paramBoolean) {
		View localView;
		if (this.mRightLayout != null) {
			localView = this.mRightLayout;
			if (!paramBoolean) {
				localView.setVisibility(View.GONE);
			} else {
				localView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setTopTitleBarClickListener(
			ICommonTitleBarClickListener paramICommonTitleBarClickListener) {
		this.mClickListener = paramICommonTitleBarClickListener;
	}
}
