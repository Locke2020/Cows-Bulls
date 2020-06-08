/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author davidsteffes
 */
public class Round {
    private Integer roundID;
    private Integer fullMatch;
    private Integer partialMatch;
    private Integer guessNo;
    private Integer gameID;
    private LocalDateTime roundTime;

    

    /**
     * @return the fullMatch
     */
    public Integer getFullMatch() {
        return fullMatch;
    }

    /**
     * @param fullMatch the fullMatch to set
     */
    public void setFullMatch(Integer fullMatch) {
        this.fullMatch = fullMatch;
    }

    /**
     * @return the partialMatch
     */
    public Integer getPartialMatch() {
        return partialMatch;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.roundID);
        hash = 67 * hash + Objects.hashCode(this.fullMatch);
        hash = 67 * hash + Objects.hashCode(this.partialMatch);
        hash = 67 * hash + Objects.hashCode(this.guessNo);
        hash = 67 * hash + Objects.hashCode(this.gameID);
        hash = 67 * hash + Objects.hashCode(this.roundTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (!Objects.equals(this.roundID, other.roundID)) {
            return false;
        }
        if (!Objects.equals(this.fullMatch, other.fullMatch)) {
            return false;
        }
        if (!Objects.equals(this.partialMatch, other.partialMatch)) {
            return false;
        }
        if (!Objects.equals(this.guessNo, other.guessNo)) {
            return false;
        }
        if (!Objects.equals(this.gameID, other.gameID)) {
            return false;
        }
        if (!Objects.equals(this.roundTime, other.roundTime)) {
            return false;
        }
        return true;
    }

    /**
     * @param partialMatch the partialMatch to set
     */
    public void setPartialMatch(Integer partialMatch) {
        this.partialMatch = partialMatch;
    }

    /**
     * @return the guessNo
     */
    public Integer getGuessNo() {
        return guessNo;
    }

    /**
     * @param guessNo the guessNo to set
     */
    public void setGuessNo(Integer guessNo) {
        this.guessNo = guessNo;
    }

    /**
     * @return the gameID
     */
    public Integer getGameID() {
        return gameID;
    }

    /**
     * @param gameID the gameID to set
     */
    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    /**
     * @return the roundID
     */
    public Integer getRoundID() {
        return roundID;
    }

    /**
     * @param roundID the roundID to set
     */
    public void setRoundID(Integer roundID) {
        this.roundID = roundID;
    }

    /**
     * @return the roundTime
     */
    public LocalDateTime getRoundTime() {
        return roundTime;
    }

    /**
     * @param RoundTime the roundTime to set
     */
    public void setRoundTime(LocalDateTime RoundTime) {
        this.roundTime = RoundTime;
    }
    
    
    
}
