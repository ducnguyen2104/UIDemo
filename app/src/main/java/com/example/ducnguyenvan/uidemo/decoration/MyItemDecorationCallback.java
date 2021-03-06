package com.example.ducnguyenvan.uidemo.decoration;

import android.support.annotation.Nullable;

import com.example.ducnguyenvan.uidemo.model.MyItem;

public class MyItemDecorationCallback implements ItemDecorationCallback {
    private Class<? extends MyItem>[] mClasses;
    public MyItemDecorationCallback(Class<? extends MyItem>... classes) {
        mClasses = classes;
    }

    protected boolean valid(MyItem item) {
        if (item != null)
            for (Class clazz : mClasses)
                if (clazz.isAssignableFrom(item.getClass()))
                    return true;
        return false;
    }

    @Override
    public boolean shouldDecor(MyItem item, @Nullable MyItem nextItem) {
        return nextItem != null && valid(item);
    }
}
