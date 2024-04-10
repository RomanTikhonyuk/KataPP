package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.ConnectionGroupManager;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }
Connection connection = getConnection();

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user " +
                                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Таблица существует");
        }
    }

    public void dropUsersTable() {
    try(Statement statement = connection.createStatement()) {
       statement.executeUpdate("DROP TABLE IF EXISTS user");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public void saveUser(String name, String lastName, byte age) {

try(PreparedStatement preparedStatement = connection.prepareStatement
        ("INSERT INTO user ( name, last_name, age) VALUES(?,?,?)")) {
    preparedStatement.setString(1,name);
    preparedStatement.setString(2,lastName);
    preparedStatement.setByte(3,age);
    preparedStatement.executeUpdate();
} catch (SQLException e) {
    System.out.println("Ошибка при добавлении");
}
    }

    public void removeUserById(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
     preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                list.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении  юзеров");
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
