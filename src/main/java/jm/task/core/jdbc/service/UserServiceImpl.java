package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;


import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name , lastName, age);
        System.out.println("Пользователь c именем: " + name + " добавлен в таблицу!");
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users =  userDaoHibernate.getAllUsers();
        for(User user : users){
            System.out.println(user.toString());
        }
        return users;
    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
