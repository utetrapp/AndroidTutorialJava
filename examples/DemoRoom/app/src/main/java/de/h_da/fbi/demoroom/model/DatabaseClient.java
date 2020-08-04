package de.h_da.fbi.demoroom.model;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    public static final String DATABASE_NAME = "city_db";
    private static DatabaseClient instance;
    private Context context;
    private AppDatabase db;

    private DatabaseClient(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return db;
    }

}
