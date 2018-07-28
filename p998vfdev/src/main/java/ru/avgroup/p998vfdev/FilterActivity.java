package ru.avgroup.p998vfdev;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import ru.avgroup.p998vfdev.fragment.AbstractTabFragment;

public class FilterActivity extends HomeActivity implements View.OnClickListener {

    ListView lvMain;
    String[] trains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppDefault);
        setContentView(R.layout.activity_filter);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initFilter();
        initToolBar();

        Button Apply = (Button) findViewById(R.id.appBtn);
        Apply.setOnClickListener(this);
    }

    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFilter);
        toolbar.setTitle("ФИЛЬТР ЗАНЯТИЙ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appBtn:
                CharSequence cs = "p";
                AbstractTabFragment.getFilter(cs);
                Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFilter(){
        lvMain = (ListView) findViewById(R.id.filter_list);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.trains, android.R.layout.simple_list_item_multiple_choice);
        lvMain.setAdapter(adapter);
        trains = getResources().getStringArray(R.array.trains);
    }
}
