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

package cz.cvut.promod.plugin.notationSpecificPlugIn.notation.utils;

import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.workspace.UpdatableWorkspaceComponent;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

import org.apache.log4j.Logger;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.factories.Borders;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 14:38:30, 7.11.2009
 */

/**
 * If the notation doesn't provide it's workplace, this class will be used as a basic component for the basic workspace..
 */
public class DefaultUpdatableWorkspaceComponent extends JPanel implements UpdatableWorkspaceComponent{

    private final Logger LOG = Logger.getLogger(DefaultUpdatableWorkspaceComponent.class);

    private final String notationIdentifier;

    private final JLabel infoLabel = ModelerSession.getComponentFactoryService().createLabel(
            ModelerSession.getCommonResourceBundle().getString("modeler.default.workspace.title")
    );

    private final JLabel notationIdentifierTitleLabel = ModelerSession.getComponentFactoryService().createLabel(
            ModelerSession.getCommonResourceBundle().getString("modeler.default.workspace.notation.identifier")
    );
    private final JLabel notationIdentifierLabel = ModelerSession.getComponentFactoryService().createLabel("");

    private final JLabel diagramNameTitleLabel = ModelerSession.getComponentFactoryService().createLabel(
            ModelerSession.getCommonResourceBundle().getString("modeler.default.workspace.diagram.name")
    );
    private final JLabel diagramNameLabel = ModelerSession.getComponentFactoryService().createLabel("");

    private final JLabel diagramIdentifierTitleLabel = ModelerSession.getComponentFactoryService().createLabel(
            ModelerSession.getCommonResourceBundle().getString("modeler.default.workspace.diagram.identifier")
    );
    private final JLabel diagramIdentifierLabel = ModelerSession.getComponentFactoryService().createLabel("");


    private JPanel panel = ModelerSession.getComponentFactoryService().createPanel();

    public DefaultUpdatableWorkspaceComponent(final String notationIdentifier){
        this.notationIdentifier = notationIdentifier;

        initLayout();
    }

    private void initLayout(){
        panel.setLayout(new FormLayout(
                "pref, 3dlu, max(150;pref)",
                "pref, 10dlu, pref, 3dlu, pref, 3dlu, pref"
        ));
        final CellConstraints cellConstraints = new CellConstraints();

        int row = 1;
        panel.add(infoLabel, cellConstraints.xyw(1, row, 3));

        row += 2;
        panel.add(notationIdentifierTitleLabel, cellConstraints.xy(1, row));
        panel.add(notationIdentifierLabel, cellConstraints.xy(3, row));

        row += 2;
        panel.add(diagramNameTitleLabel, cellConstraints.xy(1, row));
        panel.add(diagramNameLabel, cellConstraints.xy(3, row));

        row += 2;
        panel.add(diagramIdentifierTitleLabel, cellConstraints.xy(1, row));
        panel.add(diagramIdentifierLabel, cellConstraints.xy(3, row));

        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
    }

    /** {@inheritDoc} */
    public void update() {
        LOG.debug("DefaultUpdatableWorkspaceComponent update.");

        final String selectedNotationIdentifier = ModelerSession.getProjectService().getSelectedDiagram().getNotationIdentifier();
        final String selectedDisplayName = ModelerSession.getProjectService().getSelectedDiagram().getDisplayName();
        final UUID selectedDiagramUUID = ModelerSession.getProjectService().getSelectedDiagram().getUuid();

        if(!notationIdentifier.equals(selectedNotationIdentifier)){
            LOG.error("Selected diagram notation doesn't match with showing workspace component.");
            return;
        }

        notationIdentifierLabel.setText(selectedNotationIdentifier);
        diagramNameLabel.setText(selectedDisplayName);
        diagramIdentifierLabel.setText(selectedDiagramUUID.toString());
    }

    /** {@inheritDoc} */
    public void over() {
        LOG.debug("DefaultUpdatableWorkspaceComponent over.");
    }
}
