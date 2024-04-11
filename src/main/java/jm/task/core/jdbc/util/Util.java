package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private static Connection connection = null;
    private static Util instance = null;

    // реализуйте настройку соеденения с БД
    public Connection getConnection() {


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

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }
}