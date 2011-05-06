/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hnatuluk.test.pdf;

import cz.cvut.hnatuluk.factories.ExportFactory;
import cz.cvut.hnatuluk.factories.PDFFactory.PdfDocument;
import cz.cvut.hnatuluk.factories.PDFFactory.PdfOutputFactory;
import java.io.File;
import java.math.BigInteger;
import org.docx4j.wml.Body;
import org.docx4j.wml.Color;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.PStyle;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Text;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.junit.Test;

/**
 *
 * @author prcek
 */
public class PdfTest {
   public static final File testDirectory = new File("testFiles/PdfFactory");

    static {
        if (!testDirectory.exists()) {
            testDirectory.mkdirs();
        }
    }

    @Test
    public void testCrateFactory() throws Exception {
        PdfOutputFactory wof = ExportFactory.getPdfFactory();
    }

    @Test
    public void testExtensions() throws Exception {
        PdfOutputFactory wof = ExportFactory.getPdfFactory();
        File f = wof.createOutputFile(testDirectory+"/testfile.pdf");
        PdfDocument wd = wof.createOutputWorkbook(f);
        PdfDocument wd1 = wof.createOutWorkbook(f.getName());
    }

    @Test
    public void testHelloWord() throws Exception {
        PdfOutputFactory wof = ExportFactory.getPdfFactory();
        File f = wof.createOutputFile(testDirectory+"/helloWorld.pdf");
        PdfDocument wd = wof.createOutputWorkbook(f);
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
    }

    @Test
    public void testComplexWord() throws Exception {
        PdfOutputFactory wof = ExportFactory.getPdfFactory();
        File f = wof.createOutputFile(testDirectory+"/complexFile.pdf");
        PdfDocument wd = wof.createOutputWorkbook(f);
        Body b = wd.getDocumentBody();
        ObjectFactory cf = wd.getCreateFactory();

        // Vlevo
        P para = cf.createP();
        //Nastaví styl
        PPr ppr = cf.createPPr();
        PStyle pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        //Nastaví run a přidá text
        R run = cf.createR();
        Text t = cf.createText();
        t.setValue("Left");
        RPr rpr = cf.createRPr();
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);


        //Na střed
        para = cf.createP();
        //Nastaví styl
        ppr = cf.createPPr();
        pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        Jc jc = cf.createJc();
        jc.setVal(JcEnumeration.CENTER);
        //Nastaví run a přidá text
        run = cf.createR();
        t = cf.createText();
        t.setValue("Center");
        rpr = cf.createRPr();
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        ppr.setJc(jc);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);

        //Na Doprava
        para = cf.createP();
        //Nastaví styl
        ppr = cf.createPPr();
        pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        jc = cf.createJc();
        jc.setVal(JcEnumeration.RIGHT);
        //Nastaví run a přidá text
        run = cf.createR();
        t = cf.createText();
        t.setValue("Right");
        rpr = cf.createRPr();
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        ppr.setJc(jc);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);

        //Bold
        para = cf.createP();
        //Nastaví styl
        ppr = cf.createPPr();
        pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        //Nastaví run a přidá text
        run = cf.createR();
        t = cf.createText();
        t.setValue("Bold");
        rpr = cf.createRPr();
        rpr.setB(cf.createBooleanDefaultTrue());
        rpr.setBCs(cf.createBooleanDefaultTrue());
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);

        //Italic
        para = cf.createP();
        //Nastaví styl
        ppr = cf.createPPr();
        pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        //Nastaví run a přidá text
        run = cf.createR();
        t = cf.createText();
        t.setValue("Italic");
        rpr = cf.createRPr();
        rpr.setI(cf.createBooleanDefaultTrue());
        rpr.setICs(cf.createBooleanDefaultTrue());
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);

        //Underline
        para = cf.createP();
        //Nastaví styl
        ppr = cf.createPPr();
        pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        //Nastaví run a přidá text
        run = cf.createR();
        t = cf.createText();
        t.setValue("Underline");
        rpr = cf.createRPr();
        U u = cf.createU();
        u.setVal(UnderlineEnumeration.SINGLE);
        rpr.setU(u);
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);


        //Red
        para = cf.createP();
        //Nastaví styl
        ppr = cf.createPPr();
        pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        //Nastaví run a přidá text
        run = cf.createR();
        t = cf.createText();
        t.setValue("Red");
        rpr = cf.createRPr();
        Color c = cf.createColor();
        c.setVal("FF0000");
        rpr.setColor(c);
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);

        //Red
        para = cf.createP();
        //Nastaví styl
        ppr = cf.createPPr();
        pStyle = cf.createPPrBasePStyle();
        pStyle.setVal("style0");
        //Nastaví run a přidá text
        run = cf.createR();
        t = cf.createText();
        t.setValue("Font");
        rpr = cf.createRPr();
        HpsMeasure hps = cf.createHpsMeasure();
        hps.setVal(new BigInteger("80"));
        RFonts rf = cf.createRFonts();
        rf.setAscii("Arial Black");
        rf.setHAnsi("Arial Black");
        rpr.setRFonts(rf);
        rpr.setSz(hps);
        rpr.setSzCs(hps);
        run.getRunContent().add(rpr);
        run.getRunContent().add(t);
        //Přidat styl do odstavce
        ppr.setPStyle(pStyle);
        //A pak to vše poskládat zpět
        para.getParagraphContent().add(ppr);
        para.getParagraphContent().add(run);
        b.getEGBlockLevelElts().add(para);

        wof.write(f, wd);
    }
}
