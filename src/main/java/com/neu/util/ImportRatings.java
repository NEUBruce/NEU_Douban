package com.neu.util;

import com.neu.pojo.Rating;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportRatings {

	public final static String TABLE_NAME = "movie_preferences";
	public final static String USER_ID_COLUMN = "userID";
	public final static String MOVIE_ID_COLUMN = "movieID";
	public final static String RATING = "preference";
	public final static String TIMESTAMP = "timestamp";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LineNumberReader lineReader = new LineNumberReader(new FileReader(
					"src/main/webapp/movielens/ratings.dat"));
			String line = "";
			List<Rating> ratingList = new ArrayList<Rating>();
			while ((line = lineReader.readLine()) != null) {
				ratingList.add(fillRating(line));
			}
			insertRatings(ratingList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Rating fillRating(String line) {

		String[] ratings = line.split("::");
		Rating rating = new Rating();
		rating.setUserId(Integer.parseInt(ratings[0]));
		rating.setMovieId(Integer.parseInt(ratings[1]));
		rating.setRating(Integer.parseInt(ratings[2]));
		rating.setTimestamp(Integer.parseInt(ratings[3]));
		return rating;
	}


	public static void insertRatings(List<Rating> ratings) {
		Connection conn = SqlSessionFactoryUtils.getJDBCConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + USER_ID_COLUMN
				+ ", " + MOVIE_ID_COLUMN + ", " + RATING + ", " + TIMESTAMP
				+ ") values (?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			for (Rating rating : ratings) {
				ps.setLong(1, rating.getUserId());
				ps.setLong(2, rating.getMovieId());
				ps.setInt(3, rating.getRating());
				ps.setLong(4, rating.getTimestamp());
				ps.addBatch();
			}

			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
