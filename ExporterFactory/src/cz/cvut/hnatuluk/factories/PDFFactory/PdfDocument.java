/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.hnatuluk.factories.PDFFactory;

import cz.cvut.hnatuluk.factories.WordFactory.WordDocument;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Body;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.ObjectFactory;

/**
 *
 * @author prcek
 */
public class PdfDocument extends WordDocument{

    public PdfDocument(ObjectFactory createFactory, Body documentBody, WordprocessingMLPackage wmlPackage,Hdr h, Ftr f) {
        super(createFactory, documentBody, wmlPackage, h, f);
    }
    
}
