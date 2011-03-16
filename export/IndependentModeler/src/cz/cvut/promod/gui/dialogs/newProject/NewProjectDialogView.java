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

package cz.cvut.promod.gui.dialogs.newProject;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.factories.Borders;

import javax.swing.*;
import java.awt.*;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 1:40:04, 20.10.2009
 *
 * The view component for NewProjectDialog.
 */
public class NewProjectDialogView extends JDialog {

    private final JLabel projectNameLabel = ModelerSession.getComponentFactoryService().createLabel("project name");
    protected final JTextField projectNameTextField = ModelerSession.getComponentFactoryService().createTextField();

    private final JLabel projectLocationLabel = ModelerSession.getComponentFactoryService().createLabel("project location");
    protected final JTextField projectLocationTextField = ModelerSession.getComponentFactoryService().createTextField();
    protected final JButton projectLocationButton =  ModelerSession.getComponentFactoryService().createButton("...", null);

    protected final JButton createProjectButton =  ModelerSession.getComponentFactoryService().createButton("create project", null);
    protected final JButton cancelButton =  ModelerSession.getComponentFactoryService().createButton("cancel", null);

    protected final JLabel errorLabel = ModelerSession.getComponentFactoryService().createLabel("");

    protected final JPanel centerPanel = ModelerSession.getComponentFactoryService().createPanel();

    public static final String TITLE =
            ModelerSession.getCommonResourceBundle().getString("modeler.action.new.project.dialog");

    public NewProjectDialogView(){
        setTitle(TITLE);
        
        setModal(true);

        errorLabel.setForeground(Color.RED);

        initLayout();

        pack();
    }

    private void initLayout() {
        centerPanel.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));
        centerPanel.setLayout(new FormLayout(
                "pref, 3dlu, max(180dlu;pref):grow, 3dlu, pref",
                "pref, 3dlu, pref, 3dlu, pref, 20dlu")
        );

        int row = 1;
        final CellConstraints cellConstraints = new CellConstraints();

        centerPanel.add(projectNameLabel, cellConstraints.xy(1, row));
        centerPanel.add(projectNameTextField, cellConstraints.xyw(3, row, 3));

        row += 2;
        centerPanel.add(projectLocationLabel, cellConstraints.xy(1, row));
        centerPanel.add(projectLocationTextField, cellConstraints.xy(3, row));
        centerPanel.add(projectLocationButton, cellConstraints.xy(5, row));


        final JPanel southPanel = ModelerSession.getComponentFactoryService().createPanel();
        southPanel.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));
        southPanel.setLayout(new FormLayout(
                "pref, 50dlu:grow, pref",
                "pref, 3dlu, pref"
        ));

        southPanel.add(errorLabel, cellConstraints.xyw(1, 1, 3));

        southPanel.add(cancelButton, cellConstraints.xy(1, 3));
        southPanel.add(createProjectButton, cellConstraints.xy(3, 3));


        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

}