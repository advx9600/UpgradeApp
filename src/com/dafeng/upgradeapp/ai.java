package com.dafeng.upgradeapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;

public class ai {
	private static final Object a = new Object();
	private static ArrayList<Handler> b = new ArrayList<Handler>();

	public static void a(int paramInt) {
		a(paramInt, 0, 0, null, null);
	}

	public static void a(int paramInt1, int paramInt2, int paramInt3) {
		synchronized (a) {
			Iterator<Handler> localIterator = b.iterator();
			while (localIterator.hasNext()) {
				Handler localHandler = (Handler) localIterator.next();
				if (localHandler != null) {
					Message localMessage = localHandler.obtainMessage();
					localMessage.what = paramInt1;
					localMessage.arg1 = paramInt2;
					localMessage.arg2 = paramInt3;
					localMessage.sendToTarget();
				}
			}
		}
	}

	public static void a(int paramInt1, int paramInt2, int paramInt3,
			Object paramObject) {
		synchronized (a) {
			Iterator<Handler> localIterator = b.iterator();
			while (localIterator.hasNext()) {
				Handler localHandler = (Handler) localIterator.next();
				if (localHandler != null) {
					Message localMessage = localHandler.obtainMessage();
					localMessage.what = paramInt1;
					localMessage.arg1 = paramInt2;
					localMessage.arg2 = paramInt3;
					localMessage.obj = paramObject;
					localMessage.sendToTarget();
				}
			}
		}
	}

	public static void a(int paramInt1, int paramInt2, int paramInt3,
			Object paramObject, Bundle paramBundle) {
		if ((b == null) || (b.isEmpty())) {
			return;
		}
		synchronized (a) {
			Iterator<Handler> localIterator = b.iterator();
			if (localIterator.hasNext()) {
				Handler localHandler = (Handler) localIterator.next();
				Message localMessage = Message.obtain();
				localMessage.what = paramInt1;
				localMessage.arg1 = paramInt2;
				localMessage.arg2 = paramInt3;
				localMessage.obj = paramObject;
				localMessage.setData(paramBundle);
				localHandler.sendMessage(localMessage);
			}
		}
	}

	public static void a(Handler paramHandler) {
		if (paramHandler == null) {
			return;
		}
		synchronized (a) {
			b.add(paramHandler);
			return;
		}
	}

	public static void b(Handler paramHandler) {
		synchronized (a) {
			b.remove(paramHandler);
			return;
		}
	}
}
