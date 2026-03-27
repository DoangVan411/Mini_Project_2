package com.nhom2.mini_project_2.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhom2.mini_project_2.model.dao.UserDao;
import com.nhom2.mini_project_2.model.entity.MovieEntity;
import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;
import com.nhom2.mini_project_2.model.entity.TheaterEntity;
import com.nhom2.mini_project_2.model.entity.TicketEntity;
import com.nhom2.mini_project_2.model.entity.UserEntity;

@Database(
        entities = {
                UserEntity.class,
                MovieEntity.class,
                TheaterEntity.class,
                ShowtimeEntity.class,
                TicketEntity.class
        },
        version = 3,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final String DB_NAME = "movie_ticket.db";

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DB_NAME
                            )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(androidx.sqlite.db.SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    seedData(db);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static void seedData(androidx.sqlite.db.SupportSQLiteDatabase db) {
        db.execSQL("INSERT INTO users (username, password, fullName) VALUES ('admin', '123456', 'Administrator')");
        db.execSQL("INSERT INTO users (username, password, fullName) VALUES ('an', '123456', 'Nguyen Van An')");
    }
}
