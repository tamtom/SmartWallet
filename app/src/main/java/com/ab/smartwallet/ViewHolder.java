package com.ab.smartwallet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
* Created by Abdullah on 3/26/2016.
*/
class ViewHolder {
   TextView category,amount;
   ImageView picture;

   public ViewHolder(View v){
       picture = (ImageView) v.findViewById(R.id.picture);
       category = (TextView) v.findViewById(R.id.category);
       amount = (TextView) v.findViewById(R.id.amount);
   }

}
