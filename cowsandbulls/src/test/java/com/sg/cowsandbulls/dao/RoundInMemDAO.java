/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.service.NullRoundObjectException;
import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullFullMatchInputException;
import com.sg.cowsandbulls.dto.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidsteffes
 */
public class RoundInMemDAO implements RoundDAO {

    List<Round> roundList = new ArrayList<>();
    
    LocalDateTime ld1 = LocalDateTime.parse("2020-03-11T12:01:40");
    LocalDateTime ld2 = LocalDateTime.parse("2020-03-31T12:01:40");
    
    public RoundInMemDAO() {
    Round testRound1 = new Round();
    testRound1.setRoundTime(ld1);
    testRound1.setGameID(9873);
    testRound1.setFullMatch(1);
    testRound1.setPartialMatch(1);
    testRound1.setGuessNo(4);
    roundList.add(testRound1);
    
    Round testRound2 = new Round();
    testRound2.setRoundTime(ld2);
    testRound2.setGameID(9873);
    testRound2.setFullMatch(2);
    testRound2.setPartialMatch(1);
    testRound2.setGuessNo(8);
    roundList.add(testRound2);

    }
    @Override
    public List<Round> getListOfRounds(Integer gameID) {
        return roundList;
    }

    @Override
    public Round addRound(Round roundStatus) throws DAOPersistenceException, NullRoundObjectException, NullGameIDException, NullFullMatchInputException {
        Round testRound = new Round();
        return testRound;
        
    }
    
}
