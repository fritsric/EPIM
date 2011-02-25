package cz.cvut.indepmod.classmodel.workspace.cell;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.VertexView;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 25.9.2010
 * Time: 10:07:23
 * <p/>
 * Implementation of the VertexView for ClassModelNotation purposes
 */
public class ClassModelVertexView extends VertexView {

    private static final ClassModelVertexRenderer RENDERER = new ClassModelVertexRenderer();
    private static final GraphCellEditor EDITOR = new ClassModelVertexEditor();

    public ClassModelVertexView() {
    }

    public ClassModelVertexView(Object o) {
        super(o);
    }

    @Override
    public CellViewRenderer getRenderer() {
        return RENDERER;
    }

    @Override
    public GraphCellEditor getEditor() {
        return EDITOR;
    }
}
