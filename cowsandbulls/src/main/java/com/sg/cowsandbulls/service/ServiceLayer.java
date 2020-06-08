/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.service;

import com.sg.cowsandbulls.dao.DAOPersistenceException;
import com.sg.cowsandbulls.dao.GameDAO;
import com.sg.cowsandbulls.dao.GameDAOException;
import com.sg.cowsandbulls.dao.RoundDAO;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.*;

/**
 *
 * @author davidsteffes
 */
@Component
public class ServiceLayer {
    
    GameDAO gameDAO;
    RoundDAO roundDAO;
    
    static Random rng = new Random();
    
    @Autowired
    public ServiceLayer(GameDAO gameDAO, RoundDAO roundDAO) {
        this.gameDAO = gameDAO;
        this.roundDAO = roundDAO;
        
    }
    
    public Game startGame() throws DAOPersistenceException, NullGameAnswerException, 
            FalseForGameActiveException {
        
        int gameAnswer = generateRandomNumber();
        Game newGame = new Game();
        newGame.setGameAnswer(gameAnswer);
        newGame.setActive(true);
        
        Game toReturn = gameDAO.createGame(newGame);
        toReturn.setGameAnswer(9999);
        
        return toReturn;
        
    }
    
    public Round guess(Integer guess, Integer gameID) throws FalseForGameActiveException, 
            NullGameIDException, GameDAOException, NumberIsNotUniqueException, 
            DAOPersistenceException, NullGameAnswerException, NullRoundObjectException, 
            NullFullMatchInputException {
        
        if( gameDAO.getGameByID(gameID).isActive() == false){
            throw new FalseForGameActiveException ( "Game not active." );
        }
        
        if (!isNumberUnique(guess)) {
            
            throw new NumberIsNotUniqueException ( "User entered non-unique number." );
            
        }
        
        if ( gameID == null ) {
            throw new NullGameIDException ( "Null GameID." );
        }
        
        if ( gameDAO.getGameByID(gameID).getGameAnswer() == null ) {
            throw new NullGameAnswerException ( "Null game answer." );
        }
        
        int target = gameDAO.getGameByID(gameID).getGameAnswer();
        
        int exactMatches = computeExactMatches(guess, target);
        
        Game ifUserWins = gameDAO.getGameByID(gameID);
        
        if( ifUserWins.isActive() == false ){
            throw new FalseForGameActiveException ( "Game not active." );
        }
            
        if (exactMatches == 4) {
            gameDAO.setGameActiveToFalse(ifUserWins);
        }
        int partialMatches = computePartialMatches(guess, target);
        
        ZoneId zoneID = ZoneId.of("America/Chicago");
        LocalDateTime timeNow = LocalDateTime.now(zoneID);
        
        Round roundStatus = new Round();
     
        roundStatus.setGameID(gameID);
        roundStatus.setGuessNo(guess);
        roundStatus.setFullMatch(exactMatches);
        roundStatus.setPartialMatch(partialMatches);
        roundStatus.setRoundTime(timeNow);
        
        if ( roundStatus.getFullMatch() == null ){
            throw new NullFullMatchInputException ( "Hit NullFullMatchInputException when adding Round." );
        }
        
        if(roundStatus == null){
            throw new NullRoundObjectException ("Hit NullRoundObjectException when adding Round.");
        }
        
        roundDAO.addRound(roundStatus);
        
        return roundStatus;
        
    }
    
    public List<Game> getListOfGames() {
        List<Game> toReturn = gameDAO.getListOfGames();
        
        for (int i = 0; i < toReturn.size(); i++) {
            Game checkGame = toReturn.get(i);
            if (checkGame.isActive()) {
                checkGame.setGameAnswer(9999);
            }
        }
        
        return toReturn;
        
    }
    
    public Game findGameByID(Integer gameID) throws NullGameIDException {
        
        if( gameID == null ){
            throw new NullGameIDException ( "Null Game ID." );
        }
        
        Game toReturn = gameDAO.getGameByID(gameID);
        if (toReturn.isActive()) {
            toReturn.setGameAnswer(9999);
        }
        
        return toReturn;
    }
    
    public List<Round> getListOfRounds(Integer gameId) throws NullGameIDException,
            DAOPersistenceException, NullGameAnswerException, FalseForGameActiveException{
        
        if( gameId == null ){
            throw new NullGameIDException ( "Null Game ID." );
        }
        
        List<Round> toReturn = roundDAO.getListOfRounds(gameId);
        
        return toReturn;
        
    }
    
    private int generateRandomNumber() {
        
        int toReturn = -5;
        
        List<Integer> availableNumbers = new ArrayList<>();
        availableNumbers.add(0);
        availableNumbers.add(1);
        availableNumbers.add(2);
        availableNumbers.add(3);
        availableNumbers.add(4);
        availableNumbers.add(5);
        availableNumbers.add(6);
        availableNumbers.add(7);
        availableNumbers.add(8);
        availableNumbers.add(9);
        
        toReturn = 0;
        
        for (int i = 0; i < 4; i++) {
            toReturn *= 10;
            int index = rng.nextInt(availableNumbers.size());
            toReturn += availableNumbers.get(index);
            availableNumbers.remove(index);
            
        }
        
        return toReturn;
        
    }
    
    private boolean isNumberUnique(int x) {
        
        if ( x < 100 ){
            return false;
        }
        
        boolean unique = true;
        
        int onesPlace = x % 10;
        int tensPlace = (x / 10) % 10;
        int hundredsPlace = (x / 100) % 10;
        int thousandsPlace = (x / 1000) % 10;
        
        if (onesPlace == tensPlace || onesPlace == hundredsPlace || onesPlace == thousandsPlace) {
            unique = false;
        }
        
        if (tensPlace == hundredsPlace || tensPlace == thousandsPlace) {
            unique = false;
        }
        
        if (hundredsPlace == thousandsPlace) {
            unique = false;
        }
        
        return unique;
    }
    
    private int computeExactMatches(Integer guess, int target) {
        
        int exactMatches = 0;
        
        int[] guessAsArray = convertIntegerIntoIntArray(guess);
        int[] targetAsArray = convertIntegerIntoIntArray(target);
        
        for (int i = 0; i < guessAsArray.length; i++) {
            if (guessAsArray[i] == targetAsArray[i]) {
                
                exactMatches++;
            }
        }
        return exactMatches;
    }
    
    private int computePartialMatches(Integer guess, int target) {
        
        int partialMatches = 0;
        
        int[] guessAsArray = convertIntegerIntoIntArray(guess);
        int[] targetAsArray = convertIntegerIntoIntArray(target);
        
        for (int i = 0; i < guessAsArray.length; i++) {
            for (int p = 0; p < targetAsArray.length; p++) {
                
                if ((guessAsArray[i] == targetAsArray[p]) && (i != p)) {
                    
                    partialMatches++;
                }
            }
        }
        
        return partialMatches;
    }
    
    private int[] convertIntegerIntoIntArray(int x) {
        
        
        
        String xAsString = Integer.toString(x);
        
        if ( x < 1000){
           xAsString = "0" + xAsString;
        }
        
        int stringLength = xAsString.length();
        
        int[] xAsArray = new int[xAsString.length()];
        
        for (int i = 0; i < xAsString.length(); i++) {
            xAsArray[i] = xAsString.charAt(i) - '0';
        }
        
        return xAsArray;
        
    }
    
}
