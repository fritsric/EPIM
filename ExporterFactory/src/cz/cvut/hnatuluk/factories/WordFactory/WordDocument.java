package cz.cvut.hnatuluk.factories.WordFactory;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Body;
import org.docx4j.wml.ObjectFactory;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class WordDocument {

    private final ObjectFactory createFactory;
    private final Body documentBody;
    private final WordprocessingMLPackage wmlPackage;

    public WordDocument(ObjectFactory createFactory, Body documentBody,WordprocessingMLPackage wmlPackage) {
        this.createFactory = createFactory;
        this.documentBody = documentBody;
        this.wmlPackage = wmlPackage;
    }

    public ObjectFactory getCreateFactory() {
        return createFactory;
    }

    public Body getDocumentBody() {
        return documentBody;
    }

    public WordprocessingMLPackage getWmlPackage() {
        return wmlPackage;
    }
    
}
