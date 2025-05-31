package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        try (Connection connection = Util.getConnection()){
            if(connection != null && !connection.isClosed()){
                System.out.println("Подключение к базе данных успешна!");
            }else{
                System.out.println("Подключение к базе данных нет!");
            }
        }catch (SQLException e){
            System.out.println("Ошибка, база не подключена");
        }

        userService.createUsersTable();
        userService.saveUser("Daniil" , "Ribiakov" , (byte) 25);
        userService.saveUser("Testing1" , "mMsql1" , (byte) 21);
        userService.saveUser("Testing2" , "mMsql2" , (byte) 22);
        userService.saveUser("Testing3" , "mMsql3" , (byte) 23);
        userService.saveUser("Testing4" , "mMsql4" , (byte) 24);
        userService.removeUserById(1);
        userService.getAllUsers();
        userDaoJDBC.ChekingTableMSQL();
    }


}


