package ru.avgroup.p998vfdev.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.avgroup.p998vfdev.data.CheckContract.CheckEntry;

public class CheckDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "checks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + CheckEntry.TABLE_NAME + " ("
            + CheckEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CheckEntry.COLUMN_NUM + " INTEGER DEFAULT 0 );";


    /**
     * Конструктор {@link CheckDbHelper}.
     *
     * @param context Контекст приложения
     */
    public CheckDbHelper(Context context) {
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
        db.execSQL("DROP TABLE IF IT EXISTS " + CheckEntry.TABLE_NAME);
        // Создаём новую таблицу
        onCreate(db);
    }
}
