package com.ab.smartwallet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdullah on 3/11/2016.
 */
public class AbAdapter extends BaseAdapter {

    List<DataBase> list ;
    LayoutInflater layoutInflater ;

    AbAdapter (List<DataBase> list , Context context) {
        this.list = new ArrayList<DataBase> (list);
        layoutInflater = LayoutInflater.from(context);
    }

   
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = null;

        if (row == null) {
            row = layoutInflater.inflate(R.layout.row, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        viewHolder.picture.setImageResource(R.drawable.splash2);
        viewHolder.category.setText(list.get(position).category);
        viewHolder.amount.setText(list.get(position).amount);
        if (list.get(position).plusOrMinus.equals("p")){
            viewHolder.category.setTextColor(Color.GREEN);
            viewHolder.amount.setTextColor(Color.GREEN);
        }
        else {
            viewHolder.category.setTextColor(Color.RED);
            viewHolder.amount.setTextColor(Color.RED);
        }

        return row;
    }


    public DataBase show (int position ) {

                return list.get(position);
    }
    public void update () {
        notifyDataSetChanged();
    }
}
