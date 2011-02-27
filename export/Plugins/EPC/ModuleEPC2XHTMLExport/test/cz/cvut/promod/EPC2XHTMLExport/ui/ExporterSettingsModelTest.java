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

package cz.cvut.promod.EPC2XHTMLExport.ui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ondrej Machulda
 * Date: 7.11.2010
 * Time: 17:05:28
 */
public class ExporterSettingsModelTest {

    private ExporterSettingsModel model;

    @Before
    public void startUp() {
        this.model = new ExporterSettingsModel(); 
    }

    @Test
    public void testNameEquals() {
        this.model.setName("name");
        assertEquals("name", this.model.getName());
    }

    @Test
    public void testTitleEquals() {
        this.model.setTitle("title");
        assertEquals("title", this.model.getTitle());
    }

    @Test
    public void testNotesEquals() {
        this.model.setNotes(true);
        assertEquals(true, this.model.getNotes());
    }

    @Test
    public void testPathEquals() {
        this.model.setPath("/p/a/t/h/");
        assertEquals("/p/a/t/h/", this.model.getPath());
    }

    @Test
    public void testOpenWithPathEquals() {
        this.model.setOpenWithPath("/p/a/t/h/");
        assertEquals("/p/a/t/h/", this.model.getOpenWithPath());
    }

    @Test
    public void testOpenWithChoice() {
        this.model.setOpenWithChoice(0);
        assertEquals(0, this.model.getOpenWithChoice());

        this.model.setOpenWithChoice(2);
        assertEquals(2, this.model.getOpenWithChoice());
    }

    @Test
    public void testFormatEquals() {

        this.model.setFormat(ExporterSettingsModel.ExportFormat.eXHTMLFolder);
        assertEquals(ExporterSettingsModel.ExportFormat.eXHTMLFolder, this.model.getFormat());

        this.model.setFormat(ExporterSettingsModel.ExportFormat.eLaTeXFolder);
        assertEquals(ExporterSettingsModel.ExportFormat.eLaTeXFolder, this.model.getFormat());

        this.model.setFormat(ExporterSettingsModel.ExportFormat.eDokuWiki);
        assertEquals(ExporterSettingsModel.ExportFormat.eDokuWiki, this.model.getFormat());
    }

    @Test
    public void testThemeEquals() throws ExporterSettingsModel.ExportThemeException {
        // Only XHTML export can use themes
        this.model.setFormat(ExporterSettingsModel.ExportFormat.eXHTMLFolder);
        
        this.model.setTheme(ExporterSettingsModel.ExportTheme.eClassicBlack);
        assertEquals("classicBlackTheme", this.model.getTheme());

        this.model.setTheme(ExporterSettingsModel.ExportTheme.eConservativeBlue);
        assertEquals("conservativeBlueTheme", this.model.getTheme());
    }

    @Test(expected=ExporterSettingsModel.ExportThemeException.class)
    public void testThemeExportThemeException1() throws ExporterSettingsModel.ExportThemeException { // can't get themes when LaTeX format is selected
        // Only XHTML export can use themes - nor can LaTeX or DokuWiki
        this.model.setFormat(ExporterSettingsModel.ExportFormat.eLaTeXFolder);

        this.model.setTheme(ExporterSettingsModel.ExportTheme.eConservativeBlue);
        this.model.getTheme(); // raise exception
    }

    @Test(expected=ExporterSettingsModel.ExportThemeException.class)
    public void testThemeExportThemeException2() throws ExporterSettingsModel.ExportThemeException { // can't get themes when LaTeX format is selected
        // Only XHTML export can use themes - nor can LaTeX or DokuWiki
        this.model.setFormat(ExporterSettingsModel.ExportFormat.eDokuWiki);

        this.model.setTheme(ExporterSettingsModel.ExportTheme.eConservativeBlue);
        this.model.getTheme(); // raise exception
    }



}
