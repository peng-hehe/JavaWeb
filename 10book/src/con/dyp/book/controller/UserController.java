package con.dyp.book.controller;

import con.dyp.book.pojo.User;
import con.dyp.book.service.UserService;

public class UserController {

    private UserService userService ;

    public String login(String uname , String pwd ){

        User user = userService.login(uname, pwd);

        System.out.println("user = " + user);
        return "index";
    }
}
