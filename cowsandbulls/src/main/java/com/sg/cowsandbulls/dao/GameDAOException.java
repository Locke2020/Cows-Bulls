/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.dao;

/**
 *
 * @author davidsteffes
 */
public class GameDAOException extends Exception {

    public GameDAOException(String msg) {
        super(msg);
    }

    public GameDAOException(String msg, Throwable innerCause) {
        super(msg, innerCause);
    }
    
}
