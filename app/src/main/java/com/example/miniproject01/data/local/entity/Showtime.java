package com.example.miniproject01.data.local.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "showtimes",
        foreignKeys = {
                @ForeignKey(entity = Movie.class,
                        parentColumns = "id",
                        childColumns = "movieId",
                        onDelete = CASCADE),
                @ForeignKey(entity = Theater.class,
                        parentColumns = "id",
                        childColumns = "theaterId",
                        onDelete = CASCADE)
        },
        indices = {@Index("movieId"), @Index("theaterId")})
public class Showtime {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int movieId;
    private int theaterId;
    private String startTime;
    private double price;

    public Showtime(int movieId, int theaterId, String startTime, double price) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.startTime = startTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
