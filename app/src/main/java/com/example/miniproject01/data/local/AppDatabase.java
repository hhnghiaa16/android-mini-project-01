package com.example.miniproject01.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.miniproject01.data.local.dao.MovieDao;
import com.example.miniproject01.data.local.dao.ShowtimeDao;
import com.example.miniproject01.data.local.dao.TheaterDao;
import com.example.miniproject01.data.local.dao.TicketDao;
import com.example.miniproject01.data.local.dao.UserDao;
import com.example.miniproject01.data.local.entity.Movie;
import com.example.miniproject01.data.local.entity.Showtime;
import com.example.miniproject01.data.local.entity.Theater;
import com.example.miniproject01.data.local.entity.Ticket;
import com.example.miniproject01.data.local.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Movie.class, Theater.class, Showtime.class, Ticket.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract UserDao userDao();
    public abstract MovieDao movieDao();
    public abstract TheaterDao theaterDao();
    public abstract ShowtimeDao showtimeDao();
    public abstract TicketDao ticketDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "movie_booking_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                UserDao userDao = INSTANCE.userDao();
                MovieDao movieDao = INSTANCE.movieDao();
                TheaterDao theaterDao = INSTANCE.theaterDao();
                ShowtimeDao showtimeDao = INSTANCE.showtimeDao();

                // 1. Pre-populate User
                userDao.insert(new User("admin", "admin123", "Test User"));

                // 2. Pre-populate Movies
                long movie1Id = movieDao.insert(new Movie("Avengers: Endgame", "The grave course of events set in motion by Thanos...", "https://example.com/poster1.jpg", 181));
                long movie2Id = movieDao.insert(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology...", "https://example.com/poster2.jpg", 148));
                long movie3Id = movieDao.insert(new Movie("Interstellar", "A team of explorers travel through a wormhole in space...", "https://example.com/poster3.jpg", 169));

                // 3. Pre-populate Theaters
                long theater1Id = theaterDao.insert(new Theater("CGV Vincom", "Hanoi"));
                long theater2Id = theaterDao.insert(new Theater("Lotte Cinema", "HCMC"));

                // 4. Pre-populate Showtimes
                showtimeDao.insert(new Showtime((int)movie1Id, (int)theater1Id, "2023-10-27 18:00", 120000));
                showtimeDao.insert(new Showtime((int)movie1Id, (int)theater2Id, "2023-10-27 20:30", 110000));
                showtimeDao.insert(new Showtime((int)movie2Id, (int)theater1Id, "2023-10-28 15:00", 100000));
                showtimeDao.insert(new Showtime((int)movie3Id, (int)theater1Id, "2023-10-28 19:00", 130000));
                showtimeDao.insert(new Showtime((int)movie3Id, (int)theater2Id, "2023-10-28 21:00", 125000));
            });
        }
    };
}
