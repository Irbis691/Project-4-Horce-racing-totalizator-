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
import races.dao.interfaces.HorseDao;
import races.entities.Horse;
import races.dao.connection.JdbcConnection;

/**
 * Class-realization of dao pattern for horse
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public class HorseDaoRealization implements HorseDao {
    /**
     * logger-variable 
     */
    private static final Logger logger = Logger.getLogger(JdbcConnection.class);

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting horse to DB
     */
    private final static String insertQuery = "INSERT INTO horses (horseName) values (?)";
    /**
     * request for finding horse in DB
     */
    private final static String findQuery = "SELECT * FROM horses where horseId = ?";
    /**
     * request for finding all horses in DB
     */
    private final static String findAllQuery = "SELECT * FROM horses";  
    /**
     * request for finding horse's name by horse's id from DB
     */
    private final static String findNameQuery = "SELECT horseName FROM horses where horseId = ?";
    /**
     * request for finding horse's id by horse's name from DB
     */
    private final static String findIdQuery = "SELECT horseId FROM horses where horseName = ?";
    /**
     * request for updating horse in DB
     */
    private final static String updateQuery = "UPDATE horses SET horseName = ? WHERE horseId = ?";
    /**
     * request for deleting horse from DB
     */
    private final static String deleteQuery = "DELETE FROM horses WHERE horseId = ?";
    
    /**
     * default constructor
     * @param connection
     */
    public HorseDaoRealization(JdbcConnection connection) {
        this.connection = connection;
    }

    /**
     * @see HorseDao#insert(races.entities.Bet) 
     * 
     * @param horse 
     */
    @Override
    public void insert(Horse horse) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(insertQuery);
                statement.setString(1, horse.getHorseName());
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
            logger.error("Horse insert error: " + ex);
        }
    }

    /**
     * @see HorseDao#find(int) 
     * 
     * @param id 
     */
    @Override
    public Horse find(int id) {
        Horse horse = new Horse();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findQuery);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horse.setHorseId(rs.getInt(1));
                    horse.setHorseName(rs.getString(2));
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
            logger.error("Horse find error: " + ex);
        }
        return horse;
    }

    /**
     * @see HorseDao#findAll() 
     * 
     */
    @Override
    public List<Horse> findAll() {
        List<Horse> horses = new ArrayList<>();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findAllQuery);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horses.add(new Horse(rs.getInt(1), rs.getString(2)));
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
            logger.error("Horse findAll error: " + ex);
        }
        return horses;
    }              
    
    /**
     * @see HorseDao#findName(int)  
     * 
     * @param id 
     */
    @Override
    public String findName(int id) {
        String name = null;
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findNameQuery);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    name = rs.getString(1);
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
            logger.error("Horse findName error: " + ex);
        }
        return name;
    }
    
    /**
     * @see HorseDao#findId(java.lang.String) 
     * 
     * @param name 
     */
    @Override
    public int findId(String name) {
        int id = 0;
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findIdQuery);
                statement.setString(1, name);
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
            logger.error("Horse findId error: " + ex);
        }
        return id;
    }

    /**
     * @see HorseDao#update(races.entities.Horse) 
     * 
     * @param horse 
     */
    @Override
    public void update(Horse horse) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updateQuery);
                statement.setString(1, horse.getHorseName());
                statement.setInt(2, horse.getHorseId());
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
            logger.error("Horse update error: " + ex);
        }
    }

    /**
     * @see HorseDao#delete(int)  
     * 
     * @param id 
     */
    @Override
    public void delete(int id) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(deleteQuery);
                statement.setInt(1, id);
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
            logger.error("Horse delete error: " + ex);
        }
    }

}
