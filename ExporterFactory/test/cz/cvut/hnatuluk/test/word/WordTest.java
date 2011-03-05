/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.hnatuluk.test.word;

import cz.cvut.hnatuluk.factories.ExportFactory;
import cz.cvut.hnatuluk.factories.WordFactory.WordDocument;
import cz.cvut.hnatuluk.factories.WordFactory.WordOutputFactory;
import java.io.File;
import org.junit.Test;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class WordTest {

    
    @Test
    public void testCrateFactory() throws Exception{
        WordOutputFactory wof = ExportFactory.getWordFactory();
    }
    
    @Test
    public void testExtensions() throws Exception{
        File f = new File("testfile.docx");
        WordOutputFactory wof = ExportFactory.getWordFactory();
        WordDocument wd = wof.createOutputWorkbook(f);
        WordDocument wd1 = wof.createOutWorkbook(f.getName());
    }
}
