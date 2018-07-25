package ru.avgroup.p998vfdev;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.avgroup.p998vfdev.adapter.SimpleMyTrainsAdapter;
import ru.avgroup.p998vfdev.data.TrainsContract.TrainsEntry;
import ru.avgroup.p998vfdev.data.TrainsDbHelper;

public class MyTrains extends HomeActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private static SimpleMyTrainsAdapter sAdapter;
    protected Context context;
    private TrainsDbHelper mDbHelper;

    ListView lv;
    TextView TV;
    ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        lv = (ListView) findViewById(R.id.trainsLW);
        TV = (TextView) findViewById(R.id.emptyTV);

        initToolBar();
        initNavigationView();

        mDbHelper = new TrainsDbHelper(this);
        displayDatabaseInfo();
        initAdapter();
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarTrains);
        toolbar.setTitle("МОИ ЗАНЯТИЯ");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem MenuItem) {
                int a = R.id.filter;
                if (a == R.id.filter){
                    SQLiteDatabase db = mDbHelper.getReadableDatabase();
                    db.delete(TrainsEntry.TABLE_NAME, null, null);
                    Data.clear();
                    sAdapter.notifyDataSetChanged();
                    TV.setText("Добавьте занятие");
                    Toast.makeText(MyTrains.this, "Очищено", Toast.LENGTH_SHORT).show();
                }
                else {
                    return false;
                }
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu_filter);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawTrains);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationTrains);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Intent intent = null;
                int a = item.getItemId();
                if (a == R.id.tabMyTrains) {
                    return false;
                }
                if (a == R.id.tabRaspisanie){
                    intent = new Intent(MyTrains.this, HomeActivity.class);
                }
                if (a == R.id.tabNotifications){
                    intent = new Intent(MyTrains.this, NotfActivity.class);
                }
                if (a == R.id.tabAbout){
                    intent = new Intent(MyTrains.this, AboutScroll.class);
                }
                startActivity(intent);
                finish();
                return false;
            }
        });
    }

    private void initAdapter(){
        String[] from = {"title", "description", "time", "image", "gym", "weekday"};
        int[] to = {R.id.title, R.id.descr, R.id.time, R.id.img, R.id.gym, R.id.weekday};
        lv = (ListView) findViewById(R.id.trainsLW);

        sAdapter = new SimpleMyTrainsAdapter(this, Data, R.layout.my_filter_item, from, to);
        lv.setAdapter(sAdapter);
    }

    private void displayDatabaseInfo() {
        // Создадим и откроем для чтения базу данных
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Map<String, Object> m;

        // Зададим условие для выборки - список столбцов
        String[] projection = {
                TrainsEntry._ID,
                TrainsEntry.COLUMN_TITLE,
                TrainsEntry.COLUMN_DESCR,
                TrainsEntry.COLUMN_TIME,
                TrainsEntry.COLUMN_GYM,
                TrainsEntry.COLUMN_WEEKDAY};

        // Делаем запрос
        Cursor cursor = db.query(TrainsEntry.TABLE_NAME, projection, null, null, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                int idColumnIndex = cursor.getColumnIndex(TrainsEntry._ID);
                int titleColumnIndex = cursor.getColumnIndex(TrainsEntry.COLUMN_TITLE);
                int descrColumnIndex = cursor.getColumnIndex(TrainsEntry.COLUMN_DESCR);
                int timeColumnIndex = cursor.getColumnIndex(TrainsEntry.COLUMN_TIME);
                int gymColumnIndex = cursor.getColumnIndex(TrainsEntry.COLUMN_GYM);
                int weekdayColumnIndex = cursor.getColumnIndex(TrainsEntry.COLUMN_WEEKDAY);

                // Проходим через все ряды
                do {

                    m = new HashMap<String, Object>();
                    int currentID = cursor.getInt(idColumnIndex);
                    m.put("title", cursor.getString(titleColumnIndex));
                    m.put("description", cursor.getString(descrColumnIndex));
                    m.put("time", cursor.getString(timeColumnIndex));
                    m.put("gym", cursor.getString(gymColumnIndex));
                    m.put("weekday", cursor.getString(weekdayColumnIndex));
                    m.put("image", R.drawable.abd);

                    Data.add(m);
                } while (cursor.moveToNext());

            } else
                TV.setText("Добавьте занятие");
        } finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
    }
}