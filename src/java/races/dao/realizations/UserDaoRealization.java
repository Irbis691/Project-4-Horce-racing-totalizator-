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
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public class UserDaoRealization implements UserDao {
    /**
     * logger-variable 
     */
    private static final Logger logger = Logger.getLogger(JdbcConnection.class);

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting user to DB
     */
    private final static String insertQuery = "INSERT INTO users (login, passwordHash, userType) values (?, ?, ?)";
    /**
     * request for finding user in DB
     */
    private final static String findQuery = "SELECT * FROM users where userId = ?";
    /**
     * request for finding all users in DB
     */
    private final static String findAllQuery = "SELECT * FROM users";
    /**
     * request for finding all user's logins in DB
     */
    private final static String findLoginsQuery = "SELECT login FROM users";
    /**
     * request for finding all hash codes of user's passwords in DB
     */
    private final static String findPassQuery = "SELECT passwordHash FROM users";
    /**
     * request for finding type of specified (by login) user in DB
     */
    private final static String findTypeQuery = "SELECT userType FROM users where login = ?";
    /**
     * request for finding id of specified (by login) user in DB
     */
    private final static String findIdQuery = "SELECT userId FROM users where login = ?";
    /**
     * request for updating user in DB
     */
    private final static String updateQuery = "UPDATE users SET login = ?, password = ?, userType = ? WHERE userId = ?";
    /**
     * request for deleting user from DB
     */
    private final static String deleteQuery = "DELETE FROM users WHERE login = ?";    

    /**
     * default constructor
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
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            try {
                statement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getLogin());
                statement.setInt(2, user.getPasswordHash());
                statement.setInt(3, user.getUserType());
                statement.executeUpdate();
                ResultSet key = statement.getGeneratedKeys();                
                if (key.next()) {
                    id = key.getInt(1);
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
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
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findQuery);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    user.setUserId(rs.getInt(1));
                    user.setLogin(rs.getString(2));
                    user.setPasswordHash(rs.getInt(3));
                    user.setUserType(rs.getInt(4));
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
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
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findAllQuery);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    users.add(new User(rs.getInt(1), rs.getString(2),
                            rs.getInt(3), rs.getInt(4)));
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException ex) {
            logger.error("User findAll error: " + ex);
        }
        return users;
    }
    
    /**
     * @see UserDao#findLogins()  
     */
    @Override
    public List<String> findLogins() {
        List<String> logins = new ArrayList<>();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findLoginsQuery);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    logins.add(rs.getString(1));
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException ex) {
            logger.error("User findLogins error: " + ex);
        }
        return logins;
    }
    
    /**
     * @see UserDao#findPass() 
     */
    @Override
    public List<Integer> findPass() {
        List<Integer> passwords = new ArrayList<>();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findPassQuery);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    passwords.add(rs.getInt(1));
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException ex) {
            logger.error("User findPass error: " + ex);
        }
        return passwords;
    }
    
    /**
     * @see UserDao#getType(java.lang.String) 
     * 
     * @param login  
     */
    @Override
    public int getType(String login) {
        int type = 0;
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findTypeQuery);
                statement.setString(1, login);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    type = rs.getInt(1);
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException ex) {
            logger.error("User getType error: " + ex);
        }
        return type;
    }
    
    /**
     * @see UserDao#getId(java.lang.String) 
     * 
     * @param login  
     */
    @Override
    public int getId(String login) {
        int id = 0;
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findIdQuery);
                statement.setString(1, login);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    id = rs.getInt(1);
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException ex) {
            logger.error("User getId error: " + ex);
        }
        return id;
    }

    /**
     * @see UserDao#update(races.entities.User) 
     * 
     * @param user 
     */
    @Override
    public void update(User user) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updateQuery);
                statement.setString(1, user.getLogin());
                statement.setInt(2, user.getPasswordHash());
                statement.setInt(3, user.getUserType());
                statement.setInt(4, user.getUserId());
                statement.executeUpdate();
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
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
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(deleteQuery);
                statement.setString(1, login);
                statement.executeUpdate();
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException ex) {
            logger.error("User delete error: " + ex);
        }
    }    

}
