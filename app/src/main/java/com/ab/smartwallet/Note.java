package com.ab.smartwallet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import petrov.kristiyan.colorpicker.ColorPicker;

/**
 * Created by Abdullah on 3/15/2016.
 */
public class Note extends AppCompatActivity {
    Button save, cancel;
    EditText note ,title;
    NoteAdapter adapter ;
    int colorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout);

        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        note= (EditText) findViewById(R.id.note);
        title = (EditText) findViewById(R.id.title);
        adapter = AddNoteActivity.noteAdapter;

        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(note.getText().toString().isEmpty()){
                    Toast.makeText(Note.this,"you must write a note before saving :) ",Toast.LENGTH_LONG).show();
                }
                else{

    final ColorPicker colorPicker = new ColorPicker(Note.this);
    colorPicker.setFastChooser(new ColorPicker.OnFastChooseColorListener() {
        @Override
        public void setOnFastChooseColorListener(int position, int color) {
            colorr = color;

        }
    }).setNegativeButton("dismiss", new ColorPicker.OnButtonListener() {
        @Override
        public void onClick(View v) {
         colorPicker.dismissDialog();
        }
    }).setPositiveButton("Ok", new ColorPicker.OnButtonListener() {
        @Override
        public void onClick(View v) {
            String dateFormat = "yyyy-MM-dd";

            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
            String time = sdf.format(new Date().getTime());
            if(colorr==0)
              colorr=  Color.parseColor("#f84c44");
Log.d("the date is ",time);
            NoteDB db = new NoteDB(note.getText().toString(), title.getText().toString(), colorr,time);
            db.save();
            Toast.makeText(Note.this, "Note was save", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Note.this, AddNoteActivity.class);
            startActivity(intent);
            finish();        }
    }).setDefaultColor(Color.parseColor("#f84c44")).setColumns(5).setRoundButton(true).show();
}


}




        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Note.this,AddNoteActivity.class);
                Toast.makeText(Note.this,"Note was not save",Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
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
                            Intent intent = new Intent(Note.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(Note.this, MonthReport.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(Note.this, YearReport.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Note.this,AddNoteActivity.class);
        startActivity(intent);
        finish();
    }

    }
