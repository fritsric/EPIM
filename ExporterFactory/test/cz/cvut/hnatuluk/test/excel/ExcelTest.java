/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hnatuluk.test.excel;

import cz.cvut.hnatuluk.Exception.BadExtensionException;
import cz.cvut.hnatuluk.factories.ExcelFactory.ExcelOutputFactory;
import cz.cvut.hnatuluk.factories.ExportFactory;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author prcek
 */
public class ExcelTest {

    public static final File testDirectory = new File("testFiles\\ExcellFactory");

    static {
        if (!testDirectory.exists()) {
            testDirectory.mkdirs();
        }
    }

    @Test
    public void createExcelFile() throws Exception {
        File xlsFile = new File(testDirectory.getAbsolutePath() + "test.xls");
        File xlsxFile = new File(testDirectory.getAbsolutePath() + "test.xlsx");
        File badFile = new File(testDirectory.getAbsolutePath() + "test.txt");

        ExcelOutputFactory factory = ExportFactory.getExcelFactory();

        if (xlsFile.exists()) {
            xlsFile.delete();
        }

        if (xlsxFile.exists()) {
            xlsxFile.delete();
        }

        if (badFile.exists()) {
            badFile.delete();
        }

        Assert.assertFalse(xlsFile.exists());
        Assert.assertFalse(xlsxFile.exists());
        Assert.assertFalse(badFile.exists());

        factory.createOutputFile(xlsFile);
        Assert.assertTrue(xlsFile.exists());

        factory.createOutputFile(xlsxFile);
        Assert.assertTrue(xlsxFile.exists());

        try {
            factory.createOutputFile(badFile);
            Assert.fail();
        } catch (BadExtensionException ex) {
//            správná cesta
        }

        Assert.assertFalse(badFile.exists());

        if (xlsFile.exists()) {
            xlsFile.delete();
        }

        if (xlsxFile.exists()) {
            xlsxFile.delete();
        }

        Assert.assertFalse(xlsFile.exists());
        Assert.assertFalse(xlsxFile.exists());

        factory.createOutputFile(xlsFile.getAbsolutePath());
        Assert.assertTrue(xlsFile.exists());

        factory.createOutputFile(xlsxFile.getAbsolutePath());
        Assert.assertTrue(xlsxFile.exists());

        try {
            factory.createOutputFile(badFile.getAbsolutePath());
            Assert.fail();
        } catch (BadExtensionException ex) {
//            správná cesta
        }

        
        if (xlsFile.exists()) {
            xlsFile.delete();
        }

        if (xlsxFile.exists()) {
            xlsxFile.delete();
        }

    }
}
