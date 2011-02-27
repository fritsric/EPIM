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

package cz.cvut.promod.gui.dialogs.pluginsOverview.extensions;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.plugin.extension.Extension;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;
import java.util.LinkedList;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:20:11, 11.2.2010
 *
 * A Model component of ExtensionOverviewTab.
 */
public class ExtensionOverviewTabModel implements ListModel {

    private final List<ExtensionLabelWrapper> extensionsList;


    public ExtensionOverviewTabModel() {
        extensionsList = new LinkedList<ExtensionLabelWrapper>();

        initList();
    }

    private void initList() {
        for(final Extension extension : ModelerSession.getExtensionService().getExtensions()){
            extensionsList.add(new ExtensionLabelWrapper(extension.getIdentifier(), extension.getName()));
        }
    }

    public int getSize() {
        return extensionsList.size();
    }

    public Object getElementAt(int index) {
        return extensionsList.get(index);
    }

    public void addListDataListener(ListDataListener l) {

    }

    public void removeListDataListener(ListDataListener l) {

    }

    /**
     * Represents a list in list of extensions.
     */
    protected static class ExtensionLabelWrapper {

        private final String identifier;
        private final String displayName;

        private ExtensionLabelWrapper(final String identifier, final String displayName) {
            this.identifier = identifier;
            this.displayName = displayName;
        }

        public String getIdentifier() {
            return identifier;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
