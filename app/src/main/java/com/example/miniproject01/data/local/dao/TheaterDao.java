package com.example.miniproject01.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.miniproject01.data.local.entity.Theater;

import java.util.List;

@Dao
public interface TheaterDao {
    @Insert
    long insert(Theater theater);

    @Update
    void update(Theater theater);

    @Delete
    void delete(Theater theater);

    @Query("SELECT * FROM theaters")
    List<Theater> getAllTheaters();
}
