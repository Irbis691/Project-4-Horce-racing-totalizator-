/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.interfaces;

import java.util.List;
import races.entities.User;

/**
 * Interface for dao pattern for user
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public interface UserDao {
    
    /**
     * method for inserting user to DB
     * 
     * @param user
     * @return generated user's id
     */
    int insert(User user);

    /**
     * find a user by id
     * 
     * @param id
     * @return user whith specified id
     */
    User find(int id);

    /**
     * find all users in DB
     * 
     * @return list of all users
     */
    List<User> findAll();

    /**
     * update some user from DB
     * 
     * @param user 
     */
    void update(User user);        

    /**
     * delete specified user
     * 
     * @param login 
     */
    void delete(String login);    
}
