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

package cz.cvut.promod.gui.projectNavigation;

import com.jidesoft.docking.DockableFrame;
import com.jidesoft.docking.DockContext;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.factories.Borders;

import javax.swing.*;
import java.awt.*;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;
import cz.cvut.promod.resources.Resources;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 15:53:36, 11.10.2009
 */

/**
 * * Project navigation view.
 */
public class ProjectNavigationView extends DockableFrame{

    private static final Dimension DEFAULT_SIZE = new Dimension(300, 400);

    protected final JTree projectTree = ModelerSession.getComponentFactoryService().createTree();

    private final JScrollPane scrollPane = ModelerSession.getComponentFactoryService().createScrollPane(projectTree);

    protected final JButton expandButton = ModelerSession.getComponentFactoryService().createButton(null,
            Resources.getIcon(Resources.NAVIGATION + Resources.ICONS + Resources.EXPAND_ALL_ICON));

    protected final JButton collapseButton = ModelerSession.getComponentFactoryService().createButton(null,
            Resources.getIcon(Resources.NAVIGATION + Resources.ICONS + Resources.COLLAPSE_ICON));

    protected final JButton addDiagramButton = ModelerSession.getComponentFactoryService().createButton(null, null);

    protected final JButton addSubfolderButton = ModelerSession.getComponentFactoryService().createButton(null, null);

    /**
     * Constructs a new dockable frame representing the project navigation tree.
     * 
     * @param dockableFrameIndex is the index of the dockable frame
     */
    public ProjectNavigationView(final int dockableFrameIndex){
        super("project navigation");

        initializeTree();

        initializeDockableFrame(dockableFrameIndex);
    }

    /**
     * Initialize the tree.
     */
    private void initializeTree() {
        projectTree.setRootVisible(false);
    }

    /**
     * Initialize the navigation dockable frame.
     */
    private void initializeDockableFrame(final int dockableFrameIndex) {
        setPreferredSize(DEFAULT_SIZE);

        getContext().setInitMode(DockContext.STATE_FRAMEDOCKED);
        getContext().setInitSide(DockContext.DOCK_SIDE_WEST);
        getContext().setInitIndex(dockableFrameIndex);
        getContext().setFloatable(false);

        setSideDockAllowed(false);
        setAutohidable(false);
        setHidable(false);
        setRearrangable(false);

        add(createProjectNavigationPane());
    }

    /**
     * Creates the dockable frame representing the project navigation.
     * @return the project navigation dockable frame
     */
    private JPanel createProjectNavigationPane() {
        final JPanel projectNavigationPane = ModelerSession.getComponentFactoryService().createPanel();

        projectNavigationPane.setLayout(new BorderLayout());
        projectNavigationPane.add(createNorthControlPane(), BorderLayout.NORTH);
        projectNavigationPane.add(scrollPane, BorderLayout.CENTER);

        return projectNavigationPane; 
    }

    /**
     * @return the control panel placed on the top of project navigation
     */
    private JPanel createNorthControlPane() {
        final JPanel northControlPane = ModelerSession.getComponentFactoryService().createPanel();

        northControlPane.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        northControlPane.setLayout(new FormLayout(
                "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref",
                "pref"));
        final CellConstraints cellConstraints = new CellConstraints();

        northControlPane.add(addDiagramButton, cellConstraints.xy(1,1));
        northControlPane.add(addSubfolderButton, cellConstraints.xy(3,1));
        northControlPane.add(expandButton, cellConstraints.xy(5,1));
        northControlPane.add(collapseButton, cellConstraints.xy(7,1));

        return northControlPane;
    }

}
