package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String dbURL = "jdbc:mysql://localhost:3307/mysql";
    private static final String dbUSER = "root";
    private static final String dbPASSWORD = "1111";

    public static Connection getConnection(){

        Connection connect = null;
        try{
            connect = DriverManager.getConnection(dbURL , dbUSER , dbPASSWORD);
        } catch (SQLException e){
            System.err.println("Проблемы с подключением к БАЗЕ ДАННЫХ Ы");
            e.getMessage();
        }
        return connect;
    }


}



