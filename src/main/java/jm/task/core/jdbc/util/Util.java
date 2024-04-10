package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    public Connection getConnection()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/myuser",
                    "root", "root");
            System.out.println("Соединение установлено");
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к БД");
        }
        return connection;
    }
}