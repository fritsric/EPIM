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

package cz.cvut.promod.epc.frames.toolChooser;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import java.awt.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 12:48:31, 29.11.2009
 *
 * The View component of the ToolChooser dockable frame.
 */
public class ToolChooserView extends JPanel {

    protected final ToolChooserOutlookTabs outlookTabbedPane;

    protected final JToggleButton controlButton = ModelerSession.getComponentFactoryService().createToggleButton(
            ToolChooserModel.CONTROL_TOOL_RES, null
    );

    protected final JToggleButton deleteButton = ModelerSession.getComponentFactoryService().createToggleButton(
            ToolChooserModel.DELETE_TOOL_RES, null
    );

    public ToolChooserView(){
        outlookTabbedPane = new ToolChooserOutlookTabs();

        initLayout();       
    }

    private void initLayout() {
        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        final FormLayout layout = new FormLayout(
                /* columns */ "pref:grow",
                /* rows */ "pref, 3dlu, pref, 10dlu"
        );

        final PanelBuilder builder = new PanelBuilder(layout);
        final CellConstraints cellConstraints = new CellConstraints();

        builder.add(controlButton, cellConstraints.xy(1,1));
        builder.add(deleteButton, cellConstraints.xy(1,3));
        
        setLayout(new BorderLayout());

        add(builder.getPanel(), BorderLayout.NORTH);
        add(outlookTabbedPane, BorderLayout.CENTER);
    }
}