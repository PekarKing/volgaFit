package ru.avgroup.p998vfdev.data;


import android.provider.BaseColumns;

public final class TrainsContract {

    private TrainsContract(){
    };

    public static final class TrainsEntry implements BaseColumns {
        public final static String TABLE_NAME = "trains";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TITLE = "title";
        public final static String COLUMN_DESCR = "descr";
        public final static String COLUMN_TIME = "time";
        public final static String COLUMN_GYM = "gym";
//        public final static String COLUMN_CHECK = "check";
        public final static String COLUMN_WEEKDAY = "weekday";

//        public final static int TRAIN_IMAGE = R.drawable.example;
    }
}
