/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ADTs.Queue;

//import ADTs.ArrayList.*;

/**
 *
 * @author Administrator
 */
public class ListFullException extends RuntimeException{
    public ListFullException(String s){ 
        super(s);
    }//end constructor
} //end ListException