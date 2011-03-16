package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelDeleteAction;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellFactory;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.HierarchyRelationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.PortView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.logging.Logger;
import org.jgraph.graph.DefaultEdge;

public class ClassModelMarqueeHandler extends BasicMarqueeHandler {

    private static final Logger LOG = Logger.getLogger(ClassModelMarqueeHandler.class.getName());
    private final ClassModelGraph graph;
    private final ToolChooserModel selectedToolModel;
    private Map<String, ClassModelAbstractAction> actions;
    private PortView actualPort;
    private Point2D actualPoint;
    private PortView startingPort;
    private Point2D startingPoint;

    public ClassModelMarqueeHandler(ClassModelGraph graph,
            ToolChooserModel selectedToolModel,
            Map<String, ClassModelAbstractAction> actions) {
        this.graph = graph;
        this.selectedToolModel = selectedToolModel;
        this.actions = actions;

        this.actualPort = null;
        this.actualPoint = null;
        this.startingPort = null;
        this.startingPoint = null;
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            this.handlePopupMenu(e);
        } else if (this.addAction()) {
            this.graph.insertCell(e.getPoint());
        } else if (this.actualPort != null && this.graph.isPortsVisible()) {
            this.startingPort = this.actualPort;
            this.startingPoint = this.startingPort.getLocation();
        } else {
            super.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (this.startingPort != null && this.graph.isPortsVisible()) {
            LOG.fine("mouseDraged");
            this.printTempLine(Color.black, this.graph.getBackground());

            this.actualPort = this.graph.getPortViewAt(mouseEvent.getPoint().getX(), mouseEvent.getPoint().getY());
            this.actualPoint = this.actualPort != null ? this.actualPort.getLocation() : mouseEvent.getPoint();

            this.printTempLine(Color.black, this.graph.getBackground());
        } else {
            super.mouseDragged(mouseEvent);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (this.startingPort != null && this.graph.isPortsVisible()) {
            this.printTempLine(Color.black, this.graph.getBackground());

            if (this.actualPort != null && !this.actualPort.equals(this.startingPort)) {
                DefaultEdge edge = ClassModelCellFactory.createEdge(this.selectedToolModel.getSelectedTool());
                edge.setSource(this.startingPort.getCell());
                edge.setTarget(this.actualPort.getCell());

                this.graph.getGraphLayoutCache().insert(edge);
            }

            this.actualPort = null;
            this.actualPoint = null;
            this.startingPort = null;
            this.startingPoint = null;
            this.selectedToolModel.setSelectedTool(ToolChooserModel.Tool.TOOL_CONTROLL);
        } else {
            super.mouseReleased(mouseEvent);
        }
    }

    @Override
    public boolean isForceMarqueeEvent(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent) && !mouseEvent.isShiftDown()) {
            return true;
        }

        this.actualPort = this.graph.getPortViewAt(mouseEvent.getPoint().getX(), mouseEvent.getPoint().getY());
        return (this.actualPort != null && this.graph.isPortsVisible()) || super.isForceMarqueeEvent(mouseEvent);
    }

    public boolean addAction() {
        ToolChooserModel.Tool tool;
        try {
            tool = this.selectedToolModel.getSelectedTool();
        } catch (ClassCastException ex) {
            return false;
        }

        switch (tool) {
            case TOOL_ADD_CLASS:
                return true;
            default:
                return false;
        }
    }

    /**
     * This method prints temporary line when user want add an connection between vertices. Line is printed in XOR mode,
     * so if newColor pixel is printed on the pixel with the same color (newColor color), the oldColor pixel is
     * printed (like XOR operation)
     *
     * @param newColor color which will be used if repainted pixel has another color
     * @param oldColor color which will be used if repainted pixel has equal color to the newColor
     */
    private void printTempLine(Color newColor, Color oldColor) {
        if (this.startingPoint != null && this.actualPoint != null) {
            Graphics2D g = (Graphics2D) this.graph.getGraphics();
            g.setColor(newColor);
            g.setXORMode(oldColor);

            g.drawLine(
                    (int) this.startingPoint.getX(),
                    (int) this.startingPoint.getY(),
                    (int) this.actualPoint.getX(),
                    (int) this.actualPoint.getY());
        }
    }

    private void handlePopupMenu(final MouseEvent e) {
        Object c = this.graph.getFirstCellForLocation(e.getX(), e.getY());
        this.graph.selectCell(c);

        if (this.graph.getSelectionCell() == null) {
            return;
        }

        if (c instanceof ClassModelClassCell) {
            showPopupMenu(e,
                    this.actions.get(ClassModelEditAction.ACTION_NAME),
                    this.actions.get(ClassModelDeleteAction.ACTION_NAME));
        } else if (c instanceof ClassModelRelation) {
            Object userObject = ((ClassModelRelation) c).getUserObject();
            if (userObject instanceof RelationModel) {
                showPopupMenu(e,
                    this.actions.get(ClassModelEditAction.ACTION_NAME),
                    this.actions.get(ClassModelDeleteAction.ACTION_NAME));
            } else if (userObject instanceof HierarchyRelationModel) {
                showPopupMenu(e,
                        this.actions.get(ClassModelDeleteAction.ACTION_NAME));
            }
        }
    }

    private void showPopupMenu(final MouseEvent e, ClassModelAbstractAction... actions) {
        JPopupMenu popup = new JPopupMenu();

        for (ClassModelAbstractAction act : actions) {
            popup.add(act);
        }

        popup.show(this.graph, e.getX(), e.getY());
    }
}
