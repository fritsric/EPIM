/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hnatuluk.factories.WordFactory;

import cz.cvut.hnatuluk.Exception.BadExtensionException;
import cz.cvut.hnatuluk.Exception.FileNotEditableException;
import cz.cvut.hnatuluk.checkers.FileChecker;
import java.io.File;
import java.io.IOException;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Body;
import org.docx4j.wml.ObjectFactory;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class WordOutputFactory {

    public static enum EXTENSIONS {

        DOCX, NONE;

        public static EXTENSIONS getExtension(String s) {
            if (s.toUpperCase().endsWith(DOCX.toString())) {
                return DOCX;
            } else {
                return NONE;
            }
        }
    }
    private FileChecker docChecker;

    public void setDocChecker(FileChecker excellChecker) {
        this.docChecker = excellChecker;
    }

    public File createOutputFile(File f) throws IOException, BadExtensionException, FileNotEditableException {
        if (!docChecker.accept(f)) {
            throw new BadExtensionException(String.format("The filename %s is not acceptable for Excell Files", f.getName()));
        }
        if (!f.exists() || f.delete()) {
            File ret = new File(f.getAbsolutePath());
            ret.createNewFile();
            return ret;
        } else {
            throw new FileNotEditableException(String.format("The file %s can not be edit", f.getName()));
        }
    }

    public File createOutputFile(String f) throws BadExtensionException, FileNotEditableException, IOException {
        return createOutputFile(new File(f));
    }

    public WordDocument createOutputWorkbook(File f) throws InvalidFormatException, BadExtensionException {
        String filename = f.getName();
        switch (EXTENSIONS.getExtension(filename)) {
            case DOCX: {
                WordprocessingMLPackage pcg = WordprocessingMLPackage.createPackage();
                ObjectFactory of = Context.getWmlObjectFactory();
                Body b = pcg.getMainDocumentPart().getJaxbElement().getBody();
                WordDocument ret = new WordDocument(of, b, pcg);
                return ret;
            }
            case NONE: {
            }
            default: {
                throw new BadExtensionException(String.format("The filename %s is not acceptable for Excell Files", f.getName()));
            }
        }
    }

    public WordDocument createOutWorkbook(String f) throws BadExtensionException, InvalidFormatException {
        return createOutputWorkbook(new File(f));
    }

    public void write(File f, WordDocument w) throws Docx4JException {
        if (docChecker.accept(f)) {
            w.getWmlPackage().save(f);
        } else {
        }
    }
}
