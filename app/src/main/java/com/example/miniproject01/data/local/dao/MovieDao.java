package com.example.miniproject01.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.miniproject01.data.local.entity.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    long insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();
}
