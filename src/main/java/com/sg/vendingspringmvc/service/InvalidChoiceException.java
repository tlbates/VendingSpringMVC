
package com.sg.vendingspringmvc.service;

/**
 *
 * @author tylerbates
 */
public class InvalidChoiceException extends Exception {

    public InvalidChoiceException(String message) {
        super(message);
    }
    
    public InvalidChoiceException(String message, Throwable cause){
        super(message, cause);
    }
}
