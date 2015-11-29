/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.entities;

import java.sql.Date;

/**
 * Class describes a race
 *
 * @version 1.0 7 Jun 2015
 *
 * @author Пазинич
 */
public class Race {

    /**
     * id number of race
     */
    private int raceId;
    /**
     * name if race
     */
    private String raceName;
    /**
     * date of race
     */
    private Date raceDateTime;

    /**
     * define is race finished or not
     */
    private boolean raceFinished;

    /**
     * default constructor
     */
    public Race() {
    }

    /**
     * constructor with all parameters
     *
     * @param raceId
     * @param raceName
     * @param raceDateTime
     * @param raceFinished
     */
    public Race(int raceId, String raceName, Date raceDateTime, boolean raceFinished) {
        this.raceId = raceId;
        this.raceName = raceName;
        this.raceDateTime = raceDateTime;
        this.raceFinished = raceFinished;
    }

    /**
     * constructor with parameters except raceId
     *
     * @param raceName
     * @param raceDateTime
     * @param raceFinished
     */
    public Race(String raceName, Date raceDateTime, boolean raceFinished) {
        this.raceName = raceName;
        this.raceDateTime = raceDateTime;
        this.raceFinished = raceFinished;
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
     * getter for raceName
     *
     * @return raceName
     */
    public String getRaceName() {
        return raceName;
    }

    /**
     * setter for raceName
     *
     * @param raceName
     */
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    /**
     * getter for raceDateTime
     *
     * @return raceDateTime
     */
    public Date getRaceDateTime() {
        return raceDateTime;
    }

    /**
     * setter for raceDateTime
     *
     * @param raceDateTime
     */
    public void setRaceDateTime(Date raceDateTime) {
        this.raceDateTime = raceDateTime;
    }

    /**
     * getter for raceFinished
     *
     * @return raceFinished
     */
    public boolean isRaceFinished() {
        return raceFinished;
    }

    /**
     * setter for raceFinished
     *
     * @param raceFinished
     */
    public void setRaceFinished(boolean raceFinished) {
        this.raceFinished = raceFinished;
    }

}
