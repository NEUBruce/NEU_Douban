package com.neu.util;

import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import com.mysql.cj.jdbc.MysqlDataSource;

public class PreferencreDataModel {

	public static JDBCDataModel getPreferenceDataModel() {
        MysqlDataSource dataSource = new MysqlDataSource();
        JDBCDataModel dataModel = null;
        try {
            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("*@Abc123");
            dataSource.setDatabaseName("movie");


            ConnectionPoolDataSource connectionPool=new ConnectionPoolDataSource(dataSource);
            // use JNDI
            dataModel = new MySQLJDBCDataModel(connectionPool,"movie_preferences", "userID", "movieID","preference", "timestamp");


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dataModel;
    } 

}
