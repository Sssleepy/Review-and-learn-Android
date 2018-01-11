package com.example.lakalaka.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lakalaka on 2018/1/11/0011.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mData;
    View view;

    public MyAdapter(ArrayList<String> data){
        this.mData=data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            mTv= (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }


    public void updateData(ArrayList<String> data){
        this.mData=data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化的view
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        //实例化viewHolder
        final ViewHolder viewHolder=new ViewHolder(v);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Toast.makeText(v.getContext(),"1"+position,Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定数据
        holder.mTv.setText(mData.get(position));
    }
    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
}
