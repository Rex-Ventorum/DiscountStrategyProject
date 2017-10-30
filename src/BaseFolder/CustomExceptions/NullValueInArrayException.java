/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseFolder.CustomExceptions;

/**
 *
 * @author Brandon
 */
public class NullValueInArrayException extends IllegalArgumentException{
   
    private static final String MESSAGE = "Null Value Found In Array Argument";
    
    public NullValueInArrayException(){
        super(MESSAGE);
    }

    public NullValueInArrayException(String s){
        super(MESSAGE);
    }

    public NullValueInArrayException(String message, Throwable cause){
        super(MESSAGE,cause);
    }

    public NullValueInArrayException(Throwable cause){
        super(MESSAGE, cause);
    }
}
