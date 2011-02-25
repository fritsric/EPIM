package cz.cvut.indepmod.classmodel.workspace.cell;

import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexRenderer;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView;
import org.jgraph.graph.CellViewFactory;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.VertexView;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 24.9.2010
 * Time: 23:33:59
 *
 * CellViewFactory that creates cell view objects.
 */
public class ClassModelCellViewFactory extends DefaultCellViewFactory {

    @Override
    protected VertexView createVertexView(Object o) {
        if (o instanceof ClassModelClassCell) {
            return new ClassModelVertexView(o);
        } else {
            return super.createVertexView(o);
        }
    }
}
