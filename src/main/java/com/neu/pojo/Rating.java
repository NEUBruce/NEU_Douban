package com.neu.pojo;

public class Rating {
	private int userId;
	private int movieId;
	private int rating;
	private int timestamp;

	public Rating(int userId, int movieId, int rating, int timestamp) {
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
		this.timestamp = timestamp;
	}

	public Rating() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
}