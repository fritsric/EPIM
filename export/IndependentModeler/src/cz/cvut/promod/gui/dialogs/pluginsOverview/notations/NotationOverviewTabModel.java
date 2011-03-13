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

package cz.cvut.promod.gui.dialogs.pluginsOverview.notations;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.pluginLoaderService.utils.NotationSpecificPlugins;
import cz.cvut.promod.plugin.notationSpecificPlugIn.module.Module;

import javax.swing.event.ListDataListener;
import javax.swing.*;
import java.util.List;
import java.util.LinkedList;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 18:00:07, 11.2.2010
 *
 * A Module component of NotationOverviewTab.
 */
public class NotationOverviewTabModel implements ListModel {

    private final List<NotationLabelWrapper> notationsList;


    public NotationOverviewTabModel() {
        notationsList = new LinkedList<NotationLabelWrapper>();

        initList();
    }

    private void initList(){
        for(final String identifier : ModelerSession.getNotationService().getNotationsIdentifiers()){
            final NotationSpecificPlugins specificPlugins = ModelerSession.getNotationService().getNotationSpecificPlugins(identifier);
                final List<ModuleListModel.ModuleLabelWrapper> modulesList = new LinkedList<ModuleListModel.ModuleLabelWrapper>();
                for(final Module module : specificPlugins.getModules()){
                    modulesList.add(new ModuleListModel.ModuleLabelWrapper(module.getIdentifier(), module.getName()));
                }

                notationsList.add(new NotationLabelWrapper(
                    specificPlugins.getNotation().getIdentifier(),  specificPlugins.getNotation().getFullName(), new ModuleListModel(modulesList)
            ));
        }
    }

    public int getSize() {
        return notationsList.size();
    }

    public Object getElementAt(int index) {
        return notationsList.get(index);
    }

    public void addListDataListener(ListDataListener l) {

    }

    public void removeListDataListener(ListDataListener l) {

    }

    /**
     * Represents a list in list of notations.
     */
    protected static class NotationLabelWrapper {

        private final String identifier;
        private final String displayName;

        private final ModuleListModel moduleListModel;

        private NotationLabelWrapper(final String identifier,
                                     final String displayName,
                                     final ModuleListModel moduleListModel) {
            
            this.identifier = identifier;
            this.displayName = displayName;
            this.moduleListModel = moduleListModel;
        }

        public String getIdentifier() {
            return identifier;
        }

        @Override
        public String toString() {
            return displayName;
        }

        public ModuleListModel getModuleListModel() {
            return moduleListModel;
        }
    }


}
