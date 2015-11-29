/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.Bet;
import races.entities.User;

/**
 *
 * @author Пазинич
 */
public class FixRaceResult implements ActionCommand {
    
    private static final int WIN = 1;
    private static final int LOSE = 2;

    @Override
    public String execute(HttpServletRequest request) {
        int raceId = (Integer) request.getSession().getAttribute("raceId");
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        daoFactory.createRaceDao().updateRaceStatus(raceId);
        
        List<Bet> bets = daoFactory.createBetDao().findByRaceId(raceId);
        for (Bet bet: bets) {
            User user = daoFactory.createUserDao().find(bet.getUserId());
            if(daoFactory.createHorseStatusDao().findHorsePlase(
                    daoFactory.createHorseDao().find(bet.getHorseName()).getHorseId(),
                    raceId) == 1) {
                double horseCoeff = daoFactory.createHorseStatusDao().
                        findHorseCoeff(daoFactory.createHorseDao().
                                find(bet.getHorseName()).getHorseId(), raceId);
                daoFactory.createBetDao().updateBetStatus(bet.getBetId(), WIN);                
                user.setUserBalance(user.getUserBalance() + 
                        bet.getBetSize() * (horseCoeff - 1));
            } else {
                daoFactory.createBetDao().updateBetStatus(bet.getBetId(), LOSE);
                user.setUserBalance(user.getUserBalance() - bet.getBetSize());
            }
            daoFactory.createUserDao().update(user);
        }
        
        return new TakeRacesCommand().execute(request);
    }
    
}
