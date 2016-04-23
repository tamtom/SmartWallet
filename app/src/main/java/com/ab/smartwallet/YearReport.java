package com.ab.smartwallet;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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

public class YearReport extends Fragment {
    Button ok;
    Spinner year;
    int incomeSum , outcomeSum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_year_report,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View v = getView();
        year = (Spinner) v.findViewById(R.id.yearY);
        ok = (Button) v.findViewById(R.id.yok);

        ArrayAdapter<CharSequence> YAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.year, android.R.layout.simple_spinner_item);
        YAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(YAdapter);

        SugarContext.init(getActivity());
        ArrayList<DataBase> arrayList = new ArrayList<>();
        arrayList =(ArrayList) DataBase.listAll(DataBase.class);
        final ArrayList<DataBase> income = new ArrayList<>();
        final ArrayList<DataBase> outcome = new ArrayList<>();


        for (int i = 0; i <arrayList.size() ; i++) {
            if(arrayList.get(i).plusOrMinus.equals("p"))
                income.add(arrayList.get(i));
            else
                outcome.add(arrayList.get(i));
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomeSum = 0;
                outcomeSum = 0;
                for (int i = 0; i < income.size(); i++) {
                    String s = income.get(i).date;
                    int y = Integer.parseInt(s.substring(0, 4));
                    int m = Integer.parseInt(s.substring(5, 7));

                    if (y == Integer.parseInt(year.getSelectedItem().toString())) {
                        incomeSum += Integer.parseInt(income.get(i).amount);
                    }
                }

                for (int i = 0; i < outcome.size(); i++) {
                    String s = outcome.get(i).date;
                    int y = Integer.parseInt(s.substring(0, 4));
                    if (y == Integer.parseInt(year.getSelectedItem().toString())) {
                        outcomeSum += Integer.parseInt(outcome.get(i).amount);
                    }

                }

                PieChart chart = (PieChart) v.findViewById(R.id.yearChart);

                ArrayList<Entry> al = new ArrayList<>();
                al.add(new Entry(incomeSum, 0));
                al.add(new Entry(outcomeSum, 1));

                ArrayList<String> labels = new ArrayList<String>();
                labels.add("Income");
                labels.add("Outcome");

                PieDataSet pieDataSet = new PieDataSet(al, "");
                pieDataSet.setColors(ColorTemplate.createColors(new int[]{R.color.md_green_400, R.color.md_red_400}));

                PieData pieData = new PieData(labels, pieDataSet);

                chart.animateY(1500);
                String s = "";
                if (incomeSum - outcomeSum > 0) {
                    chart.setCenterTextColor(Color.GREEN);
                    s = "+";
                } else
                    chart.setCenterTextColor(Color.RED);

                chart.setCenterText(s + (incomeSum - outcomeSum));
                chart.setCenterTextSize(20);

                chart.setData(pieData);
            }
        });
        /*
        //ND header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .addProfiles(new ProfileDrawerItem().withName("khalaf").withEmail("k_khatatneh@yahoo.com").withIcon(R.drawable.rr))
                .withActivity(getActivity())
                .withHeaderBackground(R.drawable.oo)
                .build();
//ND
        new DrawerBuilder()
                .withActivity(getActivity()).withAccountHeader(headerResult)
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
                        if (position == 3) {
                            Intent intent = new Intent(getActivity(), DayReportFragment.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(getActivity(), MonthReport.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(getActivity(), YearReport.class);
                            startActivity(intent);
                            getActivity().finish();
                        }

                        return true;
                    }
                });*/
    }
/*
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(),main.class);
        startActivity(intent);
        getActivity().finish();
    }*/
}
