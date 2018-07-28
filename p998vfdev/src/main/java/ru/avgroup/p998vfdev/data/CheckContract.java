package ru.avgroup.p998vfdev.data;

import android.provider.BaseColumns;

public final class CheckContract {

    private CheckContract(){
    }

    public static final class CheckEntry implements BaseColumns {
        public final static String TABLE_NAME = "checks";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NUM = "id";

    }
}
