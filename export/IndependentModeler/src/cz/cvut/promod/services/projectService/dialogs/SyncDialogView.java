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

package cz.cvut.promod.services.projectService.dialogs;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import javax.swing.*;
import java.awt.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.factories.Borders;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:06:00, 13.11.2009
 */

/**
 * SyncDialog view.
 */
public class SyncDialogView extends JDialog {

    private final JLabel errorsLabel = ModelerSession.getComponentFactoryService().createLabel(
            ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.errorlist"));

    private final JLabel searchLocationLabel = ModelerSession.getComponentFactoryService().createLabel(
            ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.searching"));

    protected final JLabel currentSearchLocationLabel = ModelerSession.getComponentFactoryService().createLabel("");

    protected final JTextArea errorsTextArea = ModelerSession.getComponentFactoryService().createTextArea();

    protected final JButton cancelButton = ModelerSession.getComponentFactoryService().createButton(
            ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.cancel"), null);

    protected final JButton okButton = ModelerSession.getComponentFactoryService().createButton(
            ModelerSession.getCommonResourceBundle().getString("modeler.ok"), null);

    public SyncDialogView(final String title){
        super(ModelerSession.getFrame(), title);

        setLocationRelativeTo(ModelerSession.getFrame());

        setSize(600, 350);

        errorsTextArea.setEditable(false);

        initLayout();
    }

    private void initLayout() {
        final JPanel panel = ModelerSession.getComponentFactoryService().createPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        final CellConstraints cellConstraints = new CellConstraints();

        final JPanel northPanel = ModelerSession.getComponentFactoryService().createPanel();
        northPanel.setLayout(new FormLayout(
                "pref:grow, 10dlu, pref",
                "pref, 3dlu, pref, 7dlu"
        ));

        northPanel.add(searchLocationLabel, cellConstraints.xy(1,1));
        northPanel.add(cancelButton, cellConstraints.xy(3,1));

        northPanel.add(currentSearchLocationLabel, cellConstraints.xyw(1, 3, 3));

        final JPanel centerPanel = ModelerSession.getComponentFactoryService().createPanel();
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(errorsLabel, BorderLayout.NORTH);
        centerPanel.add(ModelerSession.getComponentFactoryService().createScrollPane(errorsTextArea), BorderLayout.CENTER);

        final JPanel southPanel = ModelerSession.getComponentFactoryService().createPanel();
        southPanel.setLayout(new FormLayout(
                "right:pref:grow",
                "7dlu, pref"
        ));
        southPanel.add(okButton, cellConstraints.xy(1,2));

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        add(panel);
    }
}
