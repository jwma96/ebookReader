package com.example.d.ebookreader.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.ebookreader.R;

import java.util.List;

/**
 * Created by d on 2018/4/16.
 */

public class FileAdapter  extends ArrayAdapter<bookFile>{
    private int resourceId;
    public FileAdapter(Context context, int textViewResourdeId, List<bookFile> objects){
        super(context,textViewResourdeId,objects);
        resourceId=textViewResourdeId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        bookFile file=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        ImageView fImage=(ImageView)view.findViewById(R.id.items_imageView1);
        TextView textViewn=(TextView)view.findViewById(R.id.items_textView1);
        TextView textViews=(TextView)view.findViewById(R.id.items_textView2);
        CheckBox ckb=(CheckBox)view.findViewById(R.id.cb_st);
        fImage.setImageResource(file.getImageId());
        textViewn.setText(file.getName());
        textViews.setText(file.getSize());
        ckb.setChecked(file.getIsCho());
        return view;

    }

}
