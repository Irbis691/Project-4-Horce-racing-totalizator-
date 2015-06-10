/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.Bet;
import races.resources.ConfigurationManager;
import races.resources.MessageManager;

/**
 * Class-command for deleting bet from DB
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class DeleteBetCommand implements ActionCommand {

    /**
     * Logger variable
     */
    private static final Logger logger = Logger.getLogger(DeleteBetCommand.class);

    /**
     * String-constant for id of bet
     */
    private static final String PARAM_NAME_BETID = "betId";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.chDelBets");
        int betId;
        try {
            betId = Integer.parseInt(request.getParameter(PARAM_NAME_BETID));
        } catch (NumberFormatException ex) {
            logger.error("User not input bet id");
            request.setAttribute("inpBetId",
                    MessageManager.getProperty("message.inpBetId"));
            return page;
        }
        if(checkBetOwner(betId, request)) {
            deleteBet(betId);            
        } else {
            logger.error("User tried to delete not his/her bet");
            request.setAttribute("delNotOwnBet",
                    MessageManager.getProperty("message.delNotOwnBet"));
        }        
        return page;
    }
    /**
     * submethod for interaction with DB
     * @param betId 
     */
    private void deleteBet(int betId) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        daoFactory.createBetDao().delete(betId);
    }

    private boolean checkBetOwner(int betId, HttpServletRequest request) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        List<Bet> bets = daoFactory.createBetDao().findByUserId(Integer.
                parseInt(request.getSession().getAttribute("id").toString()));
        for(Bet b: bets) {
            if(b.getBetId() == betId) {
                return true;
            }
        }
        return false;
    }

}
