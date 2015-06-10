/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.HorseStatus;
import races.resources.MessageManager;

/**
 * Class-command for taking list of horses
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class TakeHorsesCommand implements ActionCommand {
    
    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(TakeHorsesCommand.class);

    /**
     * String-constant for race's name 
     */
    private static final String PARAM_NAME_RACE = "race";
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = new TakeRacesCommand().execute(request);
        String race = null;
        try {
            race = request.getParameter(PARAM_NAME_RACE).split(",")[0];
        } catch (NullPointerException ex) {
            logger.error("User not choose race");
            request.setAttribute("chooseRace",
                    MessageManager.getProperty("message.chooseRace"));
            return page;
        }
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);     
        List<HorseStatus> horst = daoFactory.createHorseStatusDao().findAll(daoFactory.createRaceDao().findRaceId(race));
        List<String> horseNames = new ArrayList<>();
        for(HorseStatus hs: horst) {
            horseNames.add(daoFactory.createHorseDao().findName(hs.getHorseId()));
        }
        request.setAttribute("horses", horseNames);
        return page;
    }
    
}
