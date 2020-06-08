/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.dao.GameDAOException;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import java.util.List;

/**
 *
 * @author davidsteffes
 */
public interface GameDAO {

    public  List<Game> getListOfGames();
    
    public Game getGameByID(Integer gameID);

    public Game createGame(Game toReturn) throws DAOPersistenceException, 
            NullGameAnswerException, FalseForGameActiveException;

//    public Round checkGuess(Integer guess, Integer gameID);

    public void setGameActiveToFalse(Game gameToEnd) throws NullGameIDException, FalseForGameActiveException, DAOPersistenceException, NullGameAnswerException, GameDAOException;

}
