/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.interfaces;

import java.util.List;
import races.entities.Bet;

/**
 * Interface for dao pattern for bet
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public interface BetDao {

    /**
     * method for inserting bet to DB
     * 
     * @param bet
     */
    void insert(Bet bet);

    /**
     * find a bet by betId
     * 
     * @param id
     * @return bet whith specified id
     */
    Bet find(int id);

    /**
     * find all bets in DB
     * 
     * @return list of all bets
     */
    List<Bet> findAll();
    
    /**
     * find all bets of specified user
     * 
     * @param userId
     * @return list of bets of specified user
     */
    List<Bet> findByUserId(int userId);

    /**
     * update some bet from DB
     * 
     * @param bet 
     */
    void update(Bet bet);
    
    /**
     * update size of specified bet 
     * 
     * @param betId
     * @param betSize 
     */
    void updateBetSize(int betId, double betSize);

    /**
     * delete specified bet
     * 
     * @param id 
     */
    void delete(int id);
}
