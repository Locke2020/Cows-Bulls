/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.controller;

import com.sg.cowsandbulls.dao.DAOPersistenceException;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.dao.GameDAO;
import com.sg.cowsandbulls.dao.GameDAOException;
import com.sg.cowsandbulls.service.NullFullMatchInputException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullRoundObjectException;
import com.sg.cowsandbulls.dao.RoundDAO;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import com.sg.cowsandbulls.service.ServiceLayer;
import com.sg.cowsandbulls.service.NumberIsNotUniqueException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author davidsteffes
 */
@RestController
@RequestMapping("/api")
public class Controller {
    
    @Autowired
    ServiceLayer service;
    
    
    @PostMapping("/begin")
    public Game beginGame () throws DAOPersistenceException, NullGameAnswerException, 
            FalseForGameActiveException {
        
        Game newGame = service.startGame();
        
        return newGame;
        
    }
    
    
    @PostMapping("/guess")
    
    public Round guess (@RequestBody Round roundGuess) throws FalseForGameActiveException, 
            NullGameIDException, GameDAOException, NullGameAnswerException, NumberIsNotUniqueException, 
            DAOPersistenceException, NullRoundObjectException, NullFullMatchInputException {
 
            Round toReturn = service.guess(roundGuess.getGuessNo(), roundGuess.getGameID());
            return toReturn;
        
            
    }
    
    @GetMapping("/game")
    public List<Game> listOfGames (){
            List<Game> toReturn = service.getListOfGames();
            return toReturn;
    }
    
    @GetMapping("game/{gameId}")
    public Game findGame (@PathVariable Integer gameId, Model model) throws NullGameIDException {
            Game toReturn = service.findGameByID(gameId);
            return toReturn;
    }
    
    @GetMapping("rounds/{gameId}")
    public List<Round> getListOfRounds (@PathVariable Integer gameId, Model model)
        throws NullGameIDException, DAOPersistenceException, NullGameAnswerException,
            FalseForGameActiveException {
            List<Round> toReturn = service.getListOfRounds(gameId);
            return toReturn;
    }
    
}
