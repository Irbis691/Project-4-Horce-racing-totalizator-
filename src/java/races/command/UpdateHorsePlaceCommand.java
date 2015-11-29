/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.resources.MessageManager;

/**
 * Class-command for uodating horse's place
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class UpdateHorsePlaceCommand implements ActionCommand {

    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(UpdateHorsePlaceCommand.class);

    /**
     * String-constant for horse's place 
     */
    private static final String PARAM_NAME_PLACE = "place";
    /**
     * String-constant for horse's name 
     */
    private static final String PARAM_NAME_NAME = "horseName";

    @Override
    public String execute(HttpServletRequest request) {
        int raceId = (Integer) request.getSession().getAttribute("raceId");
        String name = request.getParameter(PARAM_NAME_NAME);
        String page = new TakeHorsesWithCoeffOrPalacesCommand().execute(request);        
        int place;
        try {
            place = Integer.parseInt(request.getParameter(PARAM_NAME_PLACE));
        } catch (NumberFormatException ex) {
            logger.error("Admin not input new place");
            request.setAttribute("inpHorPl",
                    MessageManager.getProperty("message.inpHorPl"));
            return page;
        }
        updatePlace(name, place, raceId);
        new TakeHorsesWithCoeffOrPalacesCommand().execute(request); 
        return page;
    }

    /**
     * submethod for interaction with DB
     * @param name
     * @param place 
     */
    private void updatePlace(String name, int place, int raceId) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        int id = daoFactory.createHorseDao().find(name).getHorseId();
        daoFactory.createHorseStatusDao().updatePlace(id, place, raceId);
    }

}
