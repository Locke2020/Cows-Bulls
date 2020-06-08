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
public class NumberIsNotUniqueException extends Exception {

    public NumberIsNotUniqueException(String msg) {
        super(msg);
    }

    public NumberIsNotUniqueException(String msg, Throwable innerCause) {
        super(msg, innerCause);
    }
}
