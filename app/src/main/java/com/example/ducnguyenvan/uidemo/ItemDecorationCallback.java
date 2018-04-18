package com.example.ducnguyenvan.uidemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface ItemDecorationCallback {
    boolean shouldDecor(@NonNull MyItem item, @Nullable MyItem nextItem);
}
