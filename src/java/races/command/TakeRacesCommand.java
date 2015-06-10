/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import javax.servlet.http.HttpServletRequest;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.resources.ConfigurationManager;

/**
 * Class-command for taking list of races and redirecting acoording to user's 
 * type
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class TakeRacesCommand implements ActionCommand{

    private static final int ADMIN_TYPE = 1;
    private static final int BOOKIE_TYPE = 2;
    private static final int CLIENT_TYPE = 3;
    
    @Override
    public String execute(HttpServletRequest request) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        request.setAttribute("races", daoFactory.createRaceDao().findAll());
        String page;
        int type = Integer.parseInt(request.getSession().getAttribute("type").toString());
        switch(type) {
            case ADMIN_TYPE:
                page = ConfigurationManager.getProperty("path.page.adminConsole");
                break;
            case BOOKIE_TYPE:
                page = ConfigurationManager.getProperty("path.page.bookieConsole");
                break;
            case CLIENT_TYPE:
                page = ConfigurationManager.getProperty("path.page.placeBets");
                break;
            default:
                page = ConfigurationManager.getProperty("path.page.login");
                break;
        }        
        return page;
    }
    
}
