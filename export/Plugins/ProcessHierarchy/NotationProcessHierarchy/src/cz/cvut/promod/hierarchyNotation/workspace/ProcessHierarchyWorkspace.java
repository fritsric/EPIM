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

package cz.cvut.promod.hierarchyNotation.workspace;

import org.jgraph.JGraph;
import org.apache.log4j.Logger;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.workspace.UpdatableWorkspaceComponent;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.model.DiagramModel;
import cz.cvut.promod.hierarchyNotation.modelFactory.diagramModel.ProcessHierarchyDiagramModel;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectDiagram;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectRoot;

import javax.swing.*;

import com.jidesoft.status.LabelStatusBarItem;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 21:13:22, 30.11.2009
 *
 * Represents the top layer of ProcessHierarchyWorkspaceData plugin.
 */
public class ProcessHierarchyWorkspace extends JScrollPane implements UpdatableWorkspaceComponent {

    private final Logger LOG = Logger.getLogger(ProcessHierarchyWorkspace.class);

    private final JGraph graph;

    final LabelStatusBarItem statusBarItem;

    
    public ProcessHierarchyWorkspace(final JGraph graph, final LabelStatusBarItem statusBarItem) {
        super(graph);

        this.graph = graph;
        this.statusBarItem = statusBarItem;
    }

    public void update() {
        try{
            final ProjectRoot projectRoot = ModelerSession.getProjectService().getSelectedProject();
            final ProjectDiagram selectedDiagram = ModelerSession.getProjectService().getSelectedDiagram();
            final DiagramModel diagramModel = selectedDiagram.getDiagramModel();
            final ProcessHierarchyDiagramModel processHierarchyDiagramModel =
                    (ProcessHierarchyDiagramModel) diagramModel;

            statusBarItem.setText(""); // not necessary

            ModelerSession.setFrameTitleText(projectRoot.getDisplayName() + " - " + selectedDiagram.getDisplayName());

            graph.setGraphLayoutCache(processHierarchyDiagramModel.getGraphLayoutCache());

            // forces all ports are repainted, even when the graph has just been loaded
            graph.getGraphLayoutCache().update();

        } catch (ClassCastException exception){
            LOG.error("Invalid diagram model for process hierarchy workspace.", exception);
        } catch (Exception exception){
            LOG.error("An error has occurred during diagram switching.", exception);
        }
    }

    public void over() {
        statusBarItem.setText("");
        graph.getSelectionModel().clearSelection();
    }

}