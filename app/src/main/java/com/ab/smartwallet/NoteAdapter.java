package com.ab.smartwallet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdullah on 3/15/2016.
 */
public class NoteAdapter extends BaseAdapter {
    List<NoteDB> list ;
    LayoutInflater layoutInflater ;

    NoteAdapter (List<NoteDB> list , Context context) {
        this.list = new ArrayList<NoteDB>(list);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder viewHolder = null;
        if(row == null){
            row = layoutInflater.inflate(R.layout.note_row,viewGroup,false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        }
        else{
           viewHolder = (ViewHolder) row.getTag();
        }

        row.setBackgroundColor(list.get(i).color);
        viewHolder.textView.setText(list.get(i).title);
        viewHolder.mDateText.setText(list.get(i).date);



        return row;
    }

    public NoteDB list (int position){
        return list.get(position);
    }
    public void update () {
        notifyDataSetChanged();
    }
   static class ViewHolder {
       TextView textView;
       TextView mDateText;
       public ViewHolder(View v){
           textView = (TextView) v.findViewById(R.id.noteTextView);
           mDateText= (TextView) v.findViewById(R.id.dateText);
       }

   }
}