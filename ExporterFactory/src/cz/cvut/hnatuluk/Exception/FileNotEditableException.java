/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.hnatuluk.Exception;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class FileNotEditableException extends Exception{

    public FileNotEditableException(Throwable thrwbl) {
        super(thrwbl);
    }

    public FileNotEditableException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public FileNotEditableException(String string) {
        super(string);
    }

    public FileNotEditableException() {
    }
    
}
