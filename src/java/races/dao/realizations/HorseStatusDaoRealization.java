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
import races.dao.interfaces.HorseStatusDao;
import races.entities.HorseStatus;
import races.dao.connection.JdbcConnection;

/**
 * Class-realization of dao pattern for horse's status
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public class HorseStatusDaoRealization implements HorseStatusDao {
    /**
     * logger-variable 
     */
    private static final Logger logger = Logger.getLogger(JdbcConnection.class);

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting horse's status to DB
     */
    private final static String insertQuery = "INSERT INTO horsestatus (horseId, raceId, horsePlace, horseCoeff) values (?, ?, ?, ?))";
    /**
     * request for finding horse's status in DB
     */
    private final static String findQuery = "SELECT * FROM horsestatus where horseStatusId = ?";
    /**
     * request for finding all horse's statuses in DB
     */
    private final static String findAllQuery = "SELECT * FROM horsestatus";
    /**
     * request for finding all horses who take part in specified (by id) race in DB
     */
    private final static String findAllByIdQuery = "SELECT * FROM horsestatus where raceId = ?";    
    /**
     * request for updating horse's status in DB
     */
    private final static String updateQuery = "UPDATE horsestatus SET horseId = ?, raceId = ?, horsePlace = ?, horseCoeff = ? WHERE horseStatusId = ?";
    /**
     * request for updating horse's place in DB
     */
    private final static String updatePlaceQuery = "UPDATE horsestatus SET horsePlace = ? WHERE horseId = ?";
    /**
     * request for updating horse's win rate in DB
     */
    private final static String updateCoeffQuery = "UPDATE horsestatus SET horseCoeff = ? WHERE horseId = ?";  
    /**
     * request for deleting horse's status from DB
     */
    private final static String deleteQuery = "DELETE FROM horsestatus WHERE horseStatusId = ?";

    /**
     * default constructor
     * @param connection
     */
    public HorseStatusDaoRealization(JdbcConnection connection) {
        this.connection = connection;
    }

    /**
     * @see HorseStatusDao#insert(races.entities.HorseStatus) 
     * 
     * @param horseStatus 
     */
    @Override
    public void insert(HorseStatus horseStatus) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(insertQuery);
                statement.setInt(1, horseStatus.getHorseId());
                statement.setInt(2, horseStatus.getRaceId());
                statement.setInt(3, horseStatus.getHorsePlace());
                statement.setDouble(4, horseStatus.getHorseCoeff());
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
            logger.error("HorseStatus insert error: " + ex);
        }
    }

    /**
     * @see HorseStatusDao#find(int) 
     * 
     * @param id 
     */
    @Override
    public HorseStatus find(int id) {
        HorseStatus horseStatus = new HorseStatus();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findQuery);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horseStatus.setHorseStatusId(rs.getInt(1));
                    horseStatus.setHorseId(rs.getInt(2));
                    horseStatus.setRaceId(rs.getInt(3));
                    horseStatus.setHorsePlace(rs.getInt(4));
                    horseStatus.setHorseCoeff(rs.getDouble(5));
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
            logger.error("HorseStatus find error: " + ex);
        }
        return horseStatus;
    }

    /**
     * @see HorseStatusDao#findAll() 
     */
    @Override
    public List<HorseStatus> findAll() {
        List<HorseStatus> horseStatuses = new ArrayList<>();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findAllQuery);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horseStatuses.add(new HorseStatus(rs.getInt(1), rs.getInt(2),
                            rs.getInt(3), rs.getInt(4), rs.getDouble(5)));
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
            logger.error("HorseStatus findAll error: " + ex);
        }
        return horseStatuses;
    }
    
    /**
     * @see HorseStatusDao#findAll(int) 
     * 
     * @param raceId 
     */
    @Override
    public List<HorseStatus> findAll(int raceId){
        List<HorseStatus> horseStatuses = new ArrayList<>();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findAllByIdQuery);
                statement.setInt(1, raceId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horseStatuses.add(new HorseStatus(rs.getInt(1), rs.getInt(2),
                            rs.getInt(3), rs.getInt(4), rs.getDouble(5)));
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
            logger.error("HorseStatus findAll by id error: " + ex);
        }
        return horseStatuses;
    }

    /**
     * @see HorseStatusDao#update(races.entities.HorseStatus) 
     * 
     * @param horseStatus 
     */
    @Override
    public void update(HorseStatus horseStatus) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updateQuery);
                statement.setInt(1, horseStatus.getHorseId());
                statement.setInt(2, horseStatus.getRaceId());
                statement.setInt(3, horseStatus.getHorsePlace());
                statement.setDouble(4, horseStatus.getHorseCoeff());
                statement.setInt(5, horseStatus.getHorseStatusId());
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
            logger.error("HorseStatus update error: " + ex);
        }
    }
    
    /**
     * @see HorseStatusDao#updatePlace(int, int) 
     * 
     * @param id
     * @param place
     */
    @Override
    public void updatePlace(int id, int place) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updatePlaceQuery);
                statement.setInt(1, place);
                statement.setInt(2, id);
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
            logger.error("HorseStatus updatePlace error: " + ex);
        }
    }
    
    /**
     * @see HorseStatusDao#updateCoeff(int, double) 
     * 
     * @param id
     * @param coeff
     */
    @Override
    public void updateCoeff(int id, double coeff) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updateCoeffQuery);
                statement.setDouble(1, coeff);
                statement.setInt(2, id);
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
            logger.error("HorseStatus updateCoeff error: " + ex);
        }
    }

    /**
     * @see HorseStatusDao#delete(int) 
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
            logger.error("HorseStatus delete error: " + ex);
        }
    }

}
