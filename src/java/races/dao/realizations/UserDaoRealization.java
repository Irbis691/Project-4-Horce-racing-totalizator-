/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.realizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import races.dao.interfaces.UserDao;
import races.entities.User;
import races.dao.connection.JdbcConnection;
import java.sql.Statement;

/**
 * Class-realization of dao pattern for user
 *
 * @version 1.0 7 Jun 2015
 *
 * @author Пазинич
 */
public class UserDaoRealization implements UserDao {

    /**
     * logger-variable
     */
    private static final Logger logger = Logger.getLogger(UserDaoRealization.class);

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting user to DB
     */
    private static final String INSERT = "INSERT INTO users (login, passwordHash, userType) values (?, ?, ?)";
    /**
     * request for finding user in DB
     */
    private static final String FIND = "SELECT * FROM users where userId = ?";
    /**
     * request for finding all users in DB
     */
    private static final String FIND_ALL = "SELECT * FROM users";
    /**
     * request for updating user in DB
     */
    private static final String UPDATE = "UPDATE users SET login = ?, passwordHash = ?, userType = ?, userBalance = ? WHERE userId = ?";
    /**
     * request for deleting user from DB
     */
    private static final String DELETE = "DELETE FROM users WHERE login = ?";

    /**
     * default constructor
     *
     * @param connection
     */
    public UserDaoRealization(JdbcConnection connection) {
        this.connection = connection;
    }

    /**
     * @see UserDao#insert(races.entities.User)
     *
     * @param user
     */
    @Override
    public int insert(User user) {
        int id = 0;
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(INSERT,
                            Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getLogin());
                statement.setInt(2, user.getPasswordHash());
                statement.setInt(3, user.getUserType());
                statement.executeUpdate();
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    id = key.getInt(1);
                }
            }
        } catch (SQLException ex) {
            logger.error("User insert error: " + ex);
        }
        return id;
    }

    /**
     * @see UserDao#find(int)
     *
     * @param id
     */
    @Override
    public User find(int id) {
        User user = new User();
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    user.setUserId(rs.getInt(1));
                    user.setLogin(rs.getString(2));
                    user.setPasswordHash(rs.getInt(3));
                    user.setUserType(rs.getInt(4));
                    user.setUserBalance(rs.getInt(5));
                }
            }
        } catch (SQLException ex) {
            logger.error("User find error: " + ex);
        }
        return user;
    }

    /**
     * @see UserDao#findAll()
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND_ALL)) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    users.add(new User(rs.getInt(1), rs.getString(2),
                            rs.getInt(3), rs.getInt(4), rs.getDouble(5)));
                }
            }
        } catch (SQLException ex) {
            logger.error("User findAll error: " + ex);
        }
        return users;
    }

    /**
     * @see UserDao#update(races.entities.User)
     *
     * @param user
     */
    @Override
    public void update(User user) {
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(UPDATE)) {
                statement.setString(1, user.getLogin());
                statement.setInt(2, user.getPasswordHash());
                statement.setInt(3, user.getUserType());
                statement.setDouble(4, user.getUserBalance());
                statement.setInt(5, user.getUserId());                
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("User update error: " + ex);
        }
    }    

    /**
     * @see UserDao#delete(java.lang.String)
     *
     * @param login
     */
    @Override
    public void delete(String login) {
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(DELETE)) {
                statement.setString(1, login);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("User delete error: " + ex);
        }
    }

}
