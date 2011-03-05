/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hnatuluk.test.word;

import cz.cvut.hnatuluk.factories.ExportFactory;
import cz.cvut.hnatuluk.factories.WordFactory.WordDocument;
import cz.cvut.hnatuluk.factories.WordFactory.WordOutputFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.docx4j.wml.Body;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.PStyle;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class WordTest {

    @Test
    public void testCrateFactory() throws Exception {
        WordOutputFactory wof = ExportFactory.getWordFactory();
    }

    @Test
    public void testExtensions() throws Exception {
        WordOutputFactory wof = ExportFactory.getWordFactory();
        File f = wof.createOutputFile("testfile.docx");
        WordDocument wd = wof.createOutputWorkbook(f);
        WordDocument wd1 = wof.createOutWorkbook(f.getName());
    }

    @Test
    public void testHelloWord() throws Exception {
        WordOutputFactory wof = ExportFactory.getWordFactory();
        File f = wof.createOutputFile("testFiles/WordFactory/testfile.docx");
        WordDocument wd = wof.createOutputWorkbook(f);
        Body b = wd.getDocumentBody();
        ObjectFactory cf = wd.getCreateFactory();
        P para = cf.createP();
        //Nastaví styl
        PPr ppr = cf.createPPr();
        PStyle pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        //Nastaví run a přidá text
        R run = cf.createR();
        Text t = cf.createText();
        t.setValue("Hello world!!!");
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);
        wof.write(f, wd);
        File testFile = new File("testFiles/WordFactory/testfile.docx");
        if (testFile.exists()) {
            InputStream tis = new FileInputStream(testFile);
            InputStream fis = new FileInputStream(f);
            byte[] tbytes, fbytes;
            boolean end = false;
            do {
                Assert.assertFalse(end);
                tbytes = new byte[1000];
                fbytes = new byte[tbytes.length];
                int tread = tis.read(tbytes);
                int fread = fis.read(fbytes);
                Assert.assertEquals(tread, fread);
                Assert.assertArrayEquals(fbytes, tbytes);
                end = tread!=tbytes.length||fread!=fbytes.length;
            }while(!end);
        } else {
            Assert.fail("The file does not exists");
        }
    }
    /*
    =====================================================================================================
    Object                  XML element                 docx4j class                Factory method
    =====================================================================================================
    Document body           w:body                      org.docx4j.wml.Body         factory.createBody();
    Paragraph               w:p                         org.docx4j.wml.P            factory.createP();
    Paragraph props         w:pPr                       org.docx4j.wml.PPr          factory.createPPr();
    Run                     w:r                         org.docx4j.wml.R            factory.createR();
    Run props               w:rPr                       org.docx4j.wml.RPr          factory.createRPr();
    Text                    w:t                         org.docx4j.wml.Text         factory.createText();
    Table                   w:tbl                       org.docx4j.wml.Tbl          factory.createTbl();
    Table row               w:tr                        org.docx4j.wml.Tr           factory.createTr();
    Table cell              w:tc                        org.docx4j.wml.Tc           factory.createTc();
    Drawing                 w:drawing                   org.docx4j.wml.Drawing      factory.createDrawing();
    Page break              w:br                        org.docx4j.wml.Br           factory.createBr();
    Footnote or endnote ref ?                           org.docx4j.wml.CTFtnEdnRef  factory.createCTFtnEdnRef()
     */
}
