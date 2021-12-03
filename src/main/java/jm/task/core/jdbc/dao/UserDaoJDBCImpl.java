package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl  implements UserDao {

    private Statement statement;

    public UserDaoJDBCImpl() {
        try {
            statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {

        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id INT NOT NULL AUTO_INCREMENT ," +
                    "name VARCHAR(55) NOT NULL, lastName VARCHAR(55) NOT NULL, age INT NOT NULL, PRIMARY KEY(id))");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("drop table users");
        } catch(SQLException ex) {
//            ignore
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement psSave = Util.getConnection().prepareStatement("INSERT INTO users( name, lastName, age) VALUES (?, ?, ?)");
            psSave.setString(1, name);
            psSave.setString(2, lastName);
            psSave.setByte(3, age);
            psSave.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в таблицу");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement psRemove = Util.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");
            psRemove.setLong(1, id);
            psRemove.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
          //  Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

            while (resultSet.next()) {
                User user = new User();

                user.getId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try {

            statement.executeUpdate("TRUNCATE users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
