package com.nhom2.mini_project_2.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhom2.mini_project_2.model.dao.MovieDao;
import com.nhom2.mini_project_2.model.dao.TheaterDao;
import com.nhom2.mini_project_2.model.dao.ShowtimeDao;
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
    public abstract TheaterDao theaterDao();
    public abstract ShowtimeDao showtimeDao();

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
                                    seedTheaters(db);
                                    seedShowtimes(db);
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

    private static void seedTheaters(androidx.sqlite.db.SupportSQLiteDatabase db) {
        db.execSQL("INSERT OR IGNORE INTO theaters (id, name, address) VALUES (1, 'CGV Aeon Mall', '01 Le Trong Tan, Ha Dong')");
        db.execSQL("INSERT OR IGNORE INTO theaters (id, name, address) VALUES (2, 'Lotte Cinema', '54 Lieu Giai, Ba Dinh')");
        db.execSQL("INSERT OR IGNORE INTO theaters (id, name, address) VALUES (3, 'BHD Star', 'Vincom Pham Ngoc Thach, Dong Da')");
    }

    private static void seedShowtimes(androidx.sqlite.db.SupportSQLiteDatabase db) {
        db.execSQL("INSERT OR IGNORE INTO showtimes (id, movieId, theaterId, startTimeEpochMs, price) VALUES (1, 1, 1, 1760000000000, 95000)");
        db.execSQL("INSERT OR IGNORE INTO showtimes (id, movieId, theaterId, startTimeEpochMs, price) VALUES (2, 1, 2, 1760007200000, 100000)");
        db.execSQL("INSERT OR IGNORE INTO showtimes (id, movieId, theaterId, startTimeEpochMs, price) VALUES (3, 2, 1, 1760014400000, 85000)");
        db.execSQL("INSERT OR IGNORE INTO showtimes (id, movieId, theaterId, startTimeEpochMs, price) VALUES (4, 2, 3, 1760021600000, 90000)");
        db.execSQL("INSERT OR IGNORE INTO showtimes (id, movieId, theaterId, startTimeEpochMs, price) VALUES (5, 3, 2, 1760028800000, 95000)");
        db.execSQL("INSERT OR IGNORE INTO showtimes (id, movieId, theaterId, startTimeEpochMs, price) VALUES (6, 4, 3, 1760036000000, 100000)");
        db.execSQL("INSERT OR IGNORE INTO showtimes (id, movieId, theaterId, startTimeEpochMs, price) VALUES (7, 5, 1, 1760043200000, 90000)");
    }
}
