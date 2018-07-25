package ru.avgroup.p998vfdev.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.avgroup.p998vfdev.data.TrainsContract.TrainsEntry;

public class TrainsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "trains.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + TrainsEntry.TABLE_NAME + " ("
            + TrainsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TrainsEntry.COLUMN_TITLE + " TEXT NOT NULL, "
            + TrainsEntry.COLUMN_DESCR + " TEXT NOT NULL, "
            + TrainsEntry.COLUMN_TIME + " TEXT NOT NULL, "
            + TrainsEntry.COLUMN_GYM + " TEXT NOT NULL, "
//            + TrainsEntry.COLUMN_CHECK + " INTEGER DEFAULT 0, "
            + TrainsEntry.COLUMN_WEEKDAY + " TEXT NOT NULL );";
//            + TrainsEntry.COLUMN_CHECK + " INTEGER NOT NULL DEFAULT 0);";

    /**
     * Конструктор {@link TrainsDbHelper}.
     *
     * @param context Контекст приложения
     */
    public TrainsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + TrainsEntry.TABLE_NAME);
        // Создаём новую таблицу
        onCreate(db);
    }

}
