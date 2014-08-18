package com.dafeng.upgradeapp.provider;

import android.database.Cursor;

public abstract interface ICursorCreator<T>
{
  public abstract T createFromCursor(Cursor paramCursor);
}