package com.nhom2.mini_project_2.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhom2.mini_project_2.model.dao.MovieDao;
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
    public abstract MovieDao movieDao();

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
                                    seedUsers(db);
                                }

                                @Override
                                public void onOpen(androidx.sqlite.db.SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    seedMovies(db);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static void seedUsers(androidx.sqlite.db.SupportSQLiteDatabase db) {
        db.execSQL("INSERT INTO users (username, password, fullName) VALUES ('admin', '123456', 'Administrator')");
        db.execSQL("INSERT INTO users (username, password, fullName) VALUES ('an', '123456', 'Nguyen Van An')");
    }

    private static void seedMovies(androidx.sqlite.db.SupportSQLiteDatabase db) {
        db.execSQL("INSERT OR IGNORE INTO movies (id, title, genre, durationMinutes, ageRating, description) VALUES (1, 'Dune: Part Two', 'Sci-Fi', 166, 'T13', 'Paul Atreides unites with the Fremen to fight for Arrakis.')");
        db.execSQL("INSERT OR IGNORE INTO movies (id, title, genre, durationMinutes, ageRating, description) VALUES (2, 'Inside Out 2', 'Animation', 96, 'P', 'Riley enters her teenage years with new emotions in headquarters.')");
        db.execSQL("INSERT OR IGNORE INTO movies (id, title, genre, durationMinutes, ageRating, description) VALUES (3, 'Godzilla x Kong', 'Action', 115, 'T13', 'Two iconic titans join forces against a hidden threat.')");
        db.execSQL("INSERT OR IGNORE INTO movies (id, title, genre, durationMinutes, ageRating, description) VALUES (4, 'The Batman', 'Crime', 176, 'T16', 'Batman investigates corruption and a serial killer in Gotham.')");
        db.execSQL("INSERT OR IGNORE INTO movies (id, title, genre, durationMinutes, ageRating, description) VALUES (5, 'Interstellar', 'Sci-Fi', 169, 'T13', 'Explorers travel through a wormhole to save humanity.')");
    }
}
