package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class UserDaoJDBCImpl implements UserDao {

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)";

        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица JDBC Создана");
        } catch (SQLException e) {
            System.out.println("Проблемы создания таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("Ошибка метода dropUsersTable");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = Util.getConnection();
             PreparedStatement pstm = connect.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ОШибка при сохранении пользователя");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connect = Util.getConnection();
             PreparedStatement pstm = connect.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connect = Util.getConnection();
             ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            StringBuilder out = new StringBuilder("Users:/n");
            for(User user : users){
                out.append(user.toString()).append("/n");
            }
            System.out.println(out.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ChekingTableMSQL() {     //Просто для себя
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

            System.out.println("Все Таблицы в базе данных:");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("При запросе таблиц произошла ошибка");
        }
    }
}
