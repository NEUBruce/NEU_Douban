package com.neu.pojo;

public class History {
    private int id;
    private long userId;
    private long movieId;

    private int timestamp;

    public History(int id, long userId, long movieId, int timestamp) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }
}
