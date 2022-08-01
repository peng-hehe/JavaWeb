package com.dyp.book.dao;


import com.dyp.book.pojo.User;

public interface UserDAO {
    User getUser(String uname , String pwd );
}
