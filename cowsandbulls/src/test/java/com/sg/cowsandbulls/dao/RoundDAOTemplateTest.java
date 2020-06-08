/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.service.NullRoundObjectException;
import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullFullMatchInputException;
import com.sg.cowsandbulls.TestApplicationConfiguration;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author davidsteffes
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDAOTemplateTest {

    @Autowired
    GameDAO gameDao;
    
    @Autowired
    RoundDAO roundDao;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddRoundGoldenPath() {

        LocalDateTime ld = LocalDateTime.parse("2020-03-01T12:01:40");

        try {
            
            Game testGame = new Game();
            testGame.setActive(true);
            testGame.setGameAnswer(1234);
            gameDao.createGame(testGame);
            
            Round newRound = new Round();
            newRound.setRoundTime(ld);
            newRound.setGameID(testGame.getGameID());
            newRound.setFullMatch(2);
            newRound.setPartialMatch(0);
            newRound.setGuessNo(3);

            Round testRound = roundDao.addRound(newRound);

            assertEquals(ld, testRound.getRoundTime());
            assertEquals(2, testRound.getFullMatch());
            assertEquals(0, testRound.getPartialMatch());
            assertEquals(3, testRound.getGuessNo());

        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testAddRoundGoldenPath.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testAddRoundGoldenPath.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testAddRoundGoldenPath.");
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testAddRoundGoldenPath.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testAddRoundGoldenPath.");
        } catch (FalseForGameActiveException ex){
            fail("Hit FalseForGameActiveException during testAddRoundGoldenPath.");
        }
    }

    @Test
    public void testAddRoundNullRoundInput() {
        try {
            Round testRound = roundDao.addRound(null);

        } catch (NullRoundObjectException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testAddRoundNullRoundInput.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testAddRoundNullRoundInput.");
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testAddRoundNullRoundInput.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testAddRoundNullRoundInput.");
        } catch (FalseForGameActiveException ex){
            fail("Hit FalseForGameActiveException during testAddRoundNullRoundInput.");
        }
    }

    @Test
    public void testAddRoundNullGameID() {

        LocalDateTime ld = LocalDateTime.parse("2020-04-01T12:01:40");

        try {
            Round newRound = new Round();
            newRound.setRoundTime(ld);
            newRound.setGameID(null);
            newRound.setFullMatch(2);
            newRound.setPartialMatch(0);
            newRound.setGuessNo(3);

            Round testRound = roundDao.addRound(newRound);
        } catch (NullGameIDException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testAddRoundNullGameID.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testAddRoundNullGameID.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testAddRoundNullGameID.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testAddRoundNullGameID.");
        } catch (FalseForGameActiveException ex){
            fail("Hit FalseForGameActiveException during testAddRoundNullGameID.");
        }
    }

    @Test
    public void testAddRoundNullFullMatchInput() {

        LocalDateTime ld = LocalDateTime.parse("2020-04-03T12:01:40");

        try {
            Round newRound = new Round();
            newRound.setRoundTime(ld);
            newRound.setGameID(8765);
            newRound.setFullMatch(null);
            newRound.setPartialMatch(0);
            newRound.setGuessNo(3);

            Round testRound = roundDao.addRound(newRound);

        } catch (NullFullMatchInputException ex) {
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testAddRoundNullFullMatchInput.");
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testAddRoundNullFullMatchInput.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testAddRoundNullFullMatchInput.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testAddRoundNullFullMatchInput.");
        } catch (FalseForGameActiveException ex){
            fail("Hit FalseForGameActiveException during testAddRoundNullFullMatchInput.");
        }
    }

    @Test
    public void testGetListOfRoundsGoldenPath() {
    
    try {
        LocalDateTime ld = LocalDateTime.parse("2020-03-01T12:01:40");

        Game testGame = new Game();
        testGame.setActive(true);
        testGame.setGameAnswer(1234);
        gameDao.createGame(testGame);
            
        Round newRound = new Round();
        newRound.setRoundTime(ld);
        newRound.setGameID(12);
        newRound.setFullMatch(2);
        newRound.setPartialMatch(0);
        newRound.setGuessNo(3);
        
        List <Round> testList = roundDao.getListOfRounds(12);
        
    } catch (DAOPersistenceException ex) {
        fail("Hit DAOPersistenceException during testGetListOfRoundsGoldenPath.");
    } catch (NullGameAnswerException ex) {
        fail("Hit NullGameAnswerException during testGetListOfRoundsGoldenPath.");
    } catch (FalseForGameActiveException ex){
        fail("Hit FalseForGameActiveException during testGetListOfRoundsGoldenPath.");
    }
    }
}
