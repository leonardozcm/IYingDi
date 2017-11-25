package com.bingyan.com.iyingdi.View;


import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingyan.com.iyingdi.Model.ItemBeam;
import com.bingyan.com.iyingdi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 59771 on 2017/11/24.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder>{
    private List<ItemBeam> itemBeamList=new ArrayList<>();
    private Drawable drawable;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView titleText;
        TextView intro;
        TextView author;
        public ViewHolder(View view){
            super(view);
            cover=(ImageView)view.findViewById(R.id.cover);
            titleText=(TextView)view.findViewById(R.id.titleText);
            intro=(TextView)view.findViewById(R.id.intro);
            author=(TextView)view.findViewById(R.id.author);
        }
    }

    //public TitleAdapter(List<ItemBeam> list){itemBeamList=list;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item,parent,false);
        final TitleAdapter.ViewHolder holder=new TitleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.author.setText(itemBeamList.get(position).getAuthor());
        holder.titleText.setText(itemBeamList.get(position).getTitleText());
        holder.intro.setText(itemBeamList.get(position).getIntro());

        holder.cover.setImageBitmap(itemBeamList.get(position).getCover_bitmap());
    }

    @Override
    public int getItemCount() {
       return itemBeamList.size();
    }
    public void setItemBeamList(List<ItemBeam> list){
        itemBeamList=list;
        notifyDataSetChanged();
    }

}
