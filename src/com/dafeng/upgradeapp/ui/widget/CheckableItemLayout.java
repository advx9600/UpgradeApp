package com.dafeng.upgradeapp.ui.widget;

import com.dafeng.upgradeapp.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class CheckableItemLayout extends RelativeLayout implements Checkable {

	private static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };
	private static final int BASE_PADDING = 50;
	private Drawable mCheckMarkDrawable;
	private int mBasePaddingRight;
	private boolean mChecked;
	private int mChoiceMode = 0;

	public CheckableItemLayout(Context paramContext) {
		this(paramContext, null, 0);
	}

	public CheckableItemLayout(Context paramContext,
			AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public CheckableItemLayout(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		// setClickable(true);
		setWillNotDraw(false);
	}

	@Override
	public void setChecked(boolean checked) {
		// TODO Auto-generated method stub
		if (this.mChecked != checked) {
			this.mChecked = checked;
			refreshDrawableState();
		}
	}

	@Override
	public boolean isChecked() {
		// TODO Auto-generated method stub
		return this.mChecked;
	}

	@Override
	public void toggle() {
		// TODO Auto-generated method stub
		this.mChecked = !this.mChecked;
		setChecked(this.mChecked);
	}

	private void setCheckMarkDrawable(Drawable paramDrawable) {
		if (this.mCheckMarkDrawable != null) {
			this.mCheckMarkDrawable.setCallback(null);
			unscheduleDrawable(this.mCheckMarkDrawable);
		}
		boolean bool;
		this.mCheckMarkDrawable = paramDrawable;		
		if (paramDrawable != null) {
			paramDrawable.setCallback(this);
			if (getVisibility() == View.VISIBLE) {
				bool = true;
				paramDrawable.setVisible(bool, false);
				paramDrawable.setState(CHECKED_STATE_SET);
				paramDrawable.setState(getDrawableState());
			}
			super.setPadding(getPaddingLeft(), getPaddingTop(), BASE_PADDING,
					getPaddingBottom());
		} else {
			super.setPadding(getPaddingLeft(), getPaddingTop(),
					this.mBasePaddingRight, getPaddingBottom());
		}
	}

	@Override
	public boolean dispatchPopulateAccessibilityEvent(
			AccessibilityEvent paramAccessibilityEvent) {
		boolean bool = super
				.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
		if (!bool) {
			paramAccessibilityEvent.setChecked(this.mChecked);
		}
		return bool;
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		if (this.mCheckMarkDrawable != null) {
			int[] arrayOfInt = getDrawableState();
			this.mCheckMarkDrawable.setState(arrayOfInt);
			invalidate();
		}
	}

	public int getChoiceMode() {
		return this.mChoiceMode;
	}

	@Override
	protected int[] onCreateDrawableState(int paramInt) {
		int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
		if (isChecked()) {
			mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
		}
		return arrayOfInt;
	}

	@Override
	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		Drawable localDrawable = this.mCheckMarkDrawable;
		if (localDrawable != null) {			
			int i = localDrawable.getIntrinsicHeight();
			int j = localDrawable.getIntrinsicWidth();
			int k = (getHeight() - i) / 2;
			int m = BASE_PADDING;
			int n = getWidth() - m + (m - j) / 2;
			localDrawable.setBounds(n, k, j + n, i + k);
			localDrawable.draw(paramCanvas);
		}
	}

	@Override
	protected void onMeasure(int paramInt1, int paramInt2) {
		// int i = getChildCount();
		// int j = 0;
		// while (j < i) {
		// View localView = getChildAt(j);
		// if ((localView.getId() == android.R.id.button1) &&
		// (this.mCheckMarkDrawable != null)
		// && (this.mCheckMarkDrawable.isVisible())) {
		// localView.setPadding(0, 0, BASE_PADDING, 0);
		// } else {
		// if ((localView.getId() == 1)
		// && (((this.mCheckMarkDrawable != null) && (!this.mCheckMarkDrawable
		// .isVisible())) || (this.mCheckMarkDrawable == null))) {
		// localView.setPadding(0, 0, 8, 0);
		// }
		// }
		// j++;
		// }
		super.onMeasure(paramInt1, paramInt2);
	}

	public void setChoiceMode(int paramInt) {
		if (this.mChoiceMode == paramInt) {
			return;
		}
		this.mChoiceMode = paramInt;
		Drawable localDrawable;
		View localView = findViewById(android.R.id.button1);
		if (this.mChoiceMode == ListView.CHOICE_MODE_SINGLE) {
			localDrawable = getResources().getDrawable(
					R.drawable.rice_btn_radio);
			if (localView != null) {
				localView.setVisibility(View.INVISIBLE);
			}
		} else if (this.mChoiceMode == ListView.CHOICE_MODE_MULTIPLE) {
			if (localView != null) {
				localView.setVisibility(View.INVISIBLE);
			}
			localDrawable = getResources().getDrawable(
					R.drawable.rice_btn_check);
		} else {
			localDrawable = null;
			if (localView != null) {
				localView.setVisibility(View.VISIBLE);
			}
		}
		setCheckMarkDrawable(localDrawable);
	}

	@Override
	public void setPadding(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		super.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
		if (this.mChoiceMode == ListView.CHOICE_MODE_NONE) {
			this.mBasePaddingRight = getPaddingRight();
		}
	}

}
