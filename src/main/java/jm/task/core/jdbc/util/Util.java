package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
            if(sessionFactory == null){
                try{
                    Configuration configuration = new Configuration();

                    configuration.setProperty("hibernate.connection.driver_class" , "com.mysql.cj.jdbc.Driver");

                    configuration.setProperty("hibernate.connection.url" , "jdbc:mysql://localhost:3307/mysql");

                    configuration.setProperty("hibernate.connection.username", "root");

                    configuration.setProperty("hibernate.connection.password", "1111");

                    configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                    configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop"); // error update(create table)

                    configuration.addAnnotatedClass(User.class);

                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build();

                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                    System.out.println("Соединение установленно с базой Hibernate");
                } catch (Exception e) {
                    System.out.println("Ошибка при создании SessionFactory");
                    e.printStackTrace();
                    throw new RuntimeException("Не удалось инициализировать Hibernate", e);

                }
            }else{
                System.out.println("Подключение к базе уже доступно");
            }
            return sessionFactory;
        }
    }






