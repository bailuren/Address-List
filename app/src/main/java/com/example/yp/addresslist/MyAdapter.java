package com.example.yp.addresslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<User> {
    int resource;
    public MyAdapter(Context context, int resource, List<User> list){
        super(context,resource,list);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resource,parent,false);

            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.item_name);
            holder.tv_phone = convertView.findViewById(R.id.item_phone);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(user.getName());
        holder.tv_phone.setText(user.getPhone());
        return convertView;
    }

    class ViewHolder{
        TextView tv_name;
        TextView tv_phone;
    }
}

