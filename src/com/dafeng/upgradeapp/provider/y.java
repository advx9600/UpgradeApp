package com.dafeng.upgradeapp.provider;

import java.util.Arrays;

import android.database.Cursor;
import android.database.CursorWrapper;

public class y<T> extends CursorWrapper {
	private ICursorCreator<T> a;

	public y(Cursor paramCursor, ICursorCreator<T> paramICursorCreator) {
		super(paramCursor);
		this.a = paramICursorCreator;
	}

	public T a() {
		return this.a.createFromCursor(this);
	}

	public String toString() {
		return "ObjectCursor [mFactory=" + this.a + ", getModel()=" + a()
				+ ", isClosed()=" + isClosed() + ", getCount()=" + getCount()
				+ ", moveToFirst()=" + moveToFirst() + ", getColumnCount()="
				+ getColumnCount() + ", getColumnNames()="
				+ Arrays.toString(getColumnNames()) + ", getExtras()="
				+ getExtras() + ", getWantsAllOnMoveCalls()="
				+ getWantsAllOnMoveCalls() + ", isAfterLast()=" + isAfterLast()
				+ ", isBeforeFirst()=" + isBeforeFirst() + ", isFirst()="
				+ isFirst() + ", isLast()=" + isLast() + ", moveToLast()="
				+ moveToLast() + ", moveToNext()=" + moveToNext()
				+ ", getPosition()=" + getPosition() + ", moveToPrevious()="
				+ moveToPrevious() + ", requery()=" + requery()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
