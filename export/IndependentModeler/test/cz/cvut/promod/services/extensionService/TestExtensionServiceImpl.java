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

package cz.cvut.promod.services.extensionService;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import cz.cvut.promod.memory.MemoryIndicatorExtension;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryServiceImpl;
import cz.cvut.promod.IndependentModelerMain;
import cz.cvut.promod.plugin.extension.Extension;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;
import java.util.List;
import java.util.LinkedList;
import java.lang.reflect.Method;

import static junit.framework.Assert.fail;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 0:14:51, 11.2.2010
 */
public class TestExtensionServiceImpl {

    private final static File MEM_INDIC_PROPERTY_FILE =
            new File("./test/cz/cvut/promod/services/extensionService/files/memoryIndicator.properties");

    private ExtensionService extensionService;
    private ExtensionService extensionService_empty;

    private MemoryIndicatorExtension memoryIndicatorExtension;

    /**
     * SetUp.
     */
    @Before
    public void setUp(){
        ModelerSession.setComponentFactoryService(new ComponentFactoryServiceImpl());
        try {
            final ResourceBundle commonResources = new PropertyResourceBundle(
                    new FileReader(new File(IndependentModelerMain.COMMON_RESOURCES_FILE)));
            ModelerSession.setCommonResourceBundle(commonResources);
        } catch (IOException e) {
            fail();
        }

        try {
            memoryIndicatorExtension = new  MemoryIndicatorExtension(MEM_INDIC_PROPERTY_FILE);
        } catch (InstantiationException e) {
            fail();
        }

        final List<Extension> extensionsList = new LinkedList<Extension>();
        extensionsList.add(memoryIndicatorExtension);

        extensionService = new ExtensionServiceImpl(extensionsList);

        extensionService_empty = new ExtensionServiceImpl(new LinkedList<Extension>());
    }

    @After
    public void tearDown(){
        try { // null services
            final Method nullServicesMethod = ModelerSession.class.getDeclaredMethod(ModelerSession.NULL_METHOD_NAME);
            nullServicesMethod.setAccessible(true);
            nullServicesMethod.invoke(null);
            nullServicesMethod.setAccessible(false);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Simple testing of getExtension(String) method
     */
    @Test
    public void testGetExtension(){
        assertNull(extensionService.getExtension(null));

        // not existing extension
        assertNull(extensionService.getExtension(memoryIndicatorExtension.getIdentifier() + "_"));

        assertEquals(extensionService.getExtension(memoryIndicatorExtension.getIdentifier()), memoryIndicatorExtension);

        //empty
        assertNull(extensionService_empty.getExtension(memoryIndicatorExtension.getIdentifier()));
    }

    /**
     * Simple testing of getExtensions() method
     */
    @Test
    public void testGetExtensions(){
        List<Extension> extensions = extensionService.getExtensions();

        assertTrue(extensions.size() == 1);
        assertEquals(extensions.get(0), memoryIndicatorExtension);

        //empty
        extensions = extensionService_empty.getExtensions();
        assertTrue(extensions.isEmpty());
    }



}
