package ru.avgroup.p998vfdev;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class NotfActivity extends HomeActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notf);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        initToolBar();
        initNavigationView();
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarNotf);
        toolbar.setTitle("УВЕДОМЛЕНИЯ");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem MenuItem) {
                return false;
            }
        });
    }


    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawNotf);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigationNotf);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Intent intent = null;
                int a = item.getItemId();
                if (a == R.id.tabMyTrains) {
                    intent = new Intent(NotfActivity.this, MyTrains.class);
                }
                if (a == R.id.tabRaspisanie) {
                    intent = new Intent(NotfActivity.this, HomeActivity.class);
                }
                if (a == R.id.tabNotifications) {
                    return false;
                }
                if (a == R.id.tabAbout) {
                    intent = new Intent(NotfActivity.this, AboutScroll.class);
                }
                startActivity(intent);
                finish();
                return false;
            }
        });
    }
}

