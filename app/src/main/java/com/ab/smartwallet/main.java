package com.ab.smartwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import static android.view.View.*;

/**
 * Created by Abdullah on 3/9/2016.
 */
public class main extends AppCompatActivity {
    ImageView btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
/*
//ND header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .addProfiles(new ProfileDrawerItem().withName("khalaf").withEmail("k_khatatneh@yahoo.com").withIcon(R.drawable.rr))
                .withActivity(this)
                .withHeaderBackground(R.drawable.oo)
                .build();
//ND
        new DrawerBuilder()
                .withActivity(this).withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Reports"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Daily"),
                        new SecondaryDrawerItem().withName("Monthly"),
                        new SecondaryDrawerItem().withName("yearly")
                )
              .build()
                .setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(position==3){
                            Intent intent = new Intent(main.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();}
                        if(position==4){
                            Intent intent = new Intent(main.this, MonthReport.class);
                            startActivity(intent);
                            finish();}
                        if(position==5){
                            Intent intent = new Intent(main.this, YearReport.class);
                            startActivity(intent);
                            finish();}

                        return true;
                    }
                });*/

        btn1 = (ImageView) findViewById(R.id.btn1);
        btn2 = (ImageView) findViewById(R.id.btn2);
        btn3 = (ImageView) findViewById(R.id.btn3);

        //some Listeners
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main.this, AddNoteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main.this,Taps.class);
                startActivity(intent);
                finish();
            }
        });






    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
