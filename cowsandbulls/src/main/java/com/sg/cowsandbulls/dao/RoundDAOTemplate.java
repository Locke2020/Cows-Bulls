/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

import com.sg.cowsandbulls.dao.RoundDAO;
import com.sg.cowsandbulls.dto.Game;
import com.sg.cowsandbulls.dto.Round;
import com.sg.cowsandbulls.service.FalseForGameActiveException;
import com.sg.cowsandbulls.service.NullFullMatchInputException;
import com.sg.cowsandbulls.service.NullGameAnswerException;
import com.sg.cowsandbulls.service.NullGameIDException;
import com.sg.cowsandbulls.service.NullRoundObjectException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author davidsteffes
 */
@Repository
public class RoundDAOTemplate implements RoundDAO {

    @Autowired
    private JdbcTemplate roundTemplate;

    @Autowired
    public RoundDAOTemplate(JdbcTemplate roundTemplate) {
        this.roundTemplate = roundTemplate;
    }

    @Override
    public Round addRound(Round roundToAdd) throws NullGameIDException, 
            NullFullMatchInputException, NullRoundObjectException, 
            DAOPersistenceException, NullGameAnswerException, FalseForGameActiveException {
        
        if ( roundToAdd == null ){
            throw new NullRoundObjectException ( "Null Round object." );
        }
        
        if ( roundToAdd.getGameID() == null ){
            throw new NullGameIDException ( "Null game ID." );
        }
        
        if( roundToAdd.getFullMatch() == null ){
            throw new NullFullMatchInputException ( "Null for Full Match input." );
        }
        
        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = roundTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Rounds(RoundTime,GameID,FullMatch,PartialMatch,GuessNo)"
                    + "VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, roundToAdd.getRoundTime().toString());
            ps.setInt(2, roundToAdd.getGameID());
            ps.setInt(3, roundToAdd.getFullMatch());
            ps.setInt(4, roundToAdd.getPartialMatch());
            ps.setInt(5, roundToAdd.getGuessNo());

            return ps;

        },
                kh);

        int generatedID = kh.getKey().intValue();

        roundToAdd.setRoundID(generatedID);

        return roundToAdd;
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet row, int i) throws SQLException {
            Round toReturn = new Round();

            toReturn.setRoundID(row.getInt("RoundID"));
            toReturn.setGameID(row.getInt("GameID"));
            toReturn.setFullMatch(row.getInt("FullMatch"));
            toReturn.setPartialMatch(row.getInt("PartialMatch"));
            toReturn.setGuessNo(row.getInt("GuessNo"));
            toReturn.setRoundTime(row.getTimestamp("RoundTime").toLocalDateTime());

            return toReturn;
        }

    }

    public List<Round> getListOfRounds(Integer gameId) throws DAOPersistenceException, 
            NullGameAnswerException, FalseForGameActiveException {

        List<Round> toReturn = roundTemplate.query("SELECT * FROM Rounds WHERE "
        + "GameID = ? ORDER BY RoundTime DESC", new RoundMapper(), gameId);

        return toReturn;

    }

}
