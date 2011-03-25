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

package cz.cvut.promod.hierarchyNotation.frames.toolChooser;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.swing.JideButton;

import javax.swing.*;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;
import cz.cvut.promod.hierarchyNotation.resources.Resources;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 12:48:31, 29.11.2009
 *
 * The View component for the ToolChooser dockable frame.
 */
public class ToolChooserView extends JPanel {

    protected final JideButton controlButton = ModelerSession.getComponentFactoryService().createJideButton(
            Resources.getResources().getString("hierarchy.frame.tools.move"),
            Resources.getIcon(Resources.ICONS + Resources.MOVE), 0
    );
    protected final JideButton addProcessButton = ModelerSession.getComponentFactoryService().createJideButton(
            Resources.getResources().getString("hierarchy.frame.tools.add.process"),
            Resources.getIcon(Resources.ICONS + Resources.PROCESS), 0
    );
    protected final JideButton addEdgeButton = ModelerSession.getComponentFactoryService().createJideButton(
            Resources.getResources().getString("hierarchy.frame.tools.add.edge"),
            Resources.getIcon(Resources.ICONS + Resources.LINK), 0
    );
    protected final JideButton removeButton = ModelerSession.getComponentFactoryService().createJideButton(
            Resources.getResources().getString("hierarchy.frame.tools.delete"),
            Resources.getIcon(Resources.ICONS + Resources.DELETE), 0
    );


    public ToolChooserView(){
        initLayout();
    }

    private void initLayout() {
        initButtons();

        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        setLayout(new FormLayout(
                "pref:grow",
                "pref, 5dlu, pref, 5dlu, pref, 5dlu, pref"
        ));

        final CellConstraints cellConstraints = new CellConstraints();

        int row = 1;
        add(controlButton, cellConstraints.xy(1,row));

        row += 2;
        add(addProcessButton, cellConstraints.xy(1,row));

        row += 2;
        add(addEdgeButton, cellConstraints.xy(1,row));

        row += 2;
        add(removeButton, cellConstraints.xy(1,row));
    }

    private void initButtons() {
        controlButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        controlButton.setHorizontalTextPosition(AbstractButton.CENTER);

        addProcessButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        addProcessButton.setHorizontalTextPosition(AbstractButton.CENTER);

        addEdgeButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        addEdgeButton.setHorizontalTextPosition(AbstractButton.CENTER);

        removeButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        removeButton.setHorizontalTextPosition(AbstractButton.CENTER);        
    }
}
