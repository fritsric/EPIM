/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hnatuluk.test.xml;

import cz.cvut.hnatuluk.entities.docxTemplate.DocxTemplate;
import cz.cvut.hnatuluk.entities.style.StyleTemplate;
import cz.cvut.hnatuluk.factories.jaxb.UnmarshalFactory;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author prcek
 */
public class XMLBindingChecker {

    @Test
    public void testTemplate() throws Exception{
        UnmarshalFactory<DocxTemplate> uf = 
                UnmarshalFactory.createInstance(DocxTemplate.class);
        File f = new File("testFiles/XMLBinding/template.xml");
        if (!f.exists()) {
            Assert.fail();
        }
        DocxTemplate t = uf.unmarshal(f);        
    }
    
    @Test
    public void testStyles() throws Exception{
        UnmarshalFactory<StyleTemplate> uf = 
                UnmarshalFactory.createInstance(StyleTemplate.class);
        File f = new File("testFiles/XMLBinding/styles.xml");
        if (!f.exists()) {
            Assert.fail();
        }
        StyleTemplate t = uf.unmarshal(f);
    }
}
