package com.ab.smartwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.beardedhen.androidbootstrap.BootstrapButton;
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
import com.shamanland.fab.FloatingActionButton;
import com.shamanland.fab.ShowHideOnScroll;

/**
 * Created by Abdullah on 3/9/2016.
 */
public class AddNoteActivity extends AppCompatActivity{
    ListView listView;
   FloatingActionButton add;
   static NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        SugarContext.init(this);

        listView = (ListView) findViewById(R.id.list);

        noteAdapter = new NoteAdapter(NoteDB.listAll(NoteDB.class),AddNoteActivity.this);
        listView.setAdapter(noteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NoteDB noteDB = noteAdapter.list(position);
                Intent intent = new Intent(AddNoteActivity.this,EditNoteActivity.class);
                intent.putExtra("title",noteDB.title.toString());
                intent.putExtra("note", noteDB.note.toString());
                intent.putExtra("pos",position+"");
                startActivity(intent);
                finish();
            }
        });

        add= (FloatingActionButton) findViewById(R.id.fab);
        listView.setOnTouchListener(new ShowHideOnScroll(add));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNoteActivity.this,Note.class);
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
                            Intent intent = new Intent(AddNoteActivity.this, DayReportFragment.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 4) {
                            Intent intent = new Intent(AddNoteActivity.this, MonthReport.class);
                            startActivity(intent);
                            finish();
                        }
                        if (position == 5) {
                            Intent intent = new Intent(AddNoteActivity.this, YearReport.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });


}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddNoteActivity.this,main.class);
        startActivity(intent);
        finish();
    }
}
