/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hnatuluk.test.word;

import cz.cvut.hnatuluk.factories.ExportFactory;
import cz.cvut.hnatuluk.factories.WordFactory.WordDocument;
import cz.cvut.hnatuluk.factories.WordFactory.WordOutputFactory;
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
        File f = wof.createOutputFile("testFiles/WordFactory/helloWordl.docx");
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
    }

    @Test
    public void testComplexWord() throws Exception {
        WordOutputFactory wof = ExportFactory.getWordFactory();
        File f = wof.createOutputFile("testFiles/WordFactory/complexFile.docx");
        WordDocument wd = wof.createOutputWorkbook(f);
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
        Color c =cf.createColor();
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
