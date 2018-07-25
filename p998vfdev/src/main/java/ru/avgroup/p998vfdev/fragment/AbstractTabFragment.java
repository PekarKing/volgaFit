package ru.avgroup.p998vfdev.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

import ru.avgroup.p998vfdev.CurrentItem;
import ru.avgroup.p998vfdev.R;
import ru.avgroup.p998vfdev.adapter.SimpleTrainsTableAdapter;


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////с этими вкладками я ебался неделю, даже две....////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class AbstractTabFragment extends Fragment {

    private static int pos = 0;
    private String title = "title";
    protected static Context context;
    protected static View view;
    protected static final int LAYOUT = R.layout.activity_list;

    String[] from = {"title", "description", "time", "image", "gym"};
    int[] to = {R.id.title, R.id.descr, R.id.time, R.id.img_lyt, R.id.gym,};

    TimeZone tz = TimeZone.getTimeZone("GMT+03:00");
    Calendar calendar = Calendar.getInstance(tz);
    Calendar today = Calendar.getInstance(tz);



    public static void getPos(int a) {
        pos = a;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        String data = "";
        int day = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            day = 6;
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            day = 0;
        }
        else {
            calendar.add(Calendar.DAY_OF_WEEK, -2);
            day = calendar.get(Calendar.DAY_OF_WEEK);
        }

        if (pos == day) {
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == 1) {
            today.add(Calendar.DAY_OF_MONTH, 1);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == 2) {
            today.add(Calendar.DAY_OF_MONTH, 2);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == 3) {
            today.add(Calendar.DAY_OF_MONTH, 3);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == 4) {
            today.add(Calendar.DAY_OF_MONTH, 4);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == 5) {
            today.add(Calendar.DAY_OF_MONTH, 5);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == 6) {
            today.add(Calendar.DAY_OF_MONTH, 6);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == -1) {
            today.add(Calendar.DAY_OF_MONTH, -1);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == -2) {
            today.add(Calendar.DAY_OF_MONTH, -2);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == -3) {
            today.add(Calendar.DAY_OF_MONTH, -3);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == -4) {
            today.add(Calendar.DAY_OF_MONTH, -4);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == -5) {
            today.add(Calendar.DAY_OF_MONTH, -5);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        else if ((pos - day) == -6) {
            today.add(Calendar.DAY_OF_MONTH, -6);
            data = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        }
        this.title = data + "\n" + title;

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class MondayFragment extends AbstractTabFragment {

        ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();

        public static MondayFragment getInstance(Context context) {
            Bundle args = new Bundle();
            MondayFragment fragment = new MondayFragment();
            fragment.setArguments(args);
            fragment.setContext(context);
            fragment.setTitle("Пн");
            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT, container, false);
            Data = JsonParse.getData(0);
            ListView lv = (ListView) view.findViewById(R.id.rasp);
            SimpleTrainsTableAdapter sAdapter = new SimpleTrainsTableAdapter(context, Data, R.layout.my_list_item, from, to);
            lv.setAdapter(sAdapter);
            setTitle("Пн");
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, CurrentItem.class);
                    CurrentItem.getData(Data.get(position));
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public static class TuesdayFragment extends AbstractTabFragment {

        ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();

        public static TuesdayFragment getInstance(Context context) {
            Bundle args = new Bundle();
            TuesdayFragment fragment = new TuesdayFragment();
            fragment.setArguments(args);
            fragment.setContext(context);
            fragment.setTitle("Вт");
            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT, container, false);
            Data = JsonParse.getData(1);
            ListView lv = (ListView) view.findViewById(R.id.rasp);
            SimpleTrainsTableAdapter sAdapter = new SimpleTrainsTableAdapter(context, Data, R.layout.my_list_item, from, to);
            lv.setAdapter(sAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, CurrentItem.class);
                    CurrentItem.getData(Data.get(position));
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public static class WednesdayFragment extends AbstractTabFragment {

        ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();

        public static WednesdayFragment getInstance(Context context) {
            Bundle args = new Bundle();
            WednesdayFragment fragment = new WednesdayFragment();
            fragment.setArguments(args);
            fragment.setContext(context);
            fragment.setTitle("Ср");
            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT, container, false);
            Data = JsonParse.getData(2);
            ListView lv = (ListView) view.findViewById(R.id.rasp);
            SimpleTrainsTableAdapter sAdapter = new SimpleTrainsTableAdapter(context, Data, R.layout.my_list_item, from, to);
            lv.setAdapter(sAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, CurrentItem.class);
                    CurrentItem.getData(Data.get(position));
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public static class ThursdayFragment extends AbstractTabFragment {

        ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();

        public static ThursdayFragment getInstance(Context context) {
            Bundle args = new Bundle();
            ThursdayFragment fragment = new ThursdayFragment();
            fragment.setArguments(args);
            fragment.setContext(context);
            fragment.setTitle("Чт");
            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT, container, false);
            Data = JsonParse.getData(3);
            ListView lv = (ListView) view.findViewById(R.id.rasp);
            SimpleTrainsTableAdapter sAdapter = new SimpleTrainsTableAdapter(context, Data, R.layout.my_list_item, from, to);
            lv.setAdapter(sAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, CurrentItem.class);
                    CurrentItem.getData(Data.get(position));
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public static class FridayFragment extends AbstractTabFragment {

        ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();

        public static FridayFragment getInstance(Context context) {
            Bundle args = new Bundle();
            FridayFragment fragment = new FridayFragment();
            fragment.setArguments(args);
            fragment.setContext(context);
            fragment.setTitle("Пт");

            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT, container, false);
            Data = JsonParse.getData(4);
            ListView lv = (ListView) view.findViewById(R.id.rasp);
            SimpleTrainsTableAdapter sAdapter = new SimpleTrainsTableAdapter(context, Data, R.layout.my_list_item, from, to);
            lv.setAdapter(sAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, CurrentItem.class);
                    CurrentItem.getData(Data.get(position));
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public static class SaturdayFragment extends AbstractTabFragment {

        ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();

        public static SaturdayFragment getInstance(Context context) {
            Bundle args = new Bundle();
            SaturdayFragment fragment = new SaturdayFragment();
            fragment.setArguments(args);
            fragment.setContext(context);
            fragment.setTitle("Сб");

            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT, container, false);
            Data = JsonParse.getData(5);
            ListView lv = (ListView) view.findViewById(R.id.rasp);
            SimpleTrainsTableAdapter sAdapter = new SimpleTrainsTableAdapter(context, Data, R.layout.my_list_item, from, to);
            lv.setAdapter(sAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, CurrentItem.class);
                    CurrentItem.getData(Data.get(position));
                    startActivity(intent);
                }
            });
            return view;
        }

    }

    public static class SundayFragment extends AbstractTabFragment {

        ArrayList<Map<String, Object>> Data = new ArrayList<Map<String, Object>>();

        public static SundayFragment getInstance(Context context) {
            Bundle args = new Bundle();
            SundayFragment fragment = new SundayFragment();
            fragment.setArguments(args);
            fragment.setContext(context);
            fragment.setTitle("Вс");
            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(LAYOUT, container, false);
            Data = JsonParse.getData(6);
            ListView lv = (ListView) view.findViewById(R.id.rasp);
            SimpleTrainsTableAdapter sAdapter = new SimpleTrainsTableAdapter(context, Data, R.layout.my_list_item, from, to);
            lv.setAdapter(sAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, CurrentItem.class);
                    CurrentItem.getData(Data.get(position));
                    startActivity(intent);
                }
            });
            return view;
        }
    }
}