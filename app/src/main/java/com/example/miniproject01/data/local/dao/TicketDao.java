package com.example.miniproject01.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.miniproject01.data.local.entity.Ticket;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert
    long insert(Ticket ticket);

    @Update
    void update(Ticket ticket);

    @Delete
    void delete(Ticket ticket);

    @Query("SELECT * FROM tickets WHERE userId = :userId")
    List<Ticket> getTicketsByUser(int userId);
}
