
package com.sg.vendingspringmvc.service;

/**
 *
 * @author tylerbates
 */
public class OutOfStockException extends Exception {

    public OutOfStockException(String message) {
        super(message);
    }
    
    public OutOfStockException(String message, Throwable cause){
        super(message, cause);
    }
}
