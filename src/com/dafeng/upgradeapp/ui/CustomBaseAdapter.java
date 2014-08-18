package com.dafeng.upgradeapp.ui;

import java.text.SimpleDateFormat;

import com.dafeng.upgradeapp.R;
import com.dafeng.upgradeapp.ui.widget.CheckableItemLayout;
import com.dafeng.upgradeapp.ui.widget.ListViewEx;
import com.dafeng.upgradeapp.util.PkgProcess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends CursorAdapter {

	@SuppressWarnings("unused")
	private static final String TAG = "CustomBaseAdapter";
	@SuppressLint("SimpleDateFormat")
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd  HH:mm");
	private View.OnClickListener mActionListener;
	private final LayoutInflater mInflater;
	private final Context mContext;
	private ListViewEx mListView;

	// private final ListViewEx mListView;

	public CustomBaseAdapter(Context paramContext, ListViewEx listView) {
		super(paramContext, null, false);
		mListView = listView;
		mContext = paramContext;
		this.mInflater = ((LayoutInflater) paramContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	@SuppressLint("NewApi")
	private void bindListView(View paramView, Cursor c) {
		int i = this.mListView.getChoiceMode();
		((CheckableItemLayout) paramView).setChoiceMode(i);

		String pkgName = c.getString(1);
		cl localcl = (cl) paramView.getTag();
		localcl.b.setText(pkgName);
		localcl.f.setTag(Integer.valueOf(c.getPosition()));
		if (PkgProcess.isExist(mContext, pkgName)) {
			localcl.e.setImageDrawable(PkgProcess.getLabel(mContext, pkgName));
			localcl.c.setText(PkgProcess.getAppName(mContext, pkgName));
		} else {
			localcl.e.setImageDrawable(null);
			localcl.c.setText("³ÌÐòÎ´°²×°");
		}
	}

	private View inflateView(ViewGroup paramViewGroup) {
		View localView = this.mInflater.inflate(R.layout.item_broad_filelist,
				paramViewGroup, false);
		cl localcl = new cl();
		localcl.b = (TextView) localView.findViewById(R.id.text1);
		localcl.c = (TextView) localView.findViewById(R.id.filesize);
		localcl.e = (ImageView) localView.findViewById(R.id.image1);
		localcl.f = localView.findViewById(android.R.id.button1);
		localcl.f.setOnClickListener(this.mActionListener);
		localView.setTag(localcl);
		return localView;
	}

	@Override
	public boolean hasStableIds() {
		// allow multiple choice must return false
		return false;
	}

	@Override
	public void bindView(View paramView, Context paramContext,
			Cursor paramCursor) {
		bindListView(paramView, paramCursor);
	}

	@Override
	public View newView(Context paramContext, Cursor paramCursor,
			ViewGroup paramViewGroup) {
		return inflateView(paramViewGroup);
	}

	public void setActionListener(View.OnClickListener paramOnClickListener) {
		this.mActionListener = paramOnClickListener;
	}
}
