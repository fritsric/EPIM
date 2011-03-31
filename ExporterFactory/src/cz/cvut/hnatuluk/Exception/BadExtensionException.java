/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.hnatuluk.Exception;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class BadExtensionException extends Exception{

    public BadExtensionException(Throwable thrwbl) {
        super(thrwbl);
    }

    public BadExtensionException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public BadExtensionException(String string) {
        super(string);
    }

    public BadExtensionException() {
    }
    
}
