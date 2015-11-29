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
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.Bet;
import races.entities.User;
import races.resources.ConfigurationManager;

/**
 * Class-command for taking user's bets
 *
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class TakeUserBetsBalanceCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.myBets");
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        Map<Bet, String> betMap = new TreeMap<>();
        List<Bet> bets = daoFactory.createBetDao().findByUserId(Integer.
                parseInt(request.getSession().getAttribute("id").toString()));
        for (Bet b : bets) {
            betMap.put(b, daoFactory.createRaceDao().findRaceNameDate(b.getRaceId()));
            int horceId = daoFactory.createHorseDao().find(b.getHorseName()).getHorseId();
            b.setHorseName(b.getHorseName() + " " + daoFactory.createHorseStatusDao().findHorseCoeff(horceId, b.getRaceId()));
        }               
        request.setAttribute("bets", betMap);
        
        int userId = Integer.parseInt(request.getSession().getAttribute("id").toString());
        User user = daoFactory.createUserDao().find(userId);
        request.setAttribute("balance", user.getUserBalance());
        
        return page;
    }

}
