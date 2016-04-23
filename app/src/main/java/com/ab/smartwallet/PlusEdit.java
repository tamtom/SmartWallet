package com.ab.smartwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.List;

public class PlusEdit extends AppCompatActivity {
    EditText editText;
    RadioGroup radioGroup;
    RadioButton radioButton ;
    Button done;
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
                    Toast.makeText(PlusEdit.this, "you must add the amount :) ", Toast.LENGTH_LONG).show();
                }
                else {
                    int IDRadioButton = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(IDRadioButton);
                    String radioButtonText = radioButton.getText().toString();



                    Log.d("FUCK2",getIntent().getStringExtra("ca") +"    "+getIntent().getStringExtra("am"));
                    List<DataBase> item = DataBase.find(DataBase.class, "category = ? and amount = ?", getIntent().getStringExtra("ca") ,getIntent().getStringExtra("am") );

                    item.get(0).category = radioButtonText;
                    item.get(0).amount = editText.getText().toString();
                    item.get(0).save();
                   // DataBase dataBase = DataBase.findById(DataBase.class,Integer.parseInt(getIntent().getStringExtra("pos"))+1);
                   // dataBase.amount = editText.getText().toString();
                   // dataBase.category = radioButtonText;
                   // dataBase.save();

                    Intent intent = new Intent(PlusEdit.this,MainActivity.class);
                    startActivity(intent);
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
                            Intent intent = new Intent(PlusEdit.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(PlusEdit.this, MonthReport.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(PlusEdit.this, YearReport.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(PlusEdit.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
