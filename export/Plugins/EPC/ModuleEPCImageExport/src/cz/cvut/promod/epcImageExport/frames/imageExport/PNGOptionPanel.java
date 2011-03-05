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

package cz.cvut.promod.epcImageExport.frames.imageExport;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;

import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.epcImageExport.resources.Resources;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 20:09:17, 14.12.2009
 * 
 * Option panel for the png export.
 */
public class PNGOptionPanel extends JPanel {

    private JLabel insetLabel = ModelerSession.getComponentFactoryService().createLabel(
            Resources.getResources().getString(ImageExportModel.PNG_OPTIONS_INSET_RES)

    );

    protected JSpinner insetSpinner = ModelerSession.getComponentFactoryService().createSpinner();

    public PNGOptionPanel(final SpinnerNumberModel insetModel){
        insetSpinner.setModel(insetModel);
        initLayout();
    }

    private void initLayout() {
        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        setLayout(new FormLayout(
                "pref, 3dlu, pref",
                "pref"
        ));
        final CellConstraints cellConstraints = new CellConstraints();
        add(insetLabel, cellConstraints.xy(1,1));
        add(insetSpinner, cellConstraints.xy(3,1));
    }
}
