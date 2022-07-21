package com.dyp.fruit.dao;

import com.dyp.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDao {

    int addFruit(Fruit fruit) throws Exception;

    //获取所有的库存列表信息
    List<Fruit> getFruitList();
    List<Fruit> getFruitList(Integer pageNo);

    Fruit getFruit(String fid);

    void updateFruit(Fruit fruit);

    void delFruit(Integer fid);

    Integer getFruitCount();
}
