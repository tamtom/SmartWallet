package com.ab.smartwallet;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Taps extends AppCompatActivity {

    ViewPager viewPager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taps);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter adapter = new adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Taps.this,main.class);
        startActivity(intent);
        finish();
    }
}
class adapter extends FragmentPagerAdapter{
    public adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        if(position==0)
            f = new DayReportFragment();
        if(position==1)
            f = new MonthReport();

        if(position ==2)
            f = new YearReport();

        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "Daily Report";
        if(position==1)
            return "Monthly Report";
        if(position==2)
            return "Yearly Report";
        return null;
    }
}
