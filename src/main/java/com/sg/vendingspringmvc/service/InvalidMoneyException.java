
package com.sg.vendingspringmvc.service;

/**
 *
 * @author tylerbates
 */
public class InvalidMoneyException extends Exception {
    
    public InvalidMoneyException(String message) {
        super(message);
    }
    
    public InvalidMoneyException(String message, Throwable cause){
        super(message, cause);
    }
}
