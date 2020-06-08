/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.service.NullRoundObjectException;
import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullFullMatchInputException;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import java.util.List;

/**
 *
 * @author davidsteffes
 */
public interface RoundDAO {

    public List<Round> getListOfRounds(Integer gameID) throws DAOPersistenceException, 
            NullGameAnswerException, FalseForGameActiveException;

    public Round addRound(Round roundStatus) throws DAOPersistenceException, 
            NullRoundObjectException, NullGameIDException, 
            NullFullMatchInputException, NullGameAnswerException, FalseForGameActiveException;
    
}
