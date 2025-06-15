package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    //JDBC
    public UserServiceImpl(){
         this.userDao = new UserDaoJDBCImpl();
    }

    //Hibernate
    //public UserServiceImpl(){
    //    this.userDao = new UserDaoHibernateImpl();   Если хотим Hibernate
    // }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name , lastName, age);
        System.out.println("Пользователь c именем: " + name + " добавлен в таблицу!");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users =  userDao.getAllUsers();
        for(User user : users){
            System.out.println(user.toString());
        }
        return users;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
