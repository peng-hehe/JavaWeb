package com.dyp.fruit.service;

import com.dyp.fruit.dao.FruitDao;
import com.dyp.fruit.dao.impl.FruitDaoImpl;
import com.dyp.fruit.pojo.Fruit;

import java.util.List;

public class FruitService {

    private FruitDao fruitDao = new FruitDaoImpl();


    public boolean addFruit(Fruit fruit){
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

    public List<Fruit> getFruitList(){
        List<Fruit> fruitList = fruitDao.getFruitList();

        return fruitList;
    }

    public List<Fruit> getFruitList(Integer pageNo, String keyword){
        pageNo = (pageNo-1)*5;
        String keywordSearch = "%" + keyword + "%";
        List<Fruit> fruitList = fruitDao.getFruitList(pageNo,keywordSearch );

        return fruitList;
    }

    public Fruit eidtFruit(String fid) {
        Fruit fruit = fruitDao.getFruit(fid);
        return fruit;
    }

    public void updateFruit(Fruit fruit) {
        fruitDao.updateFruit(fruit);
    }

    public void delFruit(Integer fid) {
        fruitDao.delFruit(fid);
    }

    public Integer getFruitCount(String keyword) {
        String keywordsearch = "%" + keyword + "%";
        Integer fruitCount = fruitDao.getFruitCount(keywordsearch);
        return fruitCount;
    }
}
