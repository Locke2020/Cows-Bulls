/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.dto.Game;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidsteffes
 */
public class GameInMemDAO implements GameDAO {

    List<Game> gameList = new ArrayList<>();

    public GameInMemDAO() {
        Game testGame1 = new Game();
        testGame1.setGameID(99);
        testGame1.setActive(true);
        testGame1.setGameAnswer(1298);
        gameList.add(testGame1);

        Game testGame2 = new Game();
        testGame2.setGameID(101);
        testGame2.setActive(false);
        testGame2.setGameAnswer(1980);
        gameList.add(testGame2);

    }

    @Override
    public List<Game> getListOfGames() {

        return gameList;

    }

    @Override
    public Game getGameByID(Integer gameID) {
        
        int index = -2;
        
        for(int i = 0; i < gameList.size(); i++){
            Game gameToFind = gameList.get(i);
            if(gameID == gameToFind.getGameID()){
                
                index = i;
                break;
            }
            
        }

        return gameList.get(index);

    }

    @Override
    public Game createGame(Game toReturn) throws DAOPersistenceException, NullGameAnswerException, FalseForGameActiveException {
        return toReturn;
        
    }

    @Override
    public void setGameActiveToFalse(Game gameToEnd) throws NullGameIDException, FalseForGameActiveException, DAOPersistenceException, NullGameAnswerException, GameDAOException {
        if (gameToEnd.isActive() == false) {
            throw new FalseForGameActiveException("This game should be active, but it's listed as inactive.");
        }
        if (gameToEnd.getGameID() == null) {
            throw new NullGameIDException("Game ID is null.");
        }
        
        gameToEnd.setActive(false);
    }

}
