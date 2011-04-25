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
import java.util.List;
import javax.xml.bind.JAXBException;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.Body;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.Numbering;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.SectPr;

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
            File parFold = ret.getParentFile();
            if(parFold!=null&&!parFold.exists())
                parFold.mkdirs();
            ret.createNewFile();
            return ret;
        } else {
            throw new FileNotEditableException(String.format("The file %s can not be edit", f.getName()));
        }
    }

    public File createOutputFile(String f) throws BadExtensionException, FileNotEditableException, IOException {
        return createOutputFile(new File(f));
    }

    public WordDocument createOutputWorkbook(File f) throws InvalidFormatException, BadExtensionException, JAXBException {
        String filename = f.getName();
        switch (EXTENSIONS.getExtension(filename)) {
            case DOCX: {
                WordprocessingMLPackage pcg = WordprocessingMLPackage.createPackage();
                ObjectFactory of = Context.getWmlObjectFactory();
                Body b = pcg.getMainDocumentPart().getJaxbElement().getBody();
                Hdr lrHeader = SetupHeader(pcg);
                Ftr lrFooter = SetupFooter(pcg);
                SetupNumbering(pcg);
                WordDocument ret = new WordDocument(of, b, pcg, lrHeader, lrFooter);
                return ret;
            }
            case NONE: {
            }
            default: {
                throw new BadExtensionException(String.format("The filename %s is not acceptable for Excell Files", f.getName()));
            }
        }
    }

    public WordDocument createOutWorkbook(String f) throws BadExtensionException, InvalidFormatException, JAXBException {
        return createOutputWorkbook(new File(f));
    }

    public void write(File f, WordDocument w) throws Docx4JException {
        if (docChecker.accept(f)) {
            w.getWmlPackage().save(f);
        } else {
        }
    }
    
    /**
     * Nastaví záhlaví dokumentu. Opsáno z příkladu:
     * http://dev.plutext.org/trac/docx4j/browser/trunk/docx4j/src/main/java/org/docx4j/samples/HeaderFooterList.java
     * @param irPackage
     * @throws InvalidFormatException 
     */
    private Hdr SetupHeader(WordprocessingMLPackage irPackage) throws InvalidFormatException
    {
        ObjectFactory lrFactory = Context.getWmlObjectFactory();

        /* create header part */
        HeaderPart lrHeaderPart = new HeaderPart();
        lrHeaderPart.setPackage(irPackage);

        Hdr lrHeader = lrFactory.createHdr();

        lrHeaderPart.setJaxbElement(lrHeader);
        Relationship lrRelationship = irPackage.getMainDocumentPart().addTargetPart(lrHeaderPart);
        /* end create header part */

        /* create header reference */
        List<SectionWrapper> lrSections = irPackage.getDocumentModel().getSections(); 
        SectPr lrSectionPr = lrSections.get(lrSections.size() - 1).getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (lrSectionPr==null )
        {
            lrSectionPr = lrFactory.createSectPr();
            irPackage.getMainDocumentPart().addObject(lrSectionPr);
            lrSections.get(lrSections.size() - 1).setSectPr(lrSectionPr);
        }

        HeaderReference lrHeaderReference = lrFactory.createHeaderReference();
        lrHeaderReference.setId(lrRelationship.getId());
        lrHeaderReference.setType(HdrFtrRef.DEFAULT);
        lrSectionPr.getEGHdrFtrReferences().add(lrHeaderReference); // add header or footer references
        /* end create header reference */
        
        return lrHeader;
    }
    
    /**
     * Nastaví záhlaví dokumentu. Úprava předchozí funkce.
     * @param irPackage
     * @throws InvalidFormatException 
     */
    private Ftr SetupFooter(WordprocessingMLPackage irPackage) throws InvalidFormatException
    {
        ObjectFactory lrFactory = Context.getWmlObjectFactory();

        /* create header part */
        FooterPart lrFooterPart = new FooterPart();
        lrFooterPart.setPackage(irPackage);

        Ftr lrFooter = lrFactory.createFtr();

        lrFooterPart.setJaxbElement(lrFooter);
        Relationship lrRelationship = irPackage.getMainDocumentPart().addTargetPart(lrFooterPart);
        /* end create header part */

        /* create header reference */
        List<SectionWrapper> lrSections = irPackage.getDocumentModel().getSections(); 
        SectPr lrSectionPr = lrSections.get(lrSections.size() - 1).getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (lrSectionPr==null )
        {
            lrSectionPr = lrFactory.createSectPr();
            irPackage.getMainDocumentPart().addObject(lrSectionPr);
            lrSections.get(lrSections.size() - 1).setSectPr(lrSectionPr);
        }

        FooterReference lrFooterReference = lrFactory.createFooterReference();
        lrFooterReference.setId(lrRelationship.getId());
        lrFooterReference.setType(HdrFtrRef.DEFAULT);
        lrSectionPr.getEGHdrFtrReferences().add(lrFooterReference); // add header or footer references
        /* end create header reference */

        return lrFooter;
    }
    
    private void SetupNumbering(WordprocessingMLPackage irPackage) throws InvalidFormatException, JAXBException
    {
        NumberingDefinitionsPart lrNDP = new NumberingDefinitionsPart();
        lrNDP.setJaxbElement( (Numbering) XmlUtils.unmarshalString(csInitialNumbering) );
	irPackage.getMainDocumentPart().addTargetPart(lrNDP);
    }

    private static final String csInitialNumbering =
            "<w:numbering xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
            + "<w:abstractNum w:abstractNumId=\"0\">"
            + "<w:lvl w:ilvl=\"0\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"bullet\"/>"
                + "<w:lvlText w:val=\"\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"720\" w:hanging=\"360\"/>"
                + "</w:pPr>"
                + "<w:rPr>"
                    + "<w:rFonts w:ascii=\"Symbol\" w:hAnsi=\"Symbol\" w:hint=\"default\"/>"
                + "</w:rPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"1\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"bullet\"/>"
                + "<w:lvlText w:val=\"o\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"1440\" w:hanging=\"360\"/>"
                + "</w:pPr>"
                + "<w:rPr>"
                    + "<w:rFonts w:ascii=\"Courier New\" w:hAnsi=\"Courier New\" w:hint=\"default\"/>"
                + "</w:rPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"2\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"lowerRoman\"/>"
                + "<w:lvlText w:val=\"%3)\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"1080\" w:hanging=\"360\"/>"
                + "</w:pPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"3\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"decimal\"/>"
                + "<w:lvlText w:val=\"(%4)\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"1440\" w:hanging=\"360\"/>"
                + "</w:pPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"4\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"lowerLetter\"/>"
                + "<w:lvlText w:val=\"(%5)\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"1800\" w:hanging=\"360\"/>"
                + "</w:pPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"5\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"lowerRoman\"/>"
                + "<w:lvlText w:val=\"(%6)\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"2160\" w:hanging=\"360\"/>"
                + "</w:pPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"6\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"decimal\"/>"
                + "<w:lvlText w:val=\"%7.\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"2520\" w:hanging=\"360\"/>"
                + "</w:pPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"7\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"lowerLetter\"/>"
                + "<w:lvlText w:val=\"%8.\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"2880\" w:hanging=\"360\"/>"
                + "</w:pPr>"
            + "</w:lvl>"
            + "<w:lvl w:ilvl=\"8\">"
                + "<w:start w:val=\"1\"/>"
                + "<w:numFmt w:val=\"lowerRoman\"/>"
                + "<w:lvlText w:val=\"%9.\"/>"
                + "<w:lvlJc w:val=\"left\"/>"
                + "<w:pPr>"
                    + "<w:ind w:left=\"3240\" w:hanging=\"360\"/>"
                + "</w:pPr>"
            + "</w:lvl>"
        + "</w:abstractNum>"
        + "<w:num w:numId=\"1\">"
            + "<w:abstractNumId w:val=\"0\"/>"
         + "</w:num>"
        + "</w:numbering>";
}
