package cz.cvut.hnatuluk.checkers;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class CustomFileChecker implements FileChecker {

    private String[] extension;

    public CustomFileChecker(String[] extension) {
        this.extension = extension;
        Arrays.sort(this.extension);
    }

    @Override
    public boolean accept(File f) {
        return accept(f.getAbsolutePath());
    }

    @Override
    public boolean accept(String f) {
        final String ext = getExtension(f);
        return ((ext != null) && (Arrays.binarySearch(extension, ext) >= 0));
    }
    
    public String getExtension(String f) {
        final int index = f.lastIndexOf(".");
        if (index > 0) {
            return f.substring(index);
        } else {
            return null;
        }
    }

}
