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
import races.resources.MessageManager;

/**
 * Class-command forplacing user's bet
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class PlaceBetCommand implements ActionCommand {

    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(PlaceBetCommand.class);

    /**
     * String-constant for user's id
     */
    private static final String PARAM_NAME_USERID = "userId";
    /**
     * String-constant for race's name 
     */
    private static final String PARAM_NAME_RACENAME = "raceName";
    /**
     * String-constant for horse's name 
     */
    private static final String PARAM_NAME_HORSE = "horseName";
    /**
     * String-constant for bet's size 
     */
    private static final String PARAM_NAME_BETSIZE = "betSize";

    @Override
    public String execute(HttpServletRequest request) {
        String page = new TakeRacesCommand().execute(request);
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USERID));
        String raceName = request.getParameter(PARAM_NAME_RACENAME).split(",")[0];
        String horseName = null;
        double betSize;        
        horseName = request.getParameter(PARAM_NAME_HORSE);
        if (horseName == null) {
            logger.error("User not choose horse");
            request.setAttribute("chooseHorse",
                    MessageManager.getProperty("message.chooseHorse"));
            return page;
        }
        try {
            betSize = Double.parseDouble(request.getParameter(PARAM_NAME_BETSIZE));
        } catch (NumberFormatException ex) {
            logger.error("User not input bet size");
            request.setAttribute("notInpBet",
                    MessageManager.getProperty("message.notInpBet"));
            return page;
        }
        if (!palceBet(userId, raceName, horseName, betSize)) {
            logger.error("User tried to create existed bet");
            request.setAttribute("existedBet",
                    MessageManager.getProperty("message.existedBet"));
        }
        return page;
    }

    /**
     * submethod for interaction with DB
     * 
     * @param userId
     * @param raceName
     * @param horseName
     * @param betSize
     * @return 
     */
    private boolean palceBet(int userId, String raceName, String horseName, double betSize) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        int raceId = daoFactory.createRaceDao().findRaceId(raceName);
        Bet bet = new Bet(userId, raceId, horseName, betSize);
        List<Bet> bets = daoFactory.createBetDao().findByUserId(userId);
        if (checkBetUniq(bet, bets)) {
            daoFactory.createBetDao().insert(bet);
            return true;
        } else {
            return false;
        }
    }

    /**
     * method for cheking uniquess of placed bet
     * 
     * @param bet
     * @param bets
     * @return true if such bet isn't exist, false otherwise
     */
    private boolean checkBetUniq(Bet bet, List<Bet> bets) {
        for (Bet b : bets) {
            if (bet.equals(b)) {
                return false;
            }
        }
        return true;
    }

}
