/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.resources;

import java.util.List;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.Race;

/**
 *
 * @author Пазинич
 */
public class ForTableOnIndex {

    private static final JdbcConnection connection = JdbcConnection.getInstance();
    private static final DaoFactory daoFactory = new RealDaoFactory(connection);
    private static final List<Race> races = daoFactory.createRaceDao().findAll();

    private ForTableOnIndex() {
    }

    /**
     *
     * @return
     */
    public static List<Race> getRaceList() {
        return races;
    }
}
