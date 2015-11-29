/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.Race;
import races.resources.ConfigurationManager;

/**
 * Class-command for taking list of races and redirecting acoording to user's 
 * type
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class TakeRacesCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        List<Race> races = daoFactory.createRaceDao().findAll();        
        for(Iterator<Race> i = races.iterator(); i.hasNext();) {
            if(i.next().isRaceFinished()) {
                i.remove();
            }
        }
        request.setAttribute("races", races);
        String page;      
        page = ConfigurationManager.getProperty("path.page.races");
        return page;
    }
    
}
