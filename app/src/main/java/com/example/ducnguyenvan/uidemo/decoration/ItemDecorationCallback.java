package com.example.ducnguyenvan.uidemo.decoration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.ducnguyenvan.uidemo.model.MyItem;

public interface ItemDecorationCallback {
    boolean shouldDecor(@NonNull MyItem item, @Nullable MyItem nextItem);
}
