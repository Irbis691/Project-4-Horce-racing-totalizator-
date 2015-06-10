/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.logic;

import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.dao.connection.JdbcConnection;
import races.entities.Bet;

/**
 *
 * @author Пазинич
 */
public class PlaceBetLogic {

    public static void palceBet(int userId, String raceName, String horseName, double betSize) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        int raceId = daoFactory.createRaceDao().findRaceId(raceName);
        Bet bet = new Bet(userId, raceId, horseName, betSize);
        daoFactory.createBetDao().insert(bet);
    }
    
}
