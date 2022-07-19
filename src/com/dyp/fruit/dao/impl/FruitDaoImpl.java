package com.dyp.fruit.dao.impl;

import com.dyp.fruit.dao.BaseDao;
import com.dyp.fruit.dao.FruitDao;
import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {
    private QueryRunner queryRunner = new QueryRunner();

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
            DruidUtils.close(connection);
        }
    }

    @Override
    public List<Fruit> getFruitList() {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select fid,fname,price,fcount,remark from t_fruit where fid <= ?";
            BeanListHandler<Fruit> fruitBeanListHandler = new BeanListHandler<>(Fruit.class);

            List<Fruit> fruitList = queryRunner.query(connection, sql, fruitBeanListHandler,10);
            return fruitList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }
        return null;

    }


}
