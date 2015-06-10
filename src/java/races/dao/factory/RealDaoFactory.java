/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.factory;

import races.dao.interfaces.BetDao;
import races.dao.interfaces.HorseDao;
import races.dao.interfaces.HorseStatusDao;
import races.dao.interfaces.RaceDao;
import races.dao.interfaces.UserDao;
import races.dao.realizations.BetDaoRealization;
import races.dao.realizations.HorseDaoRealization;
import races.dao.realizations.HorseStatusDaoRealization;
import races.dao.realizations.RaceDaoRealization;
import races.dao.realizations.UserDaoRealization;
import races.dao.connection.JdbcConnection;

/**
 * Real factory of dao pattern
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class RealDaoFactory extends DaoFactory{

    /**
     * variable for connection to DB
     */
    private final JdbcConnection connection;

    /**
     * default constructor
     *
     * @param connection
     */
    public RealDaoFactory(JdbcConnection connection) {
        this.connection = connection;

    }
    
    /**
     * @return BetDaoRealization entity
     * @see DaoFactory#createBetDao() 
     */
    @Override
    public BetDao createBetDao() {
        return new BetDaoRealization(connection);
    }

    /**
     * @return HorseDaoRealization entity
     * @see DaoFactory#createHorseDao() 
     */
    @Override
    public HorseDao createHorseDao() {
        return new HorseDaoRealization(connection);
    }

    /**
     * @return createHorseStatusDao entity
     * @see DaoFactory#createHorseStatusDao() 
     */
    @Override
    public HorseStatusDao createHorseStatusDao() {
        return new HorseStatusDaoRealization(connection);
    }

    /**
     * @return RaceDaoRealization entity
     * @see DaoFactory#createRaceDao() 
     */
    @Override
    public RaceDao createRaceDao() {
        return new RaceDaoRealization(connection);
    }

    /**
     * @return UserDaoRealization entity
     * @see DaoFactory#createUserDao() 
     */
    @Override
    public UserDao createUserDao() {
        return new UserDaoRealization(connection);
    }
    
}
