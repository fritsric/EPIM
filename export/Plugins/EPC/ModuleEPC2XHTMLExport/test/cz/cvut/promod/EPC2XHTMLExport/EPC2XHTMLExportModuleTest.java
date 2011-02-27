/****************************************************************************
** This file may be used under the terms of the MIT licence:
**
** Permission is hereby granted, free of charge, to any person obtaining a copy
** of this software and associated documentation files (the "Software"), to deal
** in the Software without restriction, including without limitation the rights
** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
** copies of the Software, and to permit persons to whom the Software is
** furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included in
** all copies or substantial portions of the Software.
**
** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
** THE SOFTWARE.
****************************************************************************/

package cz.cvut.promod.EPC2XHTMLExport;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;


/**
 * Created by IntelliJ IDEA.
 * User: Ondrej Machulda
 * Date: 26.10.2010
 * Time: 15:22:43
 */
public class EPC2XHTMLExportModuleTest {

    private EPC2XHTMLExportModule exportModule;
    
    private static final String CONFIG_DIR = "." + System.getProperty("file.separator") + "config"
                    + System.getProperty("file.separator");
    
    private static final File EPC2XHTML_PROPERTY_FILE =
            new File("../Plugins/EPC/ModuleEPC2XHTMLExport/test/cz/cvut/promod/EPC2XHTMLExport/files/epcXHTMLExportModule.properties");

    public static final Properties properties = new Properties();
    private final String NOTATION_IDENTIFIER_RES = "epc.notation.identifier";
    private final String IDENTIFIER_RES          = "epc.export.xhtml.identifier";
    private final String NAME_RES                = "epc.export.xhtml.name";
    private final String DESCRIPTION_RES         = "epc.export.xhtml.description";

    @Before
    public void startUp() throws InstantiationException, IOException {

        PropertyConfigurator.configure( CONFIG_DIR + "log4j.config");
        this.exportModule = new EPC2XHTMLExportModule(EPC2XHTML_PROPERTY_FILE);        
        properties.load(new FileReader(EPC2XHTML_PROPERTY_FILE));
    }

    @Test
    public void testDescriptionEquals() {
        assertEquals(this.exportModule.getDescription(), properties.getProperty(DESCRIPTION_RES));
    }
    @Test
    public void testIdentifierEquals() {
        assertEquals(this.exportModule.getIdentifier(), properties.getProperty(IDENTIFIER_RES));
    }
    @Test
    public void testNameEquals() {
        assertEquals(this.exportModule.getName(), properties.getProperty(NAME_RES));
    }
    @Test
        public void testNotationIdentifierEquals() {
        assertEquals(this.exportModule.getRelatedNotationIdentifier(), properties.getProperty(NOTATION_IDENTIFIER_RES));
    }

}
