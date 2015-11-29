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
import races.dao.interfaces.BetDao;
import races.entities.Bet;
import races.dao.connection.JdbcConnection;
import org.apache.log4j.Logger;
import races.entities.BetStatus;

/**
 * Class-realization of dao pattern for bet
 *
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class BetDaoRealization implements BetDao {

    /**
     * logger-variable
     */
    private static final Logger logger = Logger.getLogger(BetDaoRealization.class);

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting bet to DB
     */
    private static final String INSERT = "INSERT INTO bets (userId, raceId, horseName, betSize, betStatus) values (?, ?, ?, ?, ?)";
    /**
     * request for finding bet in DB
     */
    private static final String FIND = "SELECT * FROM bets where betId = ?";
    /**
     * request for finding all bets in DB
     */
    private static final String FIND_ALL = "SELECT * FROM bets";
    /**
     * request for finding specified by userId bets in DB
     */
    private static final String FIND_BY_USER_ID = "SELECT * FROM bets where userId = ?";
    /**
     * request for finding specified by raceId bets in DB
     */
    private static final String FIND_BY_RACE_ID = "SELECT * FROM bets where raceId = ?";
    /**
     * request for updating bet size in DB
     */
    private static final String UPDATE_BET_SIZE = "UPDATE bets SET betSize = ? WHERE betId = ?";    
    /**
     * request for updating bet size in DB
     */
    private static final String UPDATE_BET_STATUS = "UPDATE bets SET betStatus = ? WHERE betId = ?";   
    /**
     * request for deleting bet from DB
     */
    private static final String DELETE = "DELETE FROM bets WHERE betId = ?";
    
    /**
     * number 0 corresponds to bet status "not played yet"
     */
    private static final int BET_NOT_PLAYED_YET = 0;

    /**
     * default constructor
     *
     * @param connection
     */
    public BetDaoRealization(JdbcConnection connection) {
        this.connection = connection;
    }

    /**
     * @see BetDao#insert(races.entities.Bet)
     *
     * @param bet
     */
    @Override
    public void insert(Bet bet) {
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(INSERT)) {
                statement.setInt(1, bet.getUserId());
                statement.setInt(2, bet.getRaceId());
                statement.setString(3, bet.getHorseName());
                statement.setDouble(4, bet.getBetSize());
                statement.setDouble(5, BET_NOT_PLAYED_YET);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("Bet insert error: " + ex);
        }
    }

    /**
     * @see BetDao#find(int)
     *
     * @param id
     */
    @Override
    public Bet find(int id) {
        Bet bet = new Bet();
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bet.setBetId(rs.getInt(1));
                    bet.setUserId(rs.getInt(2));
                    bet.setRaceId(rs.getInt(3));
                    bet.setHorseName(rs.getString(4));
                    bet.setBetSize(rs.getDouble(5)); 
                    bet.setBetStatus(defineBetStatus(rs.getInt(6)));
                }
            }
        } catch (SQLException ex) {
            logger.error("Bet find error: " + ex);
        }
        return bet;
    }
    
    /**
     * @see BetDao#findAll()
     *
     */
    @Override
    public List<Bet> findAll() {
        List<Bet> bets = new ArrayList<>();
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND_ALL)) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bets.add(new Bet(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getString(4), rs.getDouble(5),
                            defineBetStatus(rs.getInt(6))));
                }
            }
        } catch (SQLException ex) {
            logger.error("Bet find error: " + ex);
        }
        return bets;
    }

    /**
     * @see BetDao#findByUserId(int)
     *
     * @param userId
     */
    @Override
    public List<Bet> findByUserId(int userId) {
        List<Bet> bets = new ArrayList<>();
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND_BY_USER_ID)) {
                statement.setInt(1, userId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bets.add(new Bet(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getString(4), rs.getDouble(5),
                            defineBetStatus(rs.getInt(6))));
                }
            }
        } catch (SQLException ex) {
            logger.error("Bet findByUserId error: " + ex);
        }
        return bets;
    }       
    
    /**
     * @see BetDao#findByRaceId(int)
     *
     * @param raceId
     */
    @Override
    public List<Bet> findByRaceId(int raceId) {
        List<Bet> bets = new ArrayList<>();
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND_BY_RACE_ID)) {
                statement.setInt(1, raceId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bets.add(new Bet(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getString(4), rs.getDouble(5),
                            defineBetStatus(rs.getInt(6))));
                }
            }
        } catch (SQLException ex) {
            logger.error("Bet findByUserId error: " + ex);
        }
        return bets;
    }    
    
     /**
     * method for transforming db betStatus (0, 1 or 2) to web-app
     * betStatus (BetStatus enum)
     * 
     * @param status
     * @return 
     */
    private BetStatus defineBetStatus(int status) {
        switch(status) {
            case 0:
                return BetStatus.NOT_PLAYED_YET;
            case 1:
                return BetStatus.WIN;
            case 2:
                return BetStatus.LOSE;
        }
        return BetStatus.NOT_PLAYED_YET;
    }

    /**
     * @see BetDao#updateBetSize(int, double)
     *
     * @param betId
     * @param betSize
     */
    @Override
    public void updateBetSize(int betId, double betSize) {
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(UPDATE_BET_SIZE)) {
                statement.setDouble(1, betSize);
                statement.setInt(2, betId);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("Bet updateBetSize error: " + ex);
        }
    }

     /**
     * @see BetDao#updateBetStatus(int, double)
     *
     * @param betId
     * @param status
     */
    @Override
    public void updateBetStatus(int betId, int status) {
         try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(UPDATE_BET_STATUS)) {
                statement.setDouble(1, status);
                statement.setInt(2, betId);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("Bet updateBetSize error: " + ex);
        }
    }
    
    /**
     * @see BetDao#delete(int)
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(DELETE)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("Bet delete error: " + ex);
        }
    }
}
