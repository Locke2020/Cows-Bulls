/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dto;

import java.util.Objects;

/**
 *
 * @author davidsteffes
 */
public class Game {
    
    private Integer gameID;
    private boolean active;
    private Integer gameAnswer;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.gameID);
        hash = 17 * hash + (this.active ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.gameAnswer);
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
        final Game other = (Game) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.gameID, other.gameID)) {
            return false;
        }
        if (!Objects.equals(this.gameAnswer, other.gameAnswer)) {
            return false;
        }
        return true;
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
     * @return the gameAnswer
     */
    public Integer getGameAnswer() {
        return gameAnswer;
    }

    /**
     * @param gameAnswer the gameAnswer to set
     */
    public void setGameAnswer(Integer gameAnswer) {
        this.gameAnswer = gameAnswer;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    
    
}
