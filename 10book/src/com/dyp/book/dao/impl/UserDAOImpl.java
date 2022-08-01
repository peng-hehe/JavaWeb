package com.dyp.book.dao.impl;


import com.dyp.book.dao.UserDAO;
import com.dyp.book.pojo.User;
import com.dyp.myssm.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAOImpl  implements UserDAO {
    private QueryRunner queryRunner;

    @Override
    public User getUser(String uname, String pwd) {
        Connection connection = null;
        try {
            Connection conn = DruidUtils.getConnection();
            String sql = "select * from t_user where uname like ? and pwd like ? ";
            BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);

            User user = queryRunner.query(conn, sql, userBeanHandler, uname, pwd);
            System.out.println(user);
            if (user != null){
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
