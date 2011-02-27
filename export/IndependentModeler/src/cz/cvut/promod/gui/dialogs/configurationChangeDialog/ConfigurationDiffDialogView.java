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

package cz.cvut.promod.gui.dialogs.configurationChangeDialog;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import javax.swing.*;
import java.awt.*;

import com.jgoodies.forms.factories.Borders;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:55:41, 12.12.2009
 *
 * Represents the view component for ConfigurationDiffDialog.
 */
public class ConfigurationDiffDialogView extends JDialog {

    private static final Dimension DIMENSION = new Dimension(500, 450);

    private final JLabel titleLabel = ModelerSession.getComponentFactoryService().createLabel(
        ModelerSession.getCommonResourceBundle().getString("modeler.configuration.message")
    );
    private final JLabel messageLabel = ModelerSession.getComponentFactoryService().createLabel(
        ModelerSession.getCommonResourceBundle().getString("modeler.configuration.message.detail")
    );

    protected final JTextArea diffTextArea = ModelerSession.getComponentFactoryService().createTextArea();

    protected final JButton hideButton = ModelerSession.getComponentFactoryService().createButton(
            ModelerSession.getCommonResourceBundle().getString("modeler.configuration.hideButton"), null
    );

    public ConfigurationDiffDialogView(){
        initLayout();

        diffTextArea.setLineWrap(true);
        diffTextArea.setWrapStyleWord(true);

        diffTextArea.setEditable(false);

        setSize(DIMENSION);
    }

    private void initLayout() {
        final JPanel panel = ModelerSession.getComponentFactoryService().createPanel();

        panel.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        panel.setLayout(new BorderLayout());

        final JPanel northPanel = ModelerSession.getComponentFactoryService().createPanel();
        northPanel.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));
        northPanel.setLayout(new BorderLayout());

        northPanel.add(titleLabel, BorderLayout.NORTH);
        northPanel.add(messageLabel, BorderLayout.SOUTH);

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(ModelerSession.getComponentFactoryService().createScrollPane(diffTextArea), BorderLayout.CENTER);
        panel.add(hideButton, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

}