
package cz.cvut.hnatuluk.checkers;

import java.io.File;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public interface FileChecker {
    public boolean accept(File f);
    public boolean accept(String f);
}
