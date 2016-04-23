package com.ab.smartwallet;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.orm.SugarContext;
import java.util.ArrayList;

public class DayReportFragment extends Fragment {
    Spinner day, month, year;
    Button ok;

    int incomeSum ;
    int outcomeSum ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day,container,false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View v = getView();

        SugarContext.init(getActivity());
        day = (Spinner) v.findViewById(R.id.dayD);
        month = (Spinner) v.findViewById(R.id.dayM);
        year = (Spinner) v.findViewById(R.id.dayY);
        ok = (Button) v.findViewById(R.id.ok);


        ArrayAdapter<CharSequence> DAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.day, android.R.layout.simple_spinner_item);
        DAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(DAdapter);

        ArrayAdapter<CharSequence> MAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.month, android.R.layout.simple_spinner_item);
        MAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(MAdapter);

        ArrayAdapter<CharSequence> YAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.year, android.R.layout.simple_spinner_item);
        YAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(YAdapter);



        ArrayList<DataBase> arrayList = new ArrayList<>(DataBase.listAll(DataBase.class));

        final ArrayList<DataBase> income = new ArrayList<>();
        final ArrayList<DataBase> outcome = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).plusOrMinus.equals("p"))
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
                    int d = Integer.parseInt(s.substring(8, 10));
                    int y = Integer.parseInt(s.substring(0, 4));
                    int m = Integer.parseInt(s.substring(5, 7));

                    if (d == Integer.parseInt(day.getSelectedItem().toString())
                            && m == Integer.parseInt(month.getSelectedItemId()+1+"")
                            && y == Integer.parseInt(year.getSelectedItem().toString())) {
                        incomeSum += Integer.parseInt(income.get(i).amount);
                    }
                }

                for (int i = 0; i < outcome.size(); i++) {
                    String s = outcome.get(i).date;
                    int d = Integer.parseInt(s.substring(8, 10));
                    int y = Integer.parseInt(s.substring(0, 4));
                    int m = Integer.parseInt(s.substring(5, 7));

                    if (d == Integer.parseInt(day.getSelectedItem().toString())
                            && m == Integer.parseInt(month.getSelectedItemId()+1+"")
                            && y == Integer.parseInt(year.getSelectedItem().toString())) {
                        outcomeSum += Integer.parseInt(outcome.get(i).amount);
                    }

                }

                PieChart chart = (PieChart) v.findViewById(R.id.dayChart);

                ArrayList<Entry> al = new ArrayList<>();
                al.add(new Entry(incomeSum,0));
                al.add(new Entry(outcomeSum, 1));

                ArrayList<String> labels = new ArrayList<String>();
                labels.add("Income");
                labels.add("Outcome");

                PieDataSet pieDataSet = new PieDataSet(al,"");
                pieDataSet.setColors(ColorTemplate.createColors(new int[]{Color.GREEN, Color.RED}));

                PieData pieData = new PieData(labels,pieDataSet);

                chart.animateY(1500);
                String s = "";
                if(incomeSum - outcomeSum > 0) {
                    chart.setCenterTextColor(Color.GREEN);
                    s = "+";
                }else
                    chart.setCenterTextColor(Color.RED);

                chart.setCenterText(s + (incomeSum - outcomeSum));
                chart.setCenterTextSize(20);

                chart.setData(pieData);
            }
        });


    }


}
