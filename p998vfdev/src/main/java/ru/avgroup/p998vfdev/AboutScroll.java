package ru.avgroup.p998vfdev;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import at.markushi.ui.CircleButton;

public class AboutScroll extends HomeActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        initToolBar();
        initNavigationView(); // Я ДОМОЙ

        CircleButton inst = (CircleButton) findViewById(R.id.inst);
        CircleButton face = (CircleButton) findViewById(R.id.facebook);
        CircleButton vk = (CircleButton) findViewById(R.id.vk);
        CircleButton yt = (CircleButton) findViewById(R.id.youtube);

        TextView adres = (TextView) findViewById(R.id.adres);
        TextView tel = (TextView) findViewById(R.id.tel);
        TextView email = (TextView) findViewById(R.id.email);

        inst.setOnClickListener(AboutScroll.this);
        face.setOnClickListener(AboutScroll.this);
        vk.setOnClickListener(AboutScroll.this);
        yt.setOnClickListener(AboutScroll.this);

        adres.setOnClickListener(AboutScroll.this);
        tel.setOnClickListener(AboutScroll.this);
        email.setOnClickListener(AboutScroll.this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.inst:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/volgafit/")));
                break;
            case R.id.facebook:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/volgaf1t")));
                break;
            case R.id.vk:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/volgafit")));
                break;
            case R.id.youtube:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC4rFbPzHiHYTcyJWud2dMVA")));
                break;
            case R.id.adres:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:48.700686,44.5192348"));
                startActivity(intent);
                break;
            case R.id.tel:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+78442383838"));
                startActivity(intent);
                break;
            case R.id.email:
                break;
        }
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarScroll);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem MenuItem) {
                return false;
            }
        });
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawScroll);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navScroll);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Intent intent = null;
                int a = item.getItemId();
                if (a == R.id.tabMyTrains){
                    intent = new Intent(AboutScroll.this, MyTrains.class);
                }
                if (a == R.id.tabRaspisanie){
                    intent = new Intent(AboutScroll.this, HomeActivity.class);
                }
                if (a == R.id.tabNotifications){
                    intent = new Intent(AboutScroll.this, NotfActivity.class);
                }
                if (a == R.id.tabAbout) {
                    return false;
                }
                startActivity(intent);
                finish();
                return false;
            }
        });
    }
}