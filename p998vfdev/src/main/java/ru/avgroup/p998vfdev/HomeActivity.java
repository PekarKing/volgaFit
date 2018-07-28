package ru.avgroup.p998vfdev;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.Calendar;
import java.util.TimeZone;

import ru.avgroup.p998vfdev.adapter.TabsFragmentAdapter;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private SmartTabLayout tabLayout;

    TimeZone tz = TimeZone.getTimeZone("GMT+03:00");
    Calendar calendar = Calendar.getInstance(tz);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initToolBar();
        initNavigationView();
        initTabs();
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (SmartTabLayout) findViewById(R.id.tabLayout);
        tabLayout.setViewPager(viewPager);
        initCalendar();
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("РАСПИСАНИЕ ЗАНЯТИЙ");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem MenuItem) {
                int a = R.id.filter;
                if (a == R.id.filter){
                    Intent intent = new Intent(HomeActivity.this, FilterActivity.class);
                    startActivity(intent);
//                    finish();
                }
                else {
                    return false;
                }
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Intent intent = null;
                int a = item.getItemId();
                if (a == R.id.tabMyTrains) {
                    intent = new Intent(HomeActivity.this, MyTrains.class);
                }
                if (a == R.id.tabRaspisanie) {
                    return false;
                }
                if (a == R.id.tabNotifications) {
                    intent = new Intent(HomeActivity.this, NotfActivity.class);
                }
                if (a == R.id.tabAbout){
                    intent = new Intent(HomeActivity.this, AboutScroll.class);
                }
                startActivity(intent);
                return false;
            }
        });
    }

    private void initCalendar() {
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            viewPager.setCurrentItem(6);
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            viewPager.setCurrentItem(0);
        }
        else {
            calendar.add(Calendar.DAY_OF_WEEK, -2);
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            viewPager.setCurrentItem(day);
        }
    }
}