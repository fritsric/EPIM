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

import cz.cvut.promod.EPC2XHTMLExport.resources.Resources;

import cz.cvut.promod.gui.settings.SettingPageData;

import cz.cvut.promod.plugin.notationSpecificPlugIn.DockableFrameData;
import cz.cvut.promod.plugin.notationSpecificPlugIn.module.Module;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.NotationWorkspaceData;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;
import cz.cvut.promod.services.menuService.MenuService;
import cz.cvut.promod.services.menuService.utils.InsertMenuItemResult;
import cz.cvut.promod.services.menuService.utils.MenuItemPosition;

import cz.cvut.promod.epc.workspace.EPCWorkspaceData;

import cz.cvut.promod.EPC2XHTMLExport.ui.ExporterSettingsController;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Oct 6, 2010
 * Time: 3:47:49 PM
 *
 * This module enables the user to export EPC diagrams into various document formats.
 * In the current version, these formats are XHTML, LaTeX and DokuWiki. It depends upon
 * two main properties files. The path to the first of them is given on initialization.
 * This file contains module related information and persistent user data. The second is
 * located among other resources of this module. It stores text strings for use on GUI
 * component and facilitates future translations to other languages (this is a standard
 * in the entire IndependentModeler project). The java.util.Resources class is used in
 * both cases. Strings are used for both key and value. String keys, used to load resources
 * from these file, are saved as private final members of classes using them and generally
 * have a specific naming convention (capitals ending with _RES) - this helps in recognition
 * and separation from standard class members and makes code much easier to read and refractor.
 */
public class EPC2XHTMLExportModule implements Module  {

    private static final String EPC_LABEL = Resources.getStrRes("epc.export.xhtml.menu.epc");
    private static final String EXPORT_LABEL = Resources.getStrRes("epc.export.xhtml.menu.export");

    public static final Properties properties = new Properties();
    public static File propertiesFile;
    private final String NOTATION_IDENTIFIER_RES = "epc.notation.identifier";
    private final String IDENTIFIER_RES          = "epc.export.xhtml.identifier";
    private final String NAME_RES                = "epc.export.xhtml.name";
    private final String DESCRIPTION_RES         = "epc.export.xhtml.description";

    private static final Logger LOG = Logger.getLogger(EPC2XHTMLExportModule.class);

    private final String EPC2XHTML_EXPORT_ACTION_RES = "epc.export.xhtml.exportaction";


    private Set<DockableFrameData> dockableFrames;
    private final Map<String, ProModAction> actions;
    private List<SettingPageData> settingPages;

    /**
     * Public constructor called from within the dore of IndependentModeler upon initialization
     *
     * @param propertiesFile          - the file where module resources are stored
     * @throws InstantiationException - when module resources could not be loaded
     */
    public EPC2XHTMLExportModule(final File propertiesFile)  throws InstantiationException {

        try {
            properties.load(new FileReader(propertiesFile));
            EPC2XHTMLExportModule.propertiesFile = propertiesFile;
        } catch (IOException e) {
            LOG.error("Properties for the EPC XHTML Export module couldn't be read.", e);
            throw new InstantiationException("Mandatory properties couldn't be read.");
        }

        if(Resources.getResources() == null){
            throw new InstantiationException("Resource bundle not available");
        }

        actions = new HashMap<String, ProModAction>();
        dockableFrames = new HashSet<DockableFrameData>();
        settingPages = new LinkedList<SettingPageData>();


    }

    public String getRelatedNotationIdentifier() {
        return properties.getProperty(NOTATION_IDENTIFIER_RES);
    }

    public Set<DockableFrameData> getDockableFrames() {
        return dockableFrames;
    }

    public String getIdentifier() {
        return properties.getProperty(IDENTIFIER_RES);
    }

    public String getName() {
        return properties.getProperty(NAME_RES);
    }

    public String getDescription() {
        return properties.getProperty(DESCRIPTION_RES);
    }

    /**
     * Initialization of this module, this is done automatically by the core of IndependentModeler.
     */
    public void init() {
        final NotationWorkspaceData workspaceData = ModelerSession.getNotationService().
                                    getNotation(getRelatedNotationIdentifier()).getNotationWorkspaceData();

        if(!(workspaceData instanceof EPCWorkspaceData)){
            // should never happened
            LOG.error("Related notation doesn't provide appropriate notation workspace.");
            return;
        }

        final EPCWorkspaceData epcWorkspaceData = (EPCWorkspaceData) workspaceData;

        final ExporterSettingsController settings =
                new ExporterSettingsController(epcWorkspaceData, actions,
                                               EPC2XHTML_EXPORT_ACTION_RES);

        dockableFrames.add(settings);

        // init setting pages
        //ImageExportSettings imageExportSettings = new ImageExportSettings(imageExport.getPresentationModel());
        //SettingPageData settingPageData = new SettingPageData(INSET_LABEL, null, imageExportSettings);
        //settingPages.add(settingPageData);

        final ProModAction EPC2XHTMLExportAction = actions.get(EPC2XHTML_EXPORT_ACTION_RES);

        ModelerSession.getActionService().registerAction(
                getRelatedNotationIdentifier(),
                getIdentifier(),
                EPC2XHTML_EXPORT_ACTION_RES,
                EPC2XHTMLExportAction
        );

        ModelerSession.getMenuService().insertPopupMenuItem(
                getRelatedNotationIdentifier(),
                EPC2XHTMLExportAction,
                new MenuItemPosition("", MenuItemPosition.PlacementStyle.LAST)
        );

        final InsertMenuItemResult result = ModelerSession.getMenuService().insertMainMenuItem(
                EPC2XHTMLExportAction,
                new MenuItemPosition(EPC_LABEL + MenuService.PLACEMENT_DELIMITER + EXPORT_LABEL)
        );

        if(!InsertMenuItemResult.SUCCESS.equals(result)){
            LOG.error("Menu item " + Resources.getStrRes(EPC2XHTML_EXPORT_ACTION_RES) + " couldn't be inserted to the menu." +
            " Error: " + result);
        }

        settings.loadConfig();
    }

    public void finish() {
    }

    public List<SettingPageData> getSettingPages() {
        return settingPages;
    }
}
