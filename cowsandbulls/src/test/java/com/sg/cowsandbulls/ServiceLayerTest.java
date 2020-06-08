/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls;

import com.sg.cowsandbulls.dao.DAOPersistenceException;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.dao.GameDAO;
import com.sg.cowsandbulls.dao.GameDAOException;
import com.sg.cowsandbulls.dao.GameInMemDAO;
import com.sg.cowsandbulls.service.NullFullMatchInputException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullRoundObjectException;
import com.sg.cowsandbulls.dao.RoundDAO;
import com.sg.cowsandbulls.dao.RoundInMemDAO;
import com.sg.cowsandbulls.service.NumberIsNotUniqueException;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import com.sg.cowsandbulls.service.ServiceLayer;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author davidsteffes
 */
public class ServiceLayerTest {

    RoundDAO roundDaoTest = new RoundInMemDAO();
    GameDAO gameDaoTest = new GameInMemDAO();

    ServiceLayer serviceLayerTest = new ServiceLayer(gameDaoTest, roundDaoTest);

    @Test
    public void testStartGameGoldenPath() {

        try {
            Game testGame = serviceLayerTest.startGame();
            assertEquals(true, testGame.isActive());
            assertEquals(9999, testGame.getGameAnswer());

        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testStartGameGoldenPath.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testStartGameGoldenPath.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testStartGameGoldenPath.");
        }
    }

    @Test
    public void testGuessGoldenPath() {
        try {

            Integer guess = 1234;
            List<Game> testList = gameDaoTest.getListOfGames();
            Game validation = testList.get(0);
            Round testRound = serviceLayerTest.guess(guess, validation.getGameID());

            assertEquals(1234, testRound.getGuessNo());
            assertEquals(99, testRound.getGameID());
            assertEquals(0, testRound.getPartialMatch());
            assertEquals(2, testRound.getFullMatch());

        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testGuessGoldenPath.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testGuessGoldenPath.");
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testGuessGoldenPath.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testGuessGoldenPath.");
        } catch (NumberIsNotUniqueException ex) {
            fail("Hit numberIsNotUniqueException during testGuessGoldenPath.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testGuessGoldenPath.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testGuessGoldenPath.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testGuessGoldenPath");
        }
    }

    @Test
    public void testGuessGameNotActive() {
        try {

            Integer guess = 1234;
            List<Game> testList = gameDaoTest.getListOfGames();
            Game validation = testList.get(0);
            validation.setActive(false);
            Round testRound = serviceLayerTest.guess(guess, validation.getGameID());

        } catch (FalseForGameActiveException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testGuessGameNotActive.");
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testGuessGameNotActive.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testGuessGameNotActive.");
        } catch (NumberIsNotUniqueException ex) {
            fail("Hit numberIsNotUniqueException during testGuessGameNotActive.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testGuessGameNotActive.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testGuessGameNotActive.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testGuessGameNotActive");
        }
    }

    @Test
    public void testGuessNotUniqueGuess() {
        try {

            Integer guess = 1144;
            List<Game> testList = gameDaoTest.getListOfGames();
            Game validation = testList.get(0);
            Round testRound = serviceLayerTest.guess(guess, validation.getGameID());

        } catch (NumberIsNotUniqueException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testGuessNotUniqueGuess.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testGuessNotUniqueGuess.");
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testGuessNotUniqueGuess.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testGuessNotUniqueGuess.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testGuessNotUniqueGuess.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testGuessNotUniqueGuess.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testGuessNotUniqueGuess");
        }
    }

    @Test
    public void testGuessNullGameID() {
        try {

            Integer guess = 1234;
            List<Game> testList = gameDaoTest.getListOfGames();
            Game validation = testList.get(0);
            validation.setGameID(null);
            Round testRound = serviceLayerTest.guess(guess, validation.getGameID());

        } catch (NullGameIDException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testGuessNullGameID.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testGuessNullGameID.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testGuessNullGameID.");
        } catch (NumberIsNotUniqueException ex) {
            fail("Hit numberIsNotUniqueException during testGuessNullGameID.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testGuessNullGameID.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testGuessNullGameID.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testGuessNullGameID");
        }
    }

