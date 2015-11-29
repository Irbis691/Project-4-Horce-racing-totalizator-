/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.interfaces;

import java.sql.Date;
import java.util.List;
import races.entities.Race;

/**
 * Interface for dao pattern for race
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public interface RaceDao {
    
    /**
     * method for inserting race to DB
     * 
     * @param race
     */
    void insert(Race race);

    /**
     * find a race by id
     * 
     * @param id
     * @return race whith specified id
     */
    Race find(int id);

    /**
     * find all races in DB
     * 
     * @return list of all races
     */
    List<Race> findAll();
    
    /**     
     * @param raceName
     * @return id of race with specified name
     */
    int findRaceId(String raceName);
    
    /**     
     * @param raceId
     * @return  string format: [racename], [raceDate]
     */
    String findRaceNameDate(int raceId);

    /**
     * update some race from DB
     * 
     * @param race 
     */
    void update(Race race);
    
    /**
     * update race status in DB
     * 
     * @param raceId 
     */
    public void updateRaceStatus(int raceId);
    
    /**
     * delete specified race
     * 
     * @param raceName 
     */
    void delete(String raceName);
}
