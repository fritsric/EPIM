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

package cz.cvut.promod.epc.workspace;

import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.NotationWorkspaceData;
import cz.cvut.promod.epc.frames.toolChooser.ToolChooserModel;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;

import javax.swing.*;

import com.jgoodies.binding.value.ValueModel;
import org.jgraph.JGraph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 19:59:34, 5.12.2009
 *
 * EPCWorkspaceData represents the NotationWorkspaceData interface implementation for the EPCNotation plugin.  
 */
public class EPCWorkspaceData implements NotationWorkspaceData {

    private static JGraph graph;
    private static EPCWorkspace workspace;

    private final ValueModel selectedToolModel;
    private final ValueModel gridModel;
    private final ValueModel lockModel;
    private final ValueModel viewGridModel;
    private final ValueModel cellSizeModel;
    private final ValueModel scaleModel;
    private final ValueModel movableBelowZeroModel;

    public static final String PROPERTY_SELECTED_CELL = "selectedCell";

    public EPCWorkspaceData(final ValueModel selectedToolModel,
                            final ValueModel gridModel,
                            final ValueModel lockModel,
                            final ValueModel viewGridModel,
                            final ValueModel cellSizeModel,
                            final ValueModel scaleModel,
                            final ValueModel movableBelowZeroModel,
                            final Map<String, ProModAction> actions,
                            final JPopupMenu popupMenu) {

        this.selectedToolModel = selectedToolModel;

        graph = new EPCGraph(selectedToolModel, popupMenu, actions);
        workspace = new EPCWorkspace(graph, actions);

        this.gridModel = gridModel;
        this.lockModel = lockModel;
        this.viewGridModel = viewGridModel;
        this.cellSizeModel = cellSizeModel;
        this.scaleModel = scaleModel;
        this.movableBelowZeroModel = movableBelowZeroModel;

        initEventHandling();
    }

    private void initEventHandling() {
        lockModel.addValueChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                graph.setEditable((Boolean) propertyChangeEvent.getNewValue());
            }
        });

        gridModel.addValueChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                graph.setGridEnabled((Boolean) propertyChangeEvent.getNewValue());
            }
        });

        viewGridModel.addValueChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                graph.setGridVisible((Boolean) propertyChangeEvent.getNewValue());
            }
        });

        cellSizeModel.addValueChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                final Integer gridCellSize = (Integer) propertyChangeEvent.getNewValue();

                graph.setGridSize(gridCellSize.doubleValue());
                graph.refresh();
            }
        });        

        selectedToolModel.addValueChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                final ToolChooserModel.Tool selectedTool = (ToolChooserModel.Tool) propertyChangeEvent.getNewValue();

                boolean portsVisible = false;

                switch(selectedTool){
                    case ADD_CONTROL_FLOW_LINE:
                    case ADD_INFORMATION_SERVICE_FLOW_LINE:
                    case ADD_ORGANIZATION_FLOW_LINE:
                    case ADD_MATERIAL_OUTPUT_FLOW_LINE:
                    case ADD_INFORMATION_FLOW_LINE:
                        portsVisible = true;
                }

                graph.setPortsVisible(portsVisible);
            }
        });

        scaleModel.addValueChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                final double scaleMultiplicator = (((Integer) evt.getNewValue()).doubleValue()) / 100.0;
                
                graph.setScale(scaleMultiplicator);
            }
        });

        movableBelowZeroModel.addValueChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                graph.setMoveBelowZero((Boolean) evt.getNewValue());
            }
        });
    }

    public JComponent getWorkspaceComponentSingleton() {
        return workspace;
    }

    public JGraph getGraph() {
        return graph;
    }
    
}
