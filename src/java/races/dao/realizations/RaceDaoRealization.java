/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.realizations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import races.dao.interfaces.RaceDao;
import races.entities.Race;
import races.dao.connection.JdbcConnection;

/**
 * Class-realization of dao pattern for race
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public class RaceDaoRealization implements RaceDao {
    /**
     * logger-variable 
     */
    private static final Logger logger = Logger.getLogger(JdbcConnection.class);
    
    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting race to DB
     */
    private final static String insertQuery = "INSERT INTO races (raceName, raceDateTime) values (?, ?)";
    /**
     * request for finding race in DB
     */
    private final static String findQuery = "SELECT * FROM races where raceId = ?";
    /**
     * request for finding all races in DB
     */
    private final static String findAllQuery = "SELECT * FROM races";
    /**
     * request for finding date of specified (by name) race in DB
     */
    private final static String findDate = "SELECT raceDateTime FROM races where raceName = ?";
    /**
     * request for finding id of specified (by name) race in DB
     */
    private final static String findRaceIdQuery = "SELECT raceId FROM races where raceName = ?";
    /**
     * request for finding name and date of specified (by id) race in DB
     */
    private final static String findRaceNameQuery = "SELECT raceName, raceDateTime FROM races where raceId = ?";
    /**
     * request for updating race in DB
     */
    private final static String updateQuery = "UPDATE races SET raceName = ?, raceDateTime = ? WHERE raceId = ?";
    /**
     * request for deleting race from DB
     */
    private final static String deleteQuery = "DELETE FROM races WHERE raceName = ?";

    /**
     * default constructor
     * @param connection
     */
    public RaceDaoRealization(JdbcConnection connection) {
        this.connection = connection;
    }
    
    /**
     * @see RaceDao#insert(races.entities.Race) 
     * 
     * @param race 
     */
    @Override
    public void insert(Race race) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(insertQuery);
                statement.setString(1, race.getRaceName());
                statement.setDate(2, race.getRaceDateTime());
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
            logger.error("Race insert error: " + ex);
        }
    }

    /**
     * @see RaceDao#find(int) 
     * 
     * @param id 
     */
    @Override
    public Race find(int id) {
        Race race = new Race();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findQuery);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    race.setRaceId(rs.getInt(1));
                    race.setRaceName(rs.getString(2));
                    race.setRaceDateTime(rs.getDate(3));
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
            logger.error("Race find error: " + ex);
        }
        return race;
    }

    /**
     * @see RaceDao#findAll()      
     */
    @Override
    public List<Race> findAll() {
        List<Race> races = new ArrayList<>();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findAllQuery);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    races.add(new Race(rs.getInt(1), rs.getString(2), rs.getDate(3)));
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
            logger.error("Race findAll error: " + ex);
        }
        return races;
    }
    
    /**
     * @see RaceDao#findDate(java.lang.String) 
     * 
     * @param name 
     */
    @Override
    public Date findDate(String name){
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        Date date = null;
        try {
            try {
                statement = con.prepareStatement(findDate);
                statement.setString(1, name);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    date = rs.getDate(1);
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
            logger.error("Race findDate error: " + ex);
        }
        return date;
    }
    
    /**
     * @see RaceDao#findRaceId(java.lang.String) 
     * 
     * @param raceName  
     */
    @Override
    public int findRaceId(String raceName) {
        int raceId = 0;
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findRaceIdQuery);
                statement.setString(1, raceName);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    raceId = rs.getInt(1);
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
            logger.error("Race findRaceId error: " + ex);
        }
        return raceId;
    }
    
    /**
     * @see RaceDao#findRaceNameDate(int) 
     * 
     * @param raceId  
     */
    @Override
    public String findRaceNameDate(int raceId){
        String raceName = null;
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findRaceNameQuery);
                statement.setInt(1, raceId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    raceName = rs.getString(1) + ", " + rs.getString(2);
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
            logger.error("Race findRaceName error: " + ex);
        }
        return raceName;
    }

    /**
     * @see RaceDao#update(races.entities.Race) 
     * 
     * @param race 
     */
    @Override
    public void update(Race race) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updateQuery);
                statement.setString(1, race.getRaceName());
                statement.setDate(2, race.getRaceDateTime());
                statement.setInt(3, race.getRaceId());
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
            logger.error("Race update error: " + ex);
        }
    }

    /**
     * @see RaceDao#delete(java.lang.String)  
     * 
     * @param raceName  
     */
    @Override
    public void delete(String raceName) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(deleteQuery);
                statement.setString(1, raceName);
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
            logger.error("Race delete error: " + ex);
        }
    }
    
}
