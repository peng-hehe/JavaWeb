package com.dyp.fruit.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {

    private static DruidDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(resourceAsStream);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getConnection());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***
     * 获得
     * @return
     */
    public static Connection getConnection () throws SQLException {
        Connection con = null;

        con = dataSource.getConnection();

        return con;
    }


    public static void close(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
