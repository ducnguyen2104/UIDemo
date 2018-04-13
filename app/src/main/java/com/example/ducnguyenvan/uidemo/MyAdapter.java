package com.example.ducnguyenvan.uidemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_1_PIC = 1;
    private static final int ITEM_3_PICS = 2;
    private static final int ITEM_VIDEO = 3;
    private static final int ITEM_LABEL = 4;
    private static final int ITEM_BUTTON = 5;
    ArrayList<Object> items;
    Context context;

    public MyAdapter(ArrayList<Object> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object item = items.get(position);
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
        else {
            ((ItemButtonViewHolder) holder).bind((ItemButton)item);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
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
        if (items.get(position) instanceof Item1Pic) {
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
        return items.size();
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
            src = (TextView)itemView.findViewById(R.id.txtSource);
            cmts = (TextView)itemView.findViewById(R.id.txtComments);
        }

        public void bind(Item1Pic item) {
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
            img2 = (ImageView)itemView.findViewById(R.id.img_view2);
            img3 = (ImageView)itemView.findViewById(R.id.img_view3);
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
    }

    private static class ItemLabelViewHolder extends RecyclerView.ViewHolder {

        TextView content;
        public ItemLabelViewHolder (View itemView) {
            super(itemView);
            content = (TextView)itemView.findViewById(R.id.txtContent);
        }

        public void bind(ItemLabel item) {
            content.setText(item.getContent());
        }
    }

    private static class ItemButtonViewHolder extends RecyclerView.ViewHolder {

        Button content;
        public ItemButtonViewHolder (View itemView) {
            super(itemView);
            content = (Button) itemView.findViewById(R.id.btn);
        }

        public void bind(ItemButton item) {
            content.setText(item.getContent());
        }
    }
}
