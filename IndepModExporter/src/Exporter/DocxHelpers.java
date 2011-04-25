/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import java.math.BigInteger;
import org.docx4j.wml.Color;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.NumPr;
import org.docx4j.wml.PPrBase.NumPr.Ilvl;
import org.docx4j.wml.PPrBase.NumPr.NumId;
import org.docx4j.wml.PPrBase.PStyle;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Text;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;

/**
 *
 * @author Jan Bažant
 */
public class DocxHelpers
{
    private ObjectFactory frOF = null;

    public DocxHelpers(ObjectFactory irOF)
    {
        frOF = irOF;
    }

    /**
     * Vytvoří element <w:r> se zadaným řetězcem jako textovým obsahem.
     * Je to kus textu, více runů v jednom odstavci se naskládá za sebe.
     * Lze tak udělat různé formátování na jednom řádku.
     * @param isText
     * @return 
     */
    public R CreateRunWithText(String isText)
    {
        R lrRun = frOF.createR();

        // samotný text
        Text lrText = frOF.createText();
        lrText.setValue(isText);
        lrRun.getRunContent().add(lrText);

        return lrRun;
    }

    /**
     * Vytvoří element <w:r> se zadaným řetězcem jako textovým obsahem.
     * Navíc aplikuje stylování zadané druhým parametrem.
     * @param isText
     * @param irStyle
     * @return 
     */
    public R CreateRunWithText(String isText, Style irStyle)
    {
        R lrRun = CreateRunWithText(isText);
        RPr lrRunProps = frOF.createRPr();

        if(irStyle.fbItalics) {
            lrRunProps.setI(frOF.createBooleanDefaultTrue());
            lrRunProps.setICs(frOF.createBooleanDefaultTrue());
        }
        if (irStyle.fbStrike) {
            lrRunProps.setStrike(frOF.createBooleanDefaultTrue());
        }
        if(irStyle.fbUnderline) {
            U lrU = frOF.createU();
            lrU.setVal(UnderlineEnumeration.SINGLE);
            lrRunProps.setU(lrU);
        }
        if(irStyle.fbBold) {
            lrRunProps.setB(frOF.createBooleanDefaultTrue());
            lrRunProps.setBCs(frOF.createBooleanDefaultTrue());
        }
        if(!irStyle.fsColor.equals("")) {
            Color lrColor = frOF.createColor();
            lrColor.setVal(irStyle.fsColor);
            lrRunProps.setColor(lrColor);
        }
        if(irStyle.fnFontSize != -1) {
            HpsMeasure lrHPS = frOF.createHpsMeasure();
            lrHPS.setVal(new BigInteger(String.valueOf(irStyle.fnFontSize)));
            lrRunProps.setSz(lrHPS);
            lrRunProps.setSzCs(lrHPS);
        }
        if(!irStyle.fsFontFamily.equals("")) {
            RFonts lrRF = frOF.createRFonts();
            lrRF.setAscii(irStyle.fsFontFamily);
            lrRF.setHAnsi(irStyle.fsFontFamily);
            lrRunProps.setRFonts(lrRF);
        }

        lrRun.setRPr(lrRunProps);
        return lrRun;
    }

    /**
     * Vytvoří odstavec se základním stylem a bez obsahu.
     * Určeno pro další zpracování.
     * @return 
     */
    public P CreateDefaultParagraph()
    {
        // Vytvořit prvky...
        P lrPara = frOF.createP();
        PPr lrParaProps = frOF.createPPr();

        // defaultní styl
        PStyle lrParaStyle = frOF.createPPrBasePStyle();
        lrParaStyle.setVal("my style hard style");

        // odstavci nastavím styl...
        lrParaProps.setPStyle(lrParaStyle);
        lrPara.setPPr(lrParaProps);

        return lrPara; 
    }

    /**
     * Vyvtoří odstavec bez obsahu a aplikuje formátování (konkrétně zarovnání).
     * @param irStyle
     * @return 
     */
    public P CreateDefaultParagraph(Style irStyle)
    {
        P lrPara = CreateDefaultParagraph();

        if(irStyle.frAlign != Align.DEFAULT)
        {
            Jc lrJC = frOF.createJc();

            switch(irStyle.frAlign)
            {
                case LEFT:      lrJC.setVal(JcEnumeration.LEFT);    break;
                case CENTER:    lrJC.setVal(JcEnumeration.CENTER);  break;
                case RIGHT:     lrJC.setVal(JcEnumeration.RIGHT);   break;
                case JUSTIFY:   lrJC.setVal(JcEnumeration.BOTH);    break;
            }

            lrPara.getPPr().setJc(lrJC);
        }

        return lrPara;
    }

    /**
     * Vytvoří odstavec se zadaným textem.
     * @param isText Text, který má být v odstavci.
     * @return 
     */
    public P CreateParagraphWithText(String isText)
    {
        // Vytvořit prvky...
        P lrPara = CreateDefaultParagraph();

        // ... a přidám text
        lrPara.getParagraphContent().add(CreateRunWithText(isText));
        return lrPara;
    }

    /**
     * Vytvoří odstavec se zadaným textem a stylem.
     * @param isText
     * @param irStyle
     * @return 
     */
    public P CreateParagraphWithText(String isText, Style irStyle)
    {
        P lrParagraph = CreateDefaultParagraph(irStyle);
        lrParagraph.getParagraphContent().add(CreateRunWithText(isText, irStyle));
        return lrParagraph;
    }

    /**
     * Vytvoří položku seznamu se zadaným textem a úrovní.
     * @param isText    Text.
     * @param inLevel   Úroveň seznamu. 0 je nejmenší, 8 největší.
     */
    public P CreateListItem(String isText, int inLevel)
    {
        P lrParagraph = CreateParagraphWithText(isText);
        ApplyNumberingToParagraph(lrParagraph, inLevel);
        return lrParagraph;
    }

    /**
     * Vytvoří položku seznamu se zadaným textem, formátem a úrovní.
     * @param isText
     * @param inLevel
     * @param irStyle
     * @return 
     */
    public P CreateListItem(String isText, int inLevel, Style irStyle)
    {
        P lrParagraph = CreateParagraphWithText(isText, irStyle);
        ApplyNumberingToParagraph(lrParagraph, inLevel);
        return lrParagraph;
    }

    /**
     * Zadanému odstavci nastaví takové vlastnosti, aby se zobrazil jako položka seznamu na zadané úrovni.
     * @param orPara
     * @param inLevel 
     */
    private void ApplyNumberingToParagraph(P orPara, int inLevel)
    {
        NumPr lrNumPr = frOF.createPPrBaseNumPr();

        Ilvl lrILVL = frOF.createPPrBaseNumPrIlvl();
        NumId lrNumID = frOF.createPPrBaseNumPrNumId();

        lrILVL.setVal(new BigInteger(String.valueOf(inLevel)));
        lrNumID.setVal(BigInteger.ONE);

        lrNumPr.setIlvl(lrILVL);
        lrNumPr.setNumId(lrNumID);

        orPara.getPPr().setNumPr(lrNumPr);
    }
}
