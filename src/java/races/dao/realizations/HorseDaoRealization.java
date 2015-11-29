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
 *
 * @version 1.0 7 Jun 2015
 *
 * @author Пазинич
 */
public class HorseDaoRealization implements HorseDao {

    /**
     * logger-variable
     */
    private static final Logger logger = Logger.getLogger(HorseDaoRealization.class);

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;
    /**
     * request for inserting horse to DB
     */
    private static final String INSERT = "INSERT INTO horses (horseName) values (?)";
    /**
     * request for finding horse in DB
     */
    private static final String FIND = "SELECT * FROM horses where horseId = ?";
    /**
     * request for finding all horses in DB
     */
    private static final String FIND_ALL = "SELECT * FROM horses";
    /**
     * request for finding horse's id by horse's name from DB
     */
    private static final String FIND_BY_NAME = "SELECT * FROM horses where horseName = ?";
    /**
     * request for updating horse in DB
     */
    private static final String UPDATE = "UPDATE horses SET horseName = ? WHERE horseId = ?";
    /**
     * request for deleting horse from DB
     */
    private static final String DELETE = "DELETE FROM horses WHERE horseId = ?";

    /**
     * default constructor
     *
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(INSERT)) {
                statement.setString(1, horse.getHorseName());
                statement.executeUpdate();
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horse.setHorseId(rs.getInt(1));
                    horse.setHorseName(rs.getString(2));
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND_ALL)) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horses.add(new Horse(rs.getInt(1), rs.getString(2)));
                }
            }
        } catch (SQLException ex) {
            logger.error("Horse findAll error: " + ex);
        }
        return horses;
    }

    /**
     * @see HorseDao#findId(java.lang.String)
     *
     * @param name
     */
    @Override
    public Horse find(String name) {
        Horse horse = new Horse();
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(FIND_BY_NAME)) {
                statement.setString(1, name);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    horse.setHorseId(rs.getInt(1));
                    horse.setHorseName(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            logger.error("Horse findId error: " + ex);
        }
        return horse;
    }

    /**
     * @see HorseDao#update(races.entities.Horse)
     *
     * @param horse
     */
    @Override
    public void update(Horse horse) {
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(UPDATE)) {
                statement.setString(1, horse.getHorseName());
                statement.setInt(2, horse.getHorseId());
                statement.executeUpdate();
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
        try {
            try (Connection con = connection.getConnection();
                    PreparedStatement statement
                    = con.prepareStatement(DELETE)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            logger.error("Horse delete error: " + ex);
        }
    }

}