    @Test
    public void testGuessNullGameAnswer() {
        try {

            Integer guess = 1234;
            List<Game> testList = gameDaoTest.getListOfGames();
            Game validation = testList.get(0);
            validation.setGameAnswer(null);
            Round testRound = serviceLayerTest.guess(guess, validation.getGameID());

        } catch (NullGameAnswerException ex) {
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testGuessNullGameAnswer.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testGuessNullGameAnswer.");
        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testGuessNullGameAnswer.");
        } catch (GameDAOException ex) {
            fail("Hit GameDAOException during testGuessNullGameAnswer.");
        } catch (NumberIsNotUniqueException ex) {
            fail("Hit numberIsNotUniqueException during testGuessNullGameAnswer.");
        } catch (NullRoundObjectException ex) {
            fail("Hit NullRoundObjectException during testGuessNullGameAnswer.");
        } catch (NullFullMatchInputException ex) {
            fail("Hit NullFullMatchInputException during testGuessNullGameAnswer");
        }
    }

    @Test
    public void testGetListOfGamesGoldenPath() {

        List<Game> listOfGames = serviceLayerTest.getListOfGames();

        Game validation1 = listOfGames.get(0);

        assertEquals(99, validation1.getGameID());
        assertEquals(true, validation1.isActive());
        assertEquals(9999, validation1.getGameAnswer());

        Game validation2 = listOfGames.get(1);

        assertEquals(101, validation2.getGameID());
        assertEquals(false, validation2.isActive());
        assertEquals(1980, validation2.getGameAnswer());

    }

    @Test
    public void testFindGameByIDGoldenPath() {

        try {
            List<Game> testList = gameDaoTest.getListOfGames();
            Game toTest = testList.get(0);
            Game validation = serviceLayerTest.findGameByID(toTest.getGameID());

            assertEquals(true, validation.isActive());
            assertEquals(9999, validation.getGameAnswer());
            assertEquals(99, validation.getGameID());

        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testFindGameByIDGoldenPath.");
        }
    }

    @Test
    public void testFindGameByIDNullGameIDInput() {
        try {
            Game validation = serviceLayerTest.findGameByID(null);

        } catch (NullGameIDException ex) {
        }
    }

    @Test
    public void testGetListOfRoundsGoldenPath() {
        LocalDateTime ld1 = LocalDateTime.parse("2020-03-11T12:01:40");
        LocalDateTime ld2 = LocalDateTime.parse("2020-03-31T12:01:40");

        try {
            List<Round> testRoundList = serviceLayerTest.getListOfRounds(9873);

            Round validation1 = testRoundList.get(0);

            assertEquals(9873, validation1.getGameID());
            assertEquals(ld1, validation1.getRoundTime());
            assertEquals(1, validation1.getFullMatch());
            assertEquals(1, validation1.getPartialMatch());
            assertEquals(4, validation1.getGuessNo());

            Round validation2 = testRoundList.get(1);

            assertEquals(9873, validation2.getGameID());
            assertEquals(ld2, validation2.getRoundTime());
            assertEquals(2, validation2.getFullMatch());
            assertEquals(1, validation2.getPartialMatch());
            assertEquals(8, validation2.getGuessNo());

        } catch (NullGameIDException ex) {
            fail("Hit NullGameIDException during testGetListOfRoundsGoldenPath.");
        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testGetListOfRoundsGoldenPath.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testGetListOfRoundsGoldenPath.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testGetListOfRoundsGoldenPath.");
        }
    }

    @Test
    public void testGetListOfRoundsNullGameIDInput() {

        try {
            List<Round> testRoundList = serviceLayerTest.getListOfRounds(null);

        } catch (NullGameIDException ex) {

        } catch (DAOPersistenceException ex) {
            fail("Hit DAOPersistenceException during testGetListOfRoundsGoldenPath.");
        } catch (NullGameAnswerException ex) {
            fail("Hit NullGameAnswerException during testGetListOfRoundsGoldenPath.");
        } catch (FalseForGameActiveException ex) {
            fail("Hit FalseForGameActiveException during testGetListOfRoundsGoldenPath.");
        }
        
    }
}
