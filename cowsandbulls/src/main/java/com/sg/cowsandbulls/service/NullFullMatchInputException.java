/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.service;

/**
 *
 * @author davidsteffes
 */
public class NullFullMatchInputException extends Exception {

    public NullFullMatchInputException(String msg) {
        super(msg);
    }

    public NullFullMatchInputException(String msg, Throwable innerCause) {
        super(msg, innerCause);
    }
    
}
