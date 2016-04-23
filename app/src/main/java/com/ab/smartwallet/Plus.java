package com.ab.smartwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Created by Abdullah on 2/23/2016.
 */
public class Plus extends AppCompatActivity {
    Button done;
    EditText editText;
    RadioGroup radioGroup ;
    RadioButton radioButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plus);

        editText= (EditText) findViewById(R.id.editText);
        radioGroup = (RadioGroup) findViewById(R.id.plusRadioGroup);


        done = (Button) findViewById(R.id.ab);
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(Plus.this,"you must add the amount :) ",Toast.LENGTH_LONG).show();
                }else {

                    int IDRadioButton = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(IDRadioButton);
                    String radioButtonText = radioButton.getText().toString();
                    String dateFormat = "yyyy-MM-dd HH:mm";

                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
                    String time = sdf.format(new Date().getTime());

                    // DataBase
                    DataBase data = new DataBase(time, radioButtonText, editText.getText().toString(), "p");
                    data.save();

                    Intent intent1 = new Intent(Plus.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }

        });
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
                        if (position == 3) {
                            Intent intent = new Intent(Plus.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(Plus.this, MonthReport.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(Plus.this, YearReport.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Plus.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
