package com.ab.smartwallet;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import petrov.kristiyan.colorpicker.ColorPicker;

public class EditNoteActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button save, cancel,delete;
    NoteAdapter adapter ;
    int colorr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);

        ed1= (EditText) findViewById(R.id.editnote);
        ed2 = (EditText) findViewById(R.id.edittitle);
        save = (Button) findViewById(R.id.esave);
        cancel = (Button) findViewById(R.id.ecancel);
        delete = (Button) findViewById(R.id.edelete);
        adapter = AddNoteActivity.noteAdapter;

        ed1.setText(getIntent().getStringExtra("note"));
        ed2.setText(getIntent().getStringExtra("title"));

       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               final List<NoteDB> item = NoteDB.find(NoteDB.class, "title = ? and note = ?", getIntent().getStringExtra("title"), getIntent().getStringExtra("note"));
               colorr = item.get(0).color;

               item.get(0).note = ed1.getText().toString();
               item.get(0).title = ed2.getText().toString();



               final ColorPicker colorPicker = new ColorPicker(EditNoteActivity.this);
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

                       item.get(0).color =colorr;
                       item.get(0).date =time;
                       item.get(0).save();
                       Intent intent = new Intent(EditNoteActivity.this, AddNoteActivity.class);
                       startActivity(intent);
                       finish();        }
               }).setDefaultColor(Color.parseColor("#f84c44")).setColumns(5).setRoundButton(true).show();
           }
       });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditNoteActivity.this, AddNoteActivity.class);
                startActivity(intent);
                finish();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* NoteDB noteDB = NoteDB.findById(NoteDB.class, Integer.parseInt(getIntent().getStringExtra("pos").toString()));
                noteDB.title = ed2.getText().toString();
                noteDB.note = ed1.getText().toString();
                noteDB.save();*/

                List<NoteDB> item = NoteDB.find(NoteDB.class, "title = ? and note = ?", getIntent().getStringExtra("title"), getIntent().getStringExtra("note"));
                item.get(0).delete();
                adapter.update();

                Intent intent = new Intent(EditNoteActivity.this, AddNoteActivity.class);
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
                            Intent intent = new Intent(EditNoteActivity.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(EditNoteActivity.this, MonthReport.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(EditNoteActivity.this, YearReport.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditNoteActivity.this, AddNoteActivity.class);
        startActivity(intent);
        finish();
    }
}
