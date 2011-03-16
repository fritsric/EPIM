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

package cz.cvut.promod.epc.settings;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.BufferedValueModel;
import cz.cvut.promod.epc.modelFactory.diagramModel.EPCDiagramModel;
import cz.cvut.promod.epc.resources.Resources;
import cz.cvut.promod.epc.settings.settingPages.GeneralPage;
import cz.cvut.promod.gui.settings.SettingPageData;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectContainer;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectDiagram;
import org.apache.log4j.Logger;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 11:19:23, 26.1.2010
 *
 * EPCSettings is settings holder for the EPCNotation plugin.
 */
public class EPCSettings {

    private static final Logger LOG = Logger.getLogger(EPCSettings.class);

    private static EPCSettings instance;

    private final List<SettingPageData> settingPages;

    private final EPCSettingsModel model;
    private final PresentationModel<EPCSettingsModel> presentation;
    private final BufferedValueModel undoLimitModel;

    private static final String SETTINGS_LABEL = Resources.getResources().getString("epc.settings");


    public EPCSettings(final Properties properties){
        model = new EPCSettingsModel(properties);
        presentation =  new PresentationModel<EPCSettingsModel>(model);

        undoLimitModel = presentation.getBufferedModel(EPCSettingsModel.UNDO_LIMIT_PROPERTY);

        settingPages = new LinkedList<SettingPageData>();

        initPages();

        initEventHandling();
    }

    private void initEventHandling() {
        undoLimitModel.addValueChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                final int limit = (Integer) evt.getNewValue();

                for(final TreePath projectTreePath : ModelerSession.getProjectService().getProjectPaths()){
                    updateUndoLimit(projectTreePath, limit);
                }
            }
        });
    }

    private void initPages() {
        GeneralPage generalPage = new GeneralPage(presentation, undoLimitModel);
        settingPages.add(new SettingPageData(SETTINGS_LABEL, null, generalPage));
    }

    public int getUndoLimit(){
        return model.getUndoLimit();
    }

    /**
     * Recursively goes thought all diagram of EPC notation and sets their undo limit to new value.
     *
     * @param treePath is the tree path where to start EPC diagram search
     * @param limit is the new limit for number of possible undo limit operations
     */
    public void updateUndoLimit(final TreePath treePath, int limit) {
        final Object lastComponent = treePath.getLastPathComponent();
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) lastComponent;
        final Object userObject = node.getUserObject();

        if(userObject instanceof ProjectDiagram){
            final ProjectDiagram projectDiagram = (ProjectDiagram) userObject;

            if(projectDiagram.getDiagramModel() instanceof EPCDiagramModel){
                final EPCDiagramModel model = (EPCDiagramModel) projectDiagram.getDiagramModel();
                model.getUndoManager().setLimit(limit);
            }


        } else if(userObject instanceof ProjectContainer){
            for(int i = 0; i < node.getChildCount(); i++){
                final Object nextNode = node.getChildAt(i);

                updateUndoLimit(treePath.pathByAddingChild(nextNode), limit);
            }

        } else {
            LOG.error("Unknown project tree node object.");
        }
    }

    public static EPCSettings getInstance() {
        return instance;
    }

    public static void setInstance(final EPCSettings instance) {
        EPCSettings.instance = instance;
    }

    public List<SettingPageData> getSettingPages() {
        return settingPages;
    }
}
