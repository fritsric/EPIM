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

import com.jgoodies.binding.beans.Model;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Oct 6, 2010
 * Time: 4:06:44 PM
 *
 * The model component in the ExporterSettings MVC bundle. This class stores all the
 * input the user placed in the view components. Most methods are simple getters and
 * setters, but some form of validity check and type casts are made when needed.
 *
 * This class also contains public enumerations of ExporterSettings.
 */
public class ExporterSettingsModel extends Model {

    private String m_path;

    public String getPath() {
        return m_path;
    }

    public void setPath(String path) {
        m_path = path;
    }

    private String m_name;

    public void setName(String name) {
        m_name = name;
    }

    public String getName(){
        return m_name;
    }

    public enum ExportTheme {
        eClassicBlack,
        eConservativeBlue
    }

    private ExportTheme m_theme;

    public void setTheme(ExportTheme theme) {
        m_theme = theme;
    }

    public class ExportThemeException extends Exception {
        public ExportThemeException(String message) {
            super(message);
        }
    }

    /**
     * @return the string representation of the theme that is to be used
     * @throws ExportThemeException - when the theme is requested and cannot be applied
     */
    public String getTheme() throws ExportThemeException {
        if (m_format != ExportFormat.eXHTMLFolder)
            throw new ExportThemeException("Themes only applicable for XHTML!");
        switch (m_theme) {
            case eClassicBlack:
                return "classicBlackTheme";
            default:
                return "conservativeBlueTheme";
        }
    }

    private boolean m_notes;

    public void setNotes(boolean notes) {
        m_notes = notes;
    }

    public boolean getNotes() {
        return m_notes;
    }

    public enum ExportFormat {
        eXHTMLFolder,
        eDokuWiki,
        eLaTeXFolder
    }

    private ExportFormat m_format;

    public void setFormat(ExportFormat format) {
        m_format = format;
    }

    public ExportFormat getFormat() {
        return m_format;
    }

    private int m_openWithChoice;

    public void setOpenWithChoice(int choice) {
        m_openWithChoice = choice;
    }

    public int getOpenWithChoice() {
        return m_openWithChoice;
    }

    private String m_openWithPath;

    public void setOpenWithPath(String path) {
        m_openWithPath = path;
    }

    public String getOpenWithPath() {
        return m_openWithPath;
    }


    private String m_title;

    public void setTitle(String title) {
        m_title = title;
    }

    public String getTitle() {
        return m_title;
    }

}
