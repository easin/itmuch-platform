package com.cnshangquan.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.10.30.34:3307/bella?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "bellatest";
    private static final String PASSWORD = "test520";
    
    public static void main(String[] args) throws Exception {
        Connection connection = null;
	try {
	    Class.forName(DRIVER);
	    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    
	    DatabaseMetaData metadata = connection.getMetaData();
	    ResultSet resultSet = metadata.getColumns("", "bella",
                "sea_creative", null);
	    while (resultSet.next()) {
		String name = resultSet.getString("COLUMN_NAME");
		String type = resultSet.getString("TYPE_NAME");
		int size = resultSet.getInt("COLUMN_SIZE");
		
		System.out.println("Column name: [" + name + "]; type: [" + type 
		    + "]; size: [" + size + "]" + "Remarks:" + resultSet.getString("REMARKS"));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    connection.close();
	}
    }
}
