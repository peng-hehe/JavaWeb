package con.dyp.book.dao;


import con.dyp.book.pojo.User;

public interface UserDAO {
    User getUser(String uname , String pwd );
}
