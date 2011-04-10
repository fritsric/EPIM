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
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.Body;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
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

    public WordDocument createOutputWorkbook(File f) throws InvalidFormatException, BadExtensionException {
        String filename = f.getName();
        switch (EXTENSIONS.getExtension(filename)) {
            case DOCX: {
                WordprocessingMLPackage pcg = WordprocessingMLPackage.createPackage();
                ObjectFactory of = Context.getWmlObjectFactory();
                Body b = pcg.getMainDocumentPart().getJaxbElement().getBody();
                Hdr lrHeader = SetupHeader(pcg);
                Ftr lrFooter = SetupFooter(pcg);
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

    public WordDocument createOutWorkbook(String f) throws BadExtensionException, InvalidFormatException {
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
}
