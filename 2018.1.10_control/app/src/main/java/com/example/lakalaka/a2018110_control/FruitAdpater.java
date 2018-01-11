package com.example.lakalaka.a2018110_control;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lakalaka on 2018/1/10/0010.
 */

public class FruitAdpater extends ArrayAdapter<Fruit> {
    private int resourceId;

    public FruitAdpater(@NonNull Context context, @LayoutRes int textViewResourceId, @NonNull List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit=getItem(position);//获取当前Fruit的实例
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.fruitImage= (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName= (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;

    }

     class ViewHolder {
         ImageView fruitImage;
         TextView fruitName;
    }
}
