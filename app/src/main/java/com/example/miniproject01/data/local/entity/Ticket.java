package com.example.miniproject01.data.local.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tickets",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = CASCADE),
                @ForeignKey(entity = Showtime.class,
                        parentColumns = "id",
                        childColumns = "showtimeId",
                        onDelete = CASCADE)
        },
        indices = {@Index("userId"), @Index("showtimeId")})
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private int showtimeId;
    private String seatNumber;
    private String bookingDate;

    public Ticket(int userId, int showtimeId, String seatNumber, String bookingDate) {
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
