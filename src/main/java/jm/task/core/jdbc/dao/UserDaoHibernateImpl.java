package jm.task.core.jdbc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoHibernateImpl implements UserDao {
//Это не трогаем, пока что



    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            System.out.println("Таблица Hibernate создана");
        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Удаление таблицы Успешно");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name , lastName , age);
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Пользователь " + name + " Сохранен");
        }catch (Exception saveExeption){
             System.out.println("Ошибка при сохранении пользователя ");
             saveExeption.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("Пользователь с ID " + id + " удален");
            } else {
                System.out.println("Пользователь с ID " + id + " не найден");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя по ID");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try( Session session = Util.getSessionFactory().openSession()){
            users = session.createQuery("from User" , User.class).list();

            StringBuilder builder = new StringBuilder();
            builder.append("Спосок всех Пользователей: ");
            for(User user:users){
                builder.append(user.toString()).append("/n");
            }
            System.out.println(users);
        }catch (Exception e){
            System.out.println("Не удалось вывести всех пользователей");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица Удалена");
        }catch (Exception e){
            System.out.println("Не удалось удалить таблицу");
            e.printStackTrace();
        }
    }
}
