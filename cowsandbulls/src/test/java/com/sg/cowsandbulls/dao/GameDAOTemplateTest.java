/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.TestApplicationConfiguration;
import com.sg.cowsandbulls.dto.Game;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
//import org.junit.runners.Suite;
//import org.junit.runners.Suite.SuiteClasses;
//import org.junit.runner.RunWith;

/**
 *
 * @author davidsteffes
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDAOTemplateTest {

    @Autowired
    GameDAO gameDao;
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
    public void testCreateGameGoldenPath() {

        try {
            Game newGame = new Game();
            newGame.setGameAnswer(1234);
            newGame.setActive(true);

            Game testGame = gameDao.createGame(newGame);

            assertEquals(true, testGame.isActive());
            assertEquals(1234, testGame.getGameAnswer());

        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testCreateGameGoldenPath.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testCreateGameGoldenPath.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit NullGameStatusException during testCreateGameGoldenPath.");
        }

    }

    @Test
    public void testCreateGameNullGameAnswer() {

        try {
            Game newGame = new Game();
            newGame.setGameAnswer(null);
            newGame.setActive(true);

            Game testGame = gameDao.createGame(newGame);

        } catch (NullGameAnswerException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testCreateGameNullGameAnswer.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit NullGameStatusException during testCreateGameNullGameAnswer.");
        }

    }

    @Test
    public void testCreateGameFalseForGameActive() {
        try {
            Game newGame = new Game();
            newGame.setGameAnswer(5678);
            newGame.setActive(false);

            Game testGame = gameDao.createGame(newGame);

        } catch (FalseForGameActiveException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testCreateGameFalseForGameActive.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testCreateGameFalseForGameActive.");

        }
    }

//    @Test
//    public void testCheckGuess() {
//    }
    /**
     * Test of setGameActiveToFalse method, of class GameDAOTemplate.
     */
    @Test
    public void testSetGameActiveToFalseGoldenPath() {

        try {
            
            Game newGame = new Game();
            newGame.setActive(true);
            newGame.setGameAnswer(1234);
            gameDao.createGame(newGame);
            
            gameDao.setGameActiveToFalse(newGame);
            Game testGame = gameDao.getGameByID(newGame.getGameID());

            assertEquals(false, testGame.isActive());
            assertEquals(1234, testGame.getGameAnswer());

        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testSetGameActiveToFalseGoldenPath.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testSetGameActiveToFalseGoldenPath.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testSetGameActiveToFalseGoldenPath.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testSetGameActiveToFalseGoldenPath.");
        } catch ( NullGameIDException ex ){
            fail("Hit testSetGameActiveToFalseNullGameAnswer during testSetGameActiveToFalseGoldenPath.");
        }
    }

    @Test
    public void testSetGameActiveToFalseNullGameAnswer() {

        try {
            Game newGame = new Game();
            newGame.setActive(true);
            newGame.setGameAnswer(1234);
            gameDao.createGame(newGame);
            newGame.setGameAnswer(null);

            gameDao.setGameActiveToFalse(newGame);

        } catch (NullGameAnswerException ex) {
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testSetGameActiveToFalseNullGameAnswer.");
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testSetGameActiveToFalseNullGameAnswer.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testSetGameActiveToFalseNullGameAnswer.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testSetGameActiveToFalseNullGameAnswer.");

        }
    }

    @Test
    public void testSetGameActiveToFalseAlreadyFalse() {
        try {
            Game testGame = new Game();
            testGame.setActive(false);
            testGame.setGameAnswer(1234);
            testGame.setGameID(5333);

            gameDao.setGameActiveToFalse(testGame);

        } catch (FalseForGameActiveException ex) {
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testSetGameActiveToFalseAlreadyFalse.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testSetGameActiveToFalseAlreadyFalse.");
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testSetGameActiveToFalseAlreadyFalse.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testSetGameActiveToFalseAlreadyFalse.");

        }
    }

    @Test
    public void testSetGameActiveToFalseNullGameID() {

        try {
            Game testGame = new Game();
            testGame.setActive(true);
            testGame.setGameAnswer(1234);
            testGame.setGameID(null);

            gameDao.setGameActiveToFalse(testGame);

        } catch (NullGameIDException ex) {
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testSetGameActiveToFalseNullGameID.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testSetGameActiveToFalseNullGameID.");
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testSetGameActiveToFalseNullGameID.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testSetGameActiveToFalseNullGameID.");

        }
    }

    @Test
    public void testGetListOfGames() {
    }

    /**
     * Test of getGameByID method, of class GameDAOTemplate.
     */
    @Test
    public void testGetGameByID() {
    }

}
