package com.dafeng.upgradeapp.ui;

import java.util.HashSet;
import java.util.Iterator;

import com.dafeng.upgradeapp.BaseActivity;
import com.dafeng.upgradeapp.R;
import com.dafeng.upgradeapp.dao.CustomApp;
import com.dafeng.upgradeapp.dao.CustomAppDao;
import com.dafeng.upgradeapp.dao.CustomAppDaoImpl;
import com.dafeng.upgradeapp.dao.DaoMaster;
import com.dafeng.upgradeapp.dao.DaoSession;
import com.dafeng.upgradeapp.db.DB;
import com.dafeng.upgradeapp.provider.CustomAppProvider;
import com.dafeng.upgradeapp.ui.widget.ListViewEx;
import com.dafeng.upgradeapp.ui.widget.titlebar.CommonTitleBar;
import com.dafeng.upgradeapp.ui.widget.titlebar.ICommonTitleBarClickListener;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;

public class BaseAppFragment extends Fragment implements
		AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
		ICommonTitleBarClickListener, IBaseAppFragment,
		LoaderManager.LoaderCallbacks<Cursor> {
	protected boolean isViewMode = true;
	protected CustomBaseAdapter mCursorAdapter;
	protected HashSet<Integer> selectedItems = new HashSet<Integer>();
	// protected ArrayList<Integer> selectedItemPositions;
	protected CommonTitleBar mTitleBarHelper;
	protected ListViewEx mListView;
	private View mContentView;
	// private g mPresenter;
	long mLastItemClickTime = 0L;
	private static final long ITEM_CLICK_INTERVAL = 500L;
	private long onClickBackTime;
	/* database */
	private SQLiteDatabase db;
	private CustomAppDao cusAppDao;
	protected CustomAppDaoImpl cusAppDaoImpl;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	// protected Cursor cursor;
	protected CustomApp curCustomApp;

	private static final int URL_LOADER = 0;

	private boolean isAllItemSelected() {
		int i;
		if (!this.isViewMode) {
			i = this.mCursorAdapter.getCount();
			if (i != 0) {
				if (this.selectedItems.size() != i) {
					return false;
				}
			}
		}
		return true;
	}

	protected void reFreshListView() {
		// if (new d(getActivity().getApplicationContext()).a().booleanValue())
		// {
		// NetdiskStatisticsLog.c("apiget_all");
		// NetdiskStatisticsLog.c("use_pull_refresh");
		// refresh();
		// return;
		// }
		getLoaderManager().restartLoader(URL_LOADER, null, this);
	}

	// private void refreshAfterDelete() {
	// this.selectedItemPositions.clear();
	// if (this.mCursorAdapter.isEmpty()) {
	// cancelEditMode();
	// }
	// }
	private void initRefreshListener() {
		// ai localai = new ai(this);
		// this.mListView.setOnRefreshListener(localai);
		getLoaderManager().initLoader(URL_LOADER, null, this);
	}

	public void cancelEditMode() {
		// this.mListView.setIsRefreshable(true);
		// FragmentActivity localFragmentActivity = getActivity();
		// if (localFragmentActivity != null) {
		// MainActivity localMainActivity = (MainActivity) localFragmentActivity
		// .getParent();
		// if (localMainActivity != null) {
		// localMainActivity.showTabs();
		// }
		// }
		this.mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
		this.isViewMode = true;
		updateTitleBar();
		this.mTitleBarHelper.setRightLabel(getResources().getString(
				R.string.add));
		this.selectedItems.clear();
	}

	protected View findViewById(int paramInt) {
		return this.mContentView.findViewById(paramInt);
	}

	protected void updateTitleBar() {
	}

	protected void setEditButtonsEnable(boolean paramBoolean) {
	}

	protected void initListHeaderView() {
	}

	protected void initView(View paramView) {
		// this.mEmptySrcollView = ((ScrollView) paramView
		// .findViewById(2131231245));
		// this.mEmptyView = ((EmptyView) paramView.findViewById(2131230788));
		// this.mEmptyView.setRefreshListener(new af(this));
		// this.mEmptySrcollView.setVisibility(8);
		// this.mBottomEmptyView = ((ImageView)
		// paramView.findViewById(2131230969));
		this.mListView = ((ListViewEx) paramView.findViewById(R.id.listview));
		initListHeaderView();
		this.mListView.setOnItemClickListener(this);
		this.mListView.setOnItemLongClickListener(this);
		// this.mListView.setKeyOfRefreshCompleteTime("pull_to_refresh_time");
		this.mCursorAdapter = new CustomBaseAdapter(getActivity(), mListView);
		this.mListView.setAdapter(this.mCursorAdapter);
		this.mCursorAdapter.setActionListener(new ag(this));
		// this.mSort = al.g();
		initRefreshListener();
	}

	protected void initListener() {
		this.mTitleBarHelper = ((BaseActivity) getActivity()).getTitleBar();
		this.mTitleBarHelper.setTopTitleBarClickListener(this);
		// IntentFilter localIntentFilter = new IntentFilter();
		// localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
		// localIntentFilter.addAction("android.intent.action.SCREEN_ON");
		// FragmentActivity localFragmentActivity = getActivity();
		// ScreenChangeReceiver localScreenChangeReceiver = new
		// ScreenChangeReceiver();
		// this.mScreenChangeReceiver = localScreenChangeReceiver;
		// localFragmentActivity.registerReceiver(localScreenChangeReceiver,
		// localIntentFilter);
	}

	private void initDatabase() {
		db = DB.getWritableDb(this.getActivity());
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		cusAppDao = daoSession.getCustomAppDao();
		cusAppDaoImpl = new CustomAppDaoImpl(this.getActivity(), cusAppDao);
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		// this.mPresenter = new g(this);
	}

	@Override
	public void onViewCreated(View paramView, Bundle paramBundle) {
		super.onViewCreated(paramView, paramBundle);
		initView(paramView);
		// initParam();
		initListener();
		initDatabase();
		updateTitleBar();
	}

	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
		this.mContentView = paramLayoutInflater.inflate(
				R.layout.fragment_my_netdisk, paramViewGroup, false);
		return this.mContentView;
	}

	@Override
	public void onDestroy() {
		db.close();
		super.onDestroy();
	}

	protected void changeListToEditMode() {
		this.mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		setEditButtonsEnable(false);
		this.isViewMode = false;
		this.mTitleBarHelper.c(0);
	}

	private void setEditModeTitle() {
		Resources localResources = getResources();
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = Integer.valueOf(this.selectedItems.size());
		String str = localResources.getString(R.string.selected_file_to_edit,
				arrayOfObject);
		this.mTitleBarHelper.setCenterLabel(str);
		this.mTitleBarHelper.c(0);
	}

	public void showEditModeView(int paramInt) {
		// this.mListView.setIsRefreshable(false);
		// MainActivity localMainActivity = (MainActivity) getActivity()
		// .getParent();
		// if (localMainActivity != null) {
		// localMainActivity.hideTabs();
		// }
		changeListToEditMode();
		int i = this.mListView.getHeaderViewsCount();
		this.mListView.setCurrentItemChecked(i + paramInt);
		this.mTitleBarHelper.setRightLayoutVisible(true);
		setEditModeTitle();
		Integer localInteger = Integer.valueOf(paramInt);
		if (!this.selectedItems.contains(localInteger)) {
			this.selectedItems.add(localInteger);
		}
		if (!this.selectedItems.isEmpty()) {
			setEditButtonsEnable(true);
		}
		Resources localResources = getResources();
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = Integer.valueOf(this.selectedItems.size());
		String str = localResources.getString(R.string.selected_file_to_edit,
				arrayOfObject);
		this.mTitleBarHelper.setCenterLabel(str);

		if (this.mCursorAdapter.getCount() == 1) {
			this.mTitleBarHelper.setRightLabel(getResources().getString(
					R.string.deselect_all));
		} else {

			this.mTitleBarHelper.setRightLabel(getResources().getString(
					R.string.select_all));
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		if (this.isViewMode) {
			showEditModeView(position - this.mListView.getHeaderViewsCount());
		}
		return true;
	}

	protected void selectItem(int paramInt) {
		Integer localInteger = Integer.valueOf(paramInt);
		if (!this.selectedItems.contains(localInteger)) {
			this.selectedItems.add(localInteger);
		} else {
			this.selectedItems.remove(localInteger);
		}
		if (this.selectedItems.isEmpty()) {
			cancelEditMode();
			updateTitleBar();
			return;
		}
		//
		// if (this.selectedItems.size() == 1) {
		// // this.mRenameEnabled = true;
		// } else {
		//
		// }
		if (isAllItemSelected()) {
			this.mTitleBarHelper.setRightLabel(getResources().getString(
					R.string.deselect_all));
		} else {
			this.mTitleBarHelper.setRightLabel(getResources().getString(
					R.string.select_all));
		}
		setEditModeTitle();
		setEditButtonsEnable(true);
	}

	protected void onItemClickAction(CustomApp customApp) {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		int i = position - this.mListView.getHeaderViewsCount();
		if (this.isViewMode) {
			long l = System.currentTimeMillis();
			if (l - this.mLastItemClickTime < ITEM_CLICK_INTERVAL) {
				return;
			}

			curCustomApp = cusAppDao.findById(id);
			onItemClickAction(curCustomApp);
			// Adapter localAdapter = paramAdapterView.getAdapter();
			// if ((localAdapter instanceof HeaderViewListAdapter)) {}
			// for (y localy =
			// ((MyNetdiskAdapter)((HeaderViewListAdapter)localAdapter).getWrappedAdapter()).getCursor();
			// localy == null; localy =
			// ((MyNetdiskAdapter)localAdapter).getCursor())
			// {
			// ak.d("MyNetdiskFragment",
			// "onItemClick item is null ,posWithoutHeader:" + i);
			// return;
			// }
			// viewItem(localy, i);
			// return;
		} else {
			selectItem(i);
		}
	}

	@Override
	public void onBackButtonClicked() {
		// TODO Auto-generated method stub
		long l = System.currentTimeMillis();
		if (l - this.onClickBackTime < ITEM_CLICK_INTERVAL) {
			return;
		}
		this.onClickBackTime = l;
		back();
	}

	public void back() {
		if (!this.isViewMode) {
			cancelEditMode();
			updateTitleBar();
			return;
		}

		this.getActivity().finish();
	}

	@Override
	public void onRightButtonClicked() {
		// TODO Auto-generated method stub
		selectAll();
	}

	private void selectAll() {
		int i = 0;
		if (isAllItemSelected()) {
			// this.mRenameEnabled = true;
			cancelEditMode();
			updateTitleBar();
			return;
		}
		this.mTitleBarHelper.setRightLabel(getResources().getString(
				R.string.deselect_all));
		this.mListView.setAllItemChecked(true);
		setEditButtonsEnable(true);
		// this.mRenameEnabled = false;
		int j = this.mCursorAdapter.getCount();
		while (i < j) {
			this.selectedItems.add(Integer.valueOf(i));
			i++;
		}
		setEditModeTitle();
	}

	@Override
	public void showAction(View view) {
		// TODO Auto-generated method stub
		showEditModeView(((Integer) view.getTag()).intValue());
	}

	@Override
	public long[] getItemIds() {
		long[] ids = new long[this.selectedItems.size()];
		Iterator<Integer> r = selectedItems.iterator();
		int i = 0;
		while (r.hasNext()) {
			ids[i++] = this.mCursorAdapter.getItemId(r.next());
		}
		return ids;
	}

	@Override
	public void delSuccess() {
		// this.selectedItemPositions.clear();
		cancelEditMode();
	}

	@Override
	public android.support.v4.content.Loader<Cursor> onCreateLoader(
			int loaderID, Bundle arg1) {
		// TODO Auto-generated method stub
		Uri dataUrl = Uri.parse(CustomAppProvider.URL);
		switch (loaderID) {
		case URL_LOADER:
			return new CursorLoader(getActivity(), // Parent activity context
					dataUrl, // Table to query
					null, // Projection to return
					null, // No selection clause
					null, // No selection arguments
					null // Default sort order
			);
		}
		return null;
	}

	@Override
	public void onLoadFinished(
			android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
		// TODO Auto-generated method stub
		mCursorAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(android.support.v4.content.Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		mCursorAdapter.swapCursor(null);
	}

}
