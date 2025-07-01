package jm.task.core.jdbc.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.Query;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT(2) NOT NULL)";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();

            System.out.println("Таблица Hibernate создана");
        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы");
            e.printStackTrace();
        }final{
            //close session!
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
        }//final session.close()
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name , lastName , age);
            Transaction transaction = session.beginTransaction();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
            System.out.println("Пользователь " + name + " Сохранен");
        }catch (Exception saveExeption){
             System.out.println("Ошибка при сохранении пользователя ");
             saveExeption.printStackTrace();
        }//final session.close()
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
        }//final session close()
    }

    @Override
    public List<User> getAllUsers() {
        try( Session session = Util.getSessionFactory().openSession()){
           return session.createQuery("from User" , User.class).list();
        }//catch and  final session.close()
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
    }//final session.close()
}
