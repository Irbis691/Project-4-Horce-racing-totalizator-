/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.interfaces;

import java.util.List;
import races.entities.HorseStatus;

/**
 * Interface for dao pattern for horse's status
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public interface HorseStatusDao {
    
    /**
     * method for inserting horse's status to DB
     * 
     * @param horseStatus
     */
    void insert(HorseStatus horseStatus);

    /**
     * find a horseStatus by id
     * 
     * @param id
     * @return horseStatus whith specified id
     */
    HorseStatus find(int id);

    /**
     * find all horseStatuses in DB
     * 
     * @return list of all horseStatuses
     */
    List<HorseStatus> findAll();
    
    /**
     * find all horseStatuses in DB with specified raceId
     * 
     * @param raceId
     * @return list of all horseStatuses
     */
    List<HorseStatus> findAll(int raceId);

    /**
     * find horsePlace in DB with specified raceId
     * 
     * @param horseId
     * @param raceId
     * @return horse place in race
     */
    public int findHorsePlase(int horseId, int raceId);
    
    /**
     * find horseCoeff in DB with specified raceId
     * 
     * @param horseId
     * @param raceId
     * @return horse place in race
     */
    public double findHorseCoeff(int horseId, int raceId);
    
    /**
     * update some horseStatus from DB
     * 
     * @param horseStatus 
     */
    void update(HorseStatus horseStatus);
    
    /**
     * update place of specified by id horse
     * 
     * @param id
     * @param place 
     * @param raceId 
     */
    void updatePlace(int id, int place, int raceId);
    
    /**
     * update win rate of specified by id horse
     * 
     * @param id
     * @param raceId
     * @param coeff
     */
    void updateCoeff(int id, int raceId, double coeff);

    /**
     * delete specified horseStatus
     * 
     * @param id 
     */
    void delete(int id);
}
