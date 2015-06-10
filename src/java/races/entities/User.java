package races.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class describes a user
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public class User {

    /**
     * id number of user
     */
    private int userId;
    /**
     * user's login 
     */
    private String login;
    /**
     * hash code of user's password
     */
    private int passwordHash;
    /**
     * type of user (1 = admin, 2 = bookie, 3 = client)
     */
    private int userType;

    /**
     * default constructor
     */
    public User() {
    }
    
    /**
     * constructor with all parameters
     * 
     * @param userId
     * @param login
     * @param passwordHash
     * @param userType
     */
    public User(int userId, String login, int passwordHash, int userType) {
        this.userId = userId;
        this.login = login;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }
    
    /**
     * constructor with parameters except userId
     * 
     * @param login
     * @param passwordHash
     * @param userType
     */
    public User(String login, int passwordHash, int userType) {        
        this.login = login;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }
    
    /**
     * getter for userId
     * 
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setter for userId  
     * 
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    /**
     * getter for login
     * 
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * setter for login  
     * 
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * getter for passwordHash
     * 
     * @return passwordHash
     */
    public int getPasswordHash() {
        return passwordHash;
    }

    /**
     * setter for passwordHash  
     * 
     * @param passwordHash
     */
    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    /**
     * getter for userType
     * 
     * @return userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * setter for userType  
     * 
     * @param userType
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }
    
}
