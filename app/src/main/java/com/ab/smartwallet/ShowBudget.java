package com.ab.smartwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class ShowBudget extends AppCompatActivity {

    TextView cat,amt;
    Button delete,edit;
    AbAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_budget);

        cat= (TextView) findViewById(R.id.eCategory);
        amt = (TextView) findViewById(R.id.eAmount);
        delete= (Button) findViewById(R.id.delete);
        edit = (Button) findViewById(R.id.edit);

        adapter = MainActivity.adapter ;
        cat.setText(getIntent().getStringExtra("category"));
        amt.setText(getIntent().getStringExtra("amount"));
        final String pom = getIntent().getStringExtra("pom");
        final Long pos =Long.parseLong(getIntent().getStringExtra("pos"));

        Toast.makeText(ShowBudget.this,""+pos,Toast.LENGTH_SHORT).show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DataBase> item = DataBase.find(DataBase.class , "category = ? and amount = ?",cat.getText().toString(),amt.getText().toString());
                item.get(0).delete();
                adapter.update();
                Intent i = new Intent(ShowBudget.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pom.equals("m")){
                    Intent intent = new Intent(ShowBudget.this,EditMinusActivity.class);
                    intent.putExtra("pos",pos+"");
                    intent.putExtra("am",amt.getText()+"");
                    intent.putExtra("ca",cat.getText()+"");
                    startActivity(intent);
                    finish();
                }
                else if(pom.equals("p")){
                    Intent intent = new Intent(ShowBudget.this,PlusEdit.class);
                    intent.putExtra("pos",pos+"");
                    intent.putExtra("am",amt.getText()+"");
                    intent.putExtra("ca",cat.getText()+"");
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
                            Intent intent = new Intent(ShowBudget.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(ShowBudget.this, MonthReport.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(ShowBudget.this, YearReport.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });



    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ShowBudget.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
