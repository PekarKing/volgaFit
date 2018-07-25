package ru.avgroup.p998vfdev.fragment;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.avgroup.p998vfdev.R;


public class JsonParse extends AsyncTask<Void, Void, Void> {

    private static ArrayList<Map<String, Object>> monData = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> tueData = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> wedData = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> thuData = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> friData = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> satData = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> sunData = new ArrayList<Map<String, Object>>();

    private static final String[] CT = {"ABD", "Pilates", "Leg Training", "\u0421\u0443\u043f\u0435\u0440 \u043f\u043e\u043f\u0430 \u00a9",
            "FT", "BODY PUMP", "Only Women", "\u0421XWORX", "TRX PULSE*", "POWERBALL", "HOT IRON",
            "\u0414\u0435\u0442\u0441\u043a\u0438\u0439 \u0431\u043e\u043a\u0441 \u00a9", "\u0418\u0434\u0435\u0430\u043b\u044c\u043d\u0430\u044f \u0442\u0435\u0445\u043d\u0438\u043a\u0430 \u00a9",
            "Zumba", "Yoga", "Just Step", "Stretch", "Easy Yoga", "TRX*", "ABD & Stretch", "Reebok. \u0421\u0442\u0430\u043d\u044c \u0447\u0435\u043b\u043e\u0432\u0435\u043a\u043e\u043c",
            "Gymstick", "Body Training"};

    @Override
    protected Void doInBackground(Void... params) {
        HttpHandler sh = new HttpHandler();

        String url = "http://volgafit.ru/api/schedule/get_schedule/";
        String jsonStr = sh.makeServiceCall(url);

        if (jsonStr != null) {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);
                Map<String, Object> m;
                int image;

                for (int i = 0; i < 24; i++) {
                    String elem = String.valueOf(i);/// вот эта ебала была придумана из-за
                    JSONObject Train = jsonObj.getJSONObject(elem);/// кривого JSONа
                    String title = Train.getString("title"); /// парсим название
                    //ставим соответсвующую пикчу
                    if (title.equals(CT[0])){
                        image = R.drawable.abd;
                    } else if (title.equals(CT[1])){
                        image = R.drawable.pilates;
                    } else if (title.equals(CT[2])) {
                        image = R.drawable.leg_training;
                    } else if (title.equals(CT[3])) {
                        image = R.drawable.super_popa;
                    } else if (title.equals(CT[4])) {
                        image = R.drawable.ft;
                    } else if (title.equals(CT[5])) {
                        image = R.drawable.body_pump;
                    } else if (title.equals(CT[6])) {
                        image = R.drawable.only_women;
                    } else if (title.equals(CT[7])) {
                        image = R.drawable.cxworx;
                    } else if (title.equals(CT[8])) {
                        image = R.drawable.trx_pulse;
                    } else if (title.equals(CT[9])) {
                        image = R.drawable.powerball;
                    } else if (title.equals(CT[10])) {
                        image = R.drawable.hot_iron;
                    } else if (title.equals(CT[11])) {
                        image = R.drawable.detskiy_box;
                    } else if (title.equals(CT[12])) {
                        image = R.drawable.idealnya_technika;
                    } else if (title.equals(CT[13])) {
                        image = R.drawable.zumba;
                    } else if (title.equals(CT[14])) {
                        image = R.drawable.yoga;
                    } else if (title.equals(CT[15])) {
                        image = R.drawable.just_step;
                    } else if (title.equals(CT[16])) {
                        image = R.drawable.stretch;
                    } else if (title.equals(CT[17])) {
                        image = R.drawable.easy_yoga;
                    } else if (title.equals(CT[18])) {
                        image = R.drawable.trx;
                    } else if (title.equals(CT[19])) {
                        image = R.drawable.abd_stretch;
                    } else if (title.equals(CT[20])) {
                        image = R.drawable.reebok;
                    }  else if (title.equals(CT[21])) {
                        image = R.drawable.gymnastick;
                    }  else if (title.equals(CT[22])) {
                        image = R.drawable.body_training;
                    } else {
                        image = R.drawable.abd;
                    }

                    JSONArray fullTrainInfo = Train.getJSONArray("hours"); /// проходим в описание

                    for (int j = 0; j < fullTrainInfo.length(); j++) {
                        JSONObject data = fullTrainInfo.getJSONObject(j);
                        //получаем описание
                        String description = data.getString("description");
                        if (description.length() < 5) {
                            description = "Описание отсутсвует";
                        }
                        //получаем тайминги
                        String time = data.getString("start").substring(0, 5) +
                                " - " + data.getString("end").substring(0, 5);
                        //получаем зал занятий
                        String gym = data.getString("gym");
                        if (gym.length() > 5) {
                            gym = gym.substring(0, 6);
                            gym = gym.replace("№", "");
                        }
                        //получаем день недели и декодим из UNICODE в UTF8
                        String weekday = data.getString("weekday");
                        boolean ifInSecDB = false;
                        String decode = "";
                        try {
                            byte[] utf8Bytes = weekday.getBytes("UTF8");
                            decode = new String(utf8Bytes, "UTF8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        m = new HashMap<String, Object>();
                        m.put("title", title);
                        m.put("description", description);
                        m.put("time", time);
                        m.put("image", image);
                        m.put("gym", gym);
                        m.put("weekday", weekday);

                        String[] dow = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};

                        if (decode.equals(dow[0])) {
                            monData.add(m);
                        } else if (decode.equals(dow[1])) {
                            tueData.add(m);
                        } else if (decode.equals(dow[2])) {
                            wedData.add(m);
                        } else if (decode.equals(dow[3])) {
                            thuData.add(m);
                        } else if (decode.equals(dow[4])) {
                            friData.add(m);
                        } else if (decode.equals(dow[5])) {
                            satData.add(m);
                        } else if (decode.equals(dow[6])) {
                            sunData.add(m);
                        }
                    }
                }
            } catch (final JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<Map<String, Object>> getData(Integer arg) {

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        if (arg == 0) {
            data = monData;
        }
        else if (arg == 1){
            data = tueData;
        }
        else if (arg == 2){
            data = wedData;
        }
        else if (arg == 3){
            data = thuData;
        }
        else if (arg == 4){
            data = friData;
        }
        else if (arg == 5){
            data = satData;
        }
        else if (arg == 6){
            data = sunData;
        }
        return data;
    }

}
