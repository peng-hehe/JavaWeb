package com.dyp.book.service.impl;

import com.dyp.book.dao.UserDAO;
import com.dyp.book.pojo.User;
import com.dyp.book.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }
}
