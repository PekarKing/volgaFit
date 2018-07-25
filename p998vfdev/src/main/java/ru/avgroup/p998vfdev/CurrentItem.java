package ru.avgroup.p998vfdev;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import ru.avgroup.p998vfdev.data.TrainsContract.TrainsEntry;
import ru.avgroup.p998vfdev.data.TrainsDbHelper;

public class CurrentItem extends AppCompatActivity implements View.OnClickListener {

    public static Map<String, Object> Data = new HashMap<String, Object>();
    private TrainsDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTrans);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initInfo();
        initToolBar();
        initFAB();

        mDbHelper = new TrainsDbHelper(this);

    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.addToCalendar:
                addToCalendar();
                break;
            case R.id.addToTrains:
//                db.delete(TrainsEntry.TABLE_NAME, null, null);
                // Gets the database in write mode
                ContentValues values = new ContentValues();
                values.put(TrainsEntry.COLUMN_TITLE, String.valueOf(Data.get("title")));
                values.put(TrainsEntry.COLUMN_DESCR, String.valueOf(Data.get("description")));
                values.put(TrainsEntry.COLUMN_TIME, String.valueOf(Data.get("time")));
                values.put(TrainsEntry.COLUMN_GYM, String.valueOf(Data.get("gym")));
                values.put(TrainsEntry.COLUMN_WEEKDAY, String.valueOf(Data.get("weekday")));

                long newRowId = db.insert(TrainsEntry.TABLE_NAME, null, values);
                Toast.makeText(CurrentItem.this, "Добавлено в Мои занятия", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static void getData (Map<String, Object> m) {
        Data = m;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initInfo(){
        TextView title = (TextView) findViewById(R.id.titleCur);
        TextView descriprion = (TextView) findViewById(R.id.descrCur);
        TextView gym = (TextView) findViewById(R.id.gymCur);
        TextView time = (TextView) findViewById(R.id.timeCur);
        ImageView img = (ImageView) findViewById(R.id.imageCur);

        title.setText(String.valueOf(Data.get("title")));
        descriprion.setText(String.valueOf(Data.get("description")));
        time.setText(String.valueOf(Data.get("time")));
        gym.setText(String.valueOf(Data.get("gym")));
        int i = (int) Data.get("image");
        img.setImageResource(i);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCur);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initFAB(){
        FloatingActionButton toCalendar = (FloatingActionButton) findViewById(R.id.addToCalendar);
        FloatingActionButton toMyTrains = (FloatingActionButton) findViewById(R.id.addToTrains);

        toCalendar.setOnClickListener(this);
        toMyTrains.setOnClickListener(this);
    }

    private void addToCalendar() {
        String time = String.valueOf(Data.get("time"));
        String allTime[] = time.split(" - ");

        if (Build.VERSION.SDK_INT >= 14) {
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, allTime[0])
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, allTime[1])
                    .putExtra(CalendarContract.Events.TITLE, String.valueOf(Data.get("title")))
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, "Волгоград, Набережная 62-й армии, д.6, Речной вокзал, " +  String.valueOf(Data.get("gym")));
//                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            startActivity(intent);
        }

        else {
            Calendar cal = Calendar.getInstance();
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
//            intent.putExtra("beginTime", cal.getTimeInMillis());
//            intent.putExtra("allDay", true);
//            intent.putExtra("rrule", "FREQ=YEARLY");
//            intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
            intent.putExtra("title", "A Test Event from android app");
            startActivity(intent);
        }
    }

}


