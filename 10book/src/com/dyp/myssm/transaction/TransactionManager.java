package com.dyp.myssm.transaction;

import com.dyp.myssm.util.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {


    //开启事物

    public static void beginTrans() throws SQLException {
        DruidUtils.getConnection().setAutoCommit(false);
    }


    //提交事物

    public static void commit() throws SQLException {

        Connection connection = DruidUtils.getConnection();
        connection.commit();
        DruidUtils.closeConn();


    }



    //回滚事物

    public static void rollback() throws SQLException {

        Connection connection = DruidUtils.getConnection();
        connection.rollback();
        DruidUtils.closeConn();

    }
}
