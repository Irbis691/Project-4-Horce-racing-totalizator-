/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.logic;

import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.dao.connection.JdbcConnection;

/**
 *
 * @author Пазинич
 */
public class DeleteBetLogic {

    public static void deleteBetSize(int betId) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        daoFactory.createBetDao().delete(betId);
    }

}
