package com.example.ducnguyenvan.uidemo.diffutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.example.ducnguyenvan.uidemo.model.Item1Pic;
import com.example.ducnguyenvan.uidemo.model.Item3Pics;
import com.example.ducnguyenvan.uidemo.model.ItemVideo;
import com.example.ducnguyenvan.uidemo.model.MyItem;

import java.util.ArrayList;

public class ItemListDiffCallback extends DiffUtil.Callback {

    private ArrayList<MyItem> oldList;

    public ItemListDiffCallback(ArrayList<MyItem> oldList, ArrayList<MyItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    private ArrayList<MyItem> newList;


    @Override
    public int getOldListSize() {
        return  (oldList != null) ? oldList.size() : 0 ;
    }

    @Override
    public int getNewListSize() {
        return (newList != null) ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        //Log.i("same item: ", ""+newList.get(newItemPosition).getTitle().equals(oldList.get(oldItemPosition).getTitle()));
        return newList.get(newItemPosition).getTitle().equals(oldList.get(oldItemPosition).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).equals(oldList.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //return super.getChangePayload(oldItemPosition, newItemPosition);
        MyItem oldItem = oldList.get(oldItemPosition);
        MyItem newItem = newList.get(newItemPosition);
        Log.i("get change payload", "" + newItem.getClass().toString());
        Bundle diffBundle = new Bundle();
        if (newItem instanceof Item1Pic) {
            if(((Item1Pic) newItem).getComments() != ((Item1Pic)oldItem).getComments()) {
                diffBundle.putInt("comments", ((Item1Pic)newItem).getComments());
            }
            if(((Item1Pic) newItem).getImg() != ((Item1Pic)oldItem).getImg()) {
                diffBundle.putInt("img", ((Item1Pic)oldItem).getImg());
            }
        }
        else if (newItem instanceof Item3Pics) {
            if(((Item3Pics) newItem).getComments() != ((Item3Pics)oldItem).getComments()) {
                diffBundle.putInt("comments", ((Item3Pics)newItem).getComments());
            }
            if(((Item3Pics) newItem).getImg1() != ((Item3Pics)oldItem).getImg1()) {
                diffBundle.putInt("img1", ((Item3Pics)oldItem).getImg1());
            }
            if(((Item3Pics) newItem).getImg2() != ((Item3Pics)oldItem).getImg2()) {
                diffBundle.putInt("img2", ((Item3Pics)oldItem).getImg2());
            }
            if(((Item3Pics) newItem).getImg3() != ((Item3Pics)oldItem).getImg3()) {
                diffBundle.putInt("img3", ((Item3Pics)oldItem).getImg3());
            }
        }
        else if (newItem instanceof ItemVideo) {
            if(((ItemVideo) newItem).getComments() != ((ItemVideo)oldItem).getComments()) {
                diffBundle.putInt("comments", ((ItemVideo)newItem).getComments());
            }
            if(!((ItemVideo) newItem).getVideo().toString().equals(((ItemVideo)oldItem).getVideo().toString())) {
                diffBundle.putString("video", ((ItemVideo)newItem).getVideo().toString());
            }
        }
        return  diffBundle;
    }
}
