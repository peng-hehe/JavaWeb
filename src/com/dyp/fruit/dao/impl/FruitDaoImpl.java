package com.dyp.fruit.dao.impl;

import com.dyp.fruit.dao.BaseDao;
import com.dyp.fruit.dao.FruitDao;
import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class FruitDaoImpl extends BaseDao implements FruitDao {

    public int addFruit(Fruit fruit) throws Exception {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "insert into t_fruit (fname,price,fcount,remark) values(?,?,?,?)";

            int update = queryRunner.update(connection, sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
            return update;
        } catch (SQLException e) {
            throw new Exception("insert t_fruit error",e);
        } finally {
        }
    }
}