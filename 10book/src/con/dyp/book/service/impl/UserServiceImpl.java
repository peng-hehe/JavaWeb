package con.dyp.book.service.impl;

import con.dyp.book.dao.UserDAO;
import con.dyp.book.pojo.User;
import con.dyp.book.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }
}
