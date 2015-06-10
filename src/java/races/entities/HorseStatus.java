/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.entities;

/**
 * Class describes status of a horse
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public class HorseStatus {

    /**
     * id number of status record for SQL table
     */
    private int horseStatusId;
    /**
     * id number of horse
     */
    private int horseId;
    /**
     * id number of race
     */
    private int raceId;
    /**
     * place, which horse is take
     */
    private int horsePlace;
    /**
     * win rate of horse
     */
    private double horseCoeff;

    /**
     * default constructor
     */
    public HorseStatus() {
    }
    
    /**
     * constructor with all parameters
     *      
     * @param horseStatusId
     * @param horseId
     * @param raceId
     * @param horsePlace
     * @param horseCoeff
     */
    public HorseStatus(int horseStatusId, int horseId, int raceId, int horsePlace, double horseCoeff) {
        this.horseStatusId = horseStatusId;
        this.horseId = horseId;
        this.raceId = raceId;
        this.horsePlace = horsePlace;
        this.horseCoeff = horseCoeff;
    }
    
    /**
     * constructor with parameters except horseStatusId
     * 
     * @param horseId
     * @param raceId
     * @param horsePlace
     * @param horseCoeff
     */
    public HorseStatus(int horseId, int raceId, int horsePlace, double horseCoeff) {        
        this.horseId = horseId;
        this.raceId = raceId;
        this.horsePlace = horsePlace;
        this.horsePlace = horsePlace;
    }
    
    /**
     * getter for horseStatusId
     * 
     * @return horseStatusId
     */
    public int getHorseStatusId() {
        return horseStatusId;
    }

    /**
     * setter for horseStatusId  
     * 
     * @param horseStatusId
     */
    public void setHorseStatusId(int horseStatusId) {
        this.horseStatusId = horseStatusId;
    }
    
    /**
     * getter for horseId
     * 
     * @return horseId
     */
    public int getHorseId() {
        return horseId;
    }

    /**
     * setter for horseId  
     * 
     * @param horseId
     */
    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    /**
     * getter for raceId
     * 
     * @return raceId
     */
    public int getRaceId() {
        return raceId;
    }

    /**
     * setter for raceId  
     * 
     * @param raceId
     */
    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    /**
     * getter for horsePlace
     * 
     * @return horsePlace
     */
    public int getHorsePlace() {
        return horsePlace;
    }

    /**
     * setter for horsePlace  
     * 
     * @param horsePlace
     */
    public void setHorsePlace(int horsePlace) {
        this.horsePlace = horsePlace;
    }

    /**
     * getter for horseCoeff
     * 
     * @return horseCoeff
     */
    public double getHorseCoeff() {
        return horseCoeff;
    }

    /**
     * setter for horseCoeff  
     * 
     * @param horseCoeff
     */
    public void setHorseCoeff(double horseCoeff) {
        this.horseCoeff = horseCoeff;
    }

}
