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

package cz.cvut.promod.epcImageExport;

import cz.cvut.promod.epcImageExport.resources.Resources;
import cz.cvut.promod.gui.settings.SettingPageData;
import cz.cvut.promod.plugin.notationSpecificPlugIn.DockableFrameData;
import cz.cvut.promod.plugin.notationSpecificPlugIn.module.Module;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;
import cz.cvut.promod.services.menuService.MenuService;
import cz.cvut.promod.services.menuService.utils.InsertMenuItemResult;
import cz.cvut.promod.services.menuService.utils.MenuItemPosition;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:38:04, 13.12.2009
 *
 * Implementation of the EPCImageExportModule plugin.
 */
public class EPCImageExportModule implements Module {

    private static final Logger LOG = Logger.getLogger(EPCImageExportModule.class);

    private static final String EPC_LABEL = Resources.getResources().getString("epc.imageexport.menu.epc");
    private static final String EXPORT_LABEL = Resources.getResources().getString("epc.imageexport.menu.export");

    private final EPCImageExportModuleModel model;

    /**
     * Constructs a new EPCImageExportModule.
     *
     * @param propertiesFile the required plugin property file.
     * @throws InstantiationException when an error occurs during instantiation.
     */
    public EPCImageExportModule(final File propertiesFile) throws InstantiationException {
        model = new EPCImageExportModuleModel(propertiesFile);
    }

    /** {@inheritDoc} */
    public String getName() {
        return model.getName();
    }

    /** {@inheritDoc} */
    public String getDescription() {
        return model.getDescription();
    }

    /** {@inheritDoc} */
    public String getRelatedNotationIdentifier() {
        return model.getRelatedNotationIdentifier();
    }

    /** {@inheritDoc} */
    public Set<DockableFrameData> getDockableFrames() {
        return model.getDockableFrames();
    }

    /** {@inheritDoc} */
    public String getIdentifier() {
        return model.getIdentifier();
    }

    /** {@inheritDoc} */
    public void init() {
        model.initFrames(); // init frames when services are available

        // init actions

        final ProModAction pngExportAction = model.getActions().get(EPCImageExportModuleModel.PNG_EXPORT_ACTION);

        ModelerSession.getActionService().registerAction(
                getRelatedNotationIdentifier(),
                getIdentifier(),
                EPCImageExportModuleModel.PNG_EXPORT_ACTION,
                pngExportAction
        );

        ModelerSession.getMenuService().insertPopupMenuItem(
                getRelatedNotationIdentifier(),
                pngExportAction,
                new MenuItemPosition("", MenuItemPosition.PlacementStyle.LAST)
        );        

        final InsertMenuItemResult result = ModelerSession.getMenuService().insertMainMenuItem(
                pngExportAction,
                new MenuItemPosition(EPC_LABEL + MenuService.PLACEMENT_DELIMITER + EXPORT_LABEL)
        );

        if(!InsertMenuItemResult.SUCCESS.equals(result)){
            LOG.error("Menu item " + EPCImageExportModuleModel.PNG_EXPORT_ACTION + " couldn't be inserted to the menu." +
            " Error: " + result);
        }
        
    }

    /** {@inheritDoc} */
    public List<SettingPageData> getSettingPages() {
        return model.getSettingPages();
    }
    

    /** {@inheritDoc} */
    public void finish() {  }
}
