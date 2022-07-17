package com.dyp.fruit.server;

import com.dyp.fruit.dao.FruitDao;
import com.dyp.fruit.dao.impl.FruitDaoImpl;
import com.dyp.fruit.pojo.Fruit;

public class FruitServer {

    public boolean addFruit(Fruit fruit){
        FruitDao fruitDao = new FruitDaoImpl();
        try {
            int i = fruitDao.addFruit(fruit);
            if (i != 0){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
