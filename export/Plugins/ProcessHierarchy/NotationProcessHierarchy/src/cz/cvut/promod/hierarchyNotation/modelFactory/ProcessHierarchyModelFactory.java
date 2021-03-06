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

package cz.cvut.promod.hierarchyNotation.modelFactory;

import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.factory.DiagramModelFactory;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.model.DiagramModel;
import cz.cvut.promod.hierarchyNotation.modelFactory.diagramModel.ProcessHierarchyDiagramModel;
import cz.cvut.promod.hierarchyNotation.modelFactory.graphModel.ProcessHierarchyGraphModel;
import cz.cvut.promod.hierarchyNotation.modelFactory.viewFactory.PHCellViewFactory;
import org.jgraph.graph.GraphLayoutCache;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:52:38, 26.11.2009
 *
 * Implementation of DiagramModelFactory for the ProcessHierarchyNotation plugin.
 *
 * @see cz.cvut.promod.plugin.notationSpecificPlugIn.notation.factory.DiagramModelFactory
 */
public class ProcessHierarchyModelFactory implements DiagramModelFactory{

    /** {@inheritDoc}
     *
     * Creates an empty model for ProcessHierarchy notation diagram.
     */
    public DiagramModel createEmptyDiagramModel() {
        return new ProcessHierarchyDiagramModel(
                new GraphLayoutCache(new ProcessHierarchyGraphModel(), new PHCellViewFactory())
        );
    }
    
}
