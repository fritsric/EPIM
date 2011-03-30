package cz.cvut.indepmod.classmodel.workspace.cell;

import cz.cvut.indepmod.classmodel.api.model.ElementType;
import cz.cvut.indepmod.classmodel.workspace.cell.components.AbstractElementComponent;
import cz.cvut.indepmod.classmodel.workspace.cell.components.ClassComponent;
import cz.cvut.indepmod.classmodel.workspace.cell.components.EnumerationComponent;
import cz.cvut.indepmod.classmodel.workspace.cell.components.InterfaceComponent;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import java.util.logging.Level;
import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.VertexRenderer;

import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 25.9.2010
 * Time: 10:09:07
 * <p/>
 * This class renders (gets component which will be rendered) a vertex. Type of the vertex is specified by the userObject
 * of the vertex.
 */
public class ClassModelVertexRenderer /*implements CellViewRenderer, Serializable*/ extends VertexRenderer {

    private static final Logger LOG = Logger.getLogger(ClassModelVertexRenderer.class.getName());


    @Override
    public Component getRendererComponent(JGraph jGraph, CellView cellView, boolean b, boolean b1, boolean b2) {
        DefaultGraphCell cell = (DefaultGraphCell) cellView.getCell();
        Object userObject = cell.getUserObject();

        if (userObject instanceof AbstractElementModel) {
            AbstractElementModel model = (AbstractElementModel) userObject;
            ElementType type = model.getElementType();
            switch (type) {
                case CLASS:
                    return new ClassComponent(model);
                case INTERFACE:
                    return new InterfaceComponent(model);
                case ENUMERATION:
                    return new EnumerationComponent(model);
                default:
                    LOG.log(Level.SEVERE, "unknown cell type! AbstractElementModel is of type {0}", model.getElementType());
                    return null;
            }
        } else {
            LOG.log(Level.SEVERE, "unknown cell type! User Object of the cell is of {0} type", userObject.getClass().getName());
            return null;
        }
    }
}
