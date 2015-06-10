/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.entities;

/**
 * Class describes a horse
 * @version 1.0 7 Jun 2015
 * 
 * @author Пазинич
 */
public class Horse {

    /**
     * id number of horse
     */
    private int horseId;
    /**
     * name of horse
     */
    private String horseName;

    /**
     * default constructor
     */
    public Horse() {
    }

    /**
     * constructor with all parameters
     * 
     * @param horseId
     * @param horseName
     */
    public Horse(int horseId, String horseName) {
        this.horseId = horseId;
        this.horseName = horseName;
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
     * getter for horseName
     * 
     * @return horseName
     */
    public String getHorseName() {
        return horseName;
    }

    /**
     * setter for horseName  
     * 
     * @param horseName
     */
    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

}
