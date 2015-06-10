/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.HorseStatus;
import races.resources.MessageManager;

/**
 * Class-command for taking list of parameters of all horses
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class TakeHorsesParamCommand implements ActionCommand {

    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(TakeHorsesParamCommand.class);
    
    /**
     * String-constant for race's name 
     */
    private static final String PARAM_NAME_RACE = "race";
    /**
     * String-constant for horse's parameter (place or win rate)
     */
    private static final String PARAM_NAME_PARAM = "param";

    @Override
    public String execute(HttpServletRequest request) {
        String page = new TakeRacesCommand().execute(request);
        String race;
        try {
            race = request.getParameter(PARAM_NAME_RACE).split(",")[0];
        } catch (NullPointerException ex) {
            logger.error("User not choose race");
            request.setAttribute("chooseRace",
                    MessageManager.getProperty("message.chooseRace"));
            return page;
        }
        String param = request.getParameter(PARAM_NAME_PARAM);
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        Map<String, Number> map = new TreeMap<>();
        List<HorseStatus> horst = daoFactory.createHorseStatusDao().
                findAll(daoFactory.createRaceDao().findRaceId(race));
        if (param.equals("place")) {
            for (HorseStatus hs : horst) {
                map.put(daoFactory.createHorseDao().findName(hs.getHorseId()),
                        hs.getHorsePlace());
            }
        } else if (param.equals("coeff")){
            for (HorseStatus hs : horst) {
                map.put(daoFactory.createHorseDao().findName(hs.getHorseId()),
                        hs.getHorseCoeff());
            }
        }
        request.setAttribute("map", map);
        return page;
    }

}
