package com.dyp.myssm.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


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
     * 获取connection 放入threadLoad
     * @return
     */
    public static Connection getConnection(){
        Connection connection = threadLocal.get();

        if (connection == null){
            connection = createConnection();
            threadLocal.set(connection);
        }
        return threadLocal.get();

    }

    /**
     * 关闭并清掉connection
     * @return
     */

    public static void closeConn() throws SQLException {

        Connection connection = threadLocal.get();

        if (connection == null){
            return ;
        }
        if (!connection.isClosed()){
            connection.close();
            threadLocal.set(null);
        }
    }


    public static Connection createConnection()  {
        Connection con = null;

        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
