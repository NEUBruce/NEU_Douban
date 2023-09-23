package com.neu.pojo;

public class History {
    private int id;//历史推荐记录id
    private long userId;//用户id
    private long movieId;//电影id
    private long timestamp;//时间戳

    public History(int id, long userId, long movieId, int timestamp) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.timestamp = timestamp;
    }

    public History() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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
