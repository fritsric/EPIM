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

import cz.cvut.promod.plugin.extension.Extension;
import cz.cvut.promod.services.ModelerSession;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import org.apache.log4j.Logger;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 16:49:59, 11.2.2010
 *
 * Implementation of ExtensionOverviewTab for PluginsOverviewDialog.
 */
public class ExtensionOverviewTab extends ExtensionOverviewTabView {

    private static final Logger LOG = Logger.getLogger(ExtensionOverviewTab.class);

    private final ExtensionOverviewTabModel model;

    public ExtensionOverviewTab(){
        model = new ExtensionOverviewTabModel();

        initList();

        initEventHandling();
    }

    private void initEventHandling() {
        extensionList.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                final String extensionIdentifier =
                        ((ExtensionOverviewTabModel.ExtensionLabelWrapper)model.getElementAt(extensionList.getSelectedIndex())).getIdentifier();

                updateDialogView(extensionIdentifier);
            }
        });
    }

    private void updateDialogView(final String extensionIdentifier) {
        final Extension extension = ModelerSession.getExtensionService().getExtension(extensionIdentifier);

        if(extension != null){
            identifierTextArea.setText(extension.getIdentifier());
            nameTextArea.setText(extension.getName());
            descriptionTextArea.setText(extension.getDescription());
        } else {
            LOG.error("Missing notation.");
        }
    }

    private void initList() {
        extensionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        extensionList.setModel(model);
    }

}