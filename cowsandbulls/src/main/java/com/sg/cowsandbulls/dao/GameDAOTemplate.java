/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this gameTemplate file, choose Tools | Templates
 * and open the gameTemplate in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.dao.GameDAOException;
import com.sg.cowsandbulls.dao.GameDAO;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author davidsteffes
 */
@Repository
public class GameDAOTemplate implements GameDAO {

    @Autowired
    private JdbcTemplate gameTemplate;

    @Autowired
    public GameDAOTemplate(JdbcTemplate gameTemplate) {
        this.gameTemplate = gameTemplate;

    }

    @Override
    public Game createGame(Game toReturn) throws DAOPersistenceException, NullGameAnswerException,
            FalseForGameActiveException {
        
        if ( toReturn.isActive() == false ){
            throw new FalseForGameActiveException ( "Hit FalseForGameActiveException during createGame.");
        }
        
        if ( toReturn.getGameAnswer() == null){
            throw new NullGameAnswerException( " Hit NullGameAnswerException during createGame.");
        }

        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = gameTemplate.update(
                connection -> {

                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Games (GameActive, GameAnswer) "
                            + "VALUES( ?,?)",
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setBoolean(1, toReturn.isActive());
                    ps.setInt(2, toReturn.getGameAnswer());

                    return ps;

                },
                kh);

        int generatedId = kh.getKey().intValue();

        toReturn.setGameID(generatedId);

        return toReturn;
    }

//    @Override
//    public Round checkGuess(Integer guess, Integer gameID) {
//
//        Game currentGame = getGameByID(gameID);
//        int gameAnswer = currentGame.getGameAnswer();
//
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void setGameActiveToFalse(Game gameToEnd) throws NullGameIDException, FalseForGameActiveException, DAOPersistenceException, NullGameAnswerException, GameDAOException {

        if (gameToEnd.isActive() == false){
            throw new FalseForGameActiveException( "This game should be active, but it's listed as inactive." );
        }
        if (gameToEnd.getGameID() == null) {
            throw new NullGameIDException ( "Game ID is null." );
        }
        
        if (gameToEnd.getGameAnswer() == null){
            throw new NullGameAnswerException ( "Game answer is null" );
        }
        
        int rowsAffected = gameTemplate.update(
                 
            "UPDATE Games SET GameActive = false "
            + "WHERE GameID = ?",gameToEnd.getGameID());
            
        if(rowsAffected == 0){
            throw new GameDAOException("Could not found GameID: " + gameToEnd.getGameID());
        } else if (rowsAffected > 1){
            throw new GameDAOException("There are multiple games with the same GameID of " + gameToEnd.getGameID());
    }

    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet row, int i) throws SQLException {
            Game toReturn = new Game();

            toReturn.setGameID(row.getInt("GameID"));
            toReturn.setGameAnswer(row.getInt("GameAnswer"));
            toReturn.setActive(row.getBoolean("GameActive"));

            return toReturn;

        }
    }

    public List<Game> getListOfGames() {

        List<Game> toReturn = gameTemplate.query("SELECT * FROM Games", new GameMapper());

        return toReturn;

    }

    public Game getGameByID(Integer gameID) {
        
        Game toReturn = gameTemplate.queryForObject(
                "SELECT * FROM Games WHERE GameID = ?", new GameMapper(), gameID);

        return toReturn;

    }

}
