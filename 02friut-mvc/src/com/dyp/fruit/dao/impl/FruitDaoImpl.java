package com.dyp.fruit.dao.impl;

import com.dyp.fruit.dao.BaseDao;
import com.dyp.fruit.dao.FruitDao;
import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {
    private QueryRunner queryRunner = new QueryRunner();

    public int addFruit(Fruit fruit) throws Exception {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
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
            String sql = "select fid,fname,price,fcount,remark from t_fruit";
            BeanListHandler<Fruit> fruitBeanListHandler = new BeanListHandler<>(Fruit.class);

            List<Fruit> fruitList = queryRunner.query(connection, sql, fruitBeanListHandler);
            return fruitList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }
        return null;

    }

    @Override
    public List<Fruit> getFruitList(Integer pageNo, String keyword) {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            String sql = "select fid,fname,price,fcount,remark from t_fruit where fname like ? or remark like ?  limit ?,5";
            BeanListHandler<Fruit> fruitBeanListHandler = new BeanListHandler<>(Fruit.class);

            List<Fruit> fruitList = queryRunner.query(connection, sql, fruitBeanListHandler,keyword,keyword,pageNo);
            return fruitList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }
        return null;

    }

    @Override
    public Fruit getFruit(String fid) {
        Connection connection = this.getConnection();
        String sql = "select fid,fname,price,fcount,remark from t_fruit where fid = ?";
        BeanHandler<Fruit> fruitBeanHandler = new BeanHandler<>(Fruit.class);
        try {
            Fruit fruit = queryRunner.query(connection, sql, fruitBeanHandler, fid);
            if (fruit != null){
                return fruit;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        Connection connection = this.getConnection();
        String sql = "update t_fruit set fname = ?,price = ?,fcount = ?,remark = ? where fid = ?";

        try {
            queryRunner.update(connection, sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delFruit(Integer fid) {
        Connection connection = this.getConnection();
        String sql = "delete from t_fruit where fid = ?";

        try {
            queryRunner.update(connection, sql,fid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getFruitCount(String keyword) {
        Connection connection = this.getConnection();
        String sql = "select count(1) from t_fruit where fname like ? or remark like ?";
        ScalarHandler scalarHandler = new ScalarHandler();

        try {
            Long query = (Long) queryRunner.query(connection, sql, scalarHandler,keyword,keyword);
            if (query != null){
                return query.intValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
