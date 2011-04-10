package cz.cvut.hnatuluk.factories.WordFactory;

import cz.cvut.hnatuluk.visitor.WordVisitor;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Body;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.ObjectFactory;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class WordDocument {

    private final ObjectFactory createFactory;
    private final Body documentBody;
    private final Hdr header;
    private final Ftr footer;
    private final WordprocessingMLPackage wmlPackage;

    public WordDocument(
            ObjectFactory createFactory,
            Body documentBody,
            WordprocessingMLPackage wmlPackage,
            Hdr header,
            Ftr footer
            ) {
        this.createFactory = createFactory;
        this.documentBody = documentBody;
        this.wmlPackage = wmlPackage;
        this.header = header;
        this.footer = footer;
    }

    public ObjectFactory getCreateFactory() {
        return createFactory;
    }

    public Body getDocumentBody() {
        return documentBody;
    }
    
    public Hdr getHeader() {
        return header;
    }
    
    public Ftr getFooter() {
        return footer;
    }

    public WordprocessingMLPackage getWmlPackage() {
        return wmlPackage;
    }
    
    public void visit(WordVisitor v, IClassModelModel model){
        v.visitClassModel(this, model);
    }
    
}
