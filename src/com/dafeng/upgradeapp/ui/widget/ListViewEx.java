package com.dafeng.upgradeapp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewEx extends ListView {

	@SuppressWarnings("unused")
	private final String TAG = "ListViewEx";

	public ListViewEx(Context paramContext) {
		super(paramContext);
	}

	public ListViewEx(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public ListViewEx(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		setChoiceMode(ListView.CHOICE_MODE_NONE);
	}

	public void clearCurrentItemChecked(int paramInt) {
		if ((getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE)
				&& (getAdapter() != null)) {
			getCheckedItemPositions().put(paramInt, false);
		}
		requestLayout();
	}

	public void setAllItemChecked(boolean paramBoolean) {
		if (getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
			ListAdapter localListAdapter = getAdapter();
			if (localListAdapter != null) {
				SparseBooleanArray localSparseBooleanArray = getCheckedItemPositions();
				int i = 0;
				int j = localListAdapter.getCount();
				while (i < j) {
					localSparseBooleanArray.put(i, paramBoolean);
					i++;
				}
			}
		}
		requestLayout();
	}

	public void setChoiceMode(int paramInt) {
		clearChoices();
		int i = getChildCount();
		for (int j = 0; j < i; j++) {
			View localView = getChildAt(j);
			if ((localView instanceof CheckableItemLayout)) {
				((CheckableItemLayout) localView).setChoiceMode(paramInt);
			}
		}
		super.setChoiceMode(paramInt);
	}

	public void setCurrentItemChecked(int paramInt) {
		if ((getChoiceMode() == CHOICE_MODE_MULTIPLE) && (getAdapter() != null)) {
			getCheckedItemPositions().put(paramInt, true);
		}
		requestLayout();
	}
}
