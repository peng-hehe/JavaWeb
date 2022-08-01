package com.dyp.book.controller;

import com.dyp.book.pojo.User;
import com.dyp.book.service.UserService;

public class UserController {

    private UserService userService ;

    public String login(String uname , String pwd ){

        User user = userService.login(uname, pwd);

        System.out.println("user = " + user);
        return "index";
    }
}
