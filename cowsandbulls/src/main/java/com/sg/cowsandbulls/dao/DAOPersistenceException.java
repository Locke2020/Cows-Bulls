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
public class DAOPersistenceException extends Exception {

    public DAOPersistenceException(String msg) {
        super(msg);
    }

    public DAOPersistenceException(String msg, Throwable innerCause) {
        super(msg, innerCause);
    }
    
}
