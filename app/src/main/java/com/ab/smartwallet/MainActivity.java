package com.ab.smartwallet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {

    BootstrapButton plus,minus ;
    TextView balance, income, outcome ;
     ListView lv;

    LineChart lineChart;
    android.support.v7.widget.Toolbar mToolbar;
    static AbAdapter  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SugarContext.init(this);
        plus = (BootstrapButton) findViewById(R.id.bt2);
        minus = (BootstrapButton) findViewById(R.id.bt);
        income = (TextView) findViewById(R.id.total);
        outcome = (TextView) findViewById(R.id.outAmount);
        balance = (TextView) findViewById(R.id.balanceAmount);
          mToolbar = (Toolbar) findViewById(R.id.appbar);
        lineChart = (LineChart) findViewById(R.id.chart);
        lv = (ListView) findViewById(R.id.lcat);
        lv.setAdapter(new AbAdapter(DataBase.listAll(DataBase.class),this));
        calcAmount();





        setSupportActionBar(mToolbar);
        generateChart();
//        YoYo.with(Techniques.Tada).playOn(textView);


/*
//list adapter
        adapter = new AbAdapter(DataBase.listAll(DataBase.class),MainActivity.this);
        listView.setAdapter(adapter);
*/
//some listeners

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Plus.class);
                startActivity(intent);
                finish();
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Minus.class);
                startActivity(intent);
                finish();
            }
        });
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataBase dataBase =   adapter.show(position);
                Intent intent = new Intent(MainActivity.this,ShowBudget.class);
                intent.putExtra("amount",dataBase.amount.toString());
                intent.putExtra("category",dataBase.category.toString());
                intent.putExtra("pos",position+"");
                intent.putExtra("pom",dataBase.plusOrMinus.toString());
                startActivity(intent);
                finish();

            }
        });
*/

        //ND header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .addProfiles(new ProfileDrawerItem().withName("khalaf").withEmail("k_khatatneh@yahoo.com").withIcon(R.drawable.profile))
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .build();
//ND
        new DrawerBuilder()
                .withActivity(this).withToolbar(mToolbar).withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Reports"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Daily"),
                        new SecondaryDrawerItem().withName("Monthly"),
                        new SecondaryDrawerItem().withName("yearly")
                ).withSliderBackgroundColor(Color.parseColor("#004D40"))
                .build()
                .setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (position == 3) {
                            Intent intent = new Intent(MainActivity.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(MainActivity.this, MonthReport.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(MainActivity.this, YearReport.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity.this,main.class);
        startActivity(intent);
        finish();

    }

    //first time to open the app
    public boolean firstTime () {
        SharedPreferences sharedPreferences = getSharedPreferences("firstTime",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter", (sharedPreferences.getInt("counter", 0) + 1)).apply();

            return true ;

    }
    private void generateChart(){

        // creating list of entry
        ArrayList<Entry> entries = new ArrayList<>();


        List<DataBase> listdb = DataBase.listAll(DataBase.class);
        ArrayList<String> labels = new ArrayList<String>();

        for (int i=0 ; i<listdb.size() ; i++) {
//change balance
            entries.add(new Entry(Float.parseFloat(listdb.get(i).amount),i));
             labels.add(listdb.get(i).date.substring(0,10));


        }
        LineDataSet dataset = new LineDataSet(entries, "Balance");
        LineData data = new LineData(labels,dataset);
        dataset.setDrawCubic(true);
        dataset.setColor(Color.GREEN);

        lineChart.animateY(2000);
        lineChart.setData(data);



    }

//to calculate the amount that store in the data base
    private void calcAmount () {
        int amount = 0, in = 0, out = 0;
        List<DataBase> list = DataBase.listAll(DataBase.class);
        for (int i=0 ; i<list.size() ; i++) {
            String s = list.get(i).plusOrMinus ;
            if (s.equals("p")){
                amount += Integer.parseInt(list.get(i).amount);
                in += Integer.parseInt(list.get(i).amount);
            }
            else{
                amount -= Integer.parseInt(list.get(i).amount);
                out += Integer.parseInt(list.get(i).amount);
            }
        }
        balance.setText(amount+"");
        income.setText(in+"");
        outcome.setText(out+"");
    }


    public static AbAdapter returnAdapter () {
        return adapter;
    }




}
