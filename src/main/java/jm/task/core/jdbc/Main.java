package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    private static UserService userService;
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Daniil" , "JDBC - Hibernate" , (byte) 25);
        userService.saveUser("Testing1" , "mMsql1" , (byte) 21);
        userService.saveUser("Testing2" , "mMsql2" , (byte) 22);
        userService.saveUser("Testing3" , "mMsql3" , (byte) 23);
        userService.saveUser("Testing4" , "mMsql4" , (byte) 24);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }


}


