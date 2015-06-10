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
    private static final Logger logger = Logger.getLogger(JdbcConnection.class);

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting bet to DB
     */
    private final static String insertQuery = "INSERT INTO bets (userId, raceId, horseName, betSize) values (?, ?, ?, ?)";
    /**
     * request for finding bet in DB
     */
    private final static String findQuery = "SELECT * FROM bets where betId = ?";
    /**
     * request for finding all bet in DB
     */
    private final static String findAllQuery = "SELECT * FROM bets";
    /**
     * request for finding specified bets in DB
     */
    private final static String findByUserIdQuery = "SELECT * FROM bets where userId = ?";
    /**
     * request for finding specified bets in DB
     */
    private final static String findUserIdQuery = "SELECT userId FROM bets where betId = ?";    
    /**
     * request for updating bet in DB
     */
    private final static String updateQuery = "UPDATE bets SET userId = ?, raceId = ?, horseName = ?, betSize = ? WHERE betId = ?";
    /**
     * request for updating bet size in DB
     */
    private final static String updateBetSizeQuery = "UPDATE bets SET betSize = ? WHERE betId = ?";
    /**
     * request for deleting bet from DB
     */
    private final static String deleteQuery = "DELETE FROM bets WHERE betId = ?";

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
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(insertQuery);
                statement.setInt(1, bet.getUserId());
                statement.setInt(2, bet.getRaceId());
                statement.setString(3, bet.getHorseName());
                statement.setDouble(4, bet.getBetSize());
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
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findQuery);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bet.setBetId(rs.getInt(1));
                    bet.setUserId(rs.getInt(2));
                    bet.setRaceId(rs.getInt(3));
                    bet.setHorseName(rs.getString(4));
                    bet.setBetSize(rs.getDouble(5));
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
            logger.error("Bet find error: " + ex);
        }
        return bet;
    }

    /**
     * @see BetDao#findAll()
     */
    @Override
    public List<Bet> findAll() {
        List<Bet> bets = new ArrayList<>();
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findAllQuery);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bets.add(new Bet(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getString(4), rs.getDouble(5)));
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
            logger.error("Bet findAll error: " + ex);
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
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(findByUserIdQuery);
                statement.setInt(1, userId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bets.add(new Bet(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getString(4), rs.getDouble(5)));
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
            logger.error("Bet findByUserId error: " + ex);
        }
        return bets;
    }   
    
    /**
     * @see BetDao#update(races.entities.Bet)
     *
     * @param bet
     */
    @Override
    public void update(Bet bet) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updateQuery);
                statement.setInt(1, bet.getUserId());
                statement.setInt(2, bet.getRaceId());
                statement.setString(3, bet.getHorseName());
                statement.setDouble(4, bet.getBetSize());
                statement.setInt(5, bet.getBetId());
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
            logger.error("Bet update error: " + ex);
        }
    }

    /**
     * @see BetDao#updateBetSize(int, double)
     *
     * @param betId
     * @param betSize
     */
    @Override
    public void updateBetSize(int betId, double betSize) {
        Connection con = connection.getConnection();
        PreparedStatement statement = null;
        try {
            try {
                statement = con.prepareStatement(updateBetSizeQuery);
                statement.setDouble(1, betSize);
                statement.setInt(2, betId);
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
            logger.error("Bet delete error: " + ex);
        }
    }
}
