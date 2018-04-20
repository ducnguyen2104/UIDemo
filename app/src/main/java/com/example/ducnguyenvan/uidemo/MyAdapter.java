package com.example.ducnguyenvan.uidemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.ducnguyenvan.uidemo.diffutil.ItemListDiffCallback;
import com.example.ducnguyenvan.uidemo.diffutil.OnLoadMoreListener;
import com.example.ducnguyenvan.uidemo.model.Item1Pic;
import com.example.ducnguyenvan.uidemo.model.Item3Pics;
import com.example.ducnguyenvan.uidemo.model.ItemButton;
import com.example.ducnguyenvan.uidemo.model.ItemLabel;
import com.example.ducnguyenvan.uidemo.model.ItemVideo;
import com.example.ducnguyenvan.uidemo.model.MyItem;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NO_ITEM = -1;
    private static final int ITEM_LOADING = 0;
    private static final int ITEM_1_PIC = 1;
    private static final int ITEM_3_PICS = 2;
    private static final int ITEM_VIDEO = 3;
    private static final int ITEM_LABEL = 4;
    private static final int ITEM_BUTTON = 5;
    private static final int PADDING = 10;

    public static int picWidth = (MainActivity.scrWidth - PADDING*4)/3;
    public static int picHeight = picWidth*3/4;
    ArrayList<MyItem> items;
    Context context;
    private int screenWidth;
    private int screenHeight;

    private int visibleThreshold = 6;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
       onLoadMoreListener = mOnLoadMoreListener;
    }

    public MyAdapter(ArrayList<MyItem> items, Context context, RecyclerView recyclerView) {
        this.items = items;
        this.context = context;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if ( onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    loading = true;
                }
            }
        });

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //Log.i("bind","no payloads");

        MyItem item = items.get(position);
        if(holder instanceof Item1PicViewHolder) {
            ((Item1PicViewHolder) holder).bind((Item1Pic)item);
        }
        else if(holder instanceof Item3PicsViewHolder) {
            ((Item3PicsViewHolder) holder).bind((Item3Pics)item);
        }
        else if(holder instanceof ItemVideoViewHolder){
            ((ItemVideoViewHolder) holder).bind((ItemVideo)item);
        }
        else if(holder instanceof ItemLabelViewHolder) {
            ((ItemLabelViewHolder) holder).bind((ItemLabel)item);
        }
        else if(holder instanceof ItemButtonViewHolder){
            ((ItemButtonViewHolder) holder).bind((ItemButton)item);
        }
        else {
            //((ItemLoadingViewHolder) holder).bind((ItemLoading)item);
            ItemLoadingViewHolder loadingViewHolder = (ItemLoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        MyItem item = items.get(position);
        if(payloads.isEmpty()) {
            //Log.i("bind", "no change");
            onBindViewHolder(holder,position);
        }
        else {
            //Log.i("bind", "change");
            Bundle bundle = (Bundle)payloads.get(0);
            //Log.i("bundle","get");
            if(holder instanceof Item1PicViewHolder) {
                ((Item1PicViewHolder) holder).bind((Item1Pic)item, bundle);
            }
            else if(holder instanceof Item3PicsViewHolder) {
                ((Item3PicsViewHolder) holder).bind((Item3Pics)item, bundle);
            }
            else {
                ((ItemVideoViewHolder) holder).bind((ItemVideo)item, bundle);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_LOADING) {
            View view = layoutInflater.inflate(R.layout.loading_row, parent, false);
            return new ItemLoadingViewHolder(view);
        }
        if (viewType == ITEM_1_PIC) {
            View view = layoutInflater.inflate(R.layout.one_pic_row, parent, false);
            return new Item1PicViewHolder(view);
        }
        else if (viewType == ITEM_3_PICS){
            View view = layoutInflater.inflate(R.layout.three_pics_row,parent, false);
            return new Item3PicsViewHolder(view);
        }
        else if (viewType == ITEM_VIDEO){
            View view = layoutInflater.inflate(R.layout.video_row,parent, false);
            return new ItemVideoViewHolder(view);
        }
        else if (viewType == ITEM_LABEL) {
            View view = layoutInflater.inflate(R.layout.label_row,parent,false);
            return new ItemLabelViewHolder(view);
        }
        else {
            View view = layoutInflater.inflate(R.layout.button_row,parent,false);
            return new ItemButtonViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= items.size())
            return NO_ITEM;
        if (items.get(position) == null) {
            return ITEM_LOADING;
        }
        else if (items.get(position) instanceof Item1Pic) {
            return ITEM_1_PIC;
        }
        else if (items.get(position) instanceof Item3Pics){
            return ITEM_3_PICS;
        }
        else if (items.get(position) instanceof ItemVideo){
            return ITEM_VIDEO;
        }
        else if (items.get(position) instanceof ItemLabel){
            return ITEM_LABEL;
        }
        else {
            return ITEM_BUTTON;
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setLoaded() {
        loading = false;
    }

    public void updateListItems(ArrayList<MyItem> newListItems) {
        final ItemListDiffCallback diffCallback = new ItemListDiffCallback(this.items, newListItems);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        //Log.i("thread", "" + Thread.currentThread().getName());
        if (this.items != newListItems) {
            this.items.clear();
            this.items.addAll(newListItems);
        }
        diffResult.dispatchUpdatesTo(this);
    }

    private static class Item1PicViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        TextView src;
        TextView cmts;
        public Item1PicViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.txtTitle);
            img = (ImageView)itemView.findViewById(R.id.img_view);
            img.getLayoutParams().height = picHeight;
            img.getLayoutParams().width = picWidth;
            //img.setPadding(PADDING,0,0,0);
            src = (TextView)itemView.findViewById(R.id.txtSource);
            //src.setPaddingRelative(0,0, 0, R.id.img_view);
            cmts = (TextView)itemView.findViewById(R.id.txtComments);
        }


        public void bind(Item1Pic item) {
            //Log.i("bind", "...");
            title.setText(item.getTitle());
            img.setImageResource(item.getImg());
            long time = System.currentTimeMillis() - item.getTimestamp().getTime();
            int timePassHours = (int)time/(1000*60*60);
            if (timePassHours >= 24) {
                int timePassDays = (int)time/(1000*60*60*24);
                src.setText(item.getSource() + "  •  " + timePassDays + " day(s) ago"  );
            }
            else if (timePassHours >= 1) {
                src.setText(item.getSource() + "  •  " + timePassHours + " hour(s) ago" );
            }
            else {
                int timePassMins = (int)time/(1000*60);
                src.setText(item.getSource() + "  •  " + timePassMins + " minute(s) ago" );
            }
            int numOfComments = item.getComments();
            if (numOfComments == 0) {
                cmts.setText("");
            }
            else {
                cmts.setText(numOfComments + " comment(s)");
            }
        }

        public void bind(Item1Pic item, Bundle bundle) {
            for (String key : bundle.keySet()) {
                if(key.equals("comments")) {
                    int numOfComments = bundle.getInt("comments");
                    if (numOfComments == 0) {
                        cmts.setText("");
                    }
                    else {
                        cmts.setText(numOfComments + " comment(s)");
                    }
                }
                if(key.equals("img")) {
                    img.setImageResource(bundle.getInt("img"));
                }
            }
        }
    }

    private static class Item3PicsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img1;
        ImageView img2;
        ImageView img3;
        TextView src;
        TextView cmts;

        public Item3PicsViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.txtTitle);
            img1 = (ImageView)itemView.findViewById(R.id.img_view1);
            img1.getLayoutParams().height = picHeight;
            img1.getLayoutParams().width = picWidth;
            //img1.setPadding(PADDING,0,PADDING,0);
            img2 = (ImageView)itemView.findViewById(R.id.img_view2);
            img2.getLayoutParams().height = picHeight;
            img2.getLayoutParams().width = picWidth;
            //img2.setPadding(0,0,PADDING,0);
            img3 = (ImageView)itemView.findViewById(R.id.img_view3);
            img3.getLayoutParams().height = picHeight;
            img3.getLayoutParams().width = picWidth;
            //img3.setPadding(0,0,PADDING, 0);
            src = (TextView)itemView.findViewById(R.id.txtSource);
            cmts = (TextView)itemView.findViewById(R.id.txtComments);
        }

        public void bind(Item3Pics item) {
            title.setText(item.getTitle());
            img1.setImageResource(item.getImg1());
            img2.setImageResource(item.getImg2());
            img3.setImageResource(item.getImg3());
            long time = System.currentTimeMillis() - item.getTimestamp().getTime();
            int timePassHours = (int)time/(1000*60*60);
            if (timePassHours >= 24) {
                int timePassDays = (int)time/(1000*60*60*24);
                src.setText(item.getSource() + "  •  " + timePassDays + " day(s) ago"  );
            }
            else if (timePassHours >= 1) {
                src.setText(item.getSource() + "  •  " + timePassHours + " hour(s) ago" );
            }
            else {
                int timePassMins = (int)time/(1000*60);
                src.setText(item.getSource() + "  •  " + timePassMins + " minute(s) ago" );
            }
            int numOfComments = item.getComments();
            if (numOfComments == 0) {
                cmts.setText("");
            }
            else {
                cmts.setText(numOfComments + " comment(s)");
            }
        }
        public void bind(Item3Pics item, Bundle bundle) {
            for (String key : bundle.keySet()) {
                if(key.equals("comments")) {
                    int numOfComments = bundle.getInt("comments");
                    if (numOfComments == 0) {
                        cmts.setText("");
                    }
                    else {
                        cmts.setText(numOfComments + " comment(s)");
                    }
                }
                if(key.equals("img1")) {
                    img1.setImageResource(bundle.getInt("img1"));
                }
                if(key.equals("img2")) {
                    img2.setImageResource(bundle.getInt("img2"));
                }
                if(key.equals("img3")) {
                    img3.setImageResource(bundle.getInt("img3"));
                }
            }
        }
    }

    private static class ItemVideoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        VideoView video;
        TextView src;
        TextView cmts;

        public ItemVideoViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.txtTitle);
            video = (VideoView)itemView.findViewById(R.id.video_view);
            src = (TextView)itemView.findViewById(R.id.txtSource);
            cmts = (TextView)itemView.findViewById(R.id.txtComments);
        }

        public void bind(ItemVideo item) {
            title.setText(item.getTitle());
            video.setVideoURI(item.getVideo());
            video.start();
            long time = System.currentTimeMillis() - item.getTimestamp().getTime();
            int timePassHours = (int)time/(1000*60*60);
            if (timePassHours >= 24) {
                int timePassDays = (int)time/(1000*60*60*24);
                src.setText(item.getSource() + "  •  " + timePassDays + " day(s) ago"  );
            }
            else if (timePassHours >= 1) {
                src.setText(item.getSource() + "  •  " + timePassHours + " hour(s) ago" );
            }
            else {
                int timePassMins = (int)time/(1000*60);
                src.setText(item.getSource() + "  •  " + timePassMins + " minute(s) ago" );
            }
            int numOfComments = item.getComments();
            if (numOfComments == 0) {
                cmts.setText("");
            }
            else {
                cmts.setText(numOfComments + " comment(s)");
            }
        }

        public void bind(ItemVideo item, Bundle bundle) {
            for (String key : bundle.keySet()) {
                if(key.equals("comments")) {
                    int numOfComments = bundle.getInt("comments");
                    if (numOfComments == 0) {
                        cmts.setText("");
                    }
                    else {
                        cmts.setText(numOfComments + " comment(s)");
                    }
                }
                if(key.equals("video")) {
                    video.setVideoURI(Uri.parse(bundle.getString("video")));
                }
            }
        }
    }

    private static class ItemLabelViewHolder extends RecyclerView.ViewHolder {

        TextView content;
        public ItemLabelViewHolder (View itemView) {
            super(itemView);
            content = (TextView)itemView.findViewById(R.id.txtContent);
        }

        public void bind(ItemLabel item) {
            content.setText(item.getTitle());
        }
    }

    private static class ItemButtonViewHolder extends RecyclerView.ViewHolder {

        Button content;
        public ItemButtonViewHolder (View itemView) {
            super(itemView);
            content = (Button) itemView.findViewById(R.id.btn);
        }

        public void bind(ItemButton item) {
            content.setText(item.getTitle());
        }
    }

    static class ItemLoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ItemLoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
            progressBar.getLayoutParams().height = picHeight/2;
        }
    }
    public MyItem getItemAt(int position) {
        if (position >= 0 && position < items.size()) {
            //Log.i("get item", "at " + position);
            return (items != null && items.size() > position) ? items.get(position) : null;
        }
        else return null;
    }
}
