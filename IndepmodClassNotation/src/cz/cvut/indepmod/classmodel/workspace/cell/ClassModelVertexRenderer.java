package cz.cvut.indepmod.classmodel.workspace.cell;

import cz.cvut.indepmod.classmodel.workspace.cell.components.ClassComponent;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
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

        if (userObject instanceof ClassModel) {
            return new ClassComponent((ClassModel) userObject);
        } else {
            LOG.severe("unknown cell type! User Object of the cell is of " + userObject.getClass().getName() + " type");
            return null;
        }
    }
}
