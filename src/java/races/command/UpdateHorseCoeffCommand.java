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
 * Class-command for updating horse's win rate
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class UpdateHorseCoeffCommand implements ActionCommand {
    
    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(UpdateHorseCoeffCommand.class);
           
    /**
     * String-constant for horse's win rate 
     */
    private static final String PARAM_NAME_COEFF = "coeff";
    /**
     * String-constant for horse's name 
     */
    private static final String PARAM_NAME_NAME = "name";
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = new TakeRacesCommand().execute(request);
        double coeff;
        String name = (String)request.getParameter(PARAM_NAME_NAME);
        if (name == null) {
            logger.error("Bookie not choose horse");
            request.setAttribute("chooseHorse",
                    MessageManager.getProperty("message.chooseHorse"));
            return page;
        }
        try {
            coeff = Double.parseDouble(request.getParameter(PARAM_NAME_COEFF));
        } catch (NumberFormatException ex) {
            logger.error("Bookie not input new coefficient");
            request.setAttribute("inpHorCo",
                    MessageManager.getProperty("message.inpHorCo"));
            return page;
        }
        updateCoeff(name, coeff);
        return page;
    }
    
    /**
     * submethod for interaction with DB
     * @param name
     * @param coeff 
     */
    private void updateCoeff(String name, double coeff) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        int id = daoFactory.createHorseDao().findId(name);
        daoFactory.createHorseStatusDao().updateCoeff(id, coeff);
    }
}
