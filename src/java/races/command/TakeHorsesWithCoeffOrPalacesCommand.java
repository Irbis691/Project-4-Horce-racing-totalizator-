/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.HorseStatus;
import races.resources.ConfigurationManager;

/**
 * Class-command for taking list of horses
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class TakeHorsesWithCoeffOrPalacesCommand implements ActionCommand {    

    private static final int ADMIN_TYPE = 1;
    private static final int BOOKIE_TYPE = 2;
    private static final int CLIENT_TYPE = 3;
    
    /**
     * String-constant for race's name 
     */
    private static final String PARAM_NAME_RACE = "raceName";
    
    @Override
    public String execute(HttpServletRequest request) {                            
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        String raceName = request.getParameter(PARAM_NAME_RACE);    
        int raceId = getRaceIdFromSessionOrPut(raceName, request, daoFactory);
        int type = (Integer)request.getSession().getAttribute("type");
        List<HorseStatus> horst = daoFactory.createHorseStatusDao().findAll(raceId);
        request.setAttribute("horses", buildResultByUserType(type, horst, daoFactory));
        String page = defineToWhatConsoleRedirect(type);
        return page;
    }

    private Map buildResultByUserType(int type, List<HorseStatus> horst, DaoFactory daoFactory) {
        if(type == ADMIN_TYPE) {
            Map<String, Integer> horseNamesWithPalaces = new HashMap<>();
            for(HorseStatus hs: horst) {
                horseNamesWithPalaces.put(daoFactory.createHorseDao().
                        find(hs.getHorseId()).getHorseName(), hs.getHorsePlace());
            }
            return horseNamesWithPalaces;
        } else {
            Map<String, Double> horseNamesWithCoeff = new HashMap<>();            
            for(HorseStatus hs: horst) {
                horseNamesWithCoeff.put(daoFactory.createHorseDao().
                        find(hs.getHorseId()).getHorseName(), hs.getHorseCoeff());
            }
            return horseNamesWithCoeff;
        }
    }

    private int getRaceIdFromSessionOrPut(String race,
            HttpServletRequest request, DaoFactory daoFactory) {
        int raceId;        
        if(race == null) {
            raceId = (Integer) request.getSession().getAttribute("raceId");
        } else {
            raceId = daoFactory.createRaceDao().findRaceId(race);
            request.getSession().setAttribute("raceId", raceId);
        }
        return raceId;
    }

    private String defineToWhatConsoleRedirect(int type) {
        String page = ConfigurationManager.getProperty("path.page.login");
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
        }
        return page;
    }
    
}
