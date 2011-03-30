package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.actions.DeleteAction;
import cz.cvut.indepmod.classmodel.actions.EditAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.ToolChooserModelListener;
import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellFactory;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphLayoutCache;

public class ClassModelGraph extends JGraph {

    private static final Logger LOG = Logger.getLogger(ClassModelGraph.class.getName());
    private Map<Class<? extends ClassModelAbstractAction>, ClassModelAbstractAction> actions;
    private ToolChooserModel selectedTool;

    public ClassModelGraph(
            Map<Class<? extends ClassModelAbstractAction>, ClassModelAbstractAction> actions,
            ToolChooserModel selectedTool,
            GraphLayoutCache cache) {
        super(cache);

        this.actions = actions;
        this.selectedTool = selectedTool;

        this.initActions();
        this.initEventHandling();
        this.setDoubleBuffered(true);
        this.setAntiAliased(true);
    }

    /**
     * Returns collection of all classes that are in the Graph
     * @return Colection of all classes
     */
    public Collection<IElement> getAllClasses() {
        Collection<IElement> res = new LinkedList<IElement>();
        CellView[] cw = this.getGraphLayoutCache().getCellViews();
        for (int i = 0; i < cw.length; i++) {
            DefaultGraphCell cell = (DefaultGraphCell) cw[i].getCell();
            Object userObject = cell.getUserObject();
            if (userObject instanceof AbstractElementModel) {
                res.add((AbstractElementModel) userObject);
            }
        }
        return res;
    }

    public void insertCell(Point p) {
        LOG.fine("adding new cell");
        ToolChooserModel.Tool tool = this.selectedTool.getSelectedTool();
        DefaultGraphCell cell = ClassModelCellFactory.createCell(p, tool);

        this.getGraphLayoutCache().insert(cell);
        this.selectedTool.setSelectedTool(ToolChooserModel.Tool.TOOL_CONTROLL);
    }

    public void selectCell(Object cell) {
        if (cell == null) {

        }

        boolean isAlreadySelected = false;
        Object[] selectionCells = this.getSelectionCells();
        if (cell != null) {
            for (int i = 0; i < selectionCells.length; i++) {
                Object c = selectionCells[i];
                if (cell.equals(c)) {
                    isAlreadySelected = true;
                    System.arraycopy(selectionCells, i+1, selectionCells, i, selectionCells.length - i - 1);
                    selectionCells[selectionCells.length - 1] = cell;
                    this.setSelectionCells(selectionCells);
                    break;
                }
            }
        }

        if (!isAlreadySelected) {
            for (Object selection : this.getSelectionCells()) {
                this.removeSelectionCell(selection);
            }
            
            if (cell != null) {
                this.setSelectionCell(cell);
            }
        }
    }

    private void initActions() {
        this.actions.put(
                EditAction.class,
                new EditAction(this));

        this.actions.put(
                DeleteAction.class,
                new DeleteAction(this));
    }

    private void initEventHandling() {
        this.selectedTool.addListener(new ToolChooserModelListener() {

            @Override
            public void selectedToolChanged(ToolChooserModel.Tool newTool) {
                boolean showPorts = false;
                ToolChooserModel.Tool tool = newTool;

                switch (tool) {
                    case TOOL_ADD_RELATION:
                    case TOOL_ADD_GENERALIZATION:
                    case TOOL_ADD_REALISATION:
                    case TOOL_ADD_COMPOSITION:
                    case TOOL_ADD_AGREGATION:
                        showPorts = true;
                }

                setPortsVisible(showPorts);
                setJumpToDefaultPort(showPorts);
            }
        });

        this.addGraphSelectionListener(new GraphSelectionListener() {

            @Override
            public void valueChanged(GraphSelectionEvent graphSelectionEvent) {
                actions.get(EditAction.class).setEnabled(getSelectionCell() != null);
            }
        });
    }
}
