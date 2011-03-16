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

package cz.cvut.promod.ph.treeLayout;

import cz.cvut.promod.gui.settings.SettingPageData;
import cz.cvut.promod.ph.treeLayout.settings.TreeLayoutSettings;
import cz.cvut.promod.plugin.notationSpecificPlugIn.DockableFrameData;
import cz.cvut.promod.plugin.notationSpecificPlugIn.module.Module;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.menuService.MenuService;
import cz.cvut.promod.services.menuService.utils.MenuItemPosition;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 11:22:26, 28.1.2010
 *
 * Implementation of a ProcessHierarchyTreeLayout module for ProcessHierarchyNotation plugin.
 */
public class ProcessHierarchyTreeLayout implements Module {

    private static final Logger LOG = Logger.getLogger(ProcessHierarchyTreeLayout.class);

    private final ProcessHierarchyTreeLayoutModel model;

    /**
     * ProcessHierarchyTreeLayout plugin constructor taking the ProcessHierarchyNotation notation's identifier.
     *
     * @param notationIdentifier ProcessHierarchyNotation notation's identifier
     */
    public ProcessHierarchyTreeLayout(final String notationIdentifier){
        model = new ProcessHierarchyTreeLayoutModel(notationIdentifier);

        TreeLayoutSettings.setInstance(new TreeLayoutSettings());
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
        return null;
    }

    /** {@inheritDoc} */
    public String getIdentifier() {
        return model.getIdentifier();
    }

    /** {@inheritDoc} */
    public void init() {
        registerActions();

        initMainMenus();

        initToolbar();
    }

    private void initMainMenus() {
        ModelerSession.getMenuService().insertMainMenuItem(
                model.getLayoutAction(),
                new MenuItemPosition(
                        ProcessHierarchyTreeLayoutModel.PROCESS_HIERARCHY_LABEL +
                        MenuService.PLACEMENT_DELIMITER +
                        ProcessHierarchyTreeLayoutModel.LAYOUT_LABEL,
                MenuItemPosition.PlacementStyle.LAST)
        );
    }

    private void initToolbar() {
        ModelerSession.getToolBarService().addAction(
                model.getRelatedNotationIdentifier(),
                model.getLayoutAction()
        );
    }

    private void registerActions() {
        ModelerSession.getActionService().registerAction(
                getRelatedNotationIdentifier(),
                getIdentifier(),
                ProcessHierarchyTreeLayoutModel.LAYOUT_ACTION_IDENTIFIER,
                model.getLayoutAction()
        );
    }

    /** {@inheritDoc} */
    public void finish() {
        LOG.info("Process Hierarchy Notation is terminating.");
    }

    /** {@inheritDoc} */
    public List<SettingPageData> getSettingPages() {
        return TreeLayoutSettings.getInstance().getSettingPages();
    }
}
