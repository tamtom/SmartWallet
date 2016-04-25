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
    int icons[];
    List<DataBase> list ;
    LayoutInflater layoutInflater ;

    AbAdapter (List<DataBase> list , Context context) {
        this.list = new ArrayList<DataBase> (list);
        layoutInflater = LayoutInflater.from(context);
        icons = new int[]{R.drawable.ic_baby_white_36dp,
                R.drawable.ic_home_white_36dp,
                R.drawable.ic_car_white_36dp,
                R.drawable.ic_food_white_36dp,
                R.drawable.ic_cart_white_36dp,
                R.drawable.ic_gift_white_36dp,
                R.drawable.ic_cash};
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
          if(list.get(position).category.equals("Kids"))
              viewHolder.picture.setImageResource(icons[0]);
        else if(list.get(position).category.equals("Home"))
            viewHolder.picture.setImageResource(icons[1]);
        else if(list.get(position).category.equals("Car"))
              viewHolder.picture.setImageResource(icons[2]);
        else if(list.get(position).category.equals("Shawerma"))
              viewHolder.picture.setImageResource(icons[3]);
       else if(list.get(position).category.equals("Store"))
              viewHolder.picture.setImageResource(icons[4]);
        else if(list.get(position).category.equals("Gift"))
              viewHolder.picture.setImageResource(icons[5]);
        else if(list.get(position).category.equals("Salary"))
              viewHolder.picture.setImageResource(icons[6]);
        else{
              if (list.get(position).plusOrMinus.equals("p")){
                  viewHolder.picture.setImageResource(R.drawable.ic_add_white_24dp);
              }
              else
                  viewHolder.picture.setImageResource(android.R.drawable.btn_minus);
          }

        viewHolder.category.setText(list.get(position).category);
        viewHolder.amount.setText(list.get(position).amount);
        if (list.get(position).plusOrMinus.equals("p")){
            viewHolder.category.setTextColor(Color.GREEN);
            viewHolder.amount.setTextColor(Color.GREEN);
            viewHolder.picture.setColorFilter(Color.GREEN);

        }
        else {
            viewHolder.category.setTextColor(Color.RED);
            viewHolder.amount.setTextColor(Color.RED);
            viewHolder.amount.setText("-"+list.get(position).amount);
            viewHolder.picture.setColorFilter(Color.RED);

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
