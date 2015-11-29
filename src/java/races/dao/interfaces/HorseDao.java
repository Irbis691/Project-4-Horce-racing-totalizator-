/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.interfaces;

import java.util.List;
import races.entities.Horse;

/**
 * Interface for dao pattern for horse
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public interface HorseDao {
    
    /**
     * method for inserting horse to DB
     * 
     * @param horse
     */
    void insert(Horse horse);

    /**
     * find a horse by id
     * 
     * @param id
     * @return horse whith specified id
     */
    Horse find(int id);

    /**
     * find all horse in DB
     * 
     * @return list of all horses
     */
    List<Horse> findAll();
    
    /**     
     * @param name
     * @return id horse with specified name
     */
    Horse find(String name);

    /**
     * update some horse from DB
     * 
     * @param horse 
     */
    void update(Horse horse);

    /**
     * delete specified horse
     * 
     * @param id 
     */
    void delete(int id);
}
