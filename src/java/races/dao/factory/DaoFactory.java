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

/**
 * Abstract factory of dao pattern
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public abstract class DaoFactory {
    
    private static final String REAL_DAO_FACTORY_CLASS = "races.dao.factory.RealDaoFactory";
    
    /**
     * logger-variable
     */
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DaoFactory.class);

    /**    
     * @return BetDao entity
     */
    public abstract BetDao createBetDao();

    /**    
     * @return HorseDao entity
     */
    public abstract HorseDao createHorseDao();

    /**    
     * @return HorseStatusDao entity
     */
    public abstract HorseStatusDao createHorseStatusDao();

    /**    
     * @return RaceDao entity
     */
    public abstract RaceDao createRaceDao();

    /**    
     * @return UserDao entity
     */
    public abstract UserDao createUserDao();

    /**     
     * @return instance of DaoFactory
     */
    public static DaoFactory getInstance() {
        try {
            return (DaoFactory) Class.forName(REAL_DAO_FACTORY_CLASS).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("Error creating DaoFactory");
            return null;
        }

    }
}
