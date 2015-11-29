/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.entities;

import java.util.Objects;

/**
 * Class describes a bet
 *
 * @version 1.0 7 Jun 2015
 *
 * @author Пазинич
 */
public class Bet implements Comparable {

    /**
     * id number of bet
     */
    private int betId;
    /**
     * id number of users who owns a bet
     */
    private int userId;
    /**
     * id number of race
     */
    private int raceId;
    /**
     * the name of the horse on which the bet
     */
    private String horseName;
    /**
     * size of bet
     */
    private double betSize;

    /**
     * status of bet: win, lose or not played yet
     */
    private BetStatus betStatus;

    /**
     * default constructor
     */
    public Bet() {

    }

    /**
     * constructor with all parameters
     *
     * @param betId
     * @param userId
     * @param raceId
     * @param horseName
     * @param betSize
     * @param betStatus
     */
    public Bet(int betId, int userId, int raceId, String horseName,
            double betSize, BetStatus betStatus) {
        this.betId = betId;
        this.raceId = raceId;
        this.userId = userId;
        this.horseName = horseName;
        this.betSize = betSize;
        this.betStatus = betStatus;
    }

    /**
     * constructor with parameters except betId
     *
     * @param userId
     * @param raceId
     * @param horseName
     * @param betSize
     */
    public Bet(int userId, int raceId, String horseName, double betSize) {
        this.raceId = raceId;
        this.userId = userId;
        this.horseName = horseName;
        this.betSize = betSize;
    }

    /**
     * getter for betId
     *
     * @return betId
     */
    public int getBetId() {
        return betId;
    }

    /**
     * setter for betId
     *
     * @param betId
     */
    public void setBetId(int betId) {
        this.betId = betId;
    }

    /**
     * getter for betId
     *
     * @return raceId
     */
    public int getRaceId() {
        return raceId;
    }

    /**
     * setter for raceId .
     *
     * @param raceId
     */
    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    /**
     * getter for betId
     *
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setter for userId .
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * setter for horseName .
     *
     * @param horseName
     */
    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    /**
     * getter for betSize
     *
     * @return betSize
     */
    public double getBetSize() {
        return betSize;
    }

    /**
     * setter for betSize .
     *
     * @param betSize
     */
    public void setBetSize(double betSize) {
        this.betSize = betSize;
    }

    /**
     * getter for betStatus
     *
     * @return betSize
     */
    public BetStatus getBetStatus() {
        return betStatus;
    }

    /**
     * setter for betStatus
     *
     * @param betStatus
     */
    public void setBetStatus(BetStatus betStatus) {
        this.betStatus = betStatus;
    }

    /**
     * compareTo method for bets
     *
     * @param o
     * @return 0 if betId are equal, number less than 0 if first bet less than
     * second, greater than 0 otherwise
     */
    @Override
    public int compareTo(Object o) {
        Bet otherBet = (Bet) o;
        return ((Integer) this.getBetId()).compareTo((Integer) otherBet.getBetId());
    }

    /**
     * hashCode method for bets
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = ((Integer) getBetId()).hashCode();
        return hash;
    }

    /**
     * equals method for bets
     *
     * @param obj
     * @return true if userId, raceId and horsename are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bet other = (Bet) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.raceId != other.raceId) {
            return false;
        }
        if (!Objects.equals(this.horseName, other.horseName)) {
            return false;
        }
        return true;
    }

}
