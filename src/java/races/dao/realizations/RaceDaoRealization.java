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
    private static final Logger logger = Logger.getLogger(RaceDaoRealization.class);
    
    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting race to DB
     */
    private static final String INSERT = "INSERT INTO races (raceName, raceDateTime) values (?, ?)";
    /**
     * request for finding race in DB
     */
    private static final String FIND = "SELECT * FROM races where raceId = ?";
    /**
     * request for finding all races in DB
     */
    private static final String FIND_ALL = "SELECT * FROM races";
    /**
     * request for finding id of specified (by name) race in DB
     */
    private static final String FIND_RACE_ID = "SELECT raceId FROM races where raceName = ?";
    /**
     * request for finding name and date of specified (by id) race in DB
     */
    private static final String FIND_RACE_NAME = "SELECT raceName, raceDateTime FROM races where raceId = ?";
    /**
     * request for updating race in DB
     */
    private static final String UPDATE = "UPDATE races SET raceName = ?, raceDateTime = ? WHERE raceId = ?";
    /**
     * request for updating race status in DB
     */
    private static final String UPDATE_STATUS = "UPDATE races SET raceStatus = ? WHERE raceId = ?";
    /**
     * request for deleting race from DB
     */
    private static final String DELETE = "DELETE FROM races WHERE raceName = ?";
    
    /**
     * number 0 corresponds to race status "not finished"
     */
    private static final int RACE_NOT_FINISHED = 0;
    /**
     * number 1 corresponds to race status "finished"
     */
    private static final int RACE_FINISHED = 1;

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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(INSERT)){
                statement.setString(1, race.getRaceName());
                statement.setDate(2, race.getRaceDateTime());
                statement.setInt(3, RACE_NOT_FINISHED);
                statement.executeUpdate();
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(FIND)){
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    race.setRaceId(rs.getInt(1));
                    race.setRaceName(rs.getString(2));
                    race.setRaceDateTime(rs.getDate(3));
                    race.setRaceFinished((rs.getInt(4) != 0));
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(FIND_ALL)){
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    races.add(new Race(rs.getInt(1), rs.getString(2),
                            rs.getDate(3), rs.getInt(4) != 0));
                }
            }
        } catch (SQLException ex) {
            logger.error("Race findAll error: " + ex);
        }
        return races;
    }
    
    /**
     * @see RaceDao#findRaceId(java.lang.String) 
     * 
     * @param raceName  
     */
    @Override
    public int findRaceId(String raceName) {
        int raceId = 0;
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(FIND_RACE_ID)){
                statement.setString(1, raceName);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    raceId = rs.getInt(1);
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(FIND_RACE_NAME)){
                statement.setInt(1, raceId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    raceName = rs.getString(1) + ", " + rs.getString(2);
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(UPDATE)){
                statement.setString(1, race.getRaceName());
                statement.setDate(2, race.getRaceDateTime());
                statement.setInt(3, race.getRaceId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("Race update error: " + ex);
        }
    }
    
    /**
     * @see RaceDao#updateRaceStatus(races.entities.Race) 
     * 
     * @param raceId 
     */
    @Override
    public void updateRaceStatus(int raceId) {
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(UPDATE_STATUS)){
                statement.setInt(1, RACE_FINISHED);
                statement.setInt(2, raceId);
                statement.executeUpdate();
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                            = con.prepareStatement(DELETE)){
                statement.setString(1, raceName);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("Race delete error: " + ex);
        }
    }
    
}
