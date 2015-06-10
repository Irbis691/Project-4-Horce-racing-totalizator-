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
import races.resources.ConfigurationManager;
import races.resources.MessageManager;

/**
 * Class-command for changing size of bet
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class UpdateBetSizeCommand implements ActionCommand {

    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(UpdateBetSizeCommand.class);

    /**
     * String-constant for bet's id 
     */
    private static final String PARAM_NAME_BETID = "betId";
    /**
     * String-constant for bet's size 
     */
    private static final String PARAM_NAME_BETSIZE = "betSize";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.chDelBets");
        int betId;
        double betSize;
        try {
            betId = Integer.parseInt(request.getParameter(PARAM_NAME_BETID));
        } catch (NumberFormatException ex) {
            logger.error("User not input bet id");
            request.setAttribute("inpBetId",
                    MessageManager.getProperty("message.inpBetId"));
            return page;
        }
        try {
            betSize = Double.parseDouble(request.getParameter(PARAM_NAME_BETSIZE));
        } catch (NumberFormatException ex) {
            logger.error("User not input new bet size");
            request.setAttribute("inpBetSize",
                    MessageManager.getProperty("message.inpBetSize"));
            return page;
        }
        updateBetSize(betId, betSize);
        return page;
    }

    /**
     * submethod for interaction with DB
     * @param betId
     * @param betSize 
     */
    private void updateBetSize(int betId, double betSize) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        daoFactory.createBetDao().updateBetSize(betId, betSize);
    }

}
