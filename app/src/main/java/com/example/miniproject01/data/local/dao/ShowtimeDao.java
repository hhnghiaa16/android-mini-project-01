package com.example.miniproject01.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.miniproject01.data.local.entity.Showtime;

import java.util.List;

@Dao
public interface ShowtimeDao {
    @Insert
    long insert(Showtime showtime);

    @Update
    void update(Showtime showtime);

    @Delete
    void delete(Showtime showtime);

    @Query("SELECT * FROM showtimes WHERE movieId = :movieId")
    List<Showtime> getShowtimesByMovie(int movieId);

    @Query("SELECT * FROM showtimes")
    List<Showtime> getAllShowtimes();
}
