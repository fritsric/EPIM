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

package cz.cvut.promod.hierarchyNotation.modelFactory.diagramModel;

import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.model.DiagramModel;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.model.DiagramModelChangeListener;
import cz.cvut.promod.hierarchyNotation.modelFactory.graphModel.ProcessHierarchyGraphModel;
import cz.cvut.promod.hierarchyNotation.modelFactory.vertexes.ProcessVertex;
import cz.cvut.promod.services.projectService.utils.ProjectServiceUtils;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectDiagram;
import cz.cvut.promod.services.ModelerSession;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.DefaultGraphCell;
import org.apache.log4j.Logger;

import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:34:44, 30.11.2009
 *
 * An implementation a DiagramModel interface for the ProcessHierarchyNotation plugin. 
 */
public class ProcessHierarchyDiagramModel implements DiagramModel{

    private static final Logger LOG = Logger.getLogger(ProcessHierarchyDiagramModel.class);

    private transient boolean loaded = true;

    private final GraphLayoutCache graphLayoutCache;

    
    public ProcessHierarchyDiagramModel(final GraphLayoutCache graphLayoutCache){
        this.graphLayoutCache = graphLayoutCache; 
    }

    public GraphLayoutCache getGraphLayoutCache() {
        return graphLayoutCache;
    }

    public void addChangeListener(DiagramModelChangeListener listener) {
        LOG.debug("Process Hierarchy notation does not implement observable diagram change support.");
    }

    /** {@inheritDoc}
     *
     * Initialize all listeners and update data after load.
     */
    @SuppressWarnings({"ConstantConditions"})
    public void update() {
        if(loaded){
            final ProcessHierarchyGraphModel model = (ProcessHierarchyGraphModel) getGraphLayoutCache().getModel();

            for(int i = 0; i < model.getRootCount(); i++){
                if(model.getRootAt(i) instanceof DefaultGraphCell){
                    try{
                        final DefaultGraphCell cell = (DefaultGraphCell) model.getRootAt(i);

                        if(cell.getUserObject() instanceof ProcessVertex){
                            final ProcessVertex processVertex = (ProcessVertex) cell.getUserObject();

                            if(processVertex.getUuid() != null){
                                final TreePath treePath = ProjectServiceUtils.findProjectDiagram(
                                        ModelerSession.getProjectService().getSelectedProjectPath(),
                                        processVertex.getUuid()
                                );

                                if(treePath != null){
                                    if(treePath.getLastPathComponent() instanceof DefaultMutableTreeNode){
                                        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();

                                        if(node.getUserObject() instanceof ProjectDiagram){
                                            final ProjectDiagram projectDiagram = (ProjectDiagram) node.getUserObject();

                                            if(processVertex.getName() != null){
                                            if(!(processVertex.getName().equals(projectDiagram.getDisplayName()))){
                                                    LOG.warn("The process: " + processVertex.getName() + " refers to -> " + projectDiagram.getDisplayName() );
                                                }
                                            } else {
                                                processVertex.setName("");
                                                LOG.warn("Nullary process name");
                                            }

                                            ModelerSession.getProjectService().registerDiagramListener(projectDiagram, processVertex);
                                        }

                                    }
                                } else {
                                    LOG.info("Referenced diagram has not been found: " + processVertex.getName()  + ", uuid: " + processVertex.getUuid());
                                    processVertex.setUuid(null);
                                    processVertex.setName("");
                                }
                            }
                        }

                    } catch (Exception e){
                        LOG.error("Invalid project element during initialization");
                    }

                }
            }

            loaded = false;
        }
    }

    /** {@inheritDoc} */
    public void over() {}
}
